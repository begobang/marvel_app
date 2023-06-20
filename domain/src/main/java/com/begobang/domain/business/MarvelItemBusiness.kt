package com.begobang.domain.business

data class MarvelItemBusiness(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: ThumbnailBusiness?,
    val resourceURI: String?,
    val comics: CollectionBusiness?,
    val series: CollectionBusiness?,
    val stories: CollectionBusiness?,
    val events: CollectionBusiness?,
    val urls: List<UrlBusiness>?

)

data class ThumbnailBusiness(
    val path : String?,
    val extension: String?
)

data class CollectionBusiness(
    val available: Int?,
    val collectionURI: String?,
    val items: List<CollectionItemBusiness>?,
    val returned: Int?
)

data class CollectionItemBusiness(
    val resourceURI: String?,
    val name: String?
)

data class UrlBusiness(
    val type: String,
    val url: String
)