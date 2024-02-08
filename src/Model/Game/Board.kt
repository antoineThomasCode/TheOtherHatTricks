package Model.Game

import Model.Card.PropCard
import Model.Card.TrickCard


class Board(
    var theSeventhProp: PropCard
) {
    val trickDeck: MutableList<TrickCard> = TrickCard.getTrickDeck()
    private val trickPile: MutableList<TrickCard> = mutableListOf(trickDeck.removeLast())

    fun swapTrick() = trickPile.add(trickDeck.removeLast())

    fun getVisibleTrick() = trickPile.last()

    fun announceVisibleTourCard() =
        println("\nLa carte de tour est actuellement : ${trickDeck[trickDeck.size - 1]}")
}