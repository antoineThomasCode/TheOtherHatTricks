package game

import player.Player
import player.RealPlayer
import player.VirtualPlayer


class Game(
    protected val players: MutableList<Player> = mutableListOf(), private val board: Board
) {
    var currentPlayer: Int = 0


    fun initialiseGame() {
        // je créé d'abord les joueurs
        for (i in 1..3) {
            println("Entrez le nom du joueur $i (ou 'IA' pour ajouter un joueur virtuel) :")
            val playerName = readLine()

            val player = if (playerName.equals("IA", ignoreCase = true)) {
                VirtualPlayer("Joueur Virtuel $i")
            } else {
                RealPlayer(playerName ?: "Joueur $i")
            }

            players.add(player)
        }
        // je met en route le tri des tas dans le board
        board.prepareBoard()


        // je distribue les hands de chaque joueur
        distribueHand()
        board.setupSeventThProp()

        println("carte distribuées !")

        // je retourne et annonce la trick card au dessus du tas
        board.returnLastTrickCard()

        start()

    }

    fun distribueHand() {
        players.forEach { player ->
            player.hand = board.getHand()
            println(player.hand!!.propCardOne.title)
            println(player.hand!!.propCardOne.isHidden)
            println(player.hand!!.propCardTwo.title)
            println(player.hand!!.propCardTwo.isHidden)
        }
    }

    fun start() {
        println("Le jeu commence !")
        playTour()
    }

    private fun playTour() {
        println("${players[currentPlayer].name}, c'est à ton tour de jouer")

        trickChoice()
        var playerChoice: Int
        do {
            println("Si tu souhaites tenter de marquer ce tour, tape 1. Pour voir un autre tour, tape 2")
            val input = readLine()
            playerChoice = input?.toIntOrNull() ?: 0 // Convertit en entier, ou 0 si non valide

            if (playerChoice !in 1..2) {
                println("Choix invalide. Si tu souhaites tenter de marquer ce tour, tape 1. Pour voir un autre tour, tape 2")
            }
        } while (playerChoice !in 1..2)

        if (playerChoice == 1) {
            // regarder si le tour est ok sinon
            // TODO mettre les conditions de victoire ici
            println("Bravo du viens de marquer ${board.visibleTrickyCard?.value} points")
            println("Ton score est désormais de points")
        } else if (playerChoice == 2) {
            board.returnLastTrickCard()
            trickChoice()

        }


    }

    private fun trickChoice() {
        val currentTrickCard = board.visibleTrickyCard
        if (currentTrickCard != null) {
            println("La carte de tour visible est : ${currentTrickCard.title}")
            println("Si tu couples l'une des cartes suivantes :")
            currentTrickCard.combinationFirstPart.forEach { card ->
                println("-> ${card.title}")
            }

            println("avec l'une des cartes suivantes :")
            currentTrickCard.combinationSecondPart.forEach { card ->
                println("-> ${card.title}")
            }
            println("tu pourras marquer ${currentTrickCard.value} points")
        } else {
            println("Aucune carte de tour visible n'est disponible actuellement.")
        }

        println("Voici les cartes disponibles dans ta main :")
        players[currentPlayer].hand?.let {
            println("--> ${it.propCardOne.title}")
            println("--> ${it.propCardTwo.title}")
        }
    }

    private fun nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size
    }

}