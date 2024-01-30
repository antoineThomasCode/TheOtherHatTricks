package card

sealed class PropCard(
     title: String,
     isHidden: Boolean
) : Card(title, isHidden) {

    // voir pourquoi dans les sealed class les object sont des data object
    object Rabbit : PropCard("The Rabbit", true)
    object OtherRabbit : PropCard("The Other Rabbit", true)
    object Carrot: PropCard("Carrots", true)
    object Lettuce : PropCard("The Lettuce", true)
    object Hat: PropCard("The Hat", true)


}