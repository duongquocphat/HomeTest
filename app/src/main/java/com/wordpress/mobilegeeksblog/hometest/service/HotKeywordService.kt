package com.wordpress.mobilegeeksblog.hometest.service

import io.reactivex.Observable
import retrofit2.http.GET

interface HotKeywordService {
    @GET("keywords.json")
    fun getHotKeywordList(): Observable<List<String>>
}