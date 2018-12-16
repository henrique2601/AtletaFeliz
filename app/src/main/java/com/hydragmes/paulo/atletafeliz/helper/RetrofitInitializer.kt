package com.hydragmes.paulo.atletafeliz.helper
import com.hydragmes.paulo.atletafeliz.Model.BRState

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun stateService() = retrofit.create(StateService::class.java)
    fun cityService() = retrofit.create(CityService::class.java)

}

interface StateService {
    @GET("estados/")
    fun list() : Call<List<BRState>>
}

interface CityService {
    @GET("estados/{id}/municipios/")
    fun list(@Path("id") id: String) : Call<List<BRState>>
}