package com.ali.grandtask.presentation.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ali.grandtask.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val redditItem = DetailFragmentArgs.fromBundle(requireArguments()!!).selectedReddit
        //set toolbar title
        (activity as AppCompatActivity).supportActionBar?.title =redditItem.data?.title

        //bind data
        binding.item = redditItem

        return binding.root
    }

}
