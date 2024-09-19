package br.com.faculdadeimpacta.jsonplaceholder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Post
import br.com.faculdadeimpacta.jsonplaceholder.databinding.PostItemBinding

class PostAdapter(
    private val listaPost: List<Post>,
    private val acaoAutor: (Int) -> Unit,
    private val acaoComentarios: (Int) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    inner class PostVH(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Post) {
            binding.post = post
            binding.imageViewAutor.setOnClickListener {
                acaoAutor(post.userId)
            }
            binding.imageViewComentario.setOnClickListener {
                acaoComentarios(post.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(layoutInflater, parent, false)
        return PostVH(binding)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.onBind(listaPost[position])
    }

    override fun getItemCount(): Int = listaPost.size

}