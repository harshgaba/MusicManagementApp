package com.harsh.musicmanagementapp.presentation.top_albums

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.harsh.musicmanagementapp.R
import com.harsh.musicmanagementapp.shared.Constants

class TopAlbumsListFragment : Fragment(R.layout.fragment_top_albums_list) {
    private val viewModel: TopAlbumsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigateToAlbumDetail(albumId: String) {
        findNavController().navigate(
            R.id.action_topAlbumsListFragment_to_albumDetailsFragment,
            Bundle().apply {
                putString(Constants.PARAM_ALBUM_ID, albumId)
            })
    }
}