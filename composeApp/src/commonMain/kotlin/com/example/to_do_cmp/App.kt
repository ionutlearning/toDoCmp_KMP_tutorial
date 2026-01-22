package com.example.to_do_cmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.to_do_cmp.navigation.NavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavGraph()
    }
}