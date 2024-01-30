package player

import card.PropCard
import game.Hand

abstract class Player(
      var name: String,
      var hand : Hand? = null
)
