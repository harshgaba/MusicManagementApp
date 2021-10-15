package com.harsh.musicmanagementapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harsh.musicmanagementapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
    }
}