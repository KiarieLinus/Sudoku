package dev.kiarielinus.sudoku.ui.newgame.buildlogic

import android.content.Context
import dev.kiarielinus.sudoku.common.ProductionDispatcherProvider
import dev.kiarielinus.sudoku.persistence.*
import dev.kiarielinus.sudoku.persistence.settingsDataStore
import dev.kiarielinus.sudoku.ui.newgame.NewGameContainer
import dev.kiarielinus.sudoku.ui.newgame.NewGameLogic
import dev.kiarielinus.sudoku.ui.newgame.NewGameViewModel

internal fun buildNewGameLogic(
    container: NewGameContainer,
    viewModel: NewGameViewModel,
    context: Context
): NewGameLogic {
    return NewGameLogic(
        container,
        viewModel,
        GameRepositoryImpl(
            LocalGameStorageImpl(context.filesDir.path),
            LocalSettingsStorageImpl(context.settingsDataStore)
        ),
        LocalStatisticsStorageImpl(
            context.statsDataStore
        ),
        ProductionDispatcherProvider
    )
}
