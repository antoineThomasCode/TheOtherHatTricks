package Model.Card

sealed class TrickCard(
    title: String,
    isHidden: Boolean = true,  // isHidden est optionnel et a une valeur par défaut
    val value: Int,
    val combinationFirstPart: List<PropCard>,
    val combinationSecondPart: List<PropCard>
) : Card(title, isHidden) {

    // Exemple d'objet TrickCard
    object HungryRabbit : TrickCard(
        title = "The Hungry Rabbit",
        value = 1,
        combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
    )



    object BunchOfCarrots : TrickCard(
        "The Bunch of Carrots",
        value = 2,
        combinationFirstPart = listOf(PropCard.Carrot),
        combinationSecondPart = listOf(PropCard.Carrot)
    )
    object VegetablePatch : TrickCard(
        "Vegetable Patch",
        value = 3,
        combinationFirstPart = listOf(PropCard.Carrot),
        combinationSecondPart = listOf(PropCard.Lettuce)
    )
    object RabbitThatDidntLikeCarrot :
        TrickCard("The Rabbit That Didn't Like Carrot",
            value = 4,
            combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
            combinationSecondPart = listOf(PropCard.Lettuce)
        )

     object PairOfRabbits : TrickCard(
        "The Pair Of Rabbits",
        value = 5,
        combinationFirstPart = listOf(PropCard.Rabbit),
        combinationSecondPart = listOf(PropCard.OtherRabbit)
    )
    object VegetableHatTrick : TrickCard(
        "The Vegetable Hat Trick",
        true,
        value = 2,
        combinationFirstPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
    )
    object CarrotHatTrick : TrickCard(
        "The Carrot Hat Trick",
        value = 3,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.Lettuce, PropCard.Carrot)
    )
    object SlightlyEasierHatTrick :
        TrickCard("The Slighty Easier Hat Trick",
            value = 4,
            combinationFirstPart = listOf(PropCard.Hat),
            combinationSecondPart = listOf(PropCard.Rabbit, PropCard.OtherRabbit)
        )

    object HatTrick : TrickCard(
        "The Hat Trick",
        value = 5,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.Rabbit)
    )
    object OtherHatTrick : TrickCard(
        "The Other Hat Trick",
        value = 6,
        combinationFirstPart = listOf(PropCard.Hat),
        combinationSecondPart = listOf(PropCard.OtherRabbit)
    )
}