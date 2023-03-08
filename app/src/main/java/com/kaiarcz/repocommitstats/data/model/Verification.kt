package com.kaiarcz.repocommitstats.data.model

data class Verification(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)