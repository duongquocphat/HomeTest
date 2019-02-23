package com.wordpress.mobilegeeksblog.hometest.view.main

import android.arch.lifecycle.MutableLiveData
import com.wordpress.mobilegeeksblog.hometest.common.BaseViewModel
import com.wordpress.mobilegeeksblog.hometest.data.repository.HotKeywordRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainViewModel : BaseViewModel() {
    var hotKeywordList: MutableLiveData<String> = MutableLiveData()
    private val hotKeywordRepository = HotKeywordRepository(MainActivity.hotKeywordService)

    fun getHotKeywordList() {

        hotKeywordRepository.searchRepositories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<String>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(hotKeywordNameList: List<String>) {
                    hotKeywordNameList
                        .forEach {
                            hotKeywordList.value = it
                        }
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}