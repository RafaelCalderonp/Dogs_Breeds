package com.example.dogs_breeds

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogs_breeds.databinding.DogsImageBinding
import com.example.dogs_breeds.model.local_breeds.DogsImageEntity
import com.example.dogs_breeds.model.remote_breeds.DogsImage

class DogsImageAdapter: RecyclerView.Adapter<DogsImageAdapter.ImageDogsVH>() {

    private var lisDogsImage = listOf<DogsImageEntity>()


     fun update (list: List<DogsImageEntity>){
         lisDogsImage = list
         notifyDataSetChanged()
     }

    inner class ImageDogsVH(private val mBinding:DogsImageBinding):
        RecyclerView.ViewHolder(mBinding.root){
        fun bind(dogsImage: DogsImageEntity){
            Log.d("REPOS", "11")
            Glide.with(mBinding.ivDogsImage).load(dogsImage.urlImage).into(mBinding.ivDogsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDogsVH {
        return ImageDogsVH(DogsImageBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageDogsVH, position: Int) {
        val dogsImage = lisDogsImage[position]
        holder.bind(dogsImage)
    }

    override fun getItemCount(): Int = lisDogsImage.size


}