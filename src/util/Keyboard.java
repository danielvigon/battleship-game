package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {

	private static BufferedReader keyboard = new BufferedReader(
			new InputStreamReader(System.in));

	public static String stringDataTypeInput(String stringDataType) {
		try {
			System.out.println(stringDataType);
			return keyboard.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public static int intDataTypeInput(String stringDataType) {
		return Integer.parseInt(stringDataTypeInput(stringDataType));
	}

	public static double doubleDataTypeInput(String stringDataType) {
		return Double.parseDouble(stringDataTypeInput(stringDataType));
	}
}