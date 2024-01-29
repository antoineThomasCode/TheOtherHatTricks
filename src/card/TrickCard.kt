package card

import player.Player
import java.awt.Image

 class TrickCard(
     override val title: String,
     override var isHidden: Boolean,
     val value: Int
) : Card(title, isHidden){

}