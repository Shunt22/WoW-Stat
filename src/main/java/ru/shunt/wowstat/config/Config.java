package ru.shunt.wowstat.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Config {
	private final String API_KEY;

	private static Config instance = null;

	protected Config() {
		API_KEY = readApiKey();
		if (API_KEY == null) {
			System.err.println("\n====\n");
			System.err
					.println("Something wrong with your API key. Make sure you CONFIG is situated in project's root directory and it has your Blizzard API key.");
			System.err.println("\n====\n");
			System.exit(1);
		}
	}

	public synchronized static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public String getApiKey() {
		return API_KEY;
	}

	private String readApiKey() {
		return System.getenv("API_KEY");
		/*
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("CONFIG")));
			String key = br.readLine();
			br.close();
			if (!key.isEmpty() && key != null) {
				return key;
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// Hope everything is OK:)
				}
			}
		}*/
	}
}
