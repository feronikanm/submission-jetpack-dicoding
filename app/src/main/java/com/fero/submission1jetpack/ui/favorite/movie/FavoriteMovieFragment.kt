package com.fero.submission1jetpack.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fero.submission1jetpack.databinding.FragmentFavoriteMovieBinding
import com.fero.submission1jetpack.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupMV()
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            val adapter = FavoriteMovieAdapter()


            binding.rvMovies.adapter = adapter
            binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
            binding.rvMovies.setHasFixedSize(true)

            viewModel.getFavoriteMovie().observe(viewLifecycleOwner,{
                binding.progressBar.visibility = View.GONE
                if (it != null){
                    adapter.submitList(it)
                }
            })

        }

    }

//    private fun setupMV() {
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
//        val adapter = FavoriteMovieAdapter()
//        viewModel.getFavoriteMovies()
//
//        viewModel.apply {
//            livePagedMovieEntity.observe(viewLifecycleOwner, {
//                adapter.submitList(it)
//                binding.rvMovies.adapter = adapter
//                binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
//                binding.rvMovies.setHasFixedSize(true)
//            })
//
//            onProgressView.observe(viewLifecycleOwner, {
//                binding.progressBar.apply {
//                    if (it) {
//                        visibility = View.VISIBLE
//                    } else {
//                        visibility = View.GONE
//                    }
//                }
//            })
//        }
//
//    }

}