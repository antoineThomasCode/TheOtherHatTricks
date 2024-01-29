package player

import card.PropCard
import card.TrickCard
import game.Hand

class VirtualPlayer(

    override val name: String,
    override var hand : Hand? = null

) : Player(name, hand) {

    fun play() {
        // faire ici toute les étapes une à une d'un "tour" de joueur
    }

    private fun chooseAction() {

    }

     fun selectCardToExchange() {

         // sert à avoir une carte aléatoire à échanger faire attention à bine prendre une carte qui à le meme isHidden (revoir exactement dans les règles)

    }

    private fun makeTrickChoice(): Boolean {

        // savoir aléatoirement à mon avis si on marque le trick ou non
        return true
    }
}