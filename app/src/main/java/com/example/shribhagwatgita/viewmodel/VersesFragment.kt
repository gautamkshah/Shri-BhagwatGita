package com.example.shribhagwatgita.viewmodel

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
import com.example.shribhagwatgita.R
import com.example.shribhagwatgita.databinding.FragmentVersesBinding
import com.example.shribhagwatgita.view.adapter.AdapterVerses
import kotlinx.coroutines.launch


class VersesFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentVersesBinding
    private lateinit var adapterVerses: AdapterVerses

    private var chapterNumberr= 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentVersesBinding.inflate(layoutInflater)
        onReadMoreClicked()
        getAndSetChapterDetails()
        getAllVerses()
        checkInternetConnectivity()

        return binding.root

    }


    private fun checkInternetConnectivity() {
        val networkManager= NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it) {
                getAllVerses()
                binding.shimmer.visibility = View.VISIBLE
                binding.rvVerses.visibility = View.VISIBLE
                binding.tvshowingmessage.visibility = View.GONE
            } else {
                binding.shimmer.visibility = View.GONE
                binding.rvVerses.visibility = View.GONE
                binding.tvshowingmessage.visibility = View.VISIBLE
            }
        }
    }

    private fun onReadMoreClicked() {
        var isExpanded=false
        binding.tvshowmore.setOnClickListener {
            if(!isExpanded) {
                binding.tvChapterDesc.maxLines = 50
                binding.tvshowmore.text = "Read Less"
                isExpanded = true
            }
            else{
                binding.tvChapterDesc.maxLines = 4
                binding.tvshowmore.text = "Read More"
                isExpanded = false
            }

        }
    }

    private fun getAndSetChapterDetails() {
        val bundle=arguments
        chapterNumberr=bundle?.getInt("chapterNumber")?:0
        val chapterNumber=bundle?.getInt("chapterNumber")
        val chapterName=bundle?.getString("chaptertitle")
        val chapterDesc=bundle?.getString("chapterdesc")
        val chapterVerseCount=bundle?.getInt("versesCount")
        binding.apply {
            Log.d("TAG", "getAndSetChapterDetails: $chapterNumber $chapterName $chapterDesc $chapterVerseCount")
            tvChaptertitle.text=chapterName
            tvChapterDesc.text=chapterDesc
            tvnumberofverses.text=chapterVerseCount.toString()
            tvChapterNumber.text= "Chapter : ${chapterNumber}"
        }

    }

    private fun OnVerseItemViewClicked(verse: String, verseNumber: Int){
        // on click , give read more option
        findNavController().navigate(R.id.action_versesFragment_to_verseDetailFragment,
            Bundle().apply {
                putString("verse",verse)
                putInt("verseNumber",verseNumber)
                putInt("chapterNumber",chapterNumberr)
            })


    }

    private fun getAllVerses() {
        lifecycleScope.launch {
            viewModel.getVerses(chapterNumberr).collect {
                adapterVerses= AdapterVerses(::OnVerseItemViewClicked)
                binding.rvVerses.adapter=adapterVerses
                val verseList = arrayListOf<String>()
                for(currentVerse in it) {
                    for (verses in currentVerse.translations) {
                        if (verses.language == "english") {
                            verseList.add(verses.description)
                            break
                        }
                    }
                }
                adapterVerses.differ.submitList(verseList)
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

}