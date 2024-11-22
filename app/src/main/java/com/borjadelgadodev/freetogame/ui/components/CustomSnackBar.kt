package com.borjadelgadodev.freetogame.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borjadelgadodev.freetogame.R

@Composable
fun CustomSnackbar(snackbarHostState: SnackbarHostState, onDismiss: () -> Unit) {
    SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(
            modifier = Modifier.padding(8.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            action = {
                TextButton(onClick = {
                    onDismiss()
                }) {
                    Text(
                        text = stringResource(R.string.close),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        ) {
            Text(
                text = data.visuals.message,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                textDecoration = TextDecoration.None
            )
        }
    }
}
