package edu.orangecoastcollege.cs272.finalproject.model;

public class HighScore implements Comparable<HighScore>{
	private int mID;
	private String mName;
	private String mDifficulty;
	private int mTurns;
	private int mLuckyUsed;
	
	public HighScore(int iD, String name, String difficulty, int turns, int luckyUsed) {
		mID = iD;
		mName = name;
		mDifficulty = difficulty;
		mTurns = turns;
		mLuckyUsed = luckyUsed;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getDifficulty() {
		return mDifficulty;
	}

	public void setDifficulty(String difficulty) {
		mDifficulty = difficulty;
	}

	public int getTurns() {
		return mTurns;
	}

	public void setTurns(int turns) {
		mTurns = turns;
	}

	public int getLuckyUsed() {
		return mLuckyUsed;
	}

	public void setLuckyUsed(int luckyUsed) {
		mLuckyUsed = luckyUsed;
	}

	public int getID() {
		return mID;
	}

	@Override
	public String toString() {
		return mName + "[" + mDifficulty + " Difficulty, " + mTurns + " Turns, "
				+ mLuckyUsed + " Lucky Missiles Used]";
	}

	@Override
	public int compareTo(HighScore other) {
		int diffCompare = this.mDifficulty.compareTo(other.mDifficulty);
		int turnCompare = other.mTurns - this.mTurns;
		int luckyCompare = other.mLuckyUsed - this.mLuckyUsed;
		if(diffCompare != 0)
			return diffCompare;
		if(turnCompare != 0)
			return turnCompare;
		if(luckyCompare != 0)
			return luckyCompare;
		return this.mName.compareTo(other.mName);
	}
	
	
}
