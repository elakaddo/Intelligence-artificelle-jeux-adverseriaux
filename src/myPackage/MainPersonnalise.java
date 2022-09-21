package jeu;

import java.util.Scanner;

/**
*@author Akaddar Mohamed
*@date 15/02/2022
*/

public class MainPersonnalise {

	public static void main(String[] args)
	{
		System.out.println("* * * * * * * * * * * * * * * * * *\n*  *  Jeu d'infection           * *\n* * * * * * * * * * * * * * * * * *");
		System.out.println("\nC'est partie \n\n");
		
		State jeu = new State();
		Move move = new Move();
		
		//Minimax
		Minimax minBlue= new Minimax("B", Integer.parseInt(args[0]));
		Minimax minRouge = new Minimax("R", Integer.parseInt(args[1]));
		
		//Alphabeta
		Alphabeta alphaBlue = new Alphabeta("B", Integer.parseInt(args[0]));
		Alphabeta alphaRouge = new Alphabeta("R", Integer.parseInt(args[1]));
		
		//Negamax
		Negamax negBlue = new Negamax("B", Integer.parseInt(args[0]));
		Negamax negRouge = new Negamax("R", Integer.parseInt(args[1]));
		

		String joueur;
		Scanner sc  = new Scanner(System.in);
		int i =0;
		System.out.println("RAPPEL : [ceci est un JEU ALEATOIRE]");
		while(!jeu.isOver())
		{
			System.out.println("\n---------------\n Tour : "+ ++i +"   \n---------------\n");
			joueur = jeu.getJoueurCourant();
			if(joueur=="B")
			{	
				if(Boolean.parseBoolean(args[2])==true)
					move = alphaBlue.getBestMove(jeu);
					//Si vous voulez choisir d'utiliser la Version Negmax
					//negBlue.getBestMove(jeu)
				else
					move = negBlue.getBestMove(jeu);
					//Si vous voulez choisir d'utiliser Minimax
					//move = minBlue.getBestMove(jeu);
					
					
			}
			else
			{
				if(Boolean.parseBoolean(args[2])==true)
					move = alphaRouge.getBestMove(jeu);
					//Si vous voulez choisir d'utiliser la Version Negmax
					//negRouge.getBestMove(jeu)
				else
					
					move = negBlue.getBestMove(jeu);
					//Si vous voulez choisir d'utiliser Minimax
					//move = minRouge.getBestMove(jeu);
					
			}
			jeu = jeu.play(move);
			System.out.println(jeu.AfficherGrille());	
		}
		System.out.println("Wow "+jeu.getGagnant()+" est le Gagnant !!");

	}


}
