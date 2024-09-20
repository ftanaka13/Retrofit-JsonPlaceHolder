package br.com.faculdadeimpacta.jsonplaceholder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.faculdadeimpacta.jsonplaceholder.R
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Comment
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Post
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.JsonPlaceHolderEndpoints
import br.com.faculdadeimpacta.jsonplaceholder.data.remote.RetrofitUtil
import br.com.faculdadeimpacta.jsonplaceholder.databinding.FragmentPostComentariosBinding
import br.com.faculdadeimpacta.jsonplaceholder.ui.adapters.ComentarioAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class PostComentariosFragment : Fragment() {

    private var _binding: FragmentPostComentariosBinding? = null
    private val binding get() = _binding!!
    private val args: PostComentariosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostComentariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val retrofit = RetrofitUtil.getInstance(
            "https://jsonplaceholder.typicode.com/",
            GsonConverterFactory.create()
        )
        val endpoints = retrofit.create(JsonPlaceHolderEndpoints::class.java)

        endpoints.getPost(args.postId).enqueue(object : Callback<Post> {
            override fun onResponse(p0: Call<Post>, p1: Response<Post>) {
                p1.errorBody()?.let { errorBody ->
                    Log.w("ListaPostsFragment", errorBody.toString())
                }
                p1.body()?.let { post ->
                    binding.post = post
                }
            }

            override fun onFailure(p0: Call<Post>, p1: Throwable) {
                Log.e("ListaPostsFragment", p1.printStackTrace().toString())
            }
        })

        endpoints.getPostComments(args.postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(p0: Call<List<Comment>>, p1: Response<List<Comment>>) {
                p1.errorBody()?.let { errorBody ->
                    Log.w("ListaPostsFragment", errorBody.toString())
                }
                p1.body()?.let { lista ->
                    binding.recyclerViewComentarios.adapter = ComentarioAdapter(lista)
                    binding.recyclerViewComentarios.layoutManager = LinearLayoutManager(activity)
                }
            }

            override fun onFailure(p0: Call<List<Comment>>, p1: Throwable) {
                Log.e("ListaPostsFragment", p1.printStackTrace().toString())
            }

        })


    }
}