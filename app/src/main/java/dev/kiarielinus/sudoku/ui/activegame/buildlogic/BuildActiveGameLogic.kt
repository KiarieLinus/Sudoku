package dev.kiarielinus.sudoku.ui.activegame.buildlogic

import android.content.Context
import dev.kiarielinus.sudoku.common.ProductionDispatcherProvider
import dev.kiarielinus.sudoku.persistence.*
import dev.kiarielinus.sudoku.persistence.settingsDataStore
import dev.kiarielinus.sudoku.ui.activegame.ActiveGameContainer
import dev.kiarielinus.sudoku.ui.activegame.ActiveGameLogic
import dev.kiarielinus.sudoku.ui.activegame.ActiveGameViewModel

internal fun buildActiveGameLogic(
    container: ActiveGameContainer,
    viewModel: ActiveGameViewModel,
    context: Context
) : ActiveGameLogic {
    return ActiveGameLogic(
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