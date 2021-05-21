/** Author: Russell Roberts Email: russellroberts51@yahoo.com */
/** This program is a very simple combat system.
It is not way to far into depth yet as it only deals
with the damages. It is kind of modeled after a Runescape
style of combat. With each type of combat having advantages
against others.
 */

package com.bezkoder.kotlin.mutablelist
class Game {

    /** initialize all the private variables we will use
    determine whether they need to be public or not. For
    our uses all will be private as we will not need to
    access the values from these variables*/

    private val maxPlayers = 5
    private var numPlayers = 0
    private var turn = 0
    private var numPlayerDead = 0
    private var mageAttack = 5
    private var archerAttack  = 4
    private var meleeAttack = 3
    private var defenderDamage = 0
    private var attackerDamage = 0
    private var defenderPlayer = 0
    private var playerClass = IntArray(maxPlayers)
    private var playersHealth = IntArray(maxPlayers)
    private val playersNames = mutableListOf<String>("","","","","")

    /** This function will tell us how many users there are. Also enabling us to set
     * up the system for keeping track of the turns.*/
    fun numberOfUsers()
    {
        println("How many players will be playing? Keep in mind only 5 players can play!")
        numPlayers = Integer.valueOf(readLine())
    }

/** This function will get the name of all the different users
 * storing it into a mutable list of strings.*/
    fun nameOfUsers()
    {
        var count = 0
        var name = ""
        for (i in 1..numPlayers){
            println("Enter the name of player $i: ")
            name = readLine()!!
            playersNames[count] = name
            count += 1
        }
    }
/** This function will let the user choose which class of combat
 * they wish to use. With each one them having their advantages.
 * We will then save into an array of ints.*/
    fun classChoice() {
        var count = 0
        for (i in 1..numPlayers) {
            println("Player $i")
            println("Welcome which class would you like to be?")
            println("1 - archer")
            println("2 - melee")
            println("3 - mage")
            var choice = Integer.valueOf(readLine())
            choice.toInt()
            playerClass[count] = choice - 1
            count += 1
        }
    }
/** Who's turn it is will decide which player they want to
 * attack. This is the only private function. Due to the fact
 * main needs nothing from this function. */
        private fun fightingOption(): Int {
            println(" ")
            println("${playersNames[turn]} it is your turn.")
            println("Which player would you like to attack? Please enter number of player. eg (1 or 3).")
            var fightingOption = Integer.valueOf(readLine())
            defenderPlayer = fightingOption - 1

            return playerClass[fightingOption - 1]
        }
 /** This will be the whole entire combat system it will be in
  * when statements. It will the deduct the necessary values.*/

        fun combatSystem() {
            var defender = fightingOption()
            var attacker = playerClass[turn]

        /** First case if attacker is archer and each corresponding
         * combat styles defence.*/
            when (attacker) {
                0 -> {
                    when (defender) {
                        0 -> {

                            playersHealth[turn] -= archerAttack
                            playersHealth[defenderPlayer] -= archerAttack
                            attackerDamage = 4
                            defenderDamage = 4
                        }
                        1 -> {
                            playersHealth[turn] -= meleeAttack + 2
                            playersHealth[defenderPlayer] -= archerAttack - 1
                            attackerDamage = 3
                            defenderDamage = 5
                        }
                        2 -> {
                            playersHealth[turn] -= mageAttack - 2
                            playersHealth[defenderPlayer] -= archerAttack + 2
                            attackerDamage = 6
                            defenderDamage = 3
                        }
                    }


                }
                /** If the attacker has chosen melee as their attack style.
                 * With all of the corresponding defending types.*/
                1 -> {
                    when (defender) {
                        0 -> {

                            playersHealth[turn] -= archerAttack - 1
                            playersHealth[defenderPlayer] -= meleeAttack + 2
                            attackerDamage = 5
                            defenderDamage = 3
                        }
                        1 -> {
                            playersHealth[turn] -= meleeAttack
                            playersHealth[defenderPlayer] -= meleeAttack
                            attackerDamage = 3
                            defenderDamage = 3
                        }
                        2 -> {
                            playersHealth[turn] -= mageAttack + 2
                            playersHealth[defenderPlayer] -= meleeAttack + 2
                            attackerDamage = 5
                            defenderDamage = 7
                        }
                    }


                }
                /** Attack scenarios if the attacker is a mage. With once again
                 * all the different defending scenarios. */
                2 -> {
                    when (defender) {
                        0 -> {

                            playersHealth[turn] -= archerAttack + 2
                            playersHealth[defenderPlayer] -= mageAttack - 2
                            attackerDamage = 3
                            defenderDamage = 6
                        }
                        1 -> {
                            playersHealth[turn] -= meleeAttack + 2
                            playersHealth[defenderPlayer] -= mageAttack + 2
                            attackerDamage = 7
                            defenderDamage = 5
                        }
                        2 -> {
                            playersHealth[turn] -= mageAttack
                            playersHealth[defenderPlayer] -= mageAttack
                            attackerDamage = 5
                            defenderDamage = 5
                        }
                    }


                }
            }
        }

    /** This function will display the damage dealt during the turn
     * from the attacker and the defender.*/
        fun damageDealt()
        {
            println("Wow ${playersNames[turn]} dealt $attackerDamage damage to player ${playersNames[defenderPlayer]}.")
            println("He still has ${playersHealth[defenderPlayer]} health left.")
            println("The defender ${playersNames[defenderPlayer]} dealt $defenderDamage damage to " +
                    "player ${playersNames[turn]}.")
            println("He still has ${playersHealth[turn]} health left.")
        }
/** This function will populate each players health levels.*/
        fun generateHealth() {
            var count = 0
            for (i in 1..numPlayers) {
                playersHealth[count] = 20
                count += 1

            }
        }

    /** This function will keep track of which players turn it is
     * then it will just keep passing the turn variable around.*/
        fun turnCounter() {

            var listSize = numPlayers - numPlayerDead

                if(turn >= listSize - 1)
                {
                    turn = 0
                }
                else {
                    turn += 1
                }
            }



}
/** This function will act as the driver of the whole program */
fun main(){
    /** Initialize the class object*/
    val myGame = Game()

    /** Get the number of users.*/
    myGame.numberOfUsers()

    /** Get the users class choice*/
    myGame.classChoice()

    /** Get each users name*/
    myGame.nameOfUsers()

    /** Generate the health of each player*/
    myGame.generateHealth()

    /** Start the while loop with a sentinel*/
    var keepPlaying = 1
    while (keepPlaying != 0) {

        /** Actual combat system and actual main game loop */
        myGame.combatSystem()
        myGame.damageDealt()
        myGame.turnCounter()
        println("Would you like too keep playing 1 for yes 0 for no. ")
        keepPlaying = Integer.valueOf(readLine())

    }
}





