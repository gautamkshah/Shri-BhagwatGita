package com.example.shribhagwatgita.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shribhagwatgita.viewmodel.NetworkManager
import com.example.shribhagwatgita.R


import com.example.shribhagwatgita.databinding.FragmentHomeBinding
import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.view.adapter.AdapterChapters
import com.example.shribhagwatgita.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterChapters: AdapterChapters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        changeStatusBarColor()
        checkInternetConnectivity()


        return binding.root
    }

    private fun checkInternetConnectivity() {
        val networkManager= NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner,{
            if(it){
                getAllChapter()
                binding.shimmer.visibility=View.VISIBLE
                binding.rvChapters.visibility=View.VISIBLE
                binding.tvShowingMessage.visibility=View.GONE
            }else{
                binding.shimmer.visibility=View.GONE
                binding.rvChapters.visibility=View.GONE
                binding.tvShowingMessage.visibility=View.VISIBLE
            }
        })
    }

    private fun getAllChapter() {
        lifecycleScope.launch {
            viewModel.getAllChapters().collect { chapterList->
                adapterChapters= AdapterChapters(::onChapterItemViewClick)
                binding.rvChapters.adapter=adapterChapters
                adapterChapters.differ.submitList(chapterList)
                binding.shimmer.visibility=View.GONE
            }
        }
    }

    private fun changeStatusBarColor() {
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        if(window!=null){
            WindowCompat.getInsetsController(window,window.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }

    private fun onChapterItemViewClick(chapterItem: ChaptersItem) {
        val bundle=Bundle()
        Log.d("TAGI", "onChapterItemViewClick: ${chapterItem.chapter_summary}")
        bundle.putInt("chapterNumber", chapterItem.chapter_number)
        bundle.putString("chaptertitle", chapterItem.name_translated)
        bundle.putString("chapterdesc", chapterItem.chapter_summary)
        bundle.putInt("versesCount", chapterItem.verses_count)

        findNavController().navigate(R.id.action_homeFragment_to_versesFragment,bundle)
    }



}


