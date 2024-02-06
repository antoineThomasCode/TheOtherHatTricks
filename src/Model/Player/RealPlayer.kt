package Model.Player

import Model.Game.Board
import Model.Game.Game

class RealPlayer(
    name: String,
    hand: Hand? = null
) : Player(name, hand) {

    fun playTour(game: Game, board: Board) {

        println("${name} c'est à votre tour de jouer !")
        printPlayerHand()
        board.announceVisibleTourCard()
        board.announceTourCardCombinations()
        chooseATrick(game, board)
        prepareProps(game, board)

    }

    private fun printPlayerHand() {
        println("Voici vos cartes :")
        println(hand!!.propCardOne.title)
        println(hand!!.propCardTwo.title)
    }

    private fun chooseATrick(game: Game, board: Board) {
        var playerChoice: Int
        do {
            println("Voulez-vous : 1. jouer ce tour / 2. piocher un autre tour")
            val input = readLine() ?: ""
            playerChoice = input.toIntOrNull() ?: 0 // Convertit l'entrée en Int, 0 si conversion échoue

            when (playerChoice) {
                1 -> {
                    println("Vous avez choisi de jouer ce tour.")
                    return
                }

                2 -> {
                    println("Vous avez choisi de piocher un autre tour.")
                    board.piocheTrick()
                    board.announceVisibleTourCard()
                    board.announceTourCardCombinations()
                    return
                }

                else -> {
                    println("Vous devez faire un choix entre 1. jouer ce tour / 2. piocher un autre tour")
                    playerChoice = 0
                }
            }
        } while (playerChoice !in 1..2)

    }
    fun prepareProps(game: Game, board: Board) {
        println("Vous allez devoir échanger l'une de vos cartes avec l'un de vos adversaires.")

        println("Laquelle de vos cartes souhaitez-vous échanger : 1. ${hand!!.propCardOne.title} ou 2. ${hand!!.propCardTwo.title}")
        val playerCardChoice = readLine()?.toIntOrNull()


        val otherPlayers = game.players.filter { it != this }
        println("Avec quel joueur souhaitez-vous échanger votre carte ?")
        otherPlayers.forEachIndexed { index, player ->
            println("${index + 1}. ${player.name}")
        }
        val otherPlayerChoice = readLine()?.toIntOrNull()
        val otherPlayer = otherPlayers.getOrNull(otherPlayerChoice!! - 1)

        val otherPlayerCardOneDescription = if (!otherPlayer?.hand?.propCardOne?.isHidden!!) otherPlayer.hand?.propCardOne?.title else "carte retournée"
        val otherPlayerCardTwoDescription = if (!otherPlayer.hand?.propCardTwo?.isHidden!!) otherPlayer.hand?.propCardTwo?.title else "carte retournée"

        println("Quelle carte de ${otherPlayer?.name} souhaitez-vous échanger : 1. $otherPlayerCardOneDescription ou 2. $otherPlayerCardTwoDescription")
        val otherPlayerCardChoice = readLine()?.toIntOrNull()


        if (playerCardChoice != null && otherPlayer != null && otherPlayerCardChoice != null) {
            val temp = if (playerCardChoice == 1) hand!!.propCardOne else hand!!.propCardTwo
            if (playerCardChoice == 1) {
                hand!!.propCardOne = if (otherPlayerCardChoice == 1) otherPlayer.hand!!.propCardOne else otherPlayer.hand!!.propCardTwo
            } else {
                hand!!.propCardTwo = if (otherPlayerCardChoice == 1) otherPlayer.hand!!.propCardOne else otherPlayer.hand!!.propCardTwo
            }
            if (otherPlayerCardChoice == 1) {
                otherPlayer.hand!!.propCardOne = temp
            } else {
                otherPlayer.hand!!.propCardTwo = temp
            }
            println("Échange effectué.")
            printPlayerHand()
        }
    }
    fun performTrick(game: Game, board: Board, player: Player ) {
        // annoncer le Trick possible
        // demander si le joueur veut perform le Trick
        // --> Si oui voir si success
        // --> sinon forfeit
        // regarder si la carte est "the other hat trick"
    }
    fun forfeitTrick (player: Player, board: Board, game: Game){

        // appliquer la péalité au joueur
        // si les deux carte de la hand sont à isHidden = true
        // demander au user de choisir la carte à retourner
        // si l'une des cartes est à isHidden = false alors retourner l'autre carte
        // si les deux carte de la HAND sont isHidden à false -> return
    }



}