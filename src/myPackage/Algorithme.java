package jeu;

/**
 * @author Akaddar Mohamed
 * @date  01/02/2022
 * */
public class Algorithme {
	String joueur;
	int depth;
	
	
	/**
	* @param j le joueur courant
	* @param d sa profondeur de raisonnement
	*/
	public Algorithme(String j, int d)
	{
		this.joueur=j;
		this.depth=d;
	}
	
	/**
	*@param etat l'etat de jeu
	*@return m le meilleur coup a joue
	*/
	public Move getBestMove(State etat)
	{
		Move m = new Move();
		return m;
	}
	
}



