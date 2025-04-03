package model;

public class PlayerField {
	public String defaultField[][] = new String[20][20];
	{
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				defaultField[i][j] = "░░";
			}
		}
	}
}