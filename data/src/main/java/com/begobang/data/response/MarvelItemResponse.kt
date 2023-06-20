package com.begobang.data.response

import com.begobang.domain.business.CollectionBusiness
import com.begobang.domain.business.CollectionItemBusiness
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.business.ThumbnailBusiness
import com.begobang.domain.business.UrlBusiness

data class MarvelItemResponse(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: ThumbnailResponse?,
    val resourceURI: String?,
    val comics: CollectionResponse?,
    val series: CollectionResponse?,
    val stories: CollectionResponse?,
    val events: CollectionResponse?,
    val urls: List<UrlResponse>?

)

data class ThumbnailResponse(
    val path : String?,
    val extension: String?
)

data class CollectionResponse(
    val available: Int?,
    val collectionURI: String?,
    val items: List<CollectionItemResponse>?,
    val returned: Int?
)

data class CollectionItemResponse(
    val resourceURI: String?,
    val name: String?
)

data class UrlResponse(
    val type: String,
    val url: String
)

fun MarvelItemResponse.toDomain(): MarvelItemBusiness {
    return MarvelItemBusiness(
        id,
        name,
        description,
        modified,
        thumbnail?.toDomain(),
        resourceURI,
        comics?.toDomain(),
        series?.toDomain(),
        stories?.toDomain(),
        events?.toDomain(),
        urls?.map { it.toDomain() }
    )
}

fun ThumbnailResponse.toDomain() = ThumbnailBusiness(path, extension)

fun CollectionResponse.toDomain() = CollectionBusiness(
    available,
    collectionURI,
    items?.map { CollectionItemBusiness(it.resourceURI, it.name) },
    returned
)

fun UrlResponse.toDomain() = UrlBusiness(type, url)