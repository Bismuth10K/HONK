package honk.honk_code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Saver {
	
	@SuppressWarnings("unchecked")
	public static JSONObject saveStat(Statistique stat) {
		JSONObject statJSON = new JSONObject();
		statJSON.put("value", stat.getValue());
		statJSON.put("lastUpdated", stat.getLastUpdated());
		return statJSON;
	}
	
	@SuppressWarnings("unchecked")
	public static void save(Chronometer chronometer, String typeTama, Tamagotchi tamagotchi) throws Exception {
		
		JSONObject tamaXP = new JSONObject();
		tamaXP.put("currentXP", tamagotchi.getCurrentXP());
		tamaXP.put("maxXP", tamagotchi.getMaxXP());
		tamaXP.put("playerLevel", tamagotchi.getPlayerLevel());
		
		JSONObject tamaStats = new JSONObject();
		tamaStats.put("Energie", saveStat(tamagotchi.getNrj()));
		tamaStats.put("Satiete", saveStat(tamagotchi.getSat()));
		tamaStats.put("Repos", saveStat(tamagotchi.getRep()));
		tamaStats.put("Hygiene", saveStat(tamagotchi.getHyg()));
		tamaStats.put("Poids", saveStat(tamagotchi.getPoi()));
		tamaStats.put("Vie", saveStat(tamagotchi.getVie()));
		tamaStats.put("Bonheur", saveStat(tamagotchi.getBhr()));
		
		JSONObject tamaAll = new JSONObject();
		tamaAll.put("XP", tamaXP);
		tamaAll.put("Statistiques", tamaStats);
		
		JSONObject chronometreJSON = new JSONObject();
		chronometreJSON.put("howLongSinceBeginning", chronometer.getMilliseconds());
		
		JSONArray saveTama = new JSONArray();
		saveTama.add(tamaAll);
		saveTama.add(chronometreJSON);
		
		//Write JSON file
        try (FileWriter file = new FileWriter("save" + typeTama + ".json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(saveTama.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
