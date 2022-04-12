package com.andryyu.common.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.andryyu.utils.utils.Toasts

object RouterManager {
    const val TAG = "RouterManager"

    //存储路由的容器
    private val mRouterMap = HashMap<String,Class<*>>()

    /**
     * 添加路由
     * @param path 路由路径
     * @param clazz 路由目标
     */
    fun addRouter(path:String, clazz:Class<*>){
        mRouterMap[path] = clazz
    }

    /**
     * ARouter实现
     *
     * 发起跳转
     */
    //ARouter.getInstance().build("/user/UserMainActivity").navigation()
    /**
     * 跳转Activity
     * @param context 上下文
     * @param path 路由路径
     */
    fun startActivity(context: Context, path:String, bundle: Bundle?=null){
        val clazz = mRouterMap[path]
        if (clazz == null){
            val log  = "not found router by path !"
            Log.e(TAG, log)
            Toasts.showToast(context , log)
            return
        }

        //判断是否是Activity的子类
        if (Activity::class.java.isAssignableFrom(clazz)){
            val intent = Intent(context, clazz)
            if (bundle != null){
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }else{
            val log = "router's not Activity !"
            Log.e(TAG, log)
            Toasts.showToast(context , log)
        }
    }

    /**
     * ARouter实现
     *
     * 获取fragment
     */
    //val f  = ARouter.getInstance().build("/user/UserFragment").navigation() as Fragment
    /**
     * 获取Fragment
     * @param context 上下文
     * @param path 路由路径
     */
    fun getFragment(context: Context, path:String, bundle: Bundle?=null):Fragment?{
        val clazz = mRouterMap[path]
        if (clazz == null){
            val log  = "not found router by path !"
            Log.e(TAG, log)
            Toasts.showToast(context , log)
            return null
        }

        //判断是否是Fragment的子类
        if (Fragment::class.java.isAssignableFrom(clazz)){
            val fragment = clazz.newInstance() as Fragment
            //添加参数
            if (bundle != null) {
                fragment.arguments = bundle
            }
            return fragment
        }else{
            val log = "router's not Fragment !"
            Log.e(TAG, log)
            Toasts.showToast(context , log)
        }
        return null
    }

    // 获取Fragment ，并使用
    /*RouterManager.getFragment(this, "user/UserFragment")?.apply {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fl_fragment, this)
        beginTransaction.commit()
    }*/

    /**
     * 功能性路由标记接口
     * */
    interface IService
    /**
     * User模块对外提供的功能接口
     * */
    interface IUserService : IService {

        /**
         * 是否登录
         */
        fun isLogin(): Boolean
    }

    /**
     * ARouter实现
     *
     * 获取Service
     */
    //ARouter.getInstance().build("/user/UserService").navigation() as IUserService2

    /**
     * 获取用户模块提供的服务
     * */
    fun getUserService(context: Context): IUserService? {
        val clazz = mRouterMap["user/UserService"]
        if (clazz == null) {
            val log = "not found service router by path !"
            Log.e(TAG, log)
            Toasts.showToast(context, log)
            return null
        }
        // 判断是否是Service & IUserService
        if (IService::class.java.isAssignableFrom(clazz)
                && IUserService::class.java.isAssignableFrom(clazz)
        ) {
            return clazz.newInstance() as IUserService
        } else {
            val log = "router's not IUserService !"
            Log.e(TAG, log)
            Toasts.showToast(context, log)
        }
        return null
    }

    /**
     * user模块实现对外暴露的功能
     * */
//    class UserServiceImpl : IUserService {
//
//        override fun isLogin(): Boolean {
//            return UserUtils.isLogin()
//        }
//    }

    // 注册功能到路由中
//    RouterManager.addRouter("user/UserService", UserServiceImpl::class.java)
    //其他模块调用user模块
//    RouterManager.getUserService(this)?.apply {
//        Toast.makeText(
//                this@AppMainActivity,
//                "登录状态：${this.isLogin()}",
//                Toast.LENGTH_SHORT
//        ).show()
//    }
}