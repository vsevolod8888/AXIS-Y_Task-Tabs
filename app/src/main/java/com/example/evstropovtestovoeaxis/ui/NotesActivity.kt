package com.example.evstropovtestovoeaxis.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.evstropovtestovoeaxis.App
import com.example.evstropovtestovoeaxis.databinding.ActivityUserBinding
import com.example.evstropovtestovoeaxis.ui.fragments.NotesFragmentViewModel
import com.example.evstropovtestovoeaxis.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import javax.inject.Inject

class NotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    val array = arrayOf(
        "User Info",
        "Notes"
    )
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val notesFragmentViewModel: NotesFragmentViewModel by viewModels { viewModelProvider }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).getappComponent().inject(this)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = array[position]
        }.attach()


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personPhoto: Uri? = acct.photoUrl


            notesFragmentViewModel.saveUserInfo(personName, personEmail, personPhoto)
            Log.d("ooo", personEmail)
        }
    }
}