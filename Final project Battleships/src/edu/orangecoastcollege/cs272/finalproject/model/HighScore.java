package edu.orangecoastcollege.cs272.finalproject.model;

/**
 * This class creates a new Object for the highscore.
 *
 */
public class HighScore implements Comparable<HighScore>{
	
	private int mID;
	private String mName;
	private String mDifficulty;
	private int mTurns;
	private int mLuckyUsed;
	
	/**
	 * This mehtod creates a new highscore object.
	 * @param iD
	 * @param name
	 * @param difficulty
	 * @param turns
	 * @param luckyUsed
	 */
	public HighScore(int iD, String name, String difficulty, int turns, int luckyUsed) {
		mID = iD;
		mName = name;
		mDifficulty = difficulty;
		mTurns = turns;
		mLuckyUsed = luckyUsed;
	}

	/**
	 * this mehod gets teh name of the player.
	 * @return
	 */
	public String getName() {
		return mName;
	}

	/**
	 * this method sets the name of the player.
	 * @param name
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * This mehtod get's the difficulty that the player was playing.
	 * @return
	 */
	public String getDifficulty() {
		return mDifficulty;
	}

	/**
	 * set's the difficulty.
	 * @param difficulty
	 */
	public void setDifficulty(String difficulty) {
		mDifficulty = difficulty;
	}

	/**
	 * this mehtod gets the trna s that it took for you to win.
	 * @return
	 */
	public int getTurns() {
		return mTurns;
	}

	/**
	 * sets how many turns it took you to win the game.
	 * @param turns
	 */
	public void setTurns(int turns) {
		mTurns = turns;
	}
	
	/**
	 * This returns how many lucky missile you used.
	 * @return
	 */
	public int getLuckyUsed() {
		return mLuckyUsed;
	}

	/**
	 * set's how many lucky missiles you used to win the game.
	 * @param luckyUsed
	 */
	public void setLuckyUsed(int luckyUsed) {
		mLuckyUsed = luckyUsed;
	}

	/**
	 * This get's teh id
	 * @return
	 */
	public int getID() {
		return mID;
	}

	/**
	 * This gives the class an id.
	 */
	@Override
	public String toString() {
		return mName + "[" + mTurns + " Turns, " + mLuckyUsed + " Lucky Missiles Used]";
	}

	/**
	 * This method compares your score to other scores on teh high score lsit.
	 */
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
