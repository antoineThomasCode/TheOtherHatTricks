package Model.Card

import Model.Game.Board
import Model.Player.Hand
import Model.Player.Player

enum class PropCard(
    var title: String,
    var isHidden: Boolean
) {

    RABBIT("The Rabbit", true),
    OTHERRABBIT("The Other Rabbit", true),
    CARROTS("Carrots", true),
    CARROTS1("Carrots", true),
    CARROTS2("Carrots", true),
    LETTUCE("The Lettuce", true),
    HAT("The Hat", true);

    fun isSameCard(otherCard: PropCard): Boolean {
        return this.title == otherCard.title
    }

    companion object {

        fun dealCard(players: List<Player>): PropCard {

            val cardPile = entries.toMutableList()
            cardPile.shuffle()

            players.forEach { player ->
                val firstCard = cardPile.removeFirst()
                val secondCard = cardPile.removeFirst()
                player.hand = Hand(firstCard, secondCard)
            }

            return cardPile.first()
        }
    }
}
