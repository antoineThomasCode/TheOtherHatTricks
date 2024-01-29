package game

import card.Card
import card.PropCard
import card.TrickCard
import kotlin.random.Random


class Board(

    val trickList: MutableList<TrickCard> = mutableListOf(),
    val propCardList: MutableList<PropCard> = mutableListOf(),
    var theSeventThProp: PropCard? = null

) {

    init {
        // construction de ma trick liste sur le plateau
        propCardList.add(PropCard("The Rabbit", true))
        propCardList.add(PropCard("The Other Rabbit", true))
        propCardList.add(PropCard("Carrots", true))
        propCardList.add(PropCard("Carrots", true))
        propCardList.add(PropCard("Carrots", true))
        propCardList.add(PropCard("The Lettuce", true))
        propCardList.add(PropCard("The Hat", true))

        // contruction de ma propcardlist sur le plateau
        trickList.add(TrickCard("The Hungry Rabbit", true, 1))
        trickList.add(TrickCard("The Bunch of Carrots", true, 2))
        trickList.add(TrickCard("Vegetable Patch", true, 3))
        trickList.add(TrickCard("The Rabbit That Didn't Like Carrot", true, 4))
        trickList.add(TrickCard("The Pair Of Rabbits", true, 5))
        trickList.add(TrickCard("The Vegetable Hat Trick", true, 2))
        trickList.add(TrickCard("The Carrot Hat Trick", true, 3))
        trickList.add(TrickCard("The Slighty Easier Hat Trick", true, 4))
        trickList.add(TrickCard("The Hat Trick", true, 5))
        trickList.add(TrickCard("The Other Hat Trick", true, 6))

    }

    fun prepareBoard() {

        trickList.shuffle()

        val otherHatTrickIndex = trickList.indexOfFirst { it.title == "The Other Hat Trick" }
        if (otherHatTrickIndex != -1) {
            val otherHatTrick = trickList.removeAt(otherHatTrickIndex)
            trickList.add(0, otherHatTrick)
        }

        trickList.last().isHidden = false

        propCardList.shuffle()
    }

    fun getHand(): Hand {

        // Sélectionner et retirer la première carte (isHidden = true)
        val firstCardIndex = Random.nextInt(propCardList.size)
        val firstCard = propCardList.removeAt(firstCardIndex)

        // Sélectionner et préparer la deuxième carte (isHidden = false)
        val secondCardIndex = Random.nextInt(propCardList.size)
        val secondCard = propCardList[secondCardIndex].apply { isHidden = false }
        propCardList.removeAt(secondCardIndex)

        return Hand(firstCard, secondCard)
    }

    fun setupSeventThProp() {
        if (propCardList.size == 1) {
            theSeventThProp = propCardList.first()
        } else {
            throw IllegalStateException("il y'a trop de carte pour faire cette action")
        }
    }

}