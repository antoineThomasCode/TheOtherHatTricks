package Model.Game

import Model.Card.PropCard
import Model.Card.TrickCard


class Board(
    var theSeventhProp: PropCard
) {
    val trickDeck: MutableList<TrickCard> = TrickCard.getTrickDeck()
    private val trickPile: MutableList<TrickCard> = mutableListOf(trickDeck.removeLast())

    fun swapTrick() = trickPile.add(trickDeck.removeLast())

    fun getVisibleTrick(): TrickCard? = trickPile.lastOrNull()


    fun announceVisibleTourCard() {
        if (trickDeck.isEmpty()) {
            println("\nIl n'y a plus de cartes.")
        } else {
            println("\nLa carte de tour est actuellement : ${trickDeck.last()}")
        }
    }

}