package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonProperty

data class InsurersJson (
        @JsonProperty("promotions") val promotions: List<PromotionJson>?,
        @JsonProperty("_id") val id: String?,
        @JsonProperty("name") val name: String?
)