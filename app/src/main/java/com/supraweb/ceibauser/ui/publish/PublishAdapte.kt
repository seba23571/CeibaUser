package com.supraweb.ceibauser.ui.publish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.ceibauser.R
import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
import com.supraweb.ceibauser.databinding.ListasPublicacionesBinding


class PublishAdapte(

    private val detailPublish: List<UserPublishItem>?

)        :
    RecyclerView.Adapter<PublicacionViewHolder>()                    {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return   PublicacionViewHolder(layoutInflater.inflate(R.layout.listas_publicaciones, parent, false))

       //  PublicacionViewHolder(layoutInflater.inflate(binding.idListas, parent, false))


    }

    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val itemPublish = detailPublish?.get(position)

        holder.render(itemPublish!!)

    }

    override fun getItemCount(): Int {
        return detailPublish!!.size
    }


}


class PublicacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ListasPublicacionesBinding.bind(view)

    fun render(itemPublish: UserPublishItem) {

        binding.idTituloPublicacion .text = itemPublish.title
        binding.idNombrePublicacion.text = itemPublish.body

    }


}