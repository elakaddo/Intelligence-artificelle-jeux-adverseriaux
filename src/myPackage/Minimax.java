package jeu;

import java.util.List;

/**
*@author Akaddar Mohamed
*@date 04/02/2022
*/

public class Minimax extends Algorithme {


	/**
	*@param j le joueur courant
	*@param d sa profondeur de raisonnement
	*@return une valeur float de score
	*/
	public Minimax(String j, int d) {
		super(j, d);
	}
	public static int compteurNoeuds=0;
	
	/**
	*@param etat l'etat de jeu actuel
	*@param d sa profondeur de raisonnement
	*@return une valeur float de score
	*/
	public float minimax(State etat , int d)
	{
		compteurNoeuds++;
		if(d==0 || etat.isOver())
			return etat.getScore(this.joueur);
		else
		{
			float b , m;
			State etatSuivante;
			if(etat.getJoueurCourant()==this.joueur)
			{
				b = Float.MIN_VALUE;
				for(Move move : etat.getMoves(this.joueur))
				{
					etatSuivante = etat.play(move);
					m = minimax(etatSuivante, d-1);
					if (b<m)
						b=m;
				}
			}
			else
			{
				b = Float.MAX_VALUE;
				for(Move move : etat.getMoves(this.joueur))
				{
					etatSuivante = etat.play(move);
					m = minimax(etatSuivante, d-1);
					if(b>m)
						b=m;
				}
			}
			return b;
				
		}
	}
	/**
	*@param etat l'etat de jeu
	*@return m le meilleur coup a joue
	*/
	@Override 
	public Move getBestMove(State etat)
	{
		Move bestAction = new Move();
		State etatSuivante ;
		float value;
		float bestValue = Float.MIN_VALUE;
		List<Move> moves = etat.getMoves(this.joueur);
		for(Move move : moves)
		{
			etatSuivante = etat.play(move);
			value = minimax(etatSuivante, this.depth);
			if(value>bestValue)
			{
				bestValue = value;
				bestAction = move;
			}
		}
		return bestAction;
	}
}
