package Model.Game

import Model.Card.PropCard
import Model.Player.Hand
import Model.Player.Player
import Model.Player.RealPlayer
import Model.Player.VirtualPlayer


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
                VirtualPlayer("Joueur IA $i")
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

    private fun distribueHand() {
        board.getHand(players)
        // forcer le retournement des cartes
        players.forEach { player ->
            player.hand!!.propCardTwo.isHidden = false
            player.hand!!.propCardOne.isHidden = true
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
            playerChoice = input?.toIntOrNull() ?: 0

            if (playerChoice !in 1..2) {
                println("Choix invalide. Si tu souhaites tenter de marquer ce tour, tape 1. Pour voir un autre tour, tape 2")
            }
        } while (playerChoice !in 1..2)

        if (playerChoice == 1) {
            prepareProp()
            // regarder si le tour est ok sinon
            // TODO mettre les conditions de victoire ici
            players[currentPlayer].score += board.visibleTrickyCard!!.value
            println("Bravo tu viens de marquer ${board.visibleTrickyCard?.value} points")
            println("Ton score est désormais de points")
        } else if (playerChoice == 2) {
            board.returnLastTrickCard()
            trickChoice()
            prepareProp()
        }
    }

    private fun prepareProp() {

        var playerPropChoice: Int
        var propToExchange: PropCard?

        println("Les accessoires visibles dans les mains des autres joueurs sont :")
        players.forEachIndexed { index, player ->
            println("cartes dans prepar prop foreach ${player.name}")
            println(player.hand!!.propCardOne.title)
            println(player.hand!!.propCardOne.isHidden)
            println(player.hand!!.propCardTwo.title)
            println(player.hand!!.propCardTwo.isHidden)
            if (index != currentPlayer) {
                val visibleProps = mutableListOf<String>()
                player.hand?.let { hand ->
                    if (!hand.propCardOne.isHidden) visibleProps.add(hand.propCardOne.title)
                    if (!hand.propCardTwo.isHidden) visibleProps.add(hand.propCardTwo.title)
                }
                val visiblePropsDisplay =
                    if (visibleProps.isNotEmpty()) visibleProps.joinToString(", ") else "Aucune carte visible"
                println("${player.name} : $visiblePropsDisplay")
            }
        }


        do {
            println("Choisissez l'accessoire de votre main à échanger (1.${players[currentPlayer].hand?.propCardOne?.title} ou 2.${players[currentPlayer].hand?.propCardTwo?.title}) :")
            playerPropChoice = readLine()?.toIntOrNull() ?: 0
            propToExchange = if (playerPropChoice == 1) players[currentPlayer].hand?.propCardOne
            else if (playerPropChoice == 2) players[currentPlayer].hand?.propCardTwo
            else null
        } while (propToExchange == null)

        var otherPlayerIndex: Int
        var otherPlayerObj: Player

        do {
            println("Choisissez un joueur avec lequel échanger :")
            players.forEachIndexed { index, player ->
                if (index != currentPlayer) println("$index  : ${player.name}")
            }
            otherPlayerIndex = readLine()?.toIntOrNull() ?: -1
            otherPlayerObj = players.getOrNull(otherPlayerIndex) ?: players[currentPlayer]
        } while (otherPlayerIndex == currentPlayer || otherPlayerIndex !in players.indices)

        var otherPlayerPropChoice: Int
        var otherPropToExchange: PropCard?

        do {
            val visibleProps = mutableListOf<String>()
            otherPlayerObj.hand?.let {
                val firstPropDescription = if (!it.propCardOne.isHidden) it.propCardOne.title else "1: carte retournée"
                visibleProps.add(firstPropDescription)

                val secondPropDescription = if (!it.propCardTwo.isHidden) it.propCardTwo.title else "2: carte retournée"
                visibleProps.add(secondPropDescription)
            }
            println("Choisissez l'accessoire de l'autre joueur à échanger (${visibleProps.joinToString(", ")}) :")

            otherPlayerPropChoice = readLine()?.toIntOrNull() ?: 0
            otherPropToExchange = if (otherPlayerPropChoice == 1) otherPlayerObj.hand?.propCardOne
            else if (otherPlayerPropChoice == 2) otherPlayerObj.hand?.propCardTwo
            else null
        } while (otherPropToExchange == null)

        // Échanger les accessoires
        if (playerPropChoice in 1..2 && otherPlayerPropChoice in 1..2) {
            val temp = propToExchange
            players[currentPlayer].hand =
                if (playerPropChoice == 1) Hand(otherPropToExchange, players[currentPlayer].hand!!.propCardTwo)
                else Hand(players[currentPlayer].hand!!.propCardOne, otherPropToExchange)

            if (otherPlayerPropChoice == 1) otherPlayerObj.hand = Hand(temp, otherPlayerObj.hand!!.propCardTwo)
            else otherPlayerObj.hand = Hand(otherPlayerObj.hand!!.propCardOne, temp)

            println("Échange effectué.")
            println("Vous avez désormais les cartes suivantes en main :")
            println("->${players[currentPlayer].hand?.propCardOne?.title}")
            println("->${players[currentPlayer].hand?.propCardTwo?.title}")
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