package com.kaiarcz.repocommitstats.presentation.repos

import com.kaiarcz.repocommitstats.domain.model.Repo

data class RepoListState(
    val isLoading: Boolean = false,
    val repos: List<Repo> = emptyList(),
    val error: String = ""
)

