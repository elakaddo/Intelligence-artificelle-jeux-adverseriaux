package jeu;

import java.util.List;
import java.util.Set;

/**
*@author Akaddar Mohamed
*@date 10/02/2022
*/

public class Negamax extends Algorithme {
	
	/**
	* @param j le joueur courant
	* @param d sa profondeur de raisonnement
	*/
	public Negamax(String j , int d)
	{
		super(j,d);
	}
	
	/**
	*@param etat le joueur courant
	*@param d sa profondeur de raisonnement
	*@return une valeur float de score
	*/
	public float negamax(State etat, int d)
	{
		float m;
		if(d==0 || !etat.isOver())
		{
			return etat.getScore(this.joueur);
		}
		else
		{
			m = Float.MIN_VALUE;
			List<Move> listM = etat.getMoves(this.joueur);
			if (s.getJoueurCourant() == this.joueur)
			{
				for (Move move : listM)
				{
					State etatSuivante = etat.play(move);
					m = Float.max(m, (-1*negamax(etatSuivante, d-1)));
				}
			}
			return m;
		}
	}
	/**
	*@param etat l'etat de jeu
	*@return m le meilleur coup a joue
	*/
	@Override
	public Move getBestMove(State etat)
	{
		 Move bestMove;
	        float bestValue = Float.NEGATIVE_INFINITY;
	        for (Move move : etat.getMoves(this.joueur)) {
	            State etatSuivante = etat.play(move);
	            float value = this.negamax(etatSuivante, depth);
	            if (value > bestValue) {
	                bestValue = value;
	                bestMove = move;
	            }
	        } return bestMove;
	}
	

}
