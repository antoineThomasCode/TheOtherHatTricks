package Model.Game

import Controller.PlayerController
import Model.Card.PropCard
import Model.Player.Player
import Model.Player.RealPlayer


class Game(
    val players: MutableList<Player> = mutableListOf(),
    private val board: Board
) {
    var forfeitCounter = 0
    fun setupGame(playerController: PlayerController) {
        playerController.startPlayerCreation()
        PropCard.dealCard(players, board)
    }

    fun startGame(playerController: PlayerController) {
        var gameIsFinished = false
        while (!gameIsFinished) {
            players.forEach { player ->
                if (player is RealPlayer) {
                    player.playTour(this, board)
                }
                gameIsFinished = checkIfGameIsFinished(gameIsFinished, player)
            }
        }
    }


    private fun checkIfGameIsFinished(gameIsFinish: Boolean, player: Player): Boolean {
        if (player.name == "Antoine") {
            println("la partie est terminée")
            return !gameIsFinish
        }
        if (forfeitCounter == 3) {
            return !gameIsFinish
        }
        // the other hat trick condition or trickDeck is empty
        return gameIsFinish
    }

    fun stopGame() {

    }

    fun createPlayer(player: Player): Player? {
        if (players.size < 3) {
            players.add(player)
            return player
        } else {
            return null
        }
    }

    fun isPlayerAllCreated(): Boolean {
        return players.size == 3
    }

}