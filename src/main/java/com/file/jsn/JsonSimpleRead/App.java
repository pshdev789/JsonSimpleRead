package com.file.jsn.JsonSimpleRead;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/*import org..JSONArray;
import org.json.simple.JSONObject;*/

/*import org.json.JSONArray;
import org.json.JSONObject;*/

/*import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;*/

/**
 * Hello world!
 *
 */
public class App {

	private class RPS {
		private int robot;
		private int you;

	}

	public static int[] robotPreHands = new int[3];
	public static int opponentLH;
	public static int nxtHand = -1;

	public static void main(String[] args) {

		String file = args[0];
		boolean res = readFile(file);
		if (res) {
			guessNextHand();
			System.out.println("Next Hand: ");
			printNextHand();
		} else {
			System.out.println("Could not read file");
		}
		/* String file="F://test1.json"; */

	}

	private static boolean readFile(String file) {

		Gson gson = new Gson();

		try {
			RPS[] rps = gson.fromJson(new FileReader(file), RPS[].class);
			System.out.println(gson.toJson(rps));

		
			int n = 0;
			for (int i = 0; i < rps.length; i++) {
				RPS jsObj = rps[i];
				/*int robotResult = Integer.parseInt(jsObj.get("robot").toString());
				int humanResult = Integer.parseInt(jsObj.get("you").toString());
				System.out.println("Robot: " + robotResult);
				System.out.println("Human: " + humanResult);
				if (i > rps.length - 4) {
					robotPreHands[n] = robotResult;
					if (i == rps.length - 1) {
						opponentLH = humanResult;
					}

					n++;
				}*/
			}

			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private static int guessNextHand() {

		if (robotPreHands[2] == 0) {

			if (opponentLH == 0) {
				nxtHand = 1;
			} else if (opponentLH == 1) {
				nxtHand = 2;
			} else if (opponentLH == 2) {
				nxtHand = 1;
			}
		} else if (robotPreHands[2] == 1) {
			// Select 0 or 2
			if (opponentLH == 0) {
				nxtHand = 2;
			} else if (opponentLH == 1) {
				nxtHand = 0;
			} else if (opponentLH == 2) {
				nxtHand = 0;
			}
		} else if (robotPreHands[2] == 2) {
			// Select 0 or 1
			if (opponentLH == 0) {
				nxtHand = 1;
			} else if (opponentLH == 1) {
				nxtHand = 0;
			} else if (opponentLH == 2) {
				nxtHand = 1;
			}
		}

		// Implementation of other
		return nxtHand;
	}

	private static boolean checkHands(int robotH, int hHands) {
		if (robotH == 0 && hHands == 1) {
			return true;
		} else if (robotH == 1 && hHands == 2) {
			return true;
		} else if (robotH == 2 && hHands == 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void printNextHand() {
		if (nxtHand == 0)
			System.out.println("Rock");
		else if (nxtHand == 1)
			System.out.println("Scissors");
		else if (nxtHand == 2)
			System.out.println("Paper");
	}
}
