package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name="promocionesTipoSeguro")
data class Tipo(
        @Id
        @Column(name="id_type", unique=true)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idTipo: Long?,

        @Column(name="type", unique=true)
        val type: String,

)