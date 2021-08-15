package com.fero.submission1jetpack.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fero.submission1jetpack.databinding.FragmentFavoriteTvShowBinding
import com.fero.submission1jetpack.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupMV()

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            val adapter = FavoriteTvShowAdapter()


            binding.rvTvShows.adapter = adapter
            binding.rvTvShows.layoutManager = GridLayoutManager(context, 2)
            binding.rvTvShows.setHasFixedSize(true)

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner,{
                binding.progressBar.visibility = View.GONE
                if (it != null){
                    adapter.submitList(it)
                }
            })

        }
    }

//    private fun setupMV() {
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
//        val adapter = FavoriteTvShowAdapter()
//        viewModel.getFavoriteTvShows()
//
//        viewModel.apply {
//            livePagedTvShowEntity.observe(viewLifecycleOwner, {
//                adapter.submitList(it)
//                Log.d("Total List", "${it.size}")
//                binding.rvMovies.adapter = adapter
//                binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
//                binding.rvMovies.setHasFixedSize(true)
//            })
//
//            onProgressView.observe(viewLifecycleOwner, {
//                binding.progressBar.apply {
//                    visibility = if (it) {
//                        View.VISIBLE
//                    } else {
//                        View.GONE
//                    }
//                }
//            })
//        }
//
//    }


}