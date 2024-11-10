package com.borjadelgadodev.freetogame.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.ui.common.Loading
import com.borjadelgadodev.freetogame.ui.screens.home.Screen

@Composable
fun DetailScreen(viewModel: DetailViewModel, onBackClick: () -> Unit) {
    val state = viewModel.state
    Screen {
        Scaffold(topBar = {
            DetailTopBar(state.game?.title ?: "", onBackClick)
        }) { padding ->
            if (state.loading) {
                Loading(modifier = Modifier.padding(padding))
            }
            state.game?.let { game ->
                GameDetail(
                    game = game,
                    modifier = Modifier.padding(padding))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }
    )
}

@Composable
private fun GameDetail(game: Game, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = game.thumbnail,
            contentDescription = game.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = game.title,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = game.description,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}