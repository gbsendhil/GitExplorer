package com.intuit.sendhil.gitexplorer.repo

import com.intuit.sendhil.gitexplorer.api.GitHubService
import com.intuit.sendhil.gitexplorer.model.Repos
import retrofit2.Call
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val gitHubService: GitHubService): Repository {

	override fun getRepos(): Call<List<Repos>> {
		return gitHubService.getRepos()
	}
}