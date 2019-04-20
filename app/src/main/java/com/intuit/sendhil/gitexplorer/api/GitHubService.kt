package com.intuit.sendhil.gitexplorer.api

import com.intuit.sendhil.gitexplorer.model.Repos
import retrofit2.Call
import retrofit2.http.GET


interface GitHubService {

	@GET("/users/intuit/repos")
	fun getRepos(): Call<List<Repos>>

}