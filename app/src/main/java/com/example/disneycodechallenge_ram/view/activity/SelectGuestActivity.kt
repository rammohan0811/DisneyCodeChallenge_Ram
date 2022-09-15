package com.example.disneycodechallenge_ram.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.disneycodechallenge_ram.R
import com.example.disneycodechallenge_ram.view.fragment.SelectGuestFragment

class SelectGuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_guest)
        toolbarSetup()

        addSupportFragment()
    }

    private fun addSupportFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.contentFrame, SelectGuestFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = item.itemId
        if (i == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toolbarSetup() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_manage_guest)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.title = getString(R.string.select_guests)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        }
    }
}