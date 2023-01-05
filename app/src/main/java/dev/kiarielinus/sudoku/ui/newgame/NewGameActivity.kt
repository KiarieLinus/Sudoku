package dev.kiarielinus.sudoku.ui.newgame

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import dev.kiarielinus.sudoku.R
import dev.kiarielinus.sudoku.common.makeToast
import dev.kiarielinus.sudoku.ui.activegame.ActiveGameActivity
import dev.kiarielinus.sudoku.ui.newgame.buildlogic.buildNewGameLogic
import dev.kiarielinus.sudoku.ui.theme.SudokuTheme

/**
 * This feature is so simple that it is not even worth it to have a separate logic class
 */
class NewGameActivity : AppCompatActivity(), NewGameContainer {
    private lateinit var logic: NewGameLogic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = NewGameViewModel()

        setContent {
            SudokuTheme {
                NewGameScreen(
                    onEventHandler = logic::onEvent,
                    viewModel
                )
            }
        }

        logic = buildNewGameLogic(this, viewModel, applicationContext)

    }

    override fun onStart() {
        super.onStart()
        logic.onEvent(NewGameEvent.OnStart)
    }

    override fun showError() = makeToast(getString(R.string.generic_error))

    override fun onDoneClick() {
        startActiveGameActivity()
    }

    /**
     * I want the other feature to be completely restarted each time
     */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActiveGameActivity()
    }

    private fun startActiveGameActivity() {
        startActivity(
            Intent(
                this,
                ActiveGameActivity::class.java
            ).apply {
                this.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            }
        )
    }

}