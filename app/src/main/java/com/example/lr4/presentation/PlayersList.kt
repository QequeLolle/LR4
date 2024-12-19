package com.example.lr4.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lr4.domain.Player
import com.example.lr4.presentation.MainViewModel

@Composable
fun PlayerList(players: List<Player>, onCheckChange: (Boolean, Player) -> Unit) {
    LazyColumn {
        itemsIndexed(players) { _, player ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = player.isPresent,
                    onCheckedChange = { isChecked -> onCheckChange(isChecked, player) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = player.name)
            }
        }
    }
}

@Composable
fun DrawButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Розыгрыш команд")
    }
}