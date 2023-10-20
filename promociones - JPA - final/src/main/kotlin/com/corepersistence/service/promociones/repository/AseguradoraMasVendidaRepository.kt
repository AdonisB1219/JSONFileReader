package com.corepersistence.service.repository


import com.corepersistence.service.models.database.AseguradoraMasVendidaModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradoraMasVendidaRepository: JpaRepository<AseguradoraMasVendidaModel, Long> {
}