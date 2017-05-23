package edu.orangecoastcollege.cs272.finalproject.model;

/**
 * This class creates a new Missile.
 */
public class Missile extends Battleship {

	private boolean mIsLucky;
	private boolean mIsMove;

	/**
	 * this is the constructor that creates a new missile.
	 * @param id
	 * @param aphaCol
	 * @param numRol
	 * @param isPlayer
	 * @param difficulty
	 * @param isLucky
	 */
	public Missile(int id, char aphaCol, int numRol, boolean isPlayer, int difficulty, boolean isLucky, boolean isMove) {
		super(id, aphaCol, numRol, isPlayer, difficulty);
		mIsLucky = isLucky;
		mIsMove = isMove;
	}

	/**
	 * This method says if the missle is a lucky missile.
	 * @return
	 */
	public boolean isLucky() {
		return mIsLucky;
	}

	/**
	 * This mehtod sets' teh lucky missile.
	 * @param isLucky
	 */
	public void setIsLucky(boolean isLucky) {
		mIsLucky = isLucky;
	}
	
	/**
	 * Checks if missile is player or lucky spawned
	 * @return
	 */
	public boolean isIsMove() {
		return mIsMove;
	}

	/**
	 * Changes if missile is player or lucky spawned
	 * @return
	 */
	public void setIsMove(boolean isMove) {
		mIsMove = isMove;
	}

	/**
	 * This creates a object a new id.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (mIsLucky ? 1231 : 1237);
		return result;
	}

	/**
	 * This method takes two missiles to see if thear the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Missile other = (Missile) obj;
		if (mIsLucky != other.mIsLucky)
			return false;
		return true;
	}

	/**
	 * This prints out the missile data.
	 */
	@Override
	public String toString() {
		if(this.mIsPlayer)
			return "PLR missile at: "+this.mAphaCol+this.mNumRol;
		else
			return "CPU missile at: "+this.mAphaCol+this.mNumRol;
	}

}
