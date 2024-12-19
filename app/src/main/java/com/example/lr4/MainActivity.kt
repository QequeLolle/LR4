package com.example.lr4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.lr4.ui.theme.LR4Theme
import com.example.lr4.domain.Player
import com.example.lr4.presentation.MainViewModel
import com.example.lr4.presentation.MainScreen

class MainActivity : ComponentActivity() {
//    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()
    private val mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel = mainViewModel)
        }
    }
}

