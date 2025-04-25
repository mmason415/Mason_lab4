package com.example.mmason_lab4

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mmason_lab4.ui.theme.AppTheme
import androidx.compose.foundation.layout.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDirectoryApp() {
    val viewModel: UserViewModel = viewModel()
    AppTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "User Directory",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                )
            }
        ) { innerPadding ->
            UserListScreen(viewModel = viewModel, padding = innerPadding)
        }
    }
}
