package com.corepersistence.service.promociones.model

import javax.persistence.*

@Entity
@Table(name="promociones")
data class Promociones (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id_promocion", unique=true, nullable=false)
        val idPromocion : Long,

        @Column(name="tipo_id", nullable=false)
        val type : Long,

        @Column(name="id_aseguradoras", nullable=false)
        val aseguradoras : Int,

        val dto : String,

        val msi : String,

        val label : String,

        val isSpecial : Boolean,

        val urlImg : String,

        val extraMsg : String,


)