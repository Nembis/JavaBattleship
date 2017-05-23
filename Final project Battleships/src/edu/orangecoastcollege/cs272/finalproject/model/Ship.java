package edu.orangecoastcollege.cs272.finalproject.model;

/**
 * This class is the blue print to make a new ship.
 */
public class Ship extends Battleship {

	private boolean mIsDestroy;

	/**
	 * This is hte consturtor that creats a  new ship.
	 * @param id
	 * @param aphaCol
	 * @param numRol
	 * @param isPlayer
	 * @param difficulty
	 * @param isDestroy
	 */
	public Ship(int id, char aphaCol, int numRol, boolean isPlayer, int difficulty, boolean isDestroy) {
		super(id, aphaCol, numRol, isPlayer, difficulty);
		mIsDestroy = isDestroy;
	}

	/**
	 * This method retursn if the ship is dead or not.
	 * @return
	 */
	public boolean isDestroy() {
		return mIsDestroy;
	}

	/**
	 * This change if the ship is alive or not.
	 * @param isDestroy
	 */
	public void setIsDestroy(boolean isDestroy) {
		mIsDestroy = isDestroy;
	}

	/**
	 * This creates a new id for the class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (mIsDestroy ? 1231 : 1237);
		return result;
	}

	/**
	 * This checks two objects and see if they are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (mIsDestroy != other.mIsDestroy)
			return false;
		return true;
	}

	/**
	 * This prints out the data.
	 */
	@Override
	public String toString() {
		if(this.mIsPlayer)
			return "PLR ship at: "+this.mAphaCol+this.mNumRol;
		else
			return "CPU ship at: "+this.mAphaCol+this.mNumRol;
	}
	
}
