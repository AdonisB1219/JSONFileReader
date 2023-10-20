package com.corepersistence.service.models.custom

import java.sql.Date
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class PromocionesRecomendaciones (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id:Long? = 0,

        @Column(name = "aseguradora")
        var aseguradora:String? = "",

        @Column(name = "\"fechaExpiracion\"")
        var fechaExpiracion: String? = null,

        @Column(name = "\"fechaInicio\"")
        var fechaInicio: String? = null,

        @Column(name = "activo")
        var activo:Int? = 1
)