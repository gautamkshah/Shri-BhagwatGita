package com.example.shribhagwatgita.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shribhagwatgita.databinding.ItemViewVersesBinding

class AdapterVerses(val OnVerseItemViewClicked: (String, Int) -> Unit) : RecyclerView.Adapter<AdapterVerses.VersesViewHolder>() {
    class VersesViewHolder (val binding: ItemViewVersesBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtil= object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,diffUtil)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersesViewHolder {
        return VersesViewHolder(ItemViewVersesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: VersesViewHolder, position: Int) {
        val verse= differ.currentList[position]
        holder.binding.apply {
            tvVersenumber.text= "Verse : ${position+1}"
            tvVerse.text=verse
            ll.setOnClickListener {
                OnVerseItemViewClicked(verse,position+1)
            }
        }
    }
}