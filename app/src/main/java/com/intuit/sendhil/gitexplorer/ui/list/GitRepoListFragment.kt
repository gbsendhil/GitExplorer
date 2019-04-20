package com.intuit.sendhil.gitexplorer.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.intuit.sendhil.gitexplorer.R
import com.intuit.sendhil.gitexplorer.model.Repos
import com.intuit.sendhil.gitexplorer.viewmodel.list.GitRepoListFeatureViewModel
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject


class GitRepoListFragment : DaggerFragment() {

    @Inject
    lateinit var mViewModel:GitRepoListFeatureViewModel

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.repoLiveData.observe(this, Observer<List<Repos>> { repos -> updateRepos(repos)})
    }

    private fun updateRepos(repos: List<Repos>){
        Timber.d("Received ${repos.size} list of repos from repository  ")
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getRepos()
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