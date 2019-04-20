package com.intuit.sendhil.gitexplorer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.intuit.sendhil.gitexplorer.ui.GitExplorerMainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class GitExplorerMainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<GitExplorerMainActivity>
            = ActivityTestRule(GitExplorerMainActivity::class.java)

    @Test
    fun verify_if_app_launches_successfully() {
        onView(withId(R.id.fragment_content))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.repo_items_recycler_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun verify_if_list_of_repository_are_displayed() {
        onView(withId(R.id.repo_items_recycler_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}