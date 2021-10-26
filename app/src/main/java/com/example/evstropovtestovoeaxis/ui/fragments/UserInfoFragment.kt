package com.example.evstropovtestovoeaxis.ui.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.evstropovtestovoeaxis.databinding.FragmentUserInfoBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.evstropovtestovoeaxis.App
import com.example.evstropovtestovoeaxis.ui.SignInActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.android.gms.tasks.OnCompleteListener
import javax.inject.Inject

class UserInfoFragment:Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val notesFragmentViewModel: NotesFragmentViewModel by activityViewModels { viewModelProvider }
    val mGoogleSignInClient:GoogleSignInClient by lazy{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(requireActivity(), gso)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as App).getappComponent().inject(this)
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val userEnter = notesFragmentViewModel.user.value
        downloadInfo(userEnter)
        binding.buttonExit.setOnClickListener {
            signOut()
        }
        return view
    }
    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(requireActivity(), OnCompleteListener<Void?> {

                val intent = Intent(requireActivity(), SignInActivity::class.java)
                requireActivity().finish()
                startActivity(intent)
            })
    }
    fun downloadInfo(user: UserInfo?){
        val imgUrl = user?.photo
        imgUrl?.let {
            Glide.with(binding.imageViewAvatar.context)
                .load(it)
                .into(binding.imageViewAvatar)
        }
        val name = user?.name
        name?.let {
            binding.textViewName.text = it
        }
        val email = user?.email
        email?.let {
            binding.textViewEmail.text = it
        }
    }
}