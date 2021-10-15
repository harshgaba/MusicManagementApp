package com.harsh.musicmanagementapp.presentation.album_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.harsh.musicmanagementapp.R

class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details){

    private val viewModel: AlbumDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}