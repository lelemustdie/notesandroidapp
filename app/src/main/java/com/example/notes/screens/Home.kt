import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.R
import com.example.notes.data.Note
import com.example.notes.navigation.enums.Routes
import com.example.notes.ui.components.SmallFloatingButton
import com.example.notes.ui.theme.Grey40
import com.example.notes.ui.theme.Grey80
import com.example.notes.ui.theme.PrimaryPink
import com.example.notes.ui.theme.SecondaryPink
import com.example.notes.ui.theme.eightDp
import com.example.notes.ui.theme.fourDp
import com.example.notes.ui.theme.oneF
import com.example.notes.ui.theme.sixteenDp
import com.example.notes.ui.theme.sixtyfourDp
import com.example.notes.viewmodels.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Home(onNavigate: (String) -> Unit , homeViewModel: HomeViewModel = hiltViewModel()){
    val uvIndex by homeViewModel.uvIndex.collectAsState()
    val notes by homeViewModel.notes.collectAsState(emptyList())
    val loading by homeViewModel.loading.collectAsState()

    if(loading){
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(sixtyfourDp)
                        .align(Alignment.Center),
                    color = PrimaryPink,
                    trackColor = Color.Gray
                )
            }
        }
    } else Box {
        Column {
            val uvMaxTimeFormatted = uvIndex.result.uv_max_time.let { timeString ->
                try {
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val date = inputFormat.parse(timeString)
                    outputFormat.format(date)
                } catch (e: Exception) {
                    Text(
                        text = stringResource(R.string.na)
                    )
                }
            } ?:  Text(
                text = stringResource(R.string.na)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(sixteenDp)
                    .height(IntrinsicSize.Min),
                shape = RoundedCornerShape(sixteenDp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(sixteenDp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.uv_index),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(eightDp))

                    Text(
                        text = stringResource(id = R.string.maxuvindex, uvIndex.result.uv_max),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )

                    Spacer(modifier = Modifier.height(fourDp))

                    Text(
                        text = stringResource(R.string.maxuvtime, uvMaxTimeFormatted),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(notes) { note ->
                    NoteCard(
                        note = note,
                        onDelete = { homeViewModel.deleteNote(it) },
                        onToggleFavorite = { homeViewModel.toggleFavorite(it) }
                    )
                    Spacer(modifier = Modifier.height(eightDp))
                }
            }
        }
        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(eightDp)){
            SmallFloatingButton(
                onClick = {
                    onNavigate(Routes.NewNote.title)
                }
            )
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onDelete: (Note) -> Unit,
    onToggleFavorite: (Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(sixteenDp),
        shape = RoundedCornerShape(eightDp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryPink
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(sixteenDp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(oneF)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Grey80),
                    modifier = Modifier.padding(bottom = eightDp)
                )

                Text(
                    text = note.body,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Grey40)
                )
            }

            Row {
                IconButton(onClick = { onToggleFavorite(note) }) {
                    Icon(
                        imageVector = if (note.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (note.isFavorite) stringResource(id = R.string.unmark_favorite) else stringResource(id = R.string.mark_favorite),
                        tint = PrimaryPink
                    )
                }

                IconButton(onClick = { onDelete(note) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.delete_note),
                        tint = PrimaryPink
                    )
                }
            }
        }
    }
}