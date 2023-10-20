package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.Aseguradoras
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradorasRepository: JpaRepository<Aseguradoras, String> {

    fun findByName(aseguradora: String): Aseguradoras
}