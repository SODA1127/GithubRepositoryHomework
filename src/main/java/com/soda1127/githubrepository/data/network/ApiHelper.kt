package com.soda1127.githubrepository.data.network

import io.reactivex.Flowable

/**
 * Created by gwaghuijong on 2018. 5. 16..
 */
object ApiHelper {
    // string url 로 api 주소 생성
    fun getUrl(url: String?): Flowable<String> = NetworkHelper.apiSecureService.getUrl(url)

    fun getUser(userName: String) =
        NetworkHelper.apiSecureService.getResourcesTarget("users", userName)

    fun getRepositories(userName: String) =
        NetworkHelper.apiSecureService.getResourcesTargetAction("users", userName,"repos")
}