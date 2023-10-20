package com.corepersistence.service.promociones.service

import com.corepersistence.service.promociones.model.Aseguradoras
import com.corepersistence.service.promociones.model.Json
import com.corepersistence.service.promociones.model.Promociones
import com.corepersistence.service.promociones.model.Tipo
import com.corepersistence.service.promociones.repository.AseguradorasRepository
import com.corepersistence.service.promociones.repository.PromocionesRepository
import com.corepersistence.service.promociones.repository.TipoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PopulateDBService {

    @Autowired
    private lateinit var tipoRepository : TipoRepository

    @Autowired
    private lateinit var aseguradorasRepository : AseguradorasRepository

    @Autowired
    private lateinit var promocionesRepository: PromocionesRepository

    fun populateDataBase(json: Json){
        var tipos: HashSet<String?> = HashSet()
        //var aseguradoras: HashMap<String, String> = HashMap()


        for (insurer in json.insurers!!){
            for(promotion in insurer.promotions!!){
                tipos.add(promotion.type)
            }
            //aseguradorasRepository.save(Aseguradoras(idAseguradoras = null, name=insurer.name!!))
        }



        for(t in tipos){
            var nuevoTipo = Tipo(null, t!!)
            tipoRepository.save(nuevoTipo)
        }


        /*for(a in aseguradoras){
            var aseguradora = Aseguradoras(a.key, a.value)
            aseguradorasRepository.save(aseguradora)
        }*/

        var tipo : Tipo
        var idInsurer : Long

        for(insurer in json.insurers!!){
            idInsurer = aseguradorasRepository.findByName(insurer.name!!).idAseguradoras!!
            for (promotion in insurer.promotions!!){
                tipo = tipoRepository.findByType(promotion.type!!)
                var promocion = Promociones(null, promotion.dto, promotion.msi, promotion.label, promotion.isSpecial, promotion.urlImg, promotion.extraMsg, Aseguradoras(idInsurer, insurer.name), tipo)
                promocionesRepository.save(promocion)

            }
        }

    }
}