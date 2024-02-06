package Model.Game

import Model.Card.PropCard
import Model.Card.TrickCard


class Board(
    private var theSeventhProp: PropCard
) {
    val trickDeck: MutableList<TrickCard> = TrickCard.getTrickDeck()
    private val trickPile: MutableList<TrickCard> = mutableListOf(trickDeck.removeLast())
//    private var theSeventhProp = theSeventhProp

    fun piocheTrick() = trickDeck.removeLast()

    fun announceVisibleTourCard() =
        println("La carte de tour est actuellement : ${trickDeck[trickDeck.size - 1].title}")

    fun announceTourCardCombinations() {
        val visibleCard = trickDeck[trickDeck.size - 1]
        println("Pour marquez les ${visibleCard.value} points de ce tour vous devez coupler les accessoires suivant :")
        visibleCard.combinationFirstPart.first.let {
            println(it.title)
        }
        visibleCard.combinationFirstPart.second?.let {
            println("ou\n${it.title}")
        }

        println("et")

        visibleCard.combinationSecondPart.first.let {
            println(it.title)
        }
        visibleCard.combinationSecondPart.second?.let {
            println("ou\n${it.title}")
        }
    }


}