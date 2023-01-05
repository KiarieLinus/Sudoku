package dev.kiarielinus.sudoku.ui.newgame

import dev.kiarielinus.sudoku.domain.Difficulty

sealed class NewGameEvent {
    object OnStart: NewGameEvent()
    data class OnSizeChanged(val boundary: Int): NewGameEvent()
    data class OnDifficultyChanged(val diff: Difficulty): NewGameEvent()
    object OnDonePressed: NewGameEvent()
}