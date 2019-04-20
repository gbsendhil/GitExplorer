package com.intuit.sendhil.gitexplorer.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.intuit.sendhil.gitexplorer.R
import com.intuit.sendhil.gitexplorer.model.Repos
import com.intuit.sendhil.gitexplorer.viewmodel.list.GitRepoListFeatureViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import timber.log.Timber
import javax.inject.Inject


class GitRepoListFragment : DaggerFragment() , GitRepoListAdapter.Listener {

    @Inject
    lateinit var mViewModel:GitRepoListFeatureViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager

    private val adapter: GitRepoListAdapter by lazy {
        GitRepoListAdapter(this)
    }
    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.repoLiveData.observe(this, Observer<List<Repos>> { repos -> updateRepos(repos)})
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        repo_items_recycler_view.layoutManager = linearLayoutManager
        repo_items_recycler_view.adapter = adapter
        repo_items_recycler_view.itemAnimator = DefaultItemAnimator().apply { supportsChangeAnimations = true }
        ViewCompat.setNestedScrollingEnabled(repo_items_recycler_view,false)
    }
    private fun updateRepos(repos: List<Repos>){
        Timber.d("Received ${repos.size} list of repos from repository  ")
        adapter.setRepos(repos)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getRepos()
    }

    override fun onItemClick(repo: Repos) {
        repo_items_recycler_view.visibility = View.GONE
        child_fragment_container.visibility = View.VISIBLE
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