package com.simbadev.youtubeclone.remote.model

data class PlayList(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)