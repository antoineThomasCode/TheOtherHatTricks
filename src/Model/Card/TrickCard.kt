package Model.Card

enum class TrickCard(
    val title: String,
    val value: Int,
    val combinationFirstPart: Pair<PropCard, PropCard?>,
    val combinationSecondPart: Pair<PropCard, PropCard?>
) {


    HUNGRYRABBIT(
        title = "The Hungry Rabbit",
        value = 1,
        combinationFirstPart = Pair(PropCard.RABBIT, PropCard.OTHERRABBIT),
        combinationSecondPart = Pair(PropCard.LETTUCE, PropCard.CARROTS)
    ),
    BUNCHOFCARROTS(
        "The Bunch of Carrots",
        value = 2,
        combinationFirstPart = Pair(PropCard.CARROTS, null),
        combinationSecondPart = Pair(PropCard.CARROTS, null)
    ),

    VEGETABLEPATCH(
        "Vegetable Patch",
        value = 3,
        combinationFirstPart = Pair(PropCard.CARROTS, null),
        combinationSecondPart = Pair(PropCard.LETTUCE, null)
    ),
    RABBITTHATDIDNTLIKECARROTS(
        "The Rabbit That Didn't Like Carrot",
        value = 4,
        combinationFirstPart = Pair(PropCard.RABBIT, PropCard.OTHERRABBIT),
        combinationSecondPart = Pair(PropCard.LETTUCE, null)
    ),

    PAIROFRABBIT(
        "The Pair Of Rabbits",
        value = 5,
        combinationFirstPart = Pair(PropCard.RABBIT, null),
        combinationSecondPart = Pair(PropCard.OTHERRABBIT, null)
    ),
    VEGETABLEHATTRICK(
        "The Vegetable Hat Trick",
        value = 2,
        combinationFirstPart = Pair(PropCard.RABBIT, PropCard.OTHERRABBIT),
        combinationSecondPart = Pair(PropCard.LETTUCE, PropCard.CARROTS)
    ),
    CARROTHATTRICK(
        "The Carrot Hat Trick",
        value = 3,
        combinationFirstPart = Pair(PropCard.HAT, null),
        combinationSecondPart = Pair(PropCard.LETTUCE, PropCard.CARROTS)
    ),
    SLIGHTLYEASIERHATTRICK(
        "The Slighty Easier Hat Trick",
        value = 4,
        combinationFirstPart = Pair(PropCard.HAT, null),
        combinationSecondPart = Pair(PropCard.RABBIT, PropCard.OTHERRABBIT)
    ),

    HATTRICK(
        "The Hat Trick",
        value = 5,
        combinationFirstPart = Pair(PropCard.HAT, null),
        combinationSecondPart = Pair(PropCard.RABBIT, null)
    ),

    OTHERHATTRICK(
        "The Other Hat Trick",
        value = 6,
        combinationFirstPart = Pair(PropCard.HAT, null),
        combinationSecondPart = Pair(PropCard.OTHERRABBIT, null)
    );

    companion object {
        fun getTrickDeck(): MutableList<TrickCard> {
            val trickDeck: MutableList<TrickCard> = entries.toMutableList()
            trickDeck.remove(OTHERHATTRICK)
            trickDeck.shuffle()
            trickDeck.add(0, OTHERHATTRICK)
            return trickDeck
        }
    }
}