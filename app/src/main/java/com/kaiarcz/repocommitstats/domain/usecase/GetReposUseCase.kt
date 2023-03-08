package com.kaiarcz.repocommitstats.domain.usecase

import com.kaiarcz.repocommitstats.app.util.Resource
import com.kaiarcz.repocommitstats.data.model.toRepo
import com.kaiarcz.repocommitstats.domain.model.Repo
import com.kaiarcz.repocommitstats.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    operator fun invoke(): Flow<Resource<List<Repo>>> = flow {
        try {
            emit(Resource.Loading())
            val repos = repository.getRepos().map { it.toRepo() }
            emit(Resource.Success(repos))
        } catch(e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}