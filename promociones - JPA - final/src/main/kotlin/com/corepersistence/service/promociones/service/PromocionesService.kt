package com.corepersistence.service.service.promociones

import com.corepersistence.service.controller.exception.*
import com.corepersistence.service.models.custom.PromocionJsonModel
import com.corepersistence.service.models.custom.PromocionesAseguradoraJsonModel
import com.corepersistence.service.models.custom.PromocionesJsonModel
import com.corepersistence.service.models.custom.PromocionesRecomendaciones
import com.corepersistence.service.models.database.*
import com.corepersistence.service.repository.PromocionesAseguradorasRepository
import com.corepersistence.service.repository.PromocionesRepository
import com.corepersistence.service.repository.PromocionesTipoSeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable


@Service
class PromocionesService {

    @Autowired
    private lateinit var promocionesRepository: PromocionesRepository

    @Autowired
    private lateinit var aseguradorasRepository: PromocionesAseguradorasRepository

    @Autowired
    private lateinit var tipoRepository: PromocionesTipoSeguroRepository

    @Autowired
    private lateinit var aseguradorasRecomendacionesService: AseguradorasRecomendacionesServiceImpl



    override fun getOnePromociones(@PathVariable aseguradora : String): PromocionesAseguradoraJsonModel {
        var aseguradoraObj = aseguradorasRepository.findByName(aseguradora.uppercase()).orElseThrow{throw AseguradoraNotFoundException(HttpStatus.NOT_FOUND, "La aseguradora ${aseguradora} no está registrada. Revisa la escritura o regístrala")}
        var lista = promocionesRepository.findAllByAseguradoras(aseguradoraObj).orElseThrow{throw SinPromocionesRegistradasException(HttpStatus.NOT_FOUND, "La aseguradora ${aseguradora} no tiene promociones registradas")}
        var listaJson = lista.map({p -> PromocionJsonModel(p.tipo!!.type, p.dto, p.msi, p.label, p.urlImg, p.isSpecial, p.extraMsg, p.urlImgEcommerce) })
        return PromocionesAseguradoraJsonModel(listaJson, aseguradora.uppercase())
    }


    override fun getAllPromociones(): PromocionesJsonModel {
        var promocionesJson : MutableList<PromocionesAseguradoraJsonModel> = mutableListOf()

        var aseguradoras = aseguradorasRepository.findAll()

        for (a in aseguradoras){
            var lista = promocionesRepository.findAll().filter({p -> p.aseguradoras!!.name == a.name}).map({p -> PromocionJsonModel(p.tipo!!.type, p.dto, p.msi, p.label, p.urlImg, p.isSpecial, p.extraMsg, p.urlImgEcommerce) })
            promocionesJson.add(PromocionesAseguradoraJsonModel(lista, a.name))
        }
        var recomendaciones = aseguradorasRecomendacionesService.crearObjetos()
        return PromocionesJsonModel(true, aseguradoras.size.toString(), recomendaciones.get(1), recomendaciones.get(2), recomendaciones.get(0), promocionesJson)
    }


    override fun addPromocion(nombreAseguradora: String, promocion: PromocionJsonModel): ResponseEntity<Any> {
        var type : PromocionesTipoSeguro
        var aseguradora : PromocionesAseguradoras
        var resultado: Promociones? = null

        if(aseguradorasRepository.findByName(nombreAseguradora.uppercase()).isPresent) {
            aseguradora = aseguradorasRepository.findByName(nombreAseguradora.uppercase()).get()

            if(tipoRepository.findByType(promocion.type!!).isPresent) {
                type = tipoRepository.findByType(promocion.type).orElseThrow{throw TipoSeguroNotFoundException(HttpStatus.NOT_FOUND, "El tipo de seguro ${promocion.type} no está registrado. Revisa la escrítura o consulta la documentación para registrarlo") }

                if(promocionesRepository.findByAseguradorasAndTipo(aseguradora, type).isEmpty) {
                    resultado = promocionesRepository.save(Promociones(dto = promocion.dto, msi = promocion.msi, label = promocion.label, extraMsg = promocion.extraMsg, isSpecial = promocion.isSpecial, urlImg = promocion.urlImg, aseguradoras = aseguradora, tipo = type, urlImgEcommerce = promocion.urlImgEcommerce))

                } else{
                    var promocionExist = promocionesRepository.findByAseguradorasAndTipo(aseguradora, type).get()
                    throw TipoSeguroExistenteException(HttpStatus.CONFLICT, promocionExist, "El tipo de seguro ${promocion.type} ya tiene un registro. Para actualizarlo consulta la documentación")
                }
            }

        } else throw AseguradoraNotFoundException(HttpStatus.NOT_FOUND, "La aseguradora ${nombreAseguradora} no está registrada")

        return ResponseEntity(resultado, HttpStatus.CREATED)
    } // fun addPromocion



