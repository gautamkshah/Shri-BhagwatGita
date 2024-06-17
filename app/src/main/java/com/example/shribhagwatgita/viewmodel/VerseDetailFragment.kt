package com.example.shribhagwatgita.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shribhagwatgita.R
import com.example.shribhagwatgita.databinding.FragmentVerseDetailBinding
import com.example.shribhagwatgita.models.Translation
import kotlinx.coroutines.launch


class VerseDetailFragment : Fragment() {
    private lateinit var binding: FragmentVerseDetailBinding
    private val viewModel: MainViewModel by viewModels()

    private var chapterNum=0
    private var verseNum=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentVerseDetailBinding.inflate(layoutInflater)

       changeStatusBarColor()
        getandSetVerseDetails()
        getVerseDetail()
        checkInternetConnectivity()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun checkInternetConnectivity() {
        val networkManager= NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it) {
                getVerseDetail()

                binding.tvVerseNumber.visibility = View.VISIBLE
                binding.tvVersetext.visibility = View.VISIBLE
                binding.tvTransliterationIfEnglish.visibility = View.VISIBLE
            } else {

                binding.tvVerseNumber.visibility = View.GONE
                binding.tvVersetext.visibility = View.GONE
                binding.tvTransliterationIfEnglish.visibility = View.GONE
            }
        }
    }

    private fun getVerseDetail() {
        lifecycleScope.launch {
            viewModel.getParticularVerse(chapterNum,verseNum).collect {
                binding.tvVerseNumber.text= "|| $chapterNum.$verseNum ||"
                binding.tvVersetext.text=it.text
                binding.tvTransliterationIfEnglish.text=it.translations[0].toString()

                val englishTranslation= arrayListOf<Translation>()

                for (i in it.translations){
                    if(i.language=="english")
                        englishTranslation.add(i)
                }
                val englistTranslationsize=englishTranslation.size
                if(englistTranslationsize>0){
                    binding.tvAuthor.text=englishTranslation[0].author_name
                    binding.tvText.text=englishTranslation[0].description
                    if(englistTranslationsize==1){
                        binding.fabtransright.visibility=View.GONE
                    }
                    var i=0
                    binding.fabtransright.setOnClickListener { view->
                        i++
                        if(i<englistTranslationsize){
                            binding.tvAuthor.text=englishTranslation[i].author_name
                            binding.tvText.text=englishTranslation[i].description
                            binding.fabtransleft.visibility=View.VISIBLE

                            if(i==englistTranslationsize-1){
                                binding.fabtransright.visibility=View.GONE
                            }
                        }
                        else{
                            i=englistTranslationsize-1
                        }
                    }


                    binding.fabtransleft.setOnClickListener { view->
                        i
                        if(i<englistTranslationsize){
                            binding.tvAuthor.text=englishTranslation[i].author_name
                            binding.tvText.text=englishTranslation[i].description
                            binding.fabtransright.visibility=View.VISIBLE
                            if(i==0){
                                binding.fabtransleft.visibility=View.GONE
                            }
                        }
                        else{
                            i=0
                        }
                }

            }
        }}
    }

    private fun getandSetVerseDetails() {
        val bundle= arguments
        chapterNum=bundle?.getInt("chapterNum")?:0
        verseNum=bundle?.getInt("verseNum")?:0
        binding.tvVerseNumber.text= "|| $chapterNum.$verseNum ||"
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