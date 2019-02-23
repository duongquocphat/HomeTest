package com.wordpress.mobilegeeksblog.hometest

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wordpress.mobilegeeksblog.hometest.view.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @Rule @JvmField
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun getHotKeywordListCount_showData() {
        SystemClock.sleep(5000)
        onView(ViewMatchers.withId(R.id.rvHotKeywords))
            .check(
                matches(ViewMatchers.hasMinimumChildCount(0))
            )
    }
}
