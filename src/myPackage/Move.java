package jeu;
/**
*@author Akaddar Mohamed
*@date 28/01/2022
*/
public class Move {

	//Attributs :
	private int xDep;
	private int yDep;
	private int xArr;
	private int yArr;
	private boolean estCloner;
	
	/**
	 * Consructeur vide
	 * */
	public Move()
	{	
	}
	
	/**
	*Consructeur qui initialise la grille ainsi l'etat de jeu
	*@param xDep cordonnee x de depart
	*@param yDep cordonnee y de depart
	*@param xArr cordonnee x d'arrive
	*@param yArr cordonnee y d'arrive
	*@param estCloner indique si'il s'agit de cloner ou de sauter
	*/
	public Move(int xDep, int yDep, int xArr, int yArr, boolean estCloner) {
		this.xDep=xDep;
		this.yDep=yDep;
		this.xArr=xArr;
		this.yArr=yArr;
		this.estCloner=estCloner;
	}
	
	//Accesseur :
	
	/**
	*@return cordonnee x de depart
	*/
	public int getxDep()
	{
		return this.xDep;
	}
	
	/**
	*@return cordonnee y de depart
	*/
	public int getyDep()
	{
		return this.yDep;
	}
	
	/**
	*@return cordonnee x d'arrive
	*/
	public int getxArr()
	{
		return this.xArr;
	}
	
	/**
	*@return cordonnee y d'arrive
	*/
	public int getyArr()
	{
		return this.yArr;
	}
	
	/**
	*@return true s'il s'agit de cloner sinon false pour sauter
	*/
	public boolean getEstCloner()
	{
		return this.estCloner;
	}
	
	/**
	*@return details d'un coup
	*/
	public String toString()
	{
		return "( "+getxDep()+" , "+getyDep()+" ) , ( "+getxArr()+" , "+getyArr()+" ) , "+estCloner;
	}
}
