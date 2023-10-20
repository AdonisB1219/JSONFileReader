package com.corepersistence.service.repository

import com.corepersistence.service.models.database.AseguradoraChoiceModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradoraChoiceRepository : JpaRepository<AseguradoraChoiceModel,Long> {

}