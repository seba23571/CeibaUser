package com.supraweb.ceibauser.ui.auth


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.supraweb.ceibauser.data.network.response.UserDetails
import com.supraweb.ceibauser.databinding.ActivityStartBinding
import com.supraweb.ceibauser.ui.home.HomeActivity
import com.supraweb.ceibauser.util.hide
import com.supraweb.ceibauser.util.show
import com.supraweb.ceibauser.util.snackbar
import com.supraweb.ceibauser.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StartActivity : AppCompatActivity() ,KodeinAware {
    override val kodein by kodein()
  //  private val factory : AuthViewModelFactory by instance()


    private lateinit var binding: ActivityStartBinding

    // AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        binding = ActivityStartBinding.inflate(layoutInflater)




        setContentView(binding.root)

         toStartHome()


    }

    private fun toStartHome() {


        Intent (this,HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }

    }










}