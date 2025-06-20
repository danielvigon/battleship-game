package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Player {
	public Field allyField = new Field();
	public Field adversaryField = new Field();
	private LinkedHashMap<String, ArrayList<Integer>> fleet = new LinkedHashMap<>();
	{
		fleet.put("Porta-aeronaves", new ArrayList<>()); {
			fleet.get("Porta-aeronaves").add(5);
			fleet.get("Porta-aeronaves").add(2);			
		}
		fleet.put("Couraçado", new ArrayList<>()); {
			fleet.get("Couraçado").add(4);
			fleet.get("Couraçado").add(2);			
		}
		fleet.put("Cruzador", new ArrayList<>()); {
			fleet.get("Cruzador").add(3);
			fleet.get("Cruzador").add(2);			
		}
		fleet.put("Submarino", new ArrayList<>()); {
			fleet.get("Submarino").add(3);
			fleet.get("Submarino").add(2);			
		}
		fleet.put("Contratorpedeiro", new ArrayList<>()); {
			fleet.get("Contratorpedeiro").add(2);
			fleet.get("Contratorpedeiro").add(2);
		}
	}
}
