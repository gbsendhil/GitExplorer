package com.intuit.sendhil.gitexplorer.ui.list

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intuit.sendhil.gitexplorer.R
import com.intuit.sendhil.gitexplorer.model.Repos
import com.intuit.sendhil.gitexplorer.ui.inflate
import kotlinx.android.synthetic.main.cell_layout.view.*

internal class GitRepoListAdapter(val listener:Listener) : RecyclerView.Adapter<GitRepoListAdapter.RepoViewHolder>()  {

    internal interface Listener {
        fun onItemClick(repo: Repos)
    }
    private var repos =  listOf<Repos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflatedView = parent.inflate(R.layout.cell_layout, false)
        return RepoViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.repoName.text= repos[position].name
        holder.repoDescription.text= repos[position].description
        holder.repoStars.text= repos[position].stargazers_count.toString()
        holder.itemView.setOnClickListener { listener.onItemClick(repos[position]) }
    }
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = repos[position].hashCode().toLong()

    fun setRepos(repos:List<Repos>){
        this.repos = repos
        notifyDataSetChanged()
    }

    class RepoViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        val repoName: TextView = v.repo_name
        val repoDescription: TextView = v.repo_desc
        val repoStars :TextView = v.repo_stars

        init {
            v.setOnClickListener(this)
        }
        override fun onClick(v: View) {
        }
    }
}