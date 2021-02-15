package com.example.dogs_breeds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogs_breeds.databinding.FragmentSecondBinding
import com.example.dogs_breeds.viewModel.DogsImageViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var mBinding: FragmentSecondBinding
    private val viewModel : DogsImageViewModel by activityViewModels()
    private var idBreeds = "-1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("REPOS", "-2")
        arguments?.let {
            Log.d("REPOS", "-1")
            idBreeds=it.getString("id", "-1")
            Log.d("REPOS", "0 $idBreeds")
            Toast.makeText(context,idBreeds.toString(),Toast.LENGTH_LONG ).show()
            viewModel.getDogsById(idBreeds)
            Log.d("REPOS", "1")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        Log.d("REPOS", "2")
        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("REPOS", "3")
        val adapter = DogsImageAdapter()
        mBinding.rvDogsImage.adapter = adapter

        mBinding.rvDogsImage.layoutManager = GridLayoutManager(context, 2)
        Log.d("REPOS", "4")
        viewModel.getImages().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("REPOS", "5")
                adapter.update(it)
                Log.d("REPOS", "6")
            }
        })


        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}