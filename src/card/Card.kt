package card

import player.Player
import java.awt.Image

abstract class Card(
    val title: String,
    var isHidden: Boolean // j'ai du repasser en public car j'en ai besoin dans ma Class Board
)