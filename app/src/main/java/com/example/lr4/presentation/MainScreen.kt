package com.example.lr4.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lr4.domain.Player
import com.example.lr4.presentation.MainViewModel

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel
) {
    val players by rememberSaveable {
        mutableStateOf(viewModel.players)
    }

    val redTeam by rememberSaveable {
        mutableStateOf(viewModel.redTeam)
    }

    val greenTeam by rememberSaveable {
        mutableStateOf(viewModel.greenTeam)
    }


}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel) {

    val players by rememberSaveable {
        mutableStateOf(viewModel.players)
    }

    val redTeam by rememberSaveable {
        mutableStateOf(viewModel.redTeam)
    }

    val greenTeam by rememberSaveable {
        mutableStateOf(viewModel.greenTeam)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        var playerName by remember { mutableStateOf("") }

        TextField(
            value = playerName,
            onValueChange = { playerName = it },
            label = { Text("Имя игрока") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (playerName.isNotBlank()) {
                viewModel.addPlayer(playerName)
                playerName = ""
            }
        }) {
            Text("Добавить игрока")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PlayerList(players) { isChecked, player ->
            viewModel.togglePlayerPresence(player, isChecked)
        }

        Spacer(modifier = Modifier.height(16.dp))

        DrawButton {
            viewModel.drawTeams()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Красная команда:", fontWeight = FontWeight.Bold)

        PlayerList(redTeam) { _, _ -> }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Зеленая команда:", fontWeight = FontWeight.Bold)

        PlayerList(greenTeam) { _, _ -> }
    }
}
