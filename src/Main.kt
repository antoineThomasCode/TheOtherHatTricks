import Controller.PlayerController
import Controller.userAction.PlayerAction
import Model.Game.Board
import Model.Game.Game
import View.PlayerView


fun main() {


    val game = Game(board = Board())
    val playerController = PlayerController(game)
    val playerView = PlayerView(playerController)
    playerController.setPlayerViewCommand(playerView)
    game.setupGame(playerController)
    game.startGame(playerController)

}
