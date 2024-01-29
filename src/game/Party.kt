package game

import player.Player
import player.RealPlayer
import player.VirtualPlayer


class Party(
    private val players: MutableList<Player> = mutableListOf(),
    private val board: Board
) {
    fun initialiseGame() {
        // je créé d'abord les joueurs
        for (i in 1..3) {
            println("Entrez le nom du joueur $i (ou 'IA' pour ajouter un joueur virtuel) :")
            val playerName = readLine()

            val player = if (playerName.equals("IA", ignoreCase = true)) {
                VirtualPlayer("Joueur Virtuel $i")
            } else {
                RealPlayer(playerName ?: "Joueur $i")
            }

            players.add(player)
        }
        // je met en route le tri des tas dans le board
        board.prepareBoard()
        // lister les cartes pour vérifier le tri
        //println("Cartes de TrickList :")
        //board.trickList.forEach { trickCard ->
        //    println("Titre : ${trickCard.title}, Cachée : ${trickCard.isHidden}")
        //}

        // Afficher les cartes de propCardList
        //println("Cartes de PropCardList :")
        //board.propCardList.forEach { propCard ->
        //    println("Titre : ${propCard.title}, Cachée : ${propCard.isHidden}")
        //}

        // je distribue les hand de chaque joueur
        distribueHand()
        board.setupSeventThProp()

        println("carte distribuées !")

        // je start la game
        start()

    }

    fun distribueHand() {
        players.forEach { player ->
            player.hand = board.getHand()
            println(player.hand!!.propCardOne)
            println(player.hand!!.propCardOne.isHidden)
            println(player.hand!!.propCardTwo)
            println(player.hand!!.propCardTwo.isHidden)
        }
    }

    fun start() {
        println("Le jeu commence !")
    }

    fun playTour() {
        // Logique pour jouer un tour
    }
}