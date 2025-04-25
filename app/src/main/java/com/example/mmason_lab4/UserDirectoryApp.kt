package com.example.mmason_lab4

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mmason_lab4.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDirectoryApp() {
    val viewModel: UserViewModel = viewModel()
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("User Directory") })
            }
        ) {
            UserListScreen(viewModel = viewModel, padding = it)
        }
    }
}
