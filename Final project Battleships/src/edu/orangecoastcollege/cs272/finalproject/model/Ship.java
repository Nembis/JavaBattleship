package edu.orangecoastcollege.cs272.finalproject.model;

public class Ship extends Battleship {

	private boolean mIsDestroy;

	public Ship(int id, char aphaCol, int numRol, boolean isPlayer, int difficulty, boolean isDestroy) {
		super(id, aphaCol, numRol, isPlayer, difficulty);
		mIsDestroy = isDestroy;
	}

	public boolean isDestroy() {
		return mIsDestroy;
	}

	public void setIsDestroy(boolean isDestroy) {
		mIsDestroy = isDestroy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (mIsDestroy ? 1231 : 1237);
		return result;
	}

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

	@Override
	public String toString() {
		if(this.mIsPlayer)
			return "PLR ship at: "+this.mAphaCol+this.mNumRol;
		else
			return "CPU ship at: "+this.mAphaCol+this.mNumRol;
	}
	
}
