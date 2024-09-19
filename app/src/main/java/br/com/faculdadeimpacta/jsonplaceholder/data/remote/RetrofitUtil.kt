package br.com.faculdadeimpacta.jsonplaceholder.data.remote

import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitUtil {
    companion object {
        fun getInstance(baseUrl: String, converter: Converter.Factory): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build()
        }
    }
}