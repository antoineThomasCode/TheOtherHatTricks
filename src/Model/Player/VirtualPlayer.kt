package Model.Player

import Model.Game.Board
import Model.Game.Game
import Model.Player.Hand
import Model.Player.Player

class VirtualPlayer(

    name: String,
    hand : Hand? = null

) : Player(name, hand) {


    override fun playTour(game: Game, board: Board): Boolean {
        TODO("Not yet implemented")
    }

    override fun performTrick(board: Board): Boolean {
        TODO("Not yet implemented")
    }

    override fun prepareProps(game: Game, board: Board) {
        TODO("Not yet implemented")
    }

    override fun chooseTrick(game: Game, board: Board) {
        TODO("Not yet implemented")
    }
}