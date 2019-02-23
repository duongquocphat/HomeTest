package com.wordpress.mobilegeeksblog.hometest;

import android.os.SystemClock;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.wordpress.mobilegeeksblog.hometest.view.main.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void getHotKeywordListCount_showData() throws Exception {
        SystemClock.sleep(5_000);
        onView(ViewMatchers.withId(R.id.rvHotKeywords))
                .check(
                        matches(ViewMatchers.hasMinimumChildCount(0))
                );
    }
}
