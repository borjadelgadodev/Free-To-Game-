package com.borjadelgadodev.freetogame.ui.screens.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.borjadelgadodev.freetogame.R
import com.borjadelgadodev.freetogame.data.Game
import com.borjadelgadodev.freetogame.ui.components.CustomSnackbar
import com.borjadelgadodev.freetogame.ui.components.Loading
import com.borjadelgadodev.freetogame.ui.screens.home.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel, onBackClick: () -> Unit) {

    val state by viewModel.state.collectAsState()
    val detailState = rememberDetailState()
    val context = LocalContext.current

    detailState.ShowMessage(message = state.message) {
        viewModel.onAction(DetailAction.MessageShown)
    }

    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = state.game?.title ?: "",
                    scrollBehavior = detailState.scrollBehavior,
                    onBackClick = onBackClick
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {

                    val message = context.getStringResource(R.string.game_added_to_favorites)
                    viewModel.onAction(DetailAction.FavoriteClick, message)
                }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            },
            snackbarHost = {
                CustomSnackbar(snackbarHostState = detailState.snackbarHostState) {
                    viewModel.onAction(DetailAction.MessageShown)
                }
            },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
        ) { padding ->
            if (state.loading) {
                Loading(modifier = Modifier.padding(padding))
            }
            state.game?.let { game ->
                GameDetail(
                    game = game,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
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
        },
        scrollBehavior = scrollBehavior
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
            text = game.description,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = buildAnnotatedString {
                Property("Genre", game.genre)
                Property("Platform", game.platform)
                Property("Developer", game.developer, end = true)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),

            )
    }
}

@Composable
private fun AnnotatedString.Builder.Property(key: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 20.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$key: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}

fun Context.getStringResource(resId: Int): String {
    return this.getString(resId)
}