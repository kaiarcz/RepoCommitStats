package com.kaiarcz.repocommitstats.presentation.stats

data class CommitStatsState(
    val isLoading: Boolean = false,
    val months: List<String> = emptyList(),
    val commitCount: List<Int> = emptyList(),
    val error: String = ""
)

