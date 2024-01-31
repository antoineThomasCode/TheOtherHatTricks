package Model.Card

abstract class Card(
    val title: String,
    var isHidden: Boolean = true  // j'ai du repasser en public car j'en ai besoin dans ma Class Board
)