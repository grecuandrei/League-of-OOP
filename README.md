# League-of-OOP
Project Part1

Text : http://elf.cs.pub.ro/poo/teme/proiect/etapa1


	Structure : 
		main package:
			Main->Game

		map package:
			Map

		heroes package:
			HeroFactory --->  Hero --> Pyromancer
						\---> Knight
						 \---> Wizard
						  \--->	Rogue

		angels package:
			AngelsFactory ---> Angel --> DamageAngel
						 \---> DarkAngel
						  \---> Dracula
						   \---> GoodBoy
						    \---> etc
										
		magician package:
			Observer ---> GreatMagician
			SubjectAngel
			SubjectHero
			
		strategy package:
			HeroStrategy ---> Strategy1
					 \---> Strategy2
					  \---> DefaultStrategy
			
		constants package:
			Constants
	
	Used Patterns:
		Factory
		Singleton
		Strategy
		Observer
		Visitor - in DoubleDispatch
	
	Explanation:

		Main: 

			Creates a Game object and starts the game by calling the method playGame
		that uses the arguments to parse the Inputfile and Outputfile

		Map:

			A singleton implementation of the terrain that is under the heroes feet.
			
			It has 2 methods, one that adds from input in the map the terrain and one
		to get terrain at a certain position in the matrix.

		Game:
			playGame:

			The playGame method uses the InputFile to read all the data that it needs
		and store the map in a map instance, heroes in an array of heroes, same for angels.
			
			In a for after the number of rounds, first the players take the overtime damage.
			
			Then they choose a strategy and move on the map.
			
			Next, we try to match every player to see if they have the same coordinates
		as a another player. If so, they fight. (in the battles method)
			
			If they fight, first we calculate the base damage inflicted to one another.

			Then we calculate the damage, using Double Dispatch [1], for the heroes that are fighting.
			
			Apply the damage, level them up and reset the hp if needed or notify the magician
		if they are dead.

			Before the end of the round, the angels come in play and the magician
		is notified accordingly. (applyAngels method)
		
			At the end of every round, print the output in the OutputFile.

			moveHero:

			Moves a hero according to the direction got from input.

			applyOvertimeDmg:

			Apply to a hero the damage inflicted from an overtime attack.

		HeroFactory:

			Implementation of the Factory pattern to create a Hero, without exposing
		the creation logic.

		Hero:
			roundsApply : to store how many rounds the overtime damage should be applied
			
			dmgInflicted : to store the damage dealt by another hero, used by Wizard

			dealDMG:
				Method that applies the damage to a certain hero.
				And set the xp for the hero that won the fight, if he won.

			xpWinner:
				Method that updates the xp of a hero.

			levelUP:
				Method that levels up a hero, and if is not dead, reset its hp to new max.

		
		Pyromancer:

			In constructor the pyro initializes his hp, baseHp (that will be altered later),
		the multipier for level up hp and name.

			acceptAttack:
			A method that accepts a "visitor", a hero to attack this player.

			attackFireblast:
			Calculates the damage, with the terrain modifier, for the fireblast attack.

			attackIgnite:
			Calculates the damage, with the terrain modifier, for the ignite attack.

			calculateFlatDmg:
			Calculates the base damage, without the race modifiers, for wizard to use.

			attack(Heroes):
			For every hero, a method is implemented to modify the damage accordingly to the
			race modifiers.

			modifyOvtDmg:
			Calculate the ignite damage and set for the player the right parameters. 
			He is ignited, 2 rounds, with said damage.

		Rogue, Knight, Wizard:

			Implement the same methods, only they have different specific modifiers.
			Worth to mention:
				Wizard - uses the damage dealt to him to calculate the damage for the other.
					- it doesn't have overtime modifiers
				Knight - when he uses execute, if it does damage above a certain hp limit,
					he kills the other player instantly
					- it incapacitates the other hero for one round
				Rogue - when he uses backstab, he can deal critical hits every 3 rounds when
					he uses the attack
					- it paralyzes the victim and does damage overtime for 3 or 6 rounds,
					depending on the terrain he is on
		
		AngelsFactory:

			Implementation of the Factory pattern to create an Angel, without exposing
		the creation logic.
		
		SubjectAngel/SubjectHero:
		
			They are interfaces to help make a difference between subject in the Observer pattern 
		
		HeroStrategy ---> Strategy1/Strategy2/DefaultStrategy
		
			Each class, that implements the HeroStrategy interface, has a method (modifyHero) that applies
		modifications according to the strategy chosen by every hero at the start of a round.
		
		Constants:

			File that has every constant used in the implementation.

		[1]: Double Dispatch
	
			So this is how I implemented it:

			I coded this keeping in mind that I should not go on a different path and end up using Single Dispatch.
			
			Let's take an example:

			I have the Hero class that is extended by Rogue (h1 from the code) and Pyro (h2 from the code).

			When we are in a round and those two are fighting, we calculate the damage given from one
		to another(dmgPlayer1).

			h1 accepts the attack from h2. In h1, the method uses h2 and calls the method attack
		(like this: h2.attack(this))
		
			applied to his instance, personalizing the attack. In h2, the attack takes place,
		calculating the damage for the specific instance of h1.
			
			h1.acceptAttack(h2) --> h2.attack(this) --> attack(h1) in h1

			That's how I implemented the Double Dispatch in my code that respects this modified version of visitor pattern.

