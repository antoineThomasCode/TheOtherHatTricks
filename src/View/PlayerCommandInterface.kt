package View

import Model.Player.Hand
import Model.Player.Player

interface PlayerCommandInterface {
    fun displayPlayer (player: Player)
    fun displayCreationMessage(message: String)
    fun displayPlayerCreationError(message: String)
    fun displayChooseTrickMessage(message: String)
    fun displayPlayerHand(message: String, hand: Hand)


}