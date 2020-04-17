package com.example.yhaa41.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.yhaa41.util.GetAndStoreData
import com.example.yhaa41.R

class MainActivity : AppCompatActivity() {
     private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pref= GetAndStoreData(this)
        pref.saveShowPosition(true)

       /* navController=Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)*/
    }

   /* override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }*/
}
