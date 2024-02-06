package Model.Player

import Model.Game.Board
import Model.Game.Game

abstract class Player(
    var name: String,
    var hand: Hand? = null,
) {
    var score: Int = 0
    open fun playTour(game: Game, board: Board): Boolean {
        chooseTrick(game, board)
        prepareProps(game, board)
        val isSuccess = performTrick(board = board)
        return isSuccess
    }

    abstract fun performTrick(board: Board): Boolean

    abstract fun prepareProps(game: Game, board: Board)

    abstract fun chooseTrick(game: Game, board: Board)

}
