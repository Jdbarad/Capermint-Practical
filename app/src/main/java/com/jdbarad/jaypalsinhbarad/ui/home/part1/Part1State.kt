package com.jdbarad.jaypalsinhbarad.ui.home.part1

import com.jdbarad.jaypalsinhbarad.data.model.UiState
import com.jdbarad.jaypalsinhbarad.data.model.User

data class Part1State(
    val users: UiState<List<User>> = UiState.Idle,
)
