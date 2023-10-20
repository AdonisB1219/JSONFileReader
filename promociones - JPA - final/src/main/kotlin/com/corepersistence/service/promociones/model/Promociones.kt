package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name="promociones")
data class Promociones (
        @Id
        @Column(name="id_promocion", unique=true)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idPromocion : Long?,

        val dto : String?,

        val msi : String?,

        val label : String?,

        val isSpecial : Boolean?,

        val urlImg : String?,

        val extraMsg : String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_insurer")
        val aseguradoras: Aseguradoras?,

        @ManyToOne
        @JoinColumn(name="id_type")
        val tipo: Tipo



)