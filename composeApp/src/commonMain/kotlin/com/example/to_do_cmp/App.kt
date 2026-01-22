package com.example.to_do_cmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.to_do_cmp.navigation.NavGraph
import com.example.to_do_cmp.util.theme.darkScheme
import com.example.to_do_cmp.util.theme.lightScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme

    MaterialTheme(colorScheme) {
        NavGraph()
    }
}