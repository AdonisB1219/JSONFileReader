package com.corepersistence.service.promociones.controller

import com.corepersistence.service.promociones.model.InsurersJson
import com.corepersistence.service.promociones.model.Json
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONException
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets


@RestController
class GenericController() {

/*    @Value("classpath:/data.json")
    var resourceFile: Resource? = null

    var clsPath = ClassPathResource("/data.json").path*/

    val staticDataResource = ClassPathResource("data.json")
    val staticDataString: String = IOUtils.toString(staticDataResource.inputStream, StandardCharsets.UTF_8)


    @PostMapping("/obj")
    fun getObject(){
        val myJsonMapper = ObjectMapper()
        val objeto: Json = myJsonMapper.readValue(staticDataString, Json::class.java)
        print(objeto)
        objeto.insurers!!.get(1).name

    }

    @GetMapping("/json")
    fun getData() {


        var json_transform: JSONObject? = null
        try {
            json_transform = JSONObject(staticDataString)
        } catch (e: JSONException) {
            e.printStackTrace()
        }



        json_transform?.keys()?.forEachRemaining({key ->
            println("Key: ${key}\tValue: ${json_transform.get(key.toString())}")})

        var insurers: JSONArray? = null
        insurers = JSONArray(json_transform!!.get("insurers").toString())

        for (i in 0 .. insurers.length() - 1){
            val insurer = JSONObject(insurers.get(i).toString())
            println(insurer)
        }



    }


}