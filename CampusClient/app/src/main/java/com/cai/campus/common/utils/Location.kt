package com.cai.campus.common.utils

import android.util.Log
import com.amap.api.location.AMapLocation

import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.cai.campus.app.BaseApplication
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData

/**
 * 高德地图定位工具类
 *
 * 使用 location 前需要先 create 初始化
 * 使用结束后 destroy 释放资源
 */
class Location private constructor() {

    private val locationTag = "Location"

    private var locationClient: AMapLocationClient? = null
    private var locationOption: AMapLocationClientOption? = null
    private var locationResult: AMapLocation? = null

    private val localStorage = LocalRepoManager.load(AppData::class.java)

//    /**
//     * 创建定位客户端
//     * 初始化本地定位服务
//     */
//    fun create() {
//        locationClient = AMapLocationClient(BaseApplication.context)
//        locationOption = AMapLocationClientOption()
//    }

    /**
     * 定位服务
     *
     * 定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表:
     * https://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode
     */

    fun location() {
        locationClient = AMapLocationClient(BaseApplication.context)
        locationOption = AMapLocationClientOption()
        locationClient!!.setLocationListener {
            if (it.errorCode == 0) {
                locationResult = it

                localStorage.location.latitude = it.latitude
                localStorage.location.longitude = it.longitude
                localStorage.apply()
                Log.d("APPLocation",localStorage.location.toString())

            } else {
                Log.e(locationTag, "ErrorCode:${it.errorCode} , ErrorInfo: + ${it.errorInfo}")
            }
        }

        //设置定位场景，目前支持三种场景 - 签到、出行、运动 （默认无场景）
        locationOption!!.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.Sport
        locationOption!!.interval = 10000
        locationClient!!.setLocationOption(locationOption)
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        locationClient!!.stopLocation()
        locationClient!!.startLocation()
        //设置定位低功耗模式
        locationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving
        //给定位客户端对象设置定位参数
        locationClient!!.setLocationOption(locationOption)
        //启动定位
        locationClient!!.startLocation()
    }

    /**
     * 销毁定位客户端，同时销毁本地定位服务
     */
    fun destroy() {
        locationClient!!.stopLocation()
        locationClient!!.onDestroy()
    }

    /**
     * 设置为单例模式
     */
    companion object {
        private var instance: Location? = null
            get() {
                if (field == null) {
                    field = Location()
                }
                return field
            }

        fun load(): Location {
            return instance!!
        }
    }

}