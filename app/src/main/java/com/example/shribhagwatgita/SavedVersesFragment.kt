package com.example.shribhagwatgita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shribhagwatgita.databinding.FragmentSavedVersesBinding
import com.example.shribhagwatgita.view.adapter.AdapterVerses
import com.example.shribhagwatgita.viewmodel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SavedVersesFragment : Fragment() {

    private lateinit var binding: FragmentSavedVersesBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterVerses: AdapterVerses



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSavedVersesBinding.inflate(layoutInflater)

        getVerseFromRoom()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getVerseFromRoom() {
        viewModel.getSavedEnglishVerses().observe(viewLifecycleOwner) {
            val verseList= arrayListOf<String>()
            for(savedVerse in it){
                verseList.add(savedVerse.translations[0].description)
            }
            if(verseList.isEmpty()) {
                binding.tvshowingmessage.visibility = View.VISIBLE
            }
            adapterVerses= AdapterVerses(::onVerseClick,true)
            binding.rvVerses.adapter=adapterVerses
            adapterVerses.differ.submitList(verseList)
            binding.shimmer.visibility=View.GONE
        }
    }

    fun onVerseClick(verse:String, verseNumber: Int){
        val bundle = Bundle()
        bundle.putBoolean("showRoom",true)
     //   findNavController().navigate(R.id.action_savedVersesFragment_to_verseDetailFragment,bundle)

    }

}