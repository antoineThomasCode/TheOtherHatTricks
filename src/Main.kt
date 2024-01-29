import game.Board
import game.Party


fun main() {


    // Créer une instance de Party
    val party = Party(board = Board())

    // Initialiser et démarrer le jeu
    party.initialiseGame()
    party.start()
    party.playTour()

}
