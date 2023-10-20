package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonProperty

class InsurersJsonClient (
        @JsonProperty("promotions") val promotions: List<PromotionJson>?,
        @JsonProperty("name") val name: String?
)