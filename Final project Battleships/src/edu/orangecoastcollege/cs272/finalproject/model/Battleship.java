package edu.orangecoastcollege.cs272.finalproject.model;

public abstract class Battleship {

	protected int mId;
	protected String mAphaCol;
	protected int mNumRol;
	protected boolean mIsPlayer;

	Battleship(int id, String aphaCol, int numRol, boolean isPlayer) {
		super();
		mId = id;
		mAphaCol = aphaCol;
		mNumRol = numRol;
		mIsPlayer = isPlayer;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getAphaCol() {
		return mAphaCol;
	}

	public void setAphaCol(String aphaCol) {
		mAphaCol = aphaCol;
	}

	public int getNumRol() {
		return mNumRol;
	}

	public void setNumRol(int numRol) {
		mNumRol = numRol;
	}

	public boolean isIsPlayer() {
		return mIsPlayer;
	}

	public void setIsPlayer(boolean isPlayer) {
		mIsPlayer = isPlayer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mAphaCol == null) ? 0 : mAphaCol.hashCode());
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
		if (mAphaCol == null) {
			if (other.mAphaCol != null)
				return false;
		} else if (!mAphaCol.equals(other.mAphaCol))
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
