package com.fero.submission1jetpack.ui.home.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fero.submission1jetpack.databinding.FragmentTvShowBinding
import com.fero.submission1jetpack.viewmodel.ViewModelFactory


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val listTvShows = viewModel.getTvShow()

            val tvShowsAdapter = TvShowAdapter()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, { listTvShows ->
                binding.progressBar.visibility = View.GONE
                listTvShows.data?.let { tvShowsAdapter.setTvShows(it) }
                tvShowsAdapter.notifyDataSetChanged()
            })
            binding.rvTvShows.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }
}