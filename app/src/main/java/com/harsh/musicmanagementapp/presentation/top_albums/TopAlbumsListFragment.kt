package com.harsh.musicmanagementapp.presentation.top_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.databinding.AlbumItemViewBinding
import com.harsh.musicmanagementapp.databinding.ArtistItemViewBinding
import com.harsh.musicmanagementapp.databinding.FragmentSearchArtistsBinding
import com.harsh.musicmanagementapp.databinding.FragmentTopAlbumsListBinding
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.domain.model.Artist
import com.harsh.musicmanagementapp.presentation.ui.recyclerview.RecyclerViewViewHolder
import com.harsh.musicmanagementapp.shared.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumsListFragment : Fragment(R.layout.fragment_top_albums_list) {
    private val viewModel: TopAlbumsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopAlbumsListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.albumRecyclerview.apply {
            addItemBindings(object : RecyclerViewViewHolder<AlbumItemViewBinding, Album> {
                override fun inflate(parent: ViewGroup): AlbumItemViewBinding =
                    AlbumItemViewBinding.inflate(layoutInflater, parent, false)

                override fun bind(binder: AlbumItemViewBinding, item: Album) {
                    with(binder) {
                        album = item
                        onClickItem = View.OnClickListener {
                            navigateToAlbumDetail(item.id)
                        }
                    }

                    binding.executePendingBindings()
                }


            })
        }
        return binding.root
    }

    private fun navigateToAlbumDetail(albumId: String?) {
        findNavController().navigate(
            R.id.action_topAlbumsListFragment_to_albumDetailsFragment,
            Bundle().apply {
                putString(Constants.PARAM_ALBUM_ID, albumId)
            })
    }
}