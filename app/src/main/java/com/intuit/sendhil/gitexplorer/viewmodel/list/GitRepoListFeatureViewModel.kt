package com.intuit.sendhil.gitexplorer.viewmodel.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intuit.sendhil.gitexplorer.model.Repos
import com.intuit.sendhil.gitexplorer.repo.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

class GitRepoListFeatureViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val mutableRepoLiveData: MutableLiveData<List<Repos>> = MutableLiveData()

    internal val repoLiveData: LiveData<List<Repos>> get() = mutableRepoLiveData

    val stateliveData: MutableLiveData<STATE> = MutableLiveData()

    private val hasData:Boolean
        get() = stateliveData.value == STATE.HAS_DATA

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    enum class STATE {
        LOADING,
        HAS_DATA,
        NO_DATA,
        SERVICE_DOWN
    }

    fun getRepos(): Job {
        return uiScope.launch {
            stateliveData.postValue(STATE.LOADING)
            val repoList =  withContext(Dispatchers.IO) {
                repository.getRepos().execute().body()?: emptyList()
            }
            mutableRepoLiveData.postValue(repoList)
        }
    }
}