package jeu;
import java.util.Set;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Mohamed Akaddar
 * @date 27 janvier 2022
 * */
 
public class Demo {

	public Demo() {
		
	}
	
	/**
	 * @param set la liste des coups.
	 * @return un coup aleatoire.
	 * */
	public static Move RandomMove(List<Move> set)
	{
		int size = set.size();
		if(size==0)
		{
			return null;
		}
		int item = new Random().nextInt(size);
		Move move = new Move();
		int i = 0;
		for(Move m : set)
		{
			if(i == item)
			{
				move = m;
				break;
				
			}
			i++;
		}
		return move;
	}
	
	public static void main(String[] args)
	{
		
		System.out.println("* * * * * * * * * * * * * * * * * *\n*  *  Jeu d'infection           * *\n* * * * * * * * * * * * * * * * * *");
		System.out.println("\nC'est partie \n\n");
		
		State jeu = new State();
		Move move = new Move();
		Alphabeta alpha = new Alphabeta(jeu.getJoueurCourant(), 3);
		Negamax neg = new Negamax(jeu.getJoueurCourant(), 3);
		String joueur;
		Scanner sc  = new Scanner(System.in);
		int i =0;
		System.out.println("RAPPEL : [1:vous êtes le R] [2: les cordonnées x et y sont des entiers naturels]");
		while(!jeu.isOver())
		{
			System.out.println("\n---------------\n Tour : "+ ++i +"   \n---------------\n");
			joueur = jeu.getJoueurCourant();
			if(joueur == "B")
			{
				
				move = alpha.getBestMove(jeu);
			}
			else
			{	
				int xdep;
				int ydep;
				int yArr;
				int xArr;
				boolean est;
				int indicateur =0;
				Move m;
				do
				{
					if(indicateur>0)
					{
						System.out.println("Attention !! Mouvement incorrect les Cordonnées d'arrivé sont dehors 'Clonage et saute' \nMerci de donner un Mouvement correct");
					}
					System.out.println("Saisir x de départ h : ");
					xdep = sc.nextInt();
					System.out.println("Saisir y de départ : ");
					ydep = sc.nextInt();
					System.out.println("Saisir x d'arrivé :");
					xArr = sc.nextInt();
					System.out.println("Saisir y d'arrivé :");
					yArr = sc.nextInt();
					System.out.println("Saisir estClone [true ou false] :");
					est = sc.nextBoolean();
					m = new Move(xdep, ydep,xArr, yArr, est);
					indicateur++;
					
				}while(!jeu.getMoves(jeu.getJoueurCourant()).contains(m));
				move = m;
				
				
			}
			
			
			jeu = jeu.play(move);
			System.out.println(jeu.AfficherGrille());
			
			
		}
		System.out.println("Wow "+jeu.getGagnant()+" est le Gagnant !!");

	}
}
