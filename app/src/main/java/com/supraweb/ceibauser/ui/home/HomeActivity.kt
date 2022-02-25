package com.supraweb.ceibauser.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.supraweb.ceibauser.R
import com.supraweb.ceibauser.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var toolbarHome: Toolbar? = null
 //   private var navigationView: NavigationView? = null
  //  private var drawerLayout:    DrawerLayout? = null
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
      //  toolbarHome =            binding.idToolbar
        setContentView(binding.root)
     //   toolbarHome = findViewById(R.id.id_toolbar)
        setSupportActionBar(binding.idToolbar)
      //  toolbarHome =  binding.toolbar           //
     //  navigationView =  binding.navView      //findViewById(R.id.nav_view)
     //   drawerLayout =    binding.drawerLayoutSebas // findViewById(R.id.drawer_layout)
     //  setSupportActionBar(id_ )

        val navController = Navigation.findNavController(this,R.id.id_fragmentNavigation)           //this,R.id.fragmentNavigation
        NavigationUI.setupWithNavController(binding.navView!!  ,   navController                       )
        NavigationUI.setupActionBarWithNavController(this,navController ,binding.drawerLayoutSebas        )
    }


    override fun onSupportNavigateUp(): Boolean {
        return  NavigationUI.navigateUp(
            Navigation.findNavController(this,R.id.id_fragmentNavigation),
            binding.drawerLayoutSebas

        )
    }



}