package com.example.notes.ui.components
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.notes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onBack: () -> Unit,
    onSave: () -> Unit,
    onToggleFavorite: () -> Unit,
    onDelete: () -> Unit,
    isFavorite: Boolean
) {
    TopAppBar(
        title = { Text(text = "Nota") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.description)
                )
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.description)
                )
            }
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if(!isFavorite) Icons.Outlined.FavoriteBorder else Icons.Filled.Favorite,
                    contentDescription = stringResource(id = R.string.description)
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(id = R.string.description)
                )
            }
        }
    )
}