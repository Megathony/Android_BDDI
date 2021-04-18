package com.anthony.neighbors.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anthony.neighbors.NavigationListener
import com.anthony.neighbors.R
import com.anthony.neighbors.di.DI
import com.anthony.neighbors.ui.fragments.AddNeighbourFragment
import com.anthony.neighbors.ui.fragments.ListNeighborsFragment

class NeighborsActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)

        setContentView(R.layout.activity_neighbors)
        showFragment(ListNeighborsFragment())

        val button: Button = findViewById(R.id.click_me)
        button.setOnClickListener {
            // val intent = Intent(baseContext, MySecondActivity::class.java)
            // startActivity(intent)

            // var url = Uri.parse ( "www.youtube.com/user/Megathoy07")
            // val i = Intent(action = Intent.ACTION_VIEW, url)

            // var url = Uri.parse("tel:0622008554")
            // var i = Intent(Intent.ACTION_DIAL, url)
            // startActivity(i)
            showFragment(AddNeighbourFragment())
        }
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        // TODO("Not yet implemented")
    }
}
