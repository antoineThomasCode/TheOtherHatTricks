package Controller.userAction

import Model.Player.Hand

interface PlayerAction  {
    fun onNameAttribued (name : String)
    fun onTrickChoose(hand: Hand)
}