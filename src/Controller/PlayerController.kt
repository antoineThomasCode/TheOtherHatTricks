package Controller

import Controller.userAction.PlayerAction
import Model.Game.Game
import Model.Player.Hand
import Model.Player.RealPlayer
import Model.Player.VirtualPlayer
import View.PlayerCommandInterface

class PlayerController(
    val game : Game
) : PlayerAction {

    private lateinit var playerViewCommand : PlayerCommandInterface
    fun setPlayerViewCommand (playerViewCommandInterface: PlayerCommandInterface) {
        this.playerViewCommand = playerViewCommandInterface
    }
    fun startPlayerCreation () {
        this.playerViewCommand.displayCreationMessage("Entrez le nom d'un joueur  (ou 'IA' pour ajouter un joueur virtuel) :")
    }
    fun makeTrickChoice () {

    }

    override fun onNameAttribued(name: String) {
        val player = if (name.equals("IA", ignoreCase = true)) {
            VirtualPlayer("Joueur IA ")
        } else {
            RealPlayer(name)
        }
        val playerCreated = game.createPlayer(player)
        if (playerCreated == null ) {
           this.playerViewCommand.displayPlayerCreationError("Désolé le joueur n'a pas pu être créé")
        }

        if (!game.isPlayerAllCreated()) {
            this.playerViewCommand.displayCreationMessage("Entrez le nom d'un joueur  (ou 'IA' pour ajouter un joueur virtuel) :")
        }
    }

    override fun onTrickChoose(hand: Hand) {
        this.playerViewCommand.displayPlayerHand("Voici les cartes dans votre main", hand)
        this.playerViewCommand.displayChooseTrickMessage("Voulez vous tenter de marquer ce tour ou changer de tour ?")
    }


}