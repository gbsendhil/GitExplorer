package com.intuit.sendhil.gitexplorer.repo

import com.intuit.sendhil.gitexplorer.model.Repos
import retrofit2.Call


interface Repository {

	fun getRepos(): Call<List<Repos>>
}