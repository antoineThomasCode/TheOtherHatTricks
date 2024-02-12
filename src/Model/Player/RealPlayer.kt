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
        board.announceVisibleTourCard(game)
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
            playerChoice = input.toIntOrNull() ?: 0

            when (playerChoice) {
                1 -> {
                    println("Vous avez choisi de jouer ce tour.")
                    return
                }

                2 -> {
                    println()
                    println("Vous avez choisi de piocher un autre tour.")
                    board.swapTrick()
                    board.announceVisibleTourCard(game)
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


        if (playerCardChoice != null && otherPlayerCardChoice != null) {
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
                if (board.getVisibleTrick()?.performTrick(hand!!)!! > 0) {
                    println("Félicitations ! Vous avez réussi le tour.")
                    this.score += board.getVisibleTrick()!!.value
                    sleightOfHand(board)
                    return true
                } else {

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


        when {

            hand!!.propCardOne.isHidden && hand!!.propCardTwo.isHidden -> {
                println("Choisissez une carte à retourner : 1. ${hand!!.propCardOne.title} ou 2. ${hand!!.propCardTwo.title}")
                var choice: Int
                do {
                    val input = readlnOrNull()
                    choice = input?.toIntOrNull() ?: 0
                } while (choice !in 1..2)

                if (choice == 1) hand!!.propCardOne.isHidden = false else hand!!.propCardTwo.isHidden = false
            }

            !hand!!.propCardTwo.isHidden && hand!!.propCardOne.isHidden -> {
                hand!!.propCardOne.isHidden = false
                println("La carte cachée a été retournée.")
            }

            !hand!!.propCardOne.isHidden && hand!!.propCardTwo.isHidden -> {
                hand!!.propCardTwo.isHidden = false
                println("La carte cachée a été retournée.")
            }

            else -> println("Vos deux cartes sont déjà visibles, la pénalité ne s'applique pas pour ce tour")
        }
    }

    private fun sleightOfHand(board: Board) {
        val hand = this.hand
        val seventhProp = board.theSeventhProp

        println("Les deux cartes de votre main sont : 1. ${hand!!.propCardOne.title} et 2. ${hand.propCardTwo.title}")
        println("La 7ème carte est : ${seventhProp.title} (carte cachée)")

        println("Choisissez les deux cartes à garder (ex : '1 3' pour garder la première carte de votre main et la 7ème carte) :")
        var choices: List<Int>
        do {
            val input = readLine()
            choices = input?.split(" ")?.mapNotNull { it.toIntOrNull() }?.filter { it in 1..3 } ?: listOf()
        } while (choices.size != 2 || choices.distinct().size != 2)

        val newHand = when (choices.sorted()) {
            listOf(1, 2) -> Hand(hand.propCardOne, hand.propCardTwo)
            listOf(1, 3) -> Hand(hand.propCardOne, seventhProp.apply { isHidden = true })
            listOf(2, 3) -> Hand(hand.propCardTwo, seventhProp.apply { isHidden = true })
            else -> hand
        }
        this.hand = newHand
        if (!choices.contains(3)) {
            board.theSeventhProp = if (choices.contains(1)) hand.propCardTwo else hand.propCardOne
        }

        println("Échange effectué. Les cartes de votre main sont maintenant : 1. ${this.hand!!.propCardOne.title} (cachée : ${this.hand!!.propCardOne.isHidden}) et 2. ${this.hand!!.propCardTwo.title} (cachée : ${this.hand!!.propCardTwo.isHidden})")

    }

}