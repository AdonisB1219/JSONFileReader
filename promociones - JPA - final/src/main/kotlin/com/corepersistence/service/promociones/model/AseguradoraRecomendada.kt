package com.corepersistence.service.models.database

import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "\"aseguradoraRecomendada\"")
data class AseguradoraRecomendada (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id:Int = 0,

        @Column(name = "aseguradora")
        var aseguradora:String = "",

        @Column(name = "\"fechaExpiracion\"")
        var fechaExpiracion: Date? = null,

        @Column(name = "\"fechaInicio\"")
        var fechaInicio: Date? = Date( java.util.Date().time ),

        @Column(name = "activo")
        var activo:Int = 1
)