package Model.Game

import Controller.PlayerController
import Model.Card.PropCard
import Model.Player.Player
import Model.Player.RealPlayer
import Model.Player.VirtualPlayer
import javax.swing.text.StyledEditorKit.BoldAction


class Game(
    val players: MutableList<Player> = mutableListOf(),
    private val board: Board
) {
    fun setupGame(playerController: PlayerController) {
        playerController.startPlayerCreation()
        PropCard.dealCard(players, board)
    }

    fun startGame(playerController: PlayerController) {
        var gameIsFinished = false
        while (!gameIsFinished) {
            players.forEach { player ->
                playTour(player)
                gameIsFinished = checkIfGameIsFinished(gameIsFinished, player)
            }
        }
    }

    private fun playTour(player: Player) {

        println(" ${player.name} c'est à votre tour de jouer !")

        if (player is RealPlayer) {
            println("Voici vos cartes :")
            player.printPlayerHand()
            println("La carte de tour ${board.announceVisibleTourCard()}")
            println("Pour marquez les point de ce tour vous devez coupler les accessoires suivant :")
        }
        if (player is VirtualPlayer) {
            // TODO implémenter la logique pour l'IA
        }


    }

    private fun checkIfGameIsFinished(gameIsFinish : Boolean, player: Player): Boolean {
        if (player.name == "Antoine") {
            println("la partie est terminée")
            return !gameIsFinish
        }
        return gameIsFinish
    }

    fun stopGame() {

    }
    fun createPlayer (player: Player) : Player? {
        if (players.size < 3) {
            players.add(player)
            return player
        } else {
            return null
        }
    }
    fun isPlayerAllCreated () : Boolean {
        return players.size == 3
    }

}