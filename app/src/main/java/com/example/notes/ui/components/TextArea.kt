package com.example.notes.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.ui.theme.Grey80
import com.example.notes.ui.theme.eightDp
import com.example.notes.ui.theme.hundredDp
import com.example.notes.ui.theme.oneDp
import com.example.notes.ui.theme.tenDp

@Composable
fun TextArea() {
    val text = rememberSaveable { mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { text.value = it }, modifier = Modifier
            .fillMaxWidth()
            .height(hundredDp)
            .padding(tenDp)
            .border(width = oneDp, color = Grey80, shape = RoundedCornerShape(eightDp))
    )
}