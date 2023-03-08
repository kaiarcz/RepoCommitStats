package com.kaiarcz.repocommitstats.data.repository

import com.kaiarcz.repocommitstats.data.model.CommitStatsDto
import com.kaiarcz.repocommitstats.data.model.RepoDto
import com.kaiarcz.repocommitstats.data.remote.GithubApi
import com.kaiarcz.repocommitstats.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GitHubRepository {

    override suspend fun getRepos(): List<RepoDto> {
        return api.getRepos()
    }

    override suspend fun getCommitStatus(repo: String): List<CommitStatsDto> {
        return api.getCommitStats(repo)
    }
}