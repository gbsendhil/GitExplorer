package com.intuit.sendhil.gitexplorer.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intuit.sendhil.gitexplorer.R
import dagger.android.support.DaggerFragment


class GitRepoListFragment : DaggerFragment() {

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    companion object {
        private const val KEY_SAMPLE: String = "sample"

        @JvmStatic
        fun newInstance(url: String) =
            GitRepoListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_SAMPLE, url)
                }
            }
    }
}