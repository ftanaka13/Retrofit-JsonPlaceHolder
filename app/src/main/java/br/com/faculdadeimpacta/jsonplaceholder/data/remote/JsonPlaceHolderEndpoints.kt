package br.com.faculdadeimpacta.jsonplaceholder.data.remote

import br.com.faculdadeimpacta.jsonplaceholder.data.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderEndpoints {

    @GET("posts")
    fun getListaPosts(): Call<List<Post>>
}