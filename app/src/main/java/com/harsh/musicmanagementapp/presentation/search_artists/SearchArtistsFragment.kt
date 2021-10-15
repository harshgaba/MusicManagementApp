package com.harsh.musicmanagementapp.presentation.search_artists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.databinding.ArtistItemViewBinding
import com.harsh.musicmanagementapp.databinding.FragmentSearchArtistsBinding
import com.harsh.musicmanagementapp.domain.model.Artist
import com.harsh.musicmanagementapp.presentation.ui.recyclerview.RecyclerViewViewHolder
import com.harsh.musicmanagementapp.shared.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchArtistsFragment : Fragment(R.layout.fragment_search_artists) {
    private val viewModel: SearchArtistsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchArtistsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.artistRecyclerview.apply {
            addItemBindings(object : RecyclerViewViewHolder<ArtistItemViewBinding, Artist> {
                override fun inflate(parent: ViewGroup): ArtistItemViewBinding =
                    ArtistItemViewBinding.inflate(layoutInflater, parent, false)

                override fun bind(binder: ArtistItemViewBinding, item: Artist) {
                    with(binder) {
                        artist = item
                        onClickItem = View.OnClickListener {
                            navigateTopAlbums(item.name)
                        }
                    }

                    binding.executePendingBindings()
                }


            })
        }
        binding.onSearchClick = View.OnClickListener {
            viewModel.searchArtists(binding.searchBar.editTextSearch.text.toString())
        }

        if(binding.searchBar.editTextSearch.requestFocus()) {
            (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
        return binding.root
    }

    private fun navigateTopAlbums(artist: String?) {
        findNavController().navigate(
            R.id.action_searchArtistsFragment_to_topAlbumsListFragment,
            Bundle().apply {
                putString(Constants.PARAM_ARTIST_ID, artist)
            })
    }
}