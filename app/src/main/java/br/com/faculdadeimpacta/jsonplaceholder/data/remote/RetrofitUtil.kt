package br.com.faculdadeimpacta.jsonplaceholder.data.remote

import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitUtil {
    companion object {
        val instances = mutableMapOf<String, Retrofit>()
        fun getInstance(baseUrl: String, converter: Converter.Factory): Retrofit {
            if (!instances.keys.contains(baseUrl)) {
                instances[baseUrl] = Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(converter)
                    .build()
            }
            return instances[baseUrl]!!
        }
    }
}