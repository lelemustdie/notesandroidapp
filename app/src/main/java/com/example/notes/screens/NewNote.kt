package com.example.notes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.R
import com.example.notes.navigation.enums.Routes
import com.example.notes.ui.theme.PrimaryPink
import com.example.notes.ui.theme.sixteenDp
import com.example.notes.ui.theme.twoHundredDp
import com.example.notes.viewmodels.NewNoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNote(
    viewModel: NewNoteViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sixteenDp)
    ) {

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(stringResource(id = R.string.note_title)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = sixteenDp),
            colors = TextFieldDefaults.colors(
                cursorColor = PrimaryPink,
                focusedIndicatorColor = PrimaryPink,
                unfocusedIndicatorColor = PrimaryPink,
            )
        )

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text(stringResource(id = R.string.content)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(twoHundredDp),
            colors = TextFieldDefaults.colors(
                cursorColor = PrimaryPink,
                focusedIndicatorColor = PrimaryPink,
                unfocusedIndicatorColor = PrimaryPink,
            )
        )

        Spacer(modifier = Modifier.height(sixteenDp))
        
        Button(
            onClick = {
                viewModel.createNote(
                    title = title,
                    body = content
                )
                onNavigate(Routes.Home.title)
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryPink,
                contentColor = Color.White
            )
        ) {
            Text(stringResource(id = R.string.save_note))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewNewNote() {
//    NewNote()
//}