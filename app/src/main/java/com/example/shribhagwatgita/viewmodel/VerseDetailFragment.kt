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
import com.example.shribhagwatgita.R
import com.example.shribhagwatgita.databinding.FragmentVerseDetailBinding
import com.example.shribhagwatgita.models.Commentary
import com.example.shribhagwatgita.models.Translation
import kotlinx.coroutines.launch


class VerseDetailFragment : Fragment() {
    private lateinit var binding: FragmentVerseDetailBinding
    private val viewModel: MainViewModel by viewModels()

    private var chapterNum = 0
    private var verseNum = 0
    private var verseText = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVerseDetailBinding.inflate(layoutInflater)

        changeStatusBarColor()
        onReadMoreClicked()
        getandSetVerseDetails()
        getVerseDetail()
        return binding.root
    }



    private fun getVerseDetail() {

        lifecycleScope.launch {

            viewModel.getParticularVerse(chapterNum, verseNum).collect {
                Log.d("jdj ", "getVerseDetail: ${it.commentaries[0].author_name}")
                binding.tvVersetext.text = it.text
                //  binding.tvTransliterationIfEnglish.text=it.translations[0].toString()

                val englishTranslation = arrayListOf<Translation>()

                for (i in it.translations) {
                    if (i.language == "english")
                        englishTranslation.add(i)
                }

                val englistTranslationsize = englishTranslation.size

                if (englishTranslation.isNotEmpty()) {
                    binding.tvAuthorName.text = englishTranslation[0].author_name
                    binding.tvText.text = englishTranslation[0].description
                    if (englistTranslationsize == 1) {
                        binding.fabtransright.visibility = View.GONE
                    }
                    var i = 0
                    binding.fabtransright.setOnClickListener { view ->

                        if (i < englistTranslationsize - 1) {
                            i++
                            binding.tvAuthorName.text = englishTranslation[i].author_name
                            binding.tvText.text = englishTranslation[i].description
                            binding.fabtransleft.visibility = View.VISIBLE

                            if (i == englistTranslationsize - 1) {
                                binding.fabtransright.visibility = View.GONE
                            }
                        } else {
                            i = englistTranslationsize - 1
                        }
                    }


                    binding.fabtransleft.setOnClickListener { view ->

                        if (i > 0) {
                            i--
                            binding.tvAuthorName.text = englishTranslation[i].author_name
                            binding.tvText.text = englishTranslation[i].description
                            binding.fabtransright.visibility = View.VISIBLE
                            if (i == 0) {
                                binding.fabtransleft.visibility = View.GONE
                            }
                        }
                    }

                }
                val englishCommentries = arrayListOf<Commentary>()
                for (i in it.commentaries) {
                    if (i.language == "hindi")
                        englishCommentries.add(i)
                }
                val englishCommentriesSize = englishCommentries.size

                if (englishCommentries.isNotEmpty()) {
                    binding.tvAuthorNameCommentries.text = englishCommentries[0].author_name
                    binding.tvTextCommentries.text = englishCommentries[0].description
                    if (englishCommentriesSize == 1) {
                        binding.fabright.visibility = View.GONE
                    }
                    var i = 0
                    binding.fabright.setOnClickListener { view ->

                        if (i < englishCommentriesSize - 1) {
                            i++
                            binding.tvAuthorNameCommentries.text = englishCommentries[i].author_name
                            binding.tvTextCommentries.text = englishCommentries[i].description
                            binding.fableft.visibility = View.VISIBLE

                            if (i == englishCommentriesSize - 1) {
                                binding.fabright.visibility = View.GONE
                            }
                        }
                    }
                    binding.fableft.setOnClickListener {
                        if (i > 0) {
                            i--
                            binding.tvAuthorNameCommentries.text = englishCommentries[i].author_name
                            binding.tvTextCommentries.text = englishCommentries[i].description
                            binding.fabright.visibility = View.VISIBLE
                            if (i == 0) {
                                binding.fableft.visibility = View.GONE

                            }
                        }
                    }
                }
            }
        }
    }

    private fun onReadMoreClicked() {
        var isExpanded=false
        binding.tvseemore.setOnClickListener {
            if(!isExpanded) {
                binding.tvCommentries.maxLines = 100
                binding.tvseemore.text = "Read Less"
                isExpanded = true
            }
            else{
                binding.tvCommentries.maxLines = 4
                binding.tvseemore.text = "Read More"
                isExpanded = false
            }

        }
    }

    private fun getandSetVerseDetails() {
        val bundle = arguments
        chapterNum = bundle?.getInt("chapterNumber")!!
        verseNum = bundle.getInt("verseNumber")
        verseText = bundle.getString("verse")!!

        binding.tvVerseNumber.text = "|| $chapterNum.$verseNum ||"


    }

    private fun changeStatusBarColor() {
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        if (window != null) {
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }


}