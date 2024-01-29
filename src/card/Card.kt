package card

import player.Player
import java.awt.Image

abstract class Card(
    open val title: String,
    open var isHidden: Boolean
) {

}