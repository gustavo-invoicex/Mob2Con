package io.invoicex.mob2con.network.service

import retrofit2.http.GET

interface SkuService {

    @GET("2328b638-6541-4e72-b6d0-dd547476a2db")
//    @GET("skus")
    suspend fun fetchSkus(): List<SkuResponse>
}
