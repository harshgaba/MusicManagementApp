package com.harsh.musicmanagementapp.presentation.search_artists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.presentation.saved_top_albums.SavedTopAlbumsViewModel
import com.harsh.musicmanagementapp.shared.Constants

class SearchArtistsFragment : Fragment(R.layout.fragment_search_artists){
    private val viewModel: SearchArtistsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigateTopAlbums(artist: String) {
        findNavController().navigate(
            R.id.action_searchArtistsFragment_to_topAlbumsListFragment,
            Bundle().apply {
                putString(Constants.PARAM_ARTIST_ID, artist)
            })
    }
}