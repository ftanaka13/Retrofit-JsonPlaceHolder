package br.com.faculdadeimpacta.jsonplaceholder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.faculdadeimpacta.jsonplaceholder.data.models.User
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.JsonPlaceHolderEndpoints
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.RetrofitUtil
import br.com.faculdadeimpacta.jsonplaceholder.databinding.FragmentPostAutorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class PostAutorFragment : Fragment() {

    private var _binding: FragmentPostAutorBinding? = null
    private val binding get() = _binding!!
    private val args: PostAutorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostAutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val retrofit = RetrofitUtil.getInstance(
            "https://jsonplaceholder.typicode.com/",
            GsonConverterFactory.create()
        )
        val endpoints = retrofit.create(JsonPlaceHolderEndpoints::class.java)

        endpoints.getAutor(args.userId).enqueue(object : Callback<User> {
            override fun onResponse(p0: Call<User>, p1: Response<User>) {
                p1.errorBody()?.let { errorBody ->
                    Log.w("ListaPostsFragment", errorBody.toString())
                }
                p1.body()?.let { autor ->
                    binding.usuario = autor
                }
            }

            override fun onFailure(p0: Call<User>, p1: Throwable) {
                Log.e("ListaPostsFragment", p1.printStackTrace().toString())
            }

        })
    }
}