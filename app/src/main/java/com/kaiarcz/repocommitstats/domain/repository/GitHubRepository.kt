package com.kaiarcz.repocommitstats.domain.repository

import com.kaiarcz.repocommitstats.data.model.CommitStatsDto
import com.kaiarcz.repocommitstats.data.model.RepoDto

interface GitHubRepository {

    suspend fun getRepos(): List<RepoDto>

    suspend fun getCommitStatus(repo: String): List<CommitStatsDto>
}