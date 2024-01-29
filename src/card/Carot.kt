package card

import player.Player
import java.awt.Image

sealed class Carot(
    title: String,
    owner: Player,
    isHidden: Boolean
) : PropCard(title, isHidden) {

}