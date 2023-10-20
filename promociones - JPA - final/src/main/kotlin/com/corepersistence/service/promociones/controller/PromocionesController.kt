package com.corepersistence.service.controller.custom

import com.corepersistence.service.models.database.Promociones
import com.corepersistence.service.models.custom.PromocionJsonModel
import com.corepersistence.service.models.custom.PromocionesAseguradoraJsonModel
import com.corepersistence.service.models.custom.PromocionesJsonModel
import com.corepersistence.service.models.custom.PromocionesRecomendaciones
import com.corepersistence.service.service.promociones.PromocionesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PromocionesController {

    @Autowired
    private lateinit var promocionesService: PromocionesService


    @GetMapping("/promociones")
    fun getPromociones(): PromocionesJsonModel{
        return promocionesService.getAllPromociones()
    }


    @GetMapping("/promociones/{aseguradora}")
    fun getOnePromocion(@PathVariable aseguradora: String): PromocionesAseguradoraJsonModel {
        return promocionesService.getOnePromociones(aseguradora)
    }

    @PostMapping("/promociones/{aseguradora}")
    fun addPromocion(@PathVariable aseguradora: String, @RequestBody promociones: PromocionJsonModel): ResponseEntity<Any> {
        return promocionesService.addPromocion(aseguradora, promociones)
    }

    @PostMapping("/promociones/agregar/{aseguradora}")
    fun addAseguradora(@PathVariable aseguradora: String) {
        return promocionesService.addAseguradora(aseguradora)
    }

    @PostMapping("/promociones/agregar/{tipo}")
    fun addTipoSeguro(@PathVariable tipo: String) {
        return promocionesService.addTipoSeguro(tipo)
    }

    @PutMapping("/promociones/{aseguradora}")
    fun updatePromocion(@PathVariable aseguradora: String, @RequestBody promocion: PromocionJsonModel): Promociones? {
        return promocionesService.updatePromocion(aseguradora, promocion)
    }

    @PutMapping("/recomendaciones/{tipo}")
    fun updateRecomendaciones(@PathVariable tipo: String, @RequestBody recomendacion: PromocionesRecomendaciones): Any?{
        return promocionesService.updateRecomendaciones(tipo, recomendacion)
    }

    @DeleteMapping("/promociones/{aseguradora}/{tipo}")
    fun deletePromocion(@PathVariable aseguradora: String, @PathVariable tipo: String): Promociones? {
        return promocionesService.deletePromocion(aseguradora, tipo)
    }
}