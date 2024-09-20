package br.com.faculdadeimpacta.jsonplaceholder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Post
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.JsonPlaceHolderEndpoints
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.RetrofitUtil
import br.com.faculdadeimpacta.jsonplaceholder.databinding.FragmentListaPostsBinding
import br.com.faculdadeimpacta.jsonplaceholder.ui.adapters.PostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class ListaPostsFragment : Fragment() {

    private var _binding: FragmentListaPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListaPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val retrofit = RetrofitUtil.getInstance(
            "https://jsonplaceholder.typicode.com/",
            GsonConverterFactory.create()
        )
        Log.i("ListaPostsFragment", retrofit.toString())
        val endpoints = retrofit.create(JsonPlaceHolderEndpoints::class.java)

        endpoints.getListaPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
                p1.errorBody()?.let { errorBody ->
                    Log.w("ListaPostsFragment", errorBody.toString())
                }
                p1.body()?.let { lista ->
                    binding.recyclerViewPosts.adapter = PostAdapter(
                        lista,
                        { userId ->
                            val direcao =
                                ListaPostsFragmentDirections.actionListaPostsFragmentToPostAutorFragment(
                                    userId
                                )
                            findNavController().navigate(direcao)
                        },
                        { postId ->
                            val direcao =
                                ListaPostsFragmentDirections.actionListaPostsFragmentToPostComentariosFragment(
                                    postId
                                )
                            findNavController().navigate(direcao)
                        })
                    binding.recyclerViewPosts.layoutManager = LinearLayoutManager(activity)
                }

            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
                Log.e("ListaPostsFragment", p1.printStackTrace().toString())
            }

        })
    }
}