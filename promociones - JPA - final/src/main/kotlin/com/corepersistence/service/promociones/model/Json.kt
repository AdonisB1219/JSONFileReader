package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Json (
        @JsonProperty("success")val success : Boolean?,
        @JsonProperty("results")val results: String?,
        @JsonProperty("insurers") val insurers : List<InsurersJson>?,

)
