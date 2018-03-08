package com.suhas.factsbook.factsbook.model

import com.google.gson.annotations.SerializedName

data class Facts(
        @SerializedName("title") var title: String?,
        @SerializedName("rows") var rows: List<Row>
)

data class Row(
        @SerializedName("title") var title: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("imageHref") var imageHref: String?
)
