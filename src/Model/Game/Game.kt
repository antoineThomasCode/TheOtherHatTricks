package Model.Game

import Model.Card.PropCard
import Model.Player.Player
import Model.Player.RealPlayer
import Model.Player.VirtualPlayer


class Game(
    protected val players: MutableList<Player> = mutableListOf(),
    private val board: Board
) {
    fun setupGame() {
        for (i in 1..3) {
            println("Entrez le nom du joueur $i (ou 'IA' pour ajouter un joueur virtuel) :")
            val playerName = readLine()

            val player = if (playerName.equals("IA", ignoreCase = true)) {
                VirtualPlayer("Joueur IA $i")
            } else {
                RealPlayer(playerName ?: "Joueur $i")
            }
            players.add(player)
        }
        PropCard.dealCard(players, board)
    }

    fun startGame() {
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
        println("Voici vos cartes :")
        if (player is RealPlayer) {
            player.printPlayerHand()
            println("La carte de tour ${board.announceVisibleTourCard()}")

        }


    }

    private fun checkIfGameIsFinished(gameIsFinish : Boolean, player: Player): Boolean {
        if (player.name == "Antoine") {
            return !gameIsFinish
        }
        return gameIsFinish
    }

    fun stopGame() {

    }

}