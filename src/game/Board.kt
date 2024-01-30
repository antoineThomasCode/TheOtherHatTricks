package game

import card.Card
import card.PropCard
import card.TrickCard
import kotlin.random.Random


class Board(

    private val trickList: MutableList<TrickCard> = mutableListOf(),
    private val propCardList: MutableList<PropCard> = mutableListOf(),
    var theSeventThProp: PropCard? = null

) {
    var visibleTrickyCard : TrickCard? = null

    init {
        propCardList.add(PropCard.Rabbit)
        propCardList.add(PropCard.OtherRabbit)
        propCardList.add(PropCard.Carrot)
        propCardList.add(PropCard.Carrot)
        propCardList.add(PropCard.Carrot)
        propCardList.add(PropCard.Lettuce)
        propCardList.add(PropCard.Hat)

        trickList.add(TrickCard.HungryRabbit)
        trickList.add(TrickCard.BunchOfCarrots)
        trickList.add(TrickCard.VegetablePatch)
        trickList.add(TrickCard.RabbitThatDidntLikeCarrot)
        trickList.add(TrickCard.PairOfRabbits)
        trickList.add(TrickCard.VegetableHatTrick)
        trickList.add(TrickCard.CarrotHatTrick)
        trickList.add(TrickCard.SlightlyEasierHatTrick)
        trickList.add(TrickCard.HatTrick)
        trickList.add(TrickCard.OtherHatTrick)
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
    fun returnLastTrickCard() {
        if (trickList.isNotEmpty()) {
            visibleTrickyCard = trickList.last()
            trickList.removeAt(trickList.size - 1)
        } else {
            throw IllegalStateException("désolé la partie est fini, comptons les points")
            // TODO implémentez ici la fin de game du coup c'est qu'on dépasser les 10 tours
        }
    }

    fun getHand(): Hand {

        // gère la logique ici pour distribuer les cartes dans les hand des joueurs mais avec toujours une carte à l'endroit et une à l'envers
        // 1ere carte
        val firstCardIndex = Random.nextInt(propCardList.size)
        val firstCard = propCardList.removeAt(firstCardIndex)

        // 2ème carte
        val secondCardIndex = Random.nextInt(propCardList.size)
        val secondCard = propCardList[secondCardIndex].apply { isHidden = false }
        propCardList.removeAt(secondCardIndex)

        return Hand(firstCard, secondCard)
    }

    fun setupSeventThProp() {
        // quand il reste une caret après distribution alors je l'utilise comme 7eme PropCard
        if (propCardList.size == 1) {
            theSeventThProp = propCardList.first()
        } else {
            throw IllegalStateException("il y'a trop de carte pour faire cette action")
        }
    }

}