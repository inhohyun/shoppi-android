package com.shoppi.shoppi.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null // theme의 컬러를 참조하던걸 초기화(안씀)
        //find값이 없으면 null값을 반환하므로 ?와 같이 사용
        val navContriller = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navContriller?.let {
            bottomNavigationView.setupWithNavController(it)
        }
    }


}