package com.corepersistence.service.service.promociones

import com.corepersistence.service.controller.car.comparator.AseguradoraChoiceController
import com.corepersistence.service.models.custom.PromocionesRecomendaciones
import com.corepersistence.service.models.database.Promociones
import com.corepersistence.service.repository.AseguradoraChoiceRepository
import com.corepersistence.service.repository.AseguradoraMasVendidaRepository
import com.corepersistence.service.repository.AseguradoraRecomendadaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDateTime

@Service
class AseguradorasRecomendacionesService {

    @Autowired
    private lateinit var aseguradoraChoiceRepository : AseguradoraChoiceRepository

    @Autowired
    private lateinit var aseguradoraRecomendadaRepository: AseguradoraRecomendadaRepository

    @Autowired
    private lateinit var aseguradoraMasVendidaRepository: AseguradoraMasVendidaRepository


    fun crearObjetos(): List<PromocionesRecomendaciones> {

        var choice = aseguradoraChoiceRepository.getById(1)
        var recomendada = aseguradoraRecomendadaRepository.getById(1)
        var masVendida = aseguradoraMasVendidaRepository.getById(1)


        var choiceResp = PromocionesRecomendaciones(choice.id, choice.aseguradora, choice.fechaExpiracion.toString(), choice.fechaInicio.toString(), choice.activo)
        var recomendadResp = PromocionesRecomendaciones(recomendada.id.toLong(), recomendada.aseguradora, recomendada.fechaExpiracion.toString(), recomendada.fechaInicio.toString(), choice.activo)
        var masVendidaResp = PromocionesRecomendaciones(masVendida.id, masVendida.aseguradora, masVendida.fechaExpiracion.toString(), masVendida.fechaInicio.toString(), masVendida.activo)

        return listOf(choiceResp, recomendadResp, masVendidaResp)

    }

    override fun updateRecomendaciones(tipo: String, tipoRecomendacion: PromocionesRecomendaciones): Any? {
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var resultado : PromocionesRecomendaciones? = null
        when (tipo.lowercase()){
            "recomendada" -> {
                var aseg = aseguradoraRecomendadaRepository.getById(1)
                if (tipoRecomendacion.aseguradora != null) aseg.aseguradora = tipoRecomendacion.aseguradora!!
                if (tipoRecomendacion.activo != null) aseg.activo = tipoRecomendacion.activo!!
                if (tipoRecomendacion.fechaExpiracion != null) aseg.fechaExpiracion = Date(formato.parse(tipoRecomendacion.fechaExpiracion).time)
                if (tipoRecomendacion.fechaInicio != null) aseg.fechaInicio = Date(formato.parse(tipoRecomendacion.fechaInicio).time)
                aseguradoraRecomendadaRepository.save(aseg)
                resultado = PromocionesRecomendaciones(aseg.id.toLong(), aseg.aseguradora, aseg.fechaExpiracion.toString(), aseg.fechaInicio.toString(), aseg.activo)

            }
            "mas-vendida" -> {
                var aseg = aseguradoraMasVendidaRepository.getById(1)
                if (tipoRecomendacion.aseguradora != null) aseg.aseguradora = tipoRecomendacion.aseguradora!!
                if (tipoRecomendacion.activo != null) aseg.activo = tipoRecomendacion.activo!!
                if (tipoRecomendacion.fechaExpiracion != null) aseg.fechaExpiracion = Date(formato.parse(tipoRecomendacion.fechaExpiracion).time)
                if (tipoRecomendacion.fechaInicio != null) aseg.fechaInicio = Date(formato.parse(tipoRecomendacion.fechaInicio).time)
                aseguradoraMasVendidaRepository.save(aseg)
                resultado = PromocionesRecomendaciones(aseg.id.toLong(), aseg.aseguradora, aseg.fechaExpiracion.toString(), aseg.fechaInicio.toString(), aseg.activo)

            }
            "choice" -> {
                var aseg = aseguradoraChoiceRepository.getById(1)
                if (tipoRecomendacion.aseguradora != null) aseg.aseguradora = tipoRecomendacion.aseguradora!!
                if (tipoRecomendacion.activo != null) aseg.activo = tipoRecomendacion.activo!!
                if (tipoRecomendacion.fechaExpiracion != null) aseg.fechaExpiracion = Date(formato.parse(tipoRecomendacion.fechaExpiracion).time)
                if (tipoRecomendacion.fechaInicio != null) aseg.fechaInicio = Date(formato.parse(tipoRecomendacion.fechaInicio).time)
                aseguradoraChoiceRepository.save(aseg)
                resultado = PromocionesRecomendaciones(aseg.id.toLong(), aseg.aseguradora, aseg.fechaExpiracion.toString(), aseg.fechaInicio.toString(), aseg.activo)

            }

        }
        return resultado
    }

}