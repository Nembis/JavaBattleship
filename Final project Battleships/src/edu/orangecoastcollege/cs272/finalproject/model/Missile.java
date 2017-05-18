package edu.orangecoastcollege.cs272.finalproject.model;

public class Missile extends Battleship {

	private boolean mIsLucky;

	public Missile(int id, String aphaCol, int numRol, boolean isPlayer, int difficulty, boolean isLucky) {
		super(id, aphaCol, numRol, isPlayer, difficulty);
		mIsLucky = isLucky;
	}

	public boolean isLucky() {
		return mIsLucky;
	}

	public void setIsLucky(boolean isLucky) {
		mIsLucky = isLucky;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (mIsLucky ? 1231 : 1237);
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
		Missile other = (Missile) obj;
		if (mIsLucky != other.mIsLucky)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Missile [mIsLucky=" + mIsLucky + ", mId=" + mId + ", mAphaCol=" + mAphaCol + ", mNumRol=" + mNumRol
				+ ", mIsPlayer=" + mIsPlayer + "]";
	}

}
