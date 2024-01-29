package player

import card.PropCard
import game.Hand

class RealPlayer(

    override val name: String,
    override var hand : Hand? = null

) : Player(name) {



}