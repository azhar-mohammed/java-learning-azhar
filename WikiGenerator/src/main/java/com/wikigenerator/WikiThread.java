package com.wikigenerator;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author azharm
 *
 */
public class WikiThread implements Runnable {

	private String queryWord;
	final static String outputDirectoryPath = "/home/azharm/Documents/OutputDirectory/";

	public WikiThread(String word) {
		this.queryWord = word;
	}

	@Override
	public void run() {
		try {
			URL url = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/" + queryWord);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder outputString = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				outputString.append(output);
			}
			conn.disconnect();

			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(outputString.toString());
			String extractField = null;
			extractField = (String) json.get("extract");
			if (extractField != null) {
				writeToFile(extractField);
			} else {
				// TODO: ADD LOGGER
				System.out.println("No extract field found for query word"+queryWord);
			}
			br.close();

		} catch (IOException | ParseException e) {
			// TODO ADD LOGGER
			e.printStackTrace();
		}
		

	}

	/**
	 * @param lines
	 *            This method writes the provided input lines to an output csv file
	 *            and increments the atomic integer value
	 */
	private void writeToFile(String line) {

		try (FileWriter fw = new FileWriter(outputDirectoryPath + queryWord + ".txt")) {

			fw.write(line);

		}

		catch (IOException e) {
			//TODO: ADD LOGGER
			e.printStackTrace();
		}
	}

}
