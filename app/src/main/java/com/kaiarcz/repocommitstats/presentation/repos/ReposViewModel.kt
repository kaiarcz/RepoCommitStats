package com.kaiarcz.repocommitstats.presentation.repos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiarcz.repocommitstats.app.util.Resource
import com.kaiarcz.repocommitstats.domain.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RepoListState())
    val state: State<RepoListState> = _state

    private val _repoName = mutableStateOf("")
    val repoName: State<String> = _repoName


    init {
        getRepos()
    }

    private fun getRepos() {
        getReposUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RepoListState(repos = result.data.orEmpty())
                }
                is Resource.Error -> {
                    _state.value = RepoListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RepoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setRepoName(name: String) {
        _repoName.value = name
    }
}