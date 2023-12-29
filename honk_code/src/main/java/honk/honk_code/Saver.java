package honk.honk_code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Saver {
	
	/**
	 * Automatisation de la création d'un JSONObjet pour une statistique.
	 * On y récupère sa valeur et son lastUpdated.
	 * @param stat Statistique : une des statistiques du Tamagotchi.
	 * @return statJSON : JSONObject contenant une valeur et une date.
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject saveStat(Statistique stat) {
		JSONObject statJSON = new JSONObject();
		statJSON.put("value", stat.getValue());
		statJSON.put("lastUpdated", stat.getLastUpdated());
		return statJSON;
	}
	
	/**
	 * Pour sauvegarder la partie.
	 * Va stocker toutes les valeurs utiles pour le prochain lancement.
	 * @param chronometer Chronometer : le chronomètre pour savoir combien de temps s'est écoulé depuis le début du jeu.
	 * @param typeTama String : pour savoir de quel type de Tamagotchi, il s'agit.
	 * @param tamagotchi Tamagotchi : pour récupérer son XP et les valeurs de ses statistiques.
	 * @throws Exception
	 */
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
