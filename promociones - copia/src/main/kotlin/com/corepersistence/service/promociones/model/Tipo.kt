package com.corepersistence.service.promociones.model

import javax.persistence.*

@Entity
@Table(name="tipo_seguro_promocion")
data class Tipo (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id_tipo", unique=true, nullable=false)
        val idTipo : Long,

        @Column(name="type", unique=true, nullable=false)
        val type : Int
)