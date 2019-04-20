package com.intuit.sendhil.gitexplorer.viewmodel.list

import androidx.lifecycle.ViewModel
import com.intuit.sendhil.gitexplorer.repo.Repository
import javax.inject.Inject

class GitRepoListFeatureViewModel @Inject constructor(private val repository: Repository): ViewModel()