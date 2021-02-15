package com.example.dogs_breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs_breeds.databinding.BreedsListBinding
import com.example.dogs_breeds.model.local_breeds.BreedsEntity

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.BreedsVH>(){
    private var listBreeds = listOf<BreedsEntity>()

    private  val selectedDogsItem = MutableLiveData<BreedsEntity>()
    fun selectedItem(): LiveData<BreedsEntity> = selectedDogsItem


    fun update(list: List<BreedsEntity>){
        listBreeds = list
        notifyDataSetChanged()
    }

    inner class BreedsVH(private val mBinding: BreedsListBinding ):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {
        fun bind(breed: BreedsEntity){
            mBinding.tvBreed.text = breed.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedDogsItem.value = listBreeds[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsVH {
        return BreedsVH((BreedsListBinding.inflate(LayoutInflater.from(parent.context))))
    }

    override fun onBindViewHolder(holder: BreedsVH, position: Int) {
        val breed = listBreeds[position]
        holder.bind(breed)
    }

    override fun getItemCount(): Int = listBreeds.size


}