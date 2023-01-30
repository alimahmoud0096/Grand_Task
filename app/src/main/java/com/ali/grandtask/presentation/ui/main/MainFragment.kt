package com.ali.grandtask.presentation.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ali.grandtask.databinding.FragmentMainBinding
import com.ali.grandtask.presentation.ui.main.adapter.RedditItemClickListener

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, MainViewModel.Factory(activity.application, redditItemCallBack)).get(
            MainViewModel::class.java
        )
    }

    private val redditItemCallBack = RedditItemClickListener {
        findNavController().apply {
            navigate(MainFragmentDirections.actionShowDetail(it))
        }
    }

    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.invalidateAll()


        return binding.root
    }

}
