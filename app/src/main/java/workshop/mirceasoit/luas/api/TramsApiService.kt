package workshop.mirceasoit.luas.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import workshop.mirceasoit.luas.model.StopInfo

private const val BASE_URL = "https://luasforecasts.rpa.ie/xml/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(SimpleXmlConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TramsApiService {
    @GET("get.ashx?action=forecast&encrypt=false")
    fun getResponse(@Query("stop") stop: String): Call<StopInfo>
}

object TramsApi {
    val retrofitService: TramsApiService by lazy { retrofit.create(
        TramsApiService::class.java) }
}