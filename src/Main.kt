import Model.Game.Board
import Model.Game.Game


object Main fun main() {

    val game = Game(board = Board())

    game.setupGame()
    game.startGame()

}
