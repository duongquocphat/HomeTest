package com.wordpress.mobilegeeksblog.hometest.data.repository

import com.wordpress.mobilegeeksblog.hometest.service.HotKeywordService
import io.reactivex.Observable

class HotKeywordRepository(private val hotKeywordService: HotKeywordService) {
    fun searchRepositories(): Observable<List<String>> = hotKeywordService.getHotKeywordList()
}