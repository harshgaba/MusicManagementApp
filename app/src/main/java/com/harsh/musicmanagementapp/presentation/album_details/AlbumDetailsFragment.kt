package com.harsh.musicmanagementapp.presentation.album_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.databinding.FragmentAlbumDetailsBinding
import com.harsh.musicmanagementapp.databinding.TrackItemViewBinding
import com.harsh.musicmanagementapp.domain.model.Track
import com.harsh.musicmanagementapp.presentation.ui.recyclerview.RecyclerViewViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {

    private val viewModel: AlbumDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlbumDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.trackRecyclerview.apply {
            addItemBindings(object : RecyclerViewViewHolder<TrackItemViewBinding, Track> {
                override fun inflate(parent: ViewGroup): TrackItemViewBinding =
                    TrackItemViewBinding.inflate(layoutInflater, parent, false)

                override fun bind(binder: TrackItemViewBinding, item: Track) {
                    with(binder) {
                        track = item
                    }

                    binding.executePendingBindings()
                }
            })
        }
        binding.onSaveClick = View.OnClickListener {
            viewModel.manageAlbumLove()
        }

        return binding.root
    }
}