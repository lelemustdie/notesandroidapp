package com.example.notes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.navigation.enums.Routes
import com.example.notes.ui.components.SmallFloatingButton
import com.example.notes.ui.theme.Grey40
import com.example.notes.ui.theme.Grey80
import com.example.notes.ui.theme.PrimaryPink
import com.example.notes.ui.theme.SecondaryPink
import com.example.notes.viewmodels.HomeViewModel

@Composable
fun Home(onNavigate: (String) -> Unit , homeViewModel: HomeViewModel = hiltViewModel()){
    val uvIndex by homeViewModel.uvIndex.collectAsState()
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = SecondaryPink
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "UV Index",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = PrimaryPink,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Max UV Index: ${uvIndex.result.uv_max}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Grey80
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Max UV Time: ${uvIndex.result.uv_max_time}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Grey40
                    )
                )
            }
        }
        SmallFloatingButton(
            onClick = {
                onNavigate(Routes.NewNote.title)
            }
        )
    }
}