package com.wordpress.mobilegeeksblog.hometest.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.wordpress.mobilegeeksblog.hometest.R
import com.wordpress.mobilegeeksblog.hometest.adapter.HotKeywordsAdapter
import com.wordpress.mobilegeeksblog.hometest.entity.HotKeyword
import com.wordpress.mobilegeeksblog.hometest.service.HotKeywordService
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val hotKeywordList = arrayListOf<HotKeyword>()
    private lateinit var hotKeywordsAdapter: HotKeywordsAdapter

    private lateinit var mainViewModel: MainViewModel

    companion object {
        var retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/tikivn/android-home-test/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
                )
                .build()

        val hotKeywordService : HotKeywordService by lazy {
            retrofit.create(HotKeywordService::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wordpress.mobilegeeksblog.hometest.R.layout.activity_main)

        initHotKeywordsAdapter()

        mainViewModel =
            ViewModelProviders
                .of(this)
                .get(MainViewModel::class.java)
        observeGetHotKeywordList()

        //  init a query
        mainViewModel.getHotKeywordList()
    }

    private fun observeGetHotKeywordList() {
        mainViewModel.hotKeywordList.observe(
            this@MainActivity,
            Observer {
                if (it?.isEmpty() == false) {
                    this@MainActivity.hotKeywordList.add(HotKeyword(it, Random.nextInt(0, 4)))
                    this@MainActivity.hotKeywordsAdapter.notifyDataSetChanged()
                }
            }
        )
    }

    private fun initHotKeywordsAdapter() {
        // white space between items
        val itemDecorator = DividerItemDecoration(this@MainActivity, LinearLayoutManager.HORIZONTAL)
        itemDecorator.setDrawable(
            ContextCompat.getDrawable(this@MainActivity, R.drawable.recyclerview_divider_item_decoration)!!
        )

        // initialize adapter
        hotKeywordsAdapter = HotKeywordsAdapter(this@MainActivity, this@MainActivity.hotKeywordList)
        rvHotKeywords.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(itemDecorator)
            adapter = hotKeywordsAdapter
        }
    }
}
