package edu.orangecoastcollege.cs272.finalproject.model;

/**
 * This is the parent class to create the ships and the missiles.
 */
public abstract class Battleship {

	/**
	 * These are fields as a parent class.
	 */
	protected int mId;
	protected char mAphaCol;
	protected int mNumRol;
	protected boolean mIsPlayer;
	protected int mDifficulty;

	/**
	 * This is the protected constructor that passes the data onto the children.
	 * @param id
	 * @param aphaCol
	 * @param numRol
	 * @param player
	 * @param difficulty
	 */
	Battleship(int id, char aphaCol, int numRol, boolean player, int difficulty) {
		super();
		mId = id;
		mAphaCol = aphaCol;
		mNumRol = numRol;
		mIsPlayer = player;
		mDifficulty = difficulty;
	}

	/**
	 * This method returns the Id.
	 * @return
	 */
	public int getId() {
		return mId;
	}

	/**
	 * This method reurns the collome.
	 * @return
	 */
	public char getAphaCol() {
		return mAphaCol;
	}

	/**
	 * This sets the col.
	 * @param aphaCol
	 */
	public void setAphaCol(char aphaCol) {
		mAphaCol = aphaCol;
	}

	/**
	 * This get's teh row.
	 * @return
	 */
	public int getNumRol() {
		return mNumRol;
	}

	/**
	 * This sets the row.
	 * @param numRol
	 */
	public void setNumRol(int numRol) {
		mNumRol = numRol;
	}

	/**
	 * is retursn if the object is a player or not.
	 * @return
	 */
	public boolean isPlayer() {
		return mIsPlayer;
	}

	/**
	 * This sets the object to be or not to be a player.
	 * @param isPlayer
	 */
	public void setIsPlayer(boolean isPlayer) {
		mIsPlayer = isPlayer;
	}

	/**
	 * This get's teh dicciculty.
	 * @return
	 */
	public int getDifficulty() {
		return mDifficulty;
	}

	/**
	 * This sets teh Difficulty.
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty) {
		mDifficulty = difficulty;
	}

	/**
	 * This creates a id for the class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mAphaCol;
		result = prime * result + mDifficulty;
		result = prime * result + mId;
		result = prime * result + (mIsPlayer ? 1231 : 1237);
		result = prime * result + mNumRol;
		return result;
	}

	/**
	 * this checks if the objects.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Battleship other = (Battleship) obj;
		if (mAphaCol != other.mAphaCol)
			return false;
		if (mDifficulty != other.mDifficulty)
			return false;
		if (mId != other.mId)
			return false;
		if (mIsPlayer != other.mIsPlayer)
			return false;
		if (mNumRol != other.mNumRol)
			return false;
		return true;
	}

}
