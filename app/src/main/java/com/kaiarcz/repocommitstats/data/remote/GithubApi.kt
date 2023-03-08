package com.kaiarcz.repocommitstats.data.remote

import com.kaiarcz.repocommitstats.data.model.CommitStatsDto
import com.kaiarcz.repocommitstats.data.model.RepoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/skydoves/repos")
    suspend fun getRepos(): List<RepoDto>

    @GET("/repos/skydoves/{repo}/commits")
    suspend fun getCommitStats(
        @Path("repo") repo: String
    ): List<CommitStatsDto>
}