package com.supraweb.ceibauser.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
import com.supraweb.ceibauser.databinding.PublishFragmentBinding
import com.supraweb.ceibauser.ui.home.profile.UsuariosAdapter
import com.supraweb.ceibauser.util.hide
import com.supraweb.ceibauser.util.show
import kotlinx.android.synthetic.main.publish_fragment.view.*
import kotlinx.android.synthetic.main.users_fragment.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PublishFragment : Fragment(), PublishListener, KodeinAware {

    override val kodein by kodein()
    private val factory: PublishViewModelFactory by instance()

    private val args by navArgs<PublishFragmentArgs>()
    private var _binding: PublishFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PublishViewModel



    //var about recyclerview
    private lateinit var  reciclerPublish: RecyclerView
    private lateinit var publishAdapte: PublishAdapte




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(PublishViewModel::class.java)
        // val view = inflater.inflate(R.layout.publish_fragment, container, false)
        _binding = PublishFragmentBinding.inflate(inflater, container, false)
        //        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        // binding.idListasDePublicaciones.text = "Listas de las Publicaciones con ID : " + args.userDetail.id

        binding.idListasDePublicaciones.text = "Listas de las Publicaciones con ID : " + args.userDetail.id
        binding.idUserNamePublis.text = args.userDetail.username //id_userNamePublis
        binding.idMailNamePublis.text = args.userDetail.email            //id_mailNamePublis
        binding.idPhonePublish.text = args.userDetail.phone       //id_mailNamePublis

        //     instancias del RECYCLERVIEW Y ADAPTADOR
        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        reciclerPublish = binding.itemsPublicaciones
          reciclerPublish.    layoutManager =    linearLayoutManager
        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        publishAdapte = PublishAdapte(emptyList())
        reciclerPublish.adapter = publishAdapte
        reciclerPublish. items_publicaciones.addItemDecoration(decoration)
        reciclerPublish .setHasFixedSize(true)
        //     instancias del RECYCLERVIEW Y ADAPTADOR

      viewModel.listenerInterfaz=this

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        reciclerUpdate = binding.itemsRecyclerview
//        reciclerUpdate.layoutManager = LinearLayoutManager(requireContext())
//        reciclerUpdate.setHasFixedSize(true)

//        reciclerPublish = binding.itemsPublicaciones
//        reciclerPublish.layoutManager = LinearLayoutManager(requireContext())
//        reciclerPublish.setHasFixedSize(true)

        viewModel.getPublishByID(binding.root,args.userDetail.id)
    }


    override fun onFailure(message: String) {
        Toast.makeText(context, "error  " + message, Toast.LENGTH_SHORT).show()
    }

    override fun onStated() {
        binding.progressfBar.show()

    }

    override fun detailPublicacion(publicacionId: LiveData<List<UserPublishItem>>) {



    }

    override fun livedataObserver(publicacionId: LiveData<List<UserPublishItem>>) {
     //   println("comenzando.........................")
        if(publicacionId!=null){
            println(" no es nulo ")
        }

        publicacionId.observe(viewLifecycleOwner, androidx.lifecycle.Observer { listPublis ->
//            listPublis.forEach {
//                println("\n LA lISTAS DE PUBLICACIONES SON \n" + it.toString())
//            }


            binding.progressfBar.hide()
          initRecyclerView(listPublis)
        })
    }


    private fun initRecyclerView(listPublis: List<UserPublishItem>) {


        publishAdapte = PublishAdapte(listPublis)
        reciclerPublish.adapter =publishAdapte

        (reciclerPublish.adapter as PublishAdapte).notifyDataSetChanged()


        println("comenzando.........................")

//        publishAdapte = PublishAdapte(listPublis)
//
//        reciclerP.adapter = publishAdapte
//       // reciclerUpdate.adapter = mProductListAdapter
//        (reciclerP.adapter as PublishAdapte).notifyDataSetChanged()

    }

}