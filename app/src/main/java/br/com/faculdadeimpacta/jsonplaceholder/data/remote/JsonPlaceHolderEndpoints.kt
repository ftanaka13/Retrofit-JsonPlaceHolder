package br.com.faculdadeimpacta.jsonplaceholder.data.remote

import br.com.faculdadeimpacta.jsonplaceholder.data.models.Comment
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Post
import br.com.faculdadeimpacta.jsonplaceholder.data.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderEndpoints {

    @GET("posts")
    fun getListaPosts(): Call<List<Post>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Call<Post>

    @GET("posts/{postId}/comments")
    fun getPostComments(@Path("postId") postId: Int): Call<List<Comment>>

    @GET("users/{userId}")
    fun getAutor(@Path("userId") userId: Int): Call<User>
}