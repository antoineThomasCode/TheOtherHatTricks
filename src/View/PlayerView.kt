package View

import Controller.userAction.PlayerAction
import Model.Player.Hand
import Model.Player.Player


class PlayerView(
    private val playerAction : PlayerAction
) : PlayerCommandInterface {

     override fun displayCreationMessage(message: String) {
        println(message)
        var userInput = readLine()

        if (userInput != null) {
            playerAction.onNameAttribued(userInput)
        }
    }

    override fun displayPlayerCreationError(message: String) {
        println(message)
    }

    override fun displayChooseTrickMessage(message: String) {
        println(message)
    }

    override fun displayPlayerHand(message: String, hand: Hand) {
        println(message)
        println("-->${hand.propCardTwo.name}")
        println("-->${hand.propCardOne.name}")
    }

    override fun displayPlayer(player: Player) {
        println(player.name)
        // TODO implément logic for this  method
    }



}