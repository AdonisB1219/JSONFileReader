package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PromotionJson (
        @JsonProperty("type") val type: String?,
        @JsonProperty("dto") val dto: String?,
        @JsonProperty("msi")val msi: String?,
        @JsonProperty("label") val label : String?,
        @JsonProperty("urlImg") val urlImg : String?,
        @JsonProperty("isSpecial") val isSpecial: Boolean?,
        @JsonProperty("extraMsg") val extraMsg: String?
)

