package com.example.shribhagwatgita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shribhagwatgita.databinding.FragmentSettingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater)

        binding.llsavedChapters.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_savedChaptersFragment)

        }
        binding.llsavedVerses.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_savedVersesFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}