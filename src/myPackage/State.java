package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Akaddar Mohamed
 * @date  28/01/2022
 * */


public class State 
{
	public final String joueurR = "R";
	public final String joueurB = "B";
	private int pionsR;
	private int pionsB;
	private String joueurCourant;
	
	
	/* * * * * * les joueurs  * * * * /


	/**
	 * @return pionsR nombre de pions rouges
	 * */
	public int getPionsR()
	{
		return this.pionsR;
	}

	/**
	 * @return pionsB nombre de pions bleus
	 * */
	public int getPionsB()
	{
		return this.pionsB;
	}
	
	/**
	 * @return joueurCourant le joueur actif
	 * */
	public String getJoueurCourant()
	{
		return this.joueurCourant;
	}
	
	public void changerJoueur()
	{
		if(this.joueurCourant==this.joueurB)
			this.joueurCourant=this.joueurR;
		else
			this.joueurCourant=this.joueurB;
	}
	
	
	
	/* * * *  La Grille de jeu * * * * */
	
	private final String[][] grille;
	
	/**
	 * Consructeur qui initialise la grille ainsi l'etat de jeu
	 * */
	public State()
	{
		this.grille = new String[7][7];
		this.joueurCourant = this.joueurB;
		this.grille[0][0] = this.joueurR;
		this.grille[0][6] = this.joueurB;
		this.grille[6][0] = this.joueurB;
		this.grille[6][6] = this.joueurR;
		this.pionsB=2;
		this.pionsR=2;
		
	}
	
	/**
	 *@return une copie de l'etat de jeu
	 */
	 public State copieState()
	 {
		 State s = new State();
		 s.pionsB = this.pionsB;
		 s.pionsR = this.pionsR;
		 s.joueurCourant = this.joueurCourant;
		 for (int i = 0; i < 7; i++)
		 {
	            for (int j = 0; j < 7; j++)
	            {
	                s.grille[i][j] = this.grille[i][j];
	            }
	     }
		 return s;
	 }
	
	@Override
	 public boolean equals(Object obj){
		if(this == obj) 
			return true;
		if( (obj == null) || (this.getClass() != obj.getClass()))
			return false;
		
		State s = (State) obj;
		if(!(this.joueurCourant.equals(s.joueurCourant)))
			return false;
		if(this.pionsB != s.pionsB) return false;
		if(this.pionsR != s.pionsR) return false;
		
		 for (int i = 0; i < 7; i++)
		 {
	            for (int j = 0; j < 7; j++)
	            {
	            	if((this.grille[i][j] == null && s.grille[i][j] != null) ||(this.grille[i][j]!=null && !this.grille[i][j].equals(grille[i][j])))
							return false;
	            
	            }
		 }return false;
			
	}
	 @Override
	 public int hashCode()
	 {
	     return Objects.hash(this.joueurCourant,this.pionsB,this.pionsR, Arrays.deepHashCode(this.grille));
	 }
	 
