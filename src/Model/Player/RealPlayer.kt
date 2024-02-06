package Model.Player

import Model.Game.Board
import Model.Game.Game

class RealPlayer(
    name: String,
    hand: Hand? = null
) : Player(name, hand) {

    override fun playTour(game: Game, board: Board): Boolean {

        println("${name} c'est à votre tour de jouer !")
        printPlayerHand()
        board.announceVisibleTourCard()
        return super.playTour(game, board)
    }

    private fun printPlayerHand() {
        println("")
        println("Voici vos cartes :")
        println("    " + hand!!.propCardOne)
        println("    " + hand!!.propCardTwo)
    }

    override fun chooseTrick(game: Game, board: Board) {
        println("")
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
                    println()
                    println("Vous avez choisi de piocher un autre tour.")
                    board.swapTrick()
                    board.announceVisibleTourCard()
//                    board.announceTourCardCombinations()
                    return
                }

                else -> {
                    println("Vous devez faire un choix entre 1. jouer ce tour / 2. piocher un autre tour")
                    playerChoice = 0
                }
            }
        } while (playerChoice !in 1..2)

    }

    override fun prepareProps(game: Game, board: Board) {
//        println("Vous allez devoir échanger l'une de vos cartes avec l'un de vos adversaires.")
        println()
        println("Quelle carte souhaitez-vous échanger avec un autre joueur : 1. ${hand!!.propCardOne.title} ou 2. ${hand!!.propCardTwo.title}")
        val playerCardChoice = readLine()?.toIntOrNull()


        val otherPlayers = game.players.filter { it != this }
        println("Avec quel joueur souhaitez-vous échanger votre carte ?")
        otherPlayers.forEachIndexed { index, player ->
            println("${index + 1}. ${player.name}")
        }
        val otherPlayerChoice = readLine()?.toIntOrNull()
        val otherPlayer = otherPlayers.getOrNull(otherPlayerChoice!! - 1)

        val otherPlayerCardOneDescription =
            if (!otherPlayer?.hand?.propCardOne?.isHidden!!) otherPlayer.hand?.propCardOne?.title else "carte retournée"
        val otherPlayerCardTwoDescription =
            if (!otherPlayer.hand?.propCardTwo?.isHidden!!) otherPlayer.hand?.propCardTwo?.title else "carte retournée"

        println("Quelle carte de ${otherPlayer?.name} souhaitez-vous échanger : 1. $otherPlayerCardOneDescription ou 2. $otherPlayerCardTwoDescription")
        val otherPlayerCardChoice = readLine()?.toIntOrNull()


        if (playerCardChoice != null && otherPlayer != null && otherPlayerCardChoice != null) {
            val temp = if (playerCardChoice == 1) hand!!.propCardOne else hand!!.propCardTwo
            if (playerCardChoice == 1) {
                hand!!.propCardOne =
                    if (otherPlayerCardChoice == 1) otherPlayer.hand!!.propCardOne else otherPlayer.hand!!.propCardTwo
            } else {
                hand!!.propCardTwo =
                    if (otherPlayerCardChoice == 1) otherPlayer.hand!!.propCardOne else otherPlayer.hand!!.propCardTwo
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

    override fun performTrick(board: Board): Boolean {
        println("Vous êtes maintenant prêt pour marquer le tour !")
        println("Voulez-vous tenter de marquer (1) ou annoncer votre échec (2) ?")

        var choice: Int
        val input = readLine()
        choice = input?.toIntOrNull() ?: 0

        when (choice) {
            1 -> {
                if (board.getVisibleTrick().performTrick(hand!!) > 0) {
                    println("Félicitations ! Vous avez réussi le tour.")

//                    Récupérer le nombre de point
                    //  TODO implémenter le passe passe
                    return true
                } else {
                    //                    Récupérer le nombre de point
                    println("Malheureusement, vous n'avez pas les cartes necessaires pour marquer ce tour")
                    forfeitTrick()
                    return false
                }
            }

            2 -> {

                println("Vous avez annoncé votre échec pour ce tour.")
                forfeitTrick()
                return true

            }

            else -> {
                println("Choix invalide. Veuillez choisir (1) pour tenter de marquer ou (2) pour annoncer votre échec.")
                return performTrick(board = board)
            }
        }
    }

    private fun forfeitTrick() {

        val cardOneIsHidden = hand!!.propCardOne.isHidden
        val cardTwoIsHidden = hand!!.propCardTwo.isHidden

        when {

            !cardOneIsHidden && !cardTwoIsHidden -> {
                println("Choisissez une carte à retourner : 1. ${hand!!.propCardOne.title} ou 2. ${hand!!.propCardTwo.title}")
                var choice: Int
                do {
                    val input = readlnOrNull()
                    choice = input?.toIntOrNull() ?: 0
                } while (choice !in 1..2)

                if (choice == 1) hand!!.propCardOne.isHidden = true else hand!!.propCardTwo.isHidden = true
            }

            cardOneIsHidden != cardTwoIsHidden -> {
                if (cardOneIsHidden) hand!!.propCardOne.isHidden = false else hand!!.propCardTwo.isHidden = false
                println("La carte cachée a été retournée.")
            }

            else -> println("Vos deux cartes sont déjà visibles, la pénalité ne s'applique pas pour ce tour")
        }
    }
}