package com.example.shribhagwatgita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shribhagwatgita.databinding.FragmentSavedChaptersBinding
import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.view.adapter.AdapterChapters
import com.example.shribhagwatgita.viewmodel.MainViewModel


class SavedChaptersFragment : Fragment() {
    private lateinit var binding: FragmentSavedChaptersBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterSavedChapters: AdapterChapters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSavedChaptersBinding.inflate(layoutInflater)
        getSavedChapters()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun onItemClicked(chapterItem: ChaptersItem){
        val bundle = Bundle()
        bundle.putInt("chapterNumber",chapterItem.chapter_number)
        bundle.putBoolean("showRoom",true)
        findNavController().navigate(R.id.action_savedChaptersFragment_to_versesFragment,bundle)

    }

    fun onFavoriteClick(chapterItem: ChaptersItem){

    }

    private fun getSavedChapters() {
        viewModel.getSavedChapters().observe(viewLifecycleOwner){
            val chapterList= arrayListOf<ChaptersItem>()
            for(i in it){
                chapterList.add(ChaptersItem(

                    i.chapter_number,
                    i.chapter_summary,
                    i.id,
                    i.name,
                    i.name_meaning,
                    i.name_translated,
                    i.name_transliterated,
                    i.verses_count,))



            }
            if(chapterList.isEmpty()){
                binding.shimmer.visibility = View.GONE
                binding.rvChapters.visibility = View.GONE
            }
            adapterSavedChapters= AdapterChapters(
                ::onItemClicked,
                ::onFavoriteClick,
                false,::onFavoriteClick
            )
            binding.rvChapters.adapter = adapterSavedChapters
            adapterSavedChapters.differ.submitList(chapterList)
            binding.shimmer.visibility = View.GONE
            binding.rvChapters.visibility = View.VISIBLE

        }


    }


}