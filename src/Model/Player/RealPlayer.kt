package Model.Player

import Model.Player.Hand
import Model.Player.Player

class RealPlayer(
    name: String,
    hand: Hand? = null
) : Player(name, hand) {

    fun printPlayerHand () {
        println(hand!!.propCardOne.title)
        println(hand!!.propCardTwo.title)
    }

}