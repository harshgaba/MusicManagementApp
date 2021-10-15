package com.harsh.musicmanagementapp.presentation.saved_top_albums

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.shared.Constants.PARAM_ALBUM_ID

class SavedTopAlbumsFragment : Fragment(R.layout.fragment_saved_top_albums) {
    private val viewModel: SavedTopAlbumsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigateToAlbumDetail(albumId: String) {
        findNavController().navigate(
            R.id.action_savedTopAlbumsFragment_to_albumDetailsFragment,
            Bundle().apply {
                putString(PARAM_ALBUM_ID, albumId)
            })
    }

    private fun navigateToSearchArtists() {
        findNavController().navigate(
            R.id.action_savedTopAlbumsFragment_to_searchArtistsFragment)
    }

}