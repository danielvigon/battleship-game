package model;

import java.util.LinkedHashMap;
import exception.EntityValidationException;

public class Field {
	public String field[][] = new String[20][20];		// for now, the field stays public
	{
		for (int row = 0; row < 20; row++) {
			for (int column = 0; column < 20; column++) {
				field[row][column] = "░░";
			}
		}
	}

	private LinkedHashMap<String, Integer> front = new LinkedHashMap<>(20);
	{
		front.put("A", 0); front.put("B", 1); front.put("C", 2); front.put("D", 3);
		front.put("E", 4); front.put("F", 5); front.put("G", 6); front.put("H", 7);
		front.put("I", 8); front.put("J", 9); front.put("K", 10); front.put("L", 11);
		front.put("M", 12); front.put("N", 13); front.put("O", 14); front.put("P", 15);
		front.put("Q", 16); front.put("R", 17); front.put("S", 18); front.put("T", 19);
	}

	public void setField(int row, String column) {
		if (!front.containsValue(row)) {
			throw new EntityValidationException("A frente horizontal deve ser de 1 a 20.");
		}
		if (!front.containsKey(column)) {
			throw new EntityValidationException("A frente vertical deve ser de A a T.");
		}

		field[row][front.get(column)] = "██";
	}

	public void displayField() {		
		for (int row = 0; row < 20; row++) {
			if (row == 0) {
				System.out.print("\n\t\t\t\t\t" + " - MAPA - " + "\n\n\t\t\t   ");
				front.keySet().forEach(key -> System.out.print(key + " "));
				System.out.println();
			}
			
			for (int column = 0; column < 20; column++) {
				if (column == 0 && row < 9) {
					System.out.print("\t\t         " + (row + 1) + " " + field[row][column]);
				} else if (column == 0) {
					System.out.print("\t\t        " + (row + 1) + " " + field[row][column]);
				} else {
					System.out.print(field[row][column]);
				}
			}
			
			System.out.println();
		}
	}
}