package player

import card.PropCard
import game.Hand

abstract class Player(
    open val name: String,
    open var hand : Hand? = null
) {


}
