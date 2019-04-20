package com.intuit.sendhil.gitexplorer.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.intuit.sendhil.gitexplorer.R
import com.intuit.sendhil.gitexplorer.model.Repos
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.detail_fragment.*

class GitRepoDetailsFragment : DaggerFragment() {

    @LayoutRes
    private var layoutId: Int = -1

    private lateinit var repoInfo: Repos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repoInfo = it.getParcelable(EXTRA_INFO) as Repos
        }
        layoutId = R.layout.detail_fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        child_fragment_content.text = repoInfo.toString()
    }

    companion object{
        const val FRAGMENT_TAG = "ChildFragment"
        private const val EXTRA_INFO = "EXTRA_INFO"

        fun buildFragment(repo: Repos) : GitRepoDetailsFragment {
            return GitRepoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_INFO, repo as Repos)
                }
            }
        }
    }
}