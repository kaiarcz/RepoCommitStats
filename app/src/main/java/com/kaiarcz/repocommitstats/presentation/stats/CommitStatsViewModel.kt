package com.kaiarcz.repocommitstats.presentation.stats

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiarcz.repocommitstats.app.util.Resource
import com.kaiarcz.repocommitstats.domain.model.CommitStats
import com.kaiarcz.repocommitstats.domain.usecase.GetCommitStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CommitStatsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCommitStatsUseCase: GetCommitStatsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CommitStatsState())
    val state: State<CommitStatsState> = _state

    init {
        savedStateHandle.get<String>("repo")?.let { repo ->
            getCommitStats(repo)
        }
    }

    private fun getCommitStats(repo: String) {
        getCommitStatsUseCase(repo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    createMonthlyCommitCount(result.data)
                }
                is Resource.Error -> {
                    _state.value = CommitStatsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CommitStatsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun createMonthlyCommitCount(commits: List<CommitStats>?) {
        val monthByYear = commits?.sortedBy { it.date }?.map {
            "${it.date.month.value}/${it.date.year.toString().substring(2, 4)}"
        }

        val commitStats = monthByYear?.groupingBy { it }?.eachCount()

        val months = commitStats?.keys?.toList()
        val commitCount = commitStats?.values?.toList()

        _state.value = CommitStatsState(
            months = months.orEmpty(),
            commitCount = commitCount.orEmpty()
        )
    }
}