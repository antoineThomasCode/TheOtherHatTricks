package Model.Player

import Model.Game.Board
import Model.Game.Game
import kotlin.random.Random

class VirtualPlayer(
    name: String,
    hand: Hand? = null
) : Player(name, hand) {

    override fun playTour(game: Game, board: Board): Boolean {

        println("${name} (IA) c'est à votre tour de jouer !")
        return super.playTour(game, board)
    }

    private fun printPlayerHand() {
        println("")

    }

    override fun chooseTrick(game: Game, board: Board) {
        println("")
        var playerChoice: Int
        do {

            playerChoice = Random.nextInt(1, 3)

            when (playerChoice) {
                1 -> {
                    return
                }

                2 -> {
                    println()
                    board.swapTrick()
                    board.announceVisibleTourCard()
                    return
                }

                else -> {
                    playerChoice = 0
                }
            }
        } while (playerChoice !in 1..2)

    }

    override fun prepareProps(game: Game, board: Board) {

        println()
        val playerCardChoice = Random.nextInt(1, 3)


        val otherPlayers = game.players.filter { it != this }

        val otherPlayerChoice = Random.nextInt(1, 3)
        val otherPlayer = otherPlayers.getOrNull(otherPlayerChoice - 1)


        val otherPlayerCardChoice = Random.nextInt(1, 3)


        if (playerCardChoice != null) {
            val temp = if (playerCardChoice == 1) hand!!.propCardOne else hand!!.propCardTwo
            if (playerCardChoice == 1) {
                hand!!.propCardOne =
                    if (otherPlayerCardChoice == 1) otherPlayer!!.hand!!.propCardOne else otherPlayer!!.hand!!.propCardTwo
            } else {
                hand!!.propCardTwo =
                    if (otherPlayerCardChoice == 1) otherPlayer!!.hand!!.propCardOne else otherPlayer!!.hand!!.propCardTwo
            }
            if (otherPlayerCardChoice == 1) {
                otherPlayer.hand!!.propCardOne = temp
            } else {
                otherPlayer.hand!!.propCardTwo = temp
            }

        }
    }

    override fun performTrick(board: Board): Boolean {


        var choice = Random.nextInt(1, 3)

        when (choice) {
            1 -> {
                if (board.getVisibleTrick()?.performTrick(hand!!)!! > 0) {
                    println("L'IA avez réussi le tour.")

                    this.score += board.getVisibleTrick()!!.value
                    sleightOfHand(board)
                    return true
                } else {

                    println("Malheureusement l'IA à échouée à marquer le tour !")
                    forfeitTrick()
                    return false
                }
            }

            2 -> {

                println("L'ia a échoué pour ce tour.")
                forfeitTrick()
                return true

            }

            else -> {
                return performTrick(board = board)
            }
        }
    }

    private fun forfeitTrick() {

        val cardOneIsHidden = hand!!.propCardOne.isHidden
        val cardTwoIsHidden = hand!!.propCardTwo.isHidden

        when {

            !cardOneIsHidden && !cardTwoIsHidden -> {

                var choice: Int
                do {
                    choice = Random.nextInt(1, 3)
                } while (choice !in 1..2)

                if (choice == 1) hand!!.propCardOne.isHidden = true else hand!!.propCardTwo.isHidden = true
            }

            cardOneIsHidden != cardTwoIsHidden -> {
                if (cardOneIsHidden) hand!!.propCardOne.isHidden = false else hand!!.propCardTwo.isHidden = false

            }

            else -> println("la pénalité ne s'applique pas car les deux cartes de L'ia sont visibles ")
        }
    }

    private fun sleightOfHand(board: Board) {
        val hand = this.hand
        val seventhProp = board.theSeventhProp


        var choices = List(2) { Random.nextInt(1, 4) }.distinct()
        while (choices.size < 2) {

            choices += Random.nextInt(1, 4)
        }
        do {
            val input = readLine()
            choices = input?.split(" ")?.mapNotNull { it.toIntOrNull() }?.filter { it in 1..3 } ?: listOf()
        } while (choices.size != 2 || choices.distinct().size != 2)

        val newHand = when (choices.sorted()) {
            listOf(1, 2) -> Hand(hand!!.propCardOne, hand.propCardTwo)
            listOf(1, 3) -> Hand(hand!!.propCardOne, seventhProp.apply { isHidden = true })
            listOf(2, 3) -> Hand(hand!!.propCardTwo, seventhProp.apply { isHidden = true })
            else -> hand
        }
        this.hand = newHand
        if (!choices.contains(3)) {
            board.theSeventhProp = if (choices.contains(1)) hand!!.propCardTwo else hand!!.propCardOne
        }


    }

}
