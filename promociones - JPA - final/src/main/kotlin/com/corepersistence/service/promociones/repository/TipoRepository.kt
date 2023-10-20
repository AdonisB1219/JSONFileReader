package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.Tipo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoRepository : JpaRepository<Tipo, Long> {
    fun findByType(type: String): Tipo
}