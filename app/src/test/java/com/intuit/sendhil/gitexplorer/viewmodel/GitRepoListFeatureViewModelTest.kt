package com.intuit.sendhil.gitexplorer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.intuit.sendhil.gitexplorer.model.Repos
import com.intuit.sendhil.gitexplorer.repo.Repository
import com.intuit.sendhil.gitexplorer.viewmodel.list.GitRepoListFeatureViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GitRepoListFeatureViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var mockRepository: Repository

    @RelaxedMockK
    private lateinit var mockObserver: Observer<List<Repos>>

    private lateinit var viewModel: GitRepoListFeatureViewModel

    private var captureSlot = slot<List<Repos>>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { mockObserver.onChanged(capture(captureSlot)) } answers { nothing }

        viewModel = GitRepoListFeatureViewModel(mockRepository)
        viewModel.repoLiveData.observeForever(mockObserver)
    }

    @Test
    fun `when the repository returns data, should post to the live data`() {
        every { mockRepository.getRepos().execute().body() } returns listOf()
        Dispatchers.setMain(Dispatchers.Unconfined)
        runBlocking {
            viewModel.getRepos().join()
        }
        Dispatchers.resetMain()
        verify { mockObserver.onChanged(listOf()) }
    }
}