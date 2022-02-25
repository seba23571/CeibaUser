package com.supraweb.ceibauser.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.ceibauser.data.network.response.UserDetails
import com.supraweb.ceibauser.databinding.UsersFragmentBinding
import com.supraweb.ceibauser.ui.home.profile.UsuariosAdapter
import com.supraweb.ceibauser.util.Coroutines
import com.supraweb.ceibauser.util.hide
import com.supraweb.ceibauser.util.show
import kotlinx.android.synthetic.main.users_fragment.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class UsersFragment : Fragment(), KodeinAware, UserListener, SearchView.OnQueryTextListener {

    private var _binding: UsersFragmentBinding? = null

    private val binding get() = _binding!!


    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    private lateinit var viewModel: UserViewModel

    private lateinit var searchView: SearchView

      //var about recyclerview
    private lateinit var recyclerViewFragment: RecyclerView
    private lateinit var usuariosAdapter: UsuariosAdapter

    private   var listUser: List<UserDetails>?=null
    private var listFilter = ArrayList<UserDetails>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        _binding = UsersFragmentBinding.inflate(inflater, container, false)

                //     instancias del RECYCLERVIEW Y ADAPTADOR
        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerViewFragment = binding.itemsRecyclerview //items_recyclerview usuariosAdapter
        recyclerViewFragment.layoutManager =    linearLayoutManager      //LinearLayoutManager(requireContext())
        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        usuariosAdapter = UsuariosAdapter(emptyList(), this)
        recyclerViewFragment.adapter = usuariosAdapter
        recyclerViewFragment.items_recyclerview.addItemDecoration(decoration)
        recyclerViewFragment.setHasFixedSize(true)
        //     instancias del RECYCLERVIEW Y ADAPTADOR


        viewModel.Listener = this
        // ************ comprobar si tiene datos
        viewModel.checkUserTimeLogin()
        // ************ comprobar si tiene datos
        viewModel.selectSqlUser().observe(viewLifecycleOwner, Observer { lista ->
            this.listUser = lista
            if (listUser!!.size == 0) {
                viewModel.onLoadUsers(binding.root)
            }

        }
        )
        //
        searchView = binding!!.idSearchVIEW
        searchView.setOnQueryTextListener(this)

        Coroutines.main {
            viewModel.listaUserNueva.await().observe(viewLifecycleOwner, Observer { allUser ->
                if (allUser != null) {
                    listUser = allUser
                    binding.progressfBar.hide()
                }
                initRecyclerView(allUser)
//        otra.forEach {
//            println("datos nuevo metodo "+it.toString())
//        }

            })
        }


        return binding.root

        //  return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    // private fun getALLAddress() {        Coroutines.main {            viewModel.onAllAddress()        }           }


    override fun detalleDeUsuarios(detallesUser: LiveData<List<UserDetails>>) {


        detallesUser.observe(viewLifecycleOwner, Observer { allUser ->
            //    met(allUser) abstract fun met(allUser: List<UserDetails>?)
            if (allUser != null) {
                listUser = allUser
                binding.progressfBar.hide()
                //  println("....................... DESDE PROFILE FRAGMENTE ")

                /*  allUser.forEach {
                      println(" " + it.address + " " + it.email + " " + it.username)
                  }*/

                initRecyclerView(allUser)

            }

        })
    }

    override fun onFailure(message: String) {

        Toast.makeText(context, "error  " + message, Toast.LENGTH_SHORT).show()

    }

    override fun onStated() {

        binding.progressfBar.show()
    }


    override fun idPublishFromRecy(userDetails: UserDetails) {

        Toast.makeText(
            context,
            "id desde render viewHolders call now to rest api  " + userDetails.id,
            Toast.LENGTH_SHORT
        ).show()

        val action: NavDirections =
            UsersFragmentDirections.actionProfileFragmentToPublishFragment(userDetails)
        findNavController().navigate(action)


        //llarmar a un rest api del url :
        //https://jsonplaceholder.typicode.com/posts?userId=1
        //  viewModel.getPublishByID(userDetails.id)


    }


    private fun initRecyclerView(userDetails: List<UserDetails>?) {

      //  val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
     //   val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
      //  recyclerViewFragment.items_recyclerview.layoutManager = linearLayoutManager
        usuariosAdapter= UsuariosAdapter(userDetails!!, this)
        recyclerViewFragment.adapter =usuariosAdapter

      //  recyclerViewFragment.items_recyclerview.addItemDecoration(decoration)

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {

        }
        return true

    }

    override fun onQueryTextChange(newText: String?): Boolean {
//toLowerCase().trim()
        if (newText!!.isNotEmpty()) {
            listFilter.clear()
            if(listUser==null){
                listUser= ArrayList<UserDetails>()
            }

            listUser!!.forEach { listuser ->
                if (listuser.name.contains(newText)) { //.toLowerCase().trim()
                    listFilter.add(listuser)
                }
            }
        } else {
            listFilter.clear()
            if(listUser==null){
                listUser= ArrayList<UserDetails>()
            }
            listFilter.addAll(listUser!!)
        }
        ListSearch(listFilter)
        return true
    }


    private fun ListSearch(listFiltro: java.util.ArrayList<UserDetails>?) {
        usuariosAdapter = UsuariosAdapter(listFiltro as java.util.ArrayList<UserDetails>, this)
        recyclerViewFragment.adapter = usuariosAdapter
        (recyclerViewFragment.items_recyclerview.adapter as UsuariosAdapter).notifyDataSetChanged()




    }

}