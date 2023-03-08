package com.kaiarcz.repocommitstats.data.model

import com.kaiarcz.repocommitstats.domain.model.CommitStats
import java.time.ZonedDateTime

data class CommitStatsDto(
    val author: Author,
    val comments_url: String,
    val commit: Commit,
    val committer: CommitterX,
    val html_url: String,
    val node_id: String,
    val parents: List<Parent>,
    val sha: String,
    val url: String
)

fun CommitStatsDto.toCommitStats(): CommitStats {
    val zonedDateTime = ZonedDateTime.parse(commit.committer.date)
    return CommitStats(
        date = zonedDateTime
    )
}