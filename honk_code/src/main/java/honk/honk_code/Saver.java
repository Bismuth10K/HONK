package honk.honk_code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Saver {
	public static String locationSave = System.getProperty("user.home") + "/.honk_save/";
	
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
	 * @param typeTama    String : pour savoir de quel type de Tamagotchi, il s'agit.
	 * @param tamagotchi  Tamagotchi : pour récupérer son XP et les valeurs de ses statistiques.
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
		tamaAll.put("Chronometre", chronometer.getMilliseconds());
		
		JSONArray saveTama = new JSONArray();
		saveTama.add(tamaAll);
		
		Files.createDirectories(Paths.get(locationSave));
		//Write JSON file
		try (FileWriter file = new FileWriter(locationSave + "save" + typeTama + ".json")) {
			//We can write any JSONArray or JSONObject instance to the file
			file.write(saveTama.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parser de stats.
	 * @param statistique Statistique : une des statistiques du Tamagotchi où on va mettre les résultats de jsonStat.
	 * @param jsonStat    JSONObject : un objet de la sauvegarde JSON où les valeurs ont été enregistréees.
	 * @return
	 */
	public static void parseStat(Statistique statistique, JSONObject jsonStat) throws Exception {
		try {
			statistique.setValue(((Long) jsonStat.get("value")).intValue());
			statistique.setLastUpdated((Long) jsonStat.get("lastUpdated"));
		} catch (Exception e) {
			throw new Exception("Valeurs absurdes ou fichier corrompu");
		}
	}
	
	/**
	 * Parser du fichier JSON.
	 * @param json FileReader : fichier json à lire.
	 * @param chronometer Chronometer : chronomètre à modifier.
	 * @param tamagotchi Tamagotchi : tamagotchi à modifier.
	 */
	public static void parse(FileReader json, Chronometer chronometer, Tamagotchi tamagotchi) throws Exception {
		try {
			JSONParser jsonParser = new JSONParser();
			//Read JSON file
			JSONArray saveTama = (JSONArray) jsonParser.parse(json);
			JSONObject tamaJSON = (JSONObject) saveTama.getFirst();
			
			chronometer.addTimeSkip((Long) tamaJSON.get("Chronometre"));
			
			JSONObject tamaXP = (JSONObject) tamaJSON.get("XP");
			tamagotchi.setCurrentXP((Double) tamaXP.get("currentXP"));
			tamagotchi.setMaxXP((Double) tamaXP.get("maxXP"));
			tamagotchi.setPlayerLevel(((Long) tamaXP.get("playerLevel")).intValue());
			
			JSONObject tamaStat = (JSONObject) tamaJSON.get("Statistiques");
			parseStat(tamagotchi.getNrj(), (JSONObject) tamaStat.get("Energie"));
			parseStat(tamagotchi.getSat(), (JSONObject) tamaStat.get("Satiete"));
			parseStat(tamagotchi.getRep(), (JSONObject) tamaStat.get("Repos"));
			parseStat(tamagotchi.getHyg(), (JSONObject) tamaStat.get("Hygiene"));
			parseStat(tamagotchi.getPoi(), (JSONObject) tamaStat.get("Poids"));
			parseStat(tamagotchi.getVie(), (JSONObject) tamaStat.get("Vie"));
			parseStat(tamagotchi.getBhr(), (JSONObject) tamaStat.get("Bonheur"));
		} catch (Exception e) {
			throw new Exception("Fichier incorrect");
		}
	}
}
