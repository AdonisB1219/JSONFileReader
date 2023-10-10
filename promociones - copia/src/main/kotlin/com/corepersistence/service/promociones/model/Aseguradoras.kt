package com.corepersistence.service.promociones.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="aseguradoras_promociones")
data class Aseguradoras (
        @Id
        @Column(name="id_aseguradoras", unique=true, nullable=false)
        val idAseguradoras : String,

        @Column(name="name", unique=true, nullable=false)
        val name : String
)