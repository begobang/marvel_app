package com.begobang.data.response

import com.begobang.domain.business.CollectionBusiness
import com.begobang.domain.business.CollectionItemBusiness
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.business.ThumbnailBusiness
import com.begobang.domain.business.UrlBusiness
import com.google.gson.annotations.SerializedName

data class MarvelItemResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("comics")
    val comics: CollectionResponse?,
    @SerializedName("series")
    val series: CollectionResponse?,
    @SerializedName("stories")
    val stories: CollectionResponse?,
    @SerializedName("events")
    val events: CollectionResponse?,
    @SerializedName("urls")
    val urls: List<UrlResponse>?

)

data class ThumbnailResponse(
    @SerializedName("path")
    val path : String?,
    @SerializedName("extension")
    val extension: String?
)

data class CollectionResponse(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<CollectionItemResponse>?,
    @SerializedName("returned")
    val returned: Int?
)

data class CollectionItemResponse(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?
)

data class UrlResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
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