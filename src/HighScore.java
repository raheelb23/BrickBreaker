import java.util.*;
import java.io.*;

/**
 * This class looks at the high score list, takes in a player, and then
 * checks/adds score the list
 * 
 * @author jacob, muizz, raheel
 *
 */

public class HighScore {

	private Player currentPlayer;
	private ArrayList<Player> players;
	private String filename;

	/**
	 * Constructor 1: Takes in information about current player assuming they will
	 * be checked against high score file repository
	 * 
	 * @param userScore
	 */
	public HighScore(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		players = new ArrayList<Player>();
		filename = "high_score_list.txt";
	}

	/**
	 * Constructor 2: For purposes of checking high scores in file other than the
	 * native directory
	 * 
	 * @param currentPlayer
	 * @param filename
	 */
	public HighScore(Player currentPlayer, String filename) {
		this(currentPlayer);
		this.filename = filename;
	}

	/**
	 * This method checks to see if a high score has been achieved and returns t/f
	 * 
	 */
	public boolean checkHighScore() {
		for (int i = 0; i < players.size(); i++) {
			if (currentPlayer.getScore() >= players.get(i).getScore()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method creates an arraylist of players in the high score file
	 */
	public void extractHighScore() {
		File highScoreFile = new File(filename);
		try {
			Scanner reader = new Scanner(highScoreFile);
			reader.nextLine();
			while (reader.hasNextLine()) {
				String row = reader.nextLine();
				String rowData[] = row.split(" ");

				Player newPlayer = new Player(rowData[1], Integer.parseInt(rowData[2]));
				players.add(newPlayer);

			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method updates the text file of highscores from the arraylist of players
	 * after adding the current player to the arraylist, sorting the scores in
	 * descending order and only keeping the first 10 on the list
	 */
	public void writeScore() {
		File highScoreFile = new File(filename);
		players.add(currentPlayer);
		players.sort(null);

		try {
			PrintWriter writer = new PrintWriter(highScoreFile);
			writer.println("Rank Name Score");
			for (int i = 0; i < players.size(); i++) {
				if (i < 10) {
					writer.println(i + 1 + " " + players.get(i).getUsername() + " " + players.get(i).getScore());
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// getter

	/**
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return currentPlayer;
	}

}
