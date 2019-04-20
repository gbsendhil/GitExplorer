package com.intuit.sendhil.gitexplorer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.intuit.sendhil.gitexplorer.R
import com.intuit.sendhil.gitexplorer.ui.list.GitRepoListFragment

class GitExplorerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val newCurrentFragment = supportFragmentManager.findFragmentByTag("tag")
            ?: GitRepoListFragment.newInstance("tag")
        transaction.replace(R.id.fragment_content, newCurrentFragment, "tag")
        transaction.commit()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible) {
                val childFrag = frag as GitRepoListFragment
                childFrag.showMainFragmentContent()
                val childFm = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}
