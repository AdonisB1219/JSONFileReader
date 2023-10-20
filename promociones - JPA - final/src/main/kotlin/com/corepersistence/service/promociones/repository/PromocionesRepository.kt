package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.Promociones
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromocionesRepository : JpaRepository<Promociones, Long> {




}