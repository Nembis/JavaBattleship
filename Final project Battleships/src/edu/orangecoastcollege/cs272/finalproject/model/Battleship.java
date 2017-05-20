package edu.orangecoastcollege.cs272.finalproject.model;

public abstract class Battleship {

	protected int mId;
	protected char mAphaCol;
	protected int mNumRol;
	protected boolean mIsPlayer;
	protected int mDifficulty;

	Battleship(int id, char aphaCol, int numRol, boolean player, int difficulty) {
		super();
		mId = id;
		mAphaCol = aphaCol;
		mNumRol = numRol;
		mIsPlayer = player;
		mDifficulty = difficulty;
	}

	public int getId() {
		return mId;
	}

	public char getAphaCol() {
		return mAphaCol;
	}

	public void setAphaCol(char aphaCol) {
		mAphaCol = aphaCol;
	}

	public int getNumRol() {
		return mNumRol;
	}

	public void setNumRol(int numRol) {
		mNumRol = numRol;
	}

	public boolean isPlayer() {
		return mIsPlayer;
	}

	public void setIsPlayer(boolean isPlayer) {
		mIsPlayer = isPlayer;
	}

	public int getDifficulty() {
		return mDifficulty;
	}

	public void setDifficulty(int difficulty) {
		mDifficulty = difficulty;
	}

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
