package Model.Game

import Model.Card.PropCard
import Model.Player.Player
import Model.Player.RealPlayer
import Model.Player.VirtualPlayer


class Game {

    val players: MutableList<Player> = createPlayers()

    private val board = Board(theSeventhProp = PropCard.dealCard(players))

    private fun createPlayers(): MutableList<Player> {
        val players = mutableListOf<Player>()
        while (players.size < 3) {
            println("________________")
            println("Entrez le nom d'un joueur  (ou 'IA' pour ajouter un joueur virtuel) :")
            val name = readlnOrNull()

            if (name != null) {
                val player = if (name.equals("IA", ignoreCase = true)) {
                    VirtualPlayer("Joueur IA ")
                } else {
                    RealPlayer(name)
                }
                players.add(player)
            }
        }
        return players

    }


    fun startGame() {
        var gameIsFinished = false
        while (!gameIsFinished) {
            players.forEach { player ->
                if (player is RealPlayer) {
                    player.playTour(this, board)
                }
                gameIsFinished = checkIfGameIsFinished(gameIsFinished, player)
                println("________________________________________________")
                println("______________   JOUEUR SUIVANT   ______________")
                println("________________________________________________")
            }
        }
    }


    fun checkIfGameIsFinished(gameIsFinish: Boolean, player: Player): Boolean {
        if (player.name == "Antoine") {
            println("_______________________")
            println("la partie est terminée")
            println("_______________________")
            return !gameIsFinish
        }

        return gameIsFinish
    }

    fun stopGame() {

    }


    fun isPlayerAllCreated(): Boolean {
        return players.size == 3
    }

}