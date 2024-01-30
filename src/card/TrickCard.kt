package card

sealed class TrickCard(
    title: String,
    isHidden: Boolean,
    val value: Int,
    val combinationFirstPart : List<PropCard>,
    val combinationSecondPart : List<PropCard>
) : Card(title, isHidden) {

    object HungryRabbit : TrickCard(
        "The Hungry Rabbit",
        true,
        1,
        combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
        )

    object BunchOfCarrots : TrickCard(
        "The Bunch of Carrots",
        true,
        2,
        combinationFirstPart = listOf(PropCard.Carrot),
        combinationSecondPart = listOf(PropCard.Carrot)
    )
    object VegetablePatch : TrickCard(
        "Vegetable Patch",
        true,
        3,
        combinationFirstPart = listOf(PropCard.Carrot),
        combinationSecondPart = listOf(PropCard.Lettuce)
    )
    object RabbitThatDidntLikeCarrot :
        TrickCard("The Rabbit That Didn't Like Carrot",
            true,
            4,
            combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
            combinationSecondPart = listOf(PropCard.Lettuce)
        )

    data object PairOfRabbits : TrickCard(
        "The Pair Of Rabbits",
        true,
        5,
        combinationFirstPart = listOf(PropCard.Rabbit),
        combinationSecondPart = listOf(PropCard.OtherRabbit)
    )
    object VegetableHatTrick : TrickCard(
        "The Vegetable Hat Trick",
        true,
        2,
        combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
    )
    object CarrotHatTrick : TrickCard(
        "The Carrot Hat Trick",
        true,
        3,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
    )
    object SlightlyEasierHatTrick :
        TrickCard("The Slighty Easier Hat Trick",
            true,
            4,
            combinationFirstPart = listOf(PropCard.Hat),
            combinationSecondPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit)
        )

    object HatTrick : TrickCard(
        "The Hat Trick",
        true,
        5,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.Rabbit)
    )
    object OtherHatTrick : TrickCard(
        "The Other Hat Trick",
        true,
        6,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.OtherRabbit)
    )
}