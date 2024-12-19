package com.example.lr4.presentation

import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import com.example.lr4.domain.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val context: Context) : ViewModel() {
    private val preferences = context.getSharedPreferences("PlayerData", Context.MODE_PRIVATE)

//    private val _players: MutableStateFlow<List<Player>> = MutableStateFlow(loadPlayers())
//    var players: StateFlow<List<Player>> = _players.asStateFlow()  //by mutableStateOf<List<Player>>(loadPlayers())

//    init {
//        var players by mutableStateOf<List<Player>>(emptyList())
//
//        var redTeam by mutableStateOf<List<Player>>(emptyList())
//
//        var greenTeam by mutableStateOf<List<Player>>(emptyList())
//    }
    var players by mutableStateOf<List<Player>>(emptyList())
        private set

    var redTeam by mutableStateOf<List<Player>>(emptyList())
        private set

    var greenTeam by mutableStateOf<List<Player>>(emptyList())
        private set

    fun addPlayer(name: String) {
        val newPlayer = Player(name)
        players += newPlayer
        savePlayers()
    }

    fun togglePlayerPresence(player: Player, isPresent: Boolean) {
        val updatedPlayers = players.map {
            if (it.name == player.name) it.copy(isPresent = isPresent) else it
        }
        players = updatedPlayers.toMutableStateList()
    }

    fun drawTeams() {
        val presentPlayers = players.filter { it.isPresent }
        val (team1, team2) = drawTeams(presentPlayers)
        redTeam = team1
        greenTeam = team2
    }

    private fun loadPlayers(): List<Player> {
        val n = preferences.getInt("N", 0)
        return mutableListOf<Player>().apply {
            for (i in 0 until n) {
                val name = preferences.getString("Person $i", "") ?: ""
                add(Player(name))
            }
        }
    }

    private fun savePlayers() {
        with(preferences.edit()) {
            putInt("N", players.size)
            players.forEachIndexed { index, player ->
                putString("Person $index", player.name)
            }
            apply()
        }
    }

    private fun drawTeams(presentPlayers: List<Player>): Pair<List<Player>, List<Player>> {
        if (presentPlayers.isEmpty()) return emptyList<Player>() to emptyList()

        val random = java.util.Random()
        val redTeam = mutableListOf<Player>()
        val greenTeam = mutableListOf<Player>()

        presentPlayers.forEach { player ->
            if (random.nextBoolean()) {
                redTeam.add(player)
            } else {
                greenTeam.add(player)
            }
        }

        return redTeam to greenTeam
    }
}