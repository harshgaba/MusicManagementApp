package com.harsh.musicmanagementapp.presentation.saved_top_albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.databinding.AlbumItemViewBinding
import com.harsh.musicmanagementapp.databinding.FragmentSavedTopAlbumsBinding
import com.harsh.musicmanagementapp.domain.model.Album
import com.harsh.musicmanagementapp.presentation.ui.recyclerview.RecyclerViewViewHolder
import com.harsh.musicmanagementapp.shared.Constants.PARAM_ALBUM_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedTopAlbumsFragment : Fragment(R.layout.fragment_saved_top_albums) {
    private val viewModel: SavedTopAlbumsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSavedTopAlbumsBinding.inflate(inflater)
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
        binding.onSearchBarClick = View.OnClickListener {
            navigateToSearchArtists()
        }
        return binding.root
    }

    private fun navigateToAlbumDetail(albumId: String?) {
        findNavController().navigate(
            R.id.action_savedTopAlbumsFragment_to_albumDetailsFragment,
            Bundle().apply {
                putString(PARAM_ALBUM_ID, albumId)
            })
    }

    private fun navigateToSearchArtists() {
        findNavController().navigate(
            R.id.action_savedTopAlbumsFragment_to_searchArtistsFragment
        )
    }

}