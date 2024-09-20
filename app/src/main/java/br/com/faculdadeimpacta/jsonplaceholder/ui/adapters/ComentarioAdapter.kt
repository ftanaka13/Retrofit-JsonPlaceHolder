package br.com.faculdadeimpacta.jsonplaceholder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.faculdadeimpacta.jsonplaceholder.data.models.Comment
import br.com.faculdadeimpacta.jsonplaceholder.databinding.PostCommentItemBinding

class ComentarioAdapter(private val listaComentarios: List<Comment>) :
    RecyclerView.Adapter<ComentarioAdapter.ComentarioVH>() {

    inner class ComentarioVH(private val binding: PostCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(comentario: Comment) {
            binding.commentario = comentario
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostCommentItemBinding.inflate(layoutInflater, parent, false)
        return ComentarioVH(binding)
    }

    override fun onBindViewHolder(holder: ComentarioVH, position: Int) {
        holder.onBind(listaComentarios[position])
    }

    override fun getItemCount(): Int = listaComentarios.size
}