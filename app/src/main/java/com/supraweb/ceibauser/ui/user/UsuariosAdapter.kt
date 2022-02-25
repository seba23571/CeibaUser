package com.supraweb.ceibauser.ui.home.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.ceibauser.R
import com.supraweb.ceibauser.data.network.response.UserDetails
import com.supraweb.ceibauser.databinding.ListasUsuariosBinding
import com.supraweb.ceibauser.ui.user.UserListener

class UsuariosAdapter(
    private val detailsUsers: List<UserDetails>,
    private val userListener: UserListener

    ) :
    RecyclerView.Adapter<UsuariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuariosViewHolder(layoutInflater.inflate(R.layout.listas_usuarios, parent, false))


    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val itemsUser = detailsUsers?.get(position)


        holder.render(itemsUser!!,userListener)
    }

    override fun getItemCount(): Int {
        return detailsUsers!!.size
    }
}



class UsuariosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding =  ListasUsuariosBinding .bind(view)              //listas_usuarios
    fun render(userDetails: UserDetails, holder: UserListener) {
        binding.idTiemsNombreItems   .text =userDetails.name
        binding.idPhoneNumberText.text    =userDetails.phone
        binding.idMailText  .text = userDetails.email
        binding.idVerPublicacion         .text = "publicacion num : "+userDetails.id
           binding.idVerPublicacion.setOnClickListener {
               holder.idPublishFromRecy(userDetails)
        }
    }
}
