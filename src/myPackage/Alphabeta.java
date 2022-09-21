package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Akaddar Mohamed
 * @date  01/02/2022
 * */


public class Alphabeta extends Algorithme {
	
	public int compteurNoeuds =0;
	
	/**
	* @param j le joueur courant
	* @param d sa profondeur de raisonnement
	*/
	public Alphabeta(String j, int d) {
        super(j, d);
    }
    
    /**
    *@param etat l'etat de jeu actuel
    *@param a alpha
    *@param b beta
    *@param d profondeur de raisonnement
    *@return une valeur float de score
    */
    public float alphaBeta(State etat, float a, float b, int d)
    {
    	compteurNoeuds++;
		if(d==0 || state.isOver())
		{
			float m = state.getScore(state.getJoueurCourant());
			return (state.getJoueurCourant().equals(joueur)?m:-m);
		}
		for(Move move : state.getMoves(state.getJoueurCourant()))
		{
			State s = state.play(move);
			a = Math.max(a, alphaBeta(s, -b, -a, d-1));
			if(a>=b)return a;
		}
		return a;
    }
	/**
	*@param etat l'etat de jeu
	*@return m le meilleur coup a joue
	*/
    @Override public Move getBestMove(State state) {
        Move bestMove = null;
        float bestValue = Float.NEGATIVE_INFINITY;
        for (Move move : state.getMoves(joueur)) {
            State s = state.play(move);
            float value = this.alphaBeta(s, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, depth);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        } return bestMove;
    }

}
