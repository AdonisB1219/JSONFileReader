package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name="promocionesAseguradoras")
data class Aseguradoras (
        @Id
        @Column(name="id_insurer", unique=true)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idAseguradoras : Long?,

        @Column(name="name", unique=true, nullable=false)
        val name : String,

)