    override fun updatePromocion(nombreAseguradora : String, promocionJsonModel: PromocionJsonModel): Promociones? {
        var resultado : Promociones? = null
        if(aseguradorasRepository.findByName(nombreAseguradora).isPresent){
            var aseguradora = aseguradorasRepository.findByName(nombreAseguradora.uppercase()).get()
            if(tipoRepository.findByType(promocionJsonModel.type!!).isPresent){
                var tipo = tipoRepository.findByType(promocionJsonModel.type).get()
                var promocion = promocionesRepository.findByAseguradorasAndTipo(aseguradora, tipo).get()
                if(promocionJsonModel.isSpecial != null) promocion.isSpecial = promocionJsonModel.isSpecial
                if(promocionJsonModel.dto != null) promocion.dto = promocionJsonModel.dto
                if(promocionJsonModel.msi != null) promocion.msi = promocionJsonModel.msi
                if(promocionJsonModel.label != null) promocion.label = promocionJsonModel.label
                if(promocionJsonModel.urlImg != null) promocion.urlImg = promocionJsonModel.urlImg
                if(promocionJsonModel.extraMsg != null) promocion.extraMsg = promocionJsonModel.extraMsg
                if(promocionJsonModel.urlImgEcommerce != null) promocion.urlImgEcommerce = promocionJsonModel.urlImgEcommerce

                resultado = promocionesRepository.save(promocion)

            } else throw TipoSeguroNotFoundException(HttpStatus.NOT_FOUND, "El tipo de seguro ${promocionJsonModel.type} no tiene registros para la aseguradora ${nombreAseguradora}")

        } else throw AseguradoraNotFoundException(HttpStatus.NOT_FOUND, "La aseguradora ${nombreAseguradora} no está registrada.")

        return resultado
    }// fun updatePromocion

    override fun deletePromocion(aseguradoraNombre: String, tipo: String): Promociones? {
        var aseguradora = aseguradorasRepository.findByName(aseguradoraNombre.uppercase()).orElseThrow{throw AseguradoraNotFoundException(HttpStatus.NOT_FOUND, "La aseguradora ${aseguradoraNombre} no está registrada. Revisa la escritura o regístrala")}
        var tipo = tipoRepository.findByType(tipo).orElseThrow{TipoSeguroNotFoundException(HttpStatus.NOT_FOUND, "El tipo de seguro ${tipo} no está registrado. Revisa la escritura o regístralo.")}
        var promocion = promocionesRepository.findByAseguradorasAndTipo(aseguradora, tipo).orElseThrow{throw TipoSeguroNotFoundException(HttpStatus.NOT_FOUND, "El tipo de seguro ${tipo} no está registrado para la aseguradora ${aseguradoraNombre}")}

        promocionesRepository.deleteById(promocion.idPromocion!!)
        return promocion
    }

    override fun addAseguradora(aseguradoras: String){
        var aseguradora = aseguradorasRepository.findByName(aseguradoras.uppercase())

        if(aseguradora.isEmpty) aseguradorasRepository.save(PromocionesAseguradoras(null, aseguradoras.uppercase()))
        else throw AseguradoraExistenteException(HttpStatus.CONFLICT, "La aseguradora ${aseguradoras} ya está registrada.")
    }

    override fun addTipoSeguro(tipo: String){
        var type = tipoRepository.findByType(tipo)
        if (type.isEmpty) tipoRepository.save(PromocionesTipoSeguro(null, tipo))
        else throw TipoSeguroExistenteException(HttpStatus.CONFLICT,null,  "El tipo de seguro ${tipo} ya está registrado.")
    }

    override fun updateRecomendaciones(tipo: String, recomendacion: PromocionesRecomendaciones): Any? {
        return aseguradorasRecomendacionesService.updateRecomendaciones(tipo, recomendacion)
    }

}