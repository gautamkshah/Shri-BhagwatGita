package com.example.shribhagwatgita.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shribhagwatgita.databinding.ItemViewChaptersBinding
import com.example.shribhagwatgita.models.ChaptersItem
import kotlin.reflect.KFunction1

class AdapterChapters(
    val onChapterItemViewClick: (ChaptersItem) -> Unit,
    val onFavoriteClick: (ChaptersItem) -> Unit,
    val show: Boolean,
    val onFavoriteFilledclicked: KFunction1<ChaptersItem, Unit>
) :RecyclerView.Adapter<AdapterChapters.ChaptersViewHolder>(){
    class ChaptersViewHolder (val binding: ItemViewChaptersBinding):RecyclerView.ViewHolder(binding.root){

    }

    val diffUtil= object: DiffUtil.ItemCallback<ChaptersItem>(){
        override fun areItemsTheSame(oldItem: ChaptersItem, newItem: ChaptersItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ChaptersItem, newItem: ChaptersItem): Boolean {
            return oldItem.id==newItem.id
        }

    }
    val differ= AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersViewHolder {
              return ChaptersViewHolder(ItemViewChaptersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount():Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ChaptersViewHolder, position: Int) {
        val chapter= differ.currentList[position]
        holder.binding.apply {
            tvChaptername.text=chapter.name_translated
            tvChapterdesc.text=chapter.chapter_summary
            tvVerseNumber.text=chapter.verses_count.toString()
            tvChapternumber.text= "Chapter : ${chapter.chapter_number}"


        }
        if(!show){
            holder.binding.ivFavorite.visibility=android.view.View.GONE

        }
        else{
            holder.binding.ivFavorite.visibility=android.view.View.VISIBLE

        }

        holder.binding.ll.setOnClickListener {
            onChapterItemViewClick(chapter)

        }

        holder.binding.apply {
            ivFavorite.setOnClickListener {
                ivFavoriteFilled.visibility=android.view.View.VISIBLE
                ivFavorite.visibility=android.view.View.GONE
                onFavoriteClick(chapter)
            }
            ivFavoriteFilled.setOnClickListener {
                ivFavoriteFilled.visibility=android.view.View.GONE
                ivFavorite.visibility=android.view.View.VISIBLE
                onFavoriteFilledclicked(chapter)
            }
        }
    }

}