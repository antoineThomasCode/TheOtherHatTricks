package Model.Player

import Model.Game.Board
import Model.Game.Game

class RealPlayer(
    name: String,
    hand: Hand? = null
) : Player(name, hand) {

    fun playTour(game: Game, board: Board) {

        println("${name} c'est à votre tour de jouer !")
        println("Voici vos cartes :")
        printPlayerHand()
        board.announceVisibleTourCard()
        board.announceTourCardCombinations()
        chooseATrick(game, board)

    }

    private fun printPlayerHand() {
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


}