	 /**
	  * getMoves : retourne les mouvements possibles d'un joueur donne en parametre.
	  * @param Joueur : le joueur concerne
	  * @return List<Move> liste des mouvements
	  * */
	 public List<Move> getMoves(String Joueur)
	 {
		 List<Move> moves = new ArrayList<Move>();
		 int xDep, yDep, xArr, yArr;
		 for(xDep=0; xDep<7; xDep++)
		 {
			 for(yDep=0; yDep<7; yDep++)
			 {
				 if(this.grille[xDep][yDep]==null || !this.grille[xDep][yDep].equals(Joueur))
					 continue;
				 for(int k = -1; k<=1 ; k++)
				 {
					 for(int l = -1; l<=1 ; l++)
					 {
						// ici on va cloner
						 xArr = xDep + k;
						 yArr = yDep + l;
						 if(xArr >= 0 && xArr < 7 && yArr>=0 && yArr < 7 && this.grille[xArr][yArr]==null) 
							 moves.add(new Move(xDep ,yDep, xArr, yArr, true));
						 
						 //ici on va sauter
						 xArr = xDep + 2 * k;
						 yArr = yDep + 2 * l;
						 if(xArr >= 0 && xArr < 7 && yArr>=0 && yArr < 7 && this.grille[xArr][yArr]==null) 
							 moves.add(new Move(xDep ,yDep, xArr, yArr, false));
					 }
				 }
			 }
		 }return moves;
	 }

	 
	 /**
	  * getScore : retourne le score d'un joueur donne en parametre.
	  * @param Joueur : le joueur concerne
	  * @return Score de joueur donne
	  * */
	 public float getScore(String joueur)
	 {
		 float score=0f;
		 if(joueur == this.joueurB)
			 score = this.pionsB /(this.pionsB+this.pionsR);
		 if(joueur == this.joueurR)
			 score = this.pionsR /(this.pionsB+this.pionsR);
		
		 return score;
	 }
	 
	 
	 /**
	  * play : jouer un coup et retourner un nouvel etat de jeu
	  * @param Move le coup a joue
	  * @return nouvel etat
	  * */
	 public State play(Move move)
	 {
		 State s = copieState();
		 if(move != null)
		 {
			 String joueurInactif = (this.joueurCourant.equals(this.joueurB)?this.joueurR:this.joueurB);
			 int nbJoueurActif = 0;
			 int nbJoueurInactif = 0;
			 int xDep, yDep,xArr,yArr;
			 
			 xDep = move.getxDep();
			 yDep = move.getyDep();
			 xArr = move.getxArr();
			 yArr = move.getyArr();
			 
			 s.grille[xArr][yArr]=this.joueurCourant;
			 
			 // Si on clone on incremente le nombre de pions
			 if(move.getEstCloner())
				 nbJoueurActif++;
			 
			 //Si on saute on va liberer l'ancien place
			 if(!move.getEstCloner())
				 s.grille[xDep][yDep]=null;
			 
			 //L'infection :
			 for(int k = -1; k<=1; k++)
			 {
				 for(int l = -1; l<=1; l++)
				 {
					 if((xArr+k)>=0 && (xArr+k)<7 && (yArr+l)>=0 && (yArr+l)<7 && s.grille[xArr+k][yArr+l]!=null && s.grille[xArr+k][yArr+l].equals(joueurInactif))
					 {
						 s.grille[xArr+k][yArr+l] = this.joueurCourant;
						 nbJoueurActif++;
						 nbJoueurInactif--;
					 }
				 }
			 }
			 
			 if(this.joueurCourant.equals(this.joueurB))
			 {
				 s.pionsB += nbJoueurActif;
				 s.pionsR += nbJoueurInactif;
			 }
			 else
			 {
				 s.pionsR += nbJoueurActif;
				 s.pionsB += nbJoueurInactif;
			 }

				  
		 }
		 s.changerJoueur();
		 return s;
		 
	 }
	 
	 /**
	  * isOver() determine si la partie est terminee ou pas
	  * @return return true si la partie est terminee
	  * */
	 public boolean isOver()
	 {
		 if(this.pionsB==0 || this.pionsR==0 || (this.getMoves(this.joueurB).isEmpty() && this.getMoves(this.joueurR).isEmpty()) )
		 {
			 return true;
		 }
		 return false;
	 }
	 
	 public String getGagnant()
	 {
		 if(this.pionsB==0 && this.pionsR>0)
			 return this.joueurR;
		 
		 if(this.pionsR==0 && this.pionsB>0)
			 return this.joueurB;
		 
		 if(this.pionsB==this.pionsR)
			 return null;
		 
		 return (this.pionsB>this.pionsR)? this.joueurB:this.joueurR;
		 
	 }
	 
	/**
	 * Affciher la grille
	 * */
	 public String AfficherGrille()
	 {
		 String res = "";
			for(int i=0;i<7; i++)
			{
				for(int j=0;j<7;j++)
				{
					res += " | "+ this.grille[i][j]+" |";
				}
				res += " \n";
			}
			return res;
	 }
	
	
	
}
