package Model.Player

abstract class Player(
      var name: String,
      var hand : Hand? = null,
) {
      var score : Int = 0
}
