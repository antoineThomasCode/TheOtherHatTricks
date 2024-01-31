package Model.Game

import Model.Card.TrickCard
import Model.Player.Hand
import Model.Card.PropCard
import Model.Player.Player
import kotlin.random.Random


class Board(

    private val trickList: MutableList<TrickCard> = mutableListOf(),
    private val propCardList: MutableList<PropCard> = mutableListOf(),
    var theSeventThProp: PropCard? = null

) {
    var visibleTrickyCard : TrickCard? = null

    init {
        // TODO
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

    fun getHand(players : List<Player>) {

        propCardList.shuffle()
        if (propCardList.size < 2) {
            throw IllegalStateException("Pas assez de cartes pour distribuer une main")
        }
        players.forEach { player ->
            val hiddenCard = propCardList.removeAt(0).apply { isHidden = true }
            val visibleCard = propCardList.removeAt(0).apply { isHidden = false }
            println("voici les cartes de ${player.name}")
            println(hiddenCard.title)
            println(hiddenCard.isHidden)
            println(visibleCard.title)
            println(visibleCard.isHidden)


            player.hand = Hand(hiddenCard, visibleCard)

        }



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