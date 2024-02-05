package Model.Game

import Model.Card.TrickCard

import Model.Card.PropCard


class Board() {
    private val trickDeck: MutableList<TrickCard> = TrickCard.getTrickDeck()
    private val trickPile: MutableList<TrickCard> = mutableListOf(trickDeck.removeLast())
    var theSeventThProp: PropCard? = null
    fun setupBoard () {


    }
    fun piocheTrick () = trickDeck.removeLast()

    fun announceVisibleTourCard() = trickDeck[trickDeck.size - 1].title



}