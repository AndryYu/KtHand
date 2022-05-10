package com.andryyu.network.encrpt

import org.apache.commons.codec.binary.Base64
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.Key
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


object EncryptManager {

    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class, InvalidKeyException::class)
    fun calcAuthorization(source: String, secretId: String, secretKey: String, datetime: String): String {
        val signStr = "x-date: $datetime\nx-source: $source"
        val mac: Mac = Mac.getInstance("HmacSHA1")
        val sKey: Key = SecretKeySpec(secretKey.toByteArray(charset("UTF-8")), mac.algorithm)
        mac.init(sKey)
        val hash: ByteArray = mac.doFinal(signStr.toByteArray(charset("UTF-8")))
        val sig: String = Base64.encodeBase64String(hash)
        return "hmac id=\"$secretId\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"$sig\""
    }
}