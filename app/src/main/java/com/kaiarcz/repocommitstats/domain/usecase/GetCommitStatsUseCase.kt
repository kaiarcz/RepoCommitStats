package com.kaiarcz.repocommitstats.domain.usecase

import com.kaiarcz.repocommitstats.app.util.Resource
import com.kaiarcz.repocommitstats.data.model.toCommitStats
import com.kaiarcz.repocommitstats.domain.model.CommitStats
import com.kaiarcz.repocommitstats.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommitStatsUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    operator fun invoke(repo: String): Flow<Resource<List<CommitStats>>> = flow {
        try {
            emit(Resource.Loading())
            val commitStats = repository.getCommitStatus(repo).map { it.toCommitStats() }
            emit(Resource.Success(commitStats))
        } catch(e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}