package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerLoadGame implements Initializable {
	@FXML
	private ListView listView;
	
	/**
	 * Doit être là par défaut.
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Comment ça va ?
		try {
			List<Path> paths = Files.walk(Paths.get(Saver.locationSave), 1) //by mentioning max depth as 1 it will only traverse immediate level
					.filter(Files::isRegularFile).filter(path -> path.getFileName().toString().endsWith(".json")) // fetch only the files which are ending with .JSON
					.toList();
			if (paths.isEmpty())
				listView.setPlaceholder(new Label("Pas de sauvegarde"));
			else
				for (Path path : paths)
					listView.getItems().add(path.getFileName().toString());
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Action lors de l'appui sur le bouton retour.
	 * Ferme la fenêtre load pour revenir au menu.
	 * @param event ActionEvent : appui du bouton
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
		Menu.staticClose(event);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		AnchorPane anchorPane = fxmlLoader.load();
		Scene scene = new Scene(anchorPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(getClass().getResource("textures/logo_honk.png"))));
		stageTama.setTitle("H.O.N.K.!");
		stageTama.setScene(scene);
		stageTama.show();
	}
	
	public void deleteSave(ActionEvent event) {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			File selectedJSON = new File(Saver.locationSave +
					listView.getSelectionModel().getSelectedItem().toString());
			selectedJSON.delete();
			listView.getItems().clear();
			
			try {
				List<Path> paths = Files.walk(Paths.get(Saver.locationSave), 1) //by mentioning max depth as 1 it will only traverse immediate level
						.filter(Files::isRegularFile).filter(path -> path.getFileName().toString().endsWith(".json")) // fetch only the files which are ending with .JSON
						.toList();
				if (paths.isEmpty())
					listView.setPlaceholder(new Label("Pas de sauvegarde"));
				else
					for (Path path : paths)
						listView.getItems().add(path.getFileName().toString());
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadGame(ActionEvent event) throws Exception {
		if (listView.getSelectionModel().getSelectedItem() != null) {
			String selectedJSON = listView.getSelectionModel().getSelectedItem().toString();
			Menu.staticClose(event);
			String subsName = selectedJSON.substring(4, selectedJSON.length() - 5);
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
			StackPane stackPane = fxmlLoader.load();
			Game game = fxmlLoader.getController(); // récupération du controller
			game.setTama(subsName);
			try (FileReader json = new FileReader(Saver.locationSave + selectedJSON)) {
				game.setJSON(json); // on set le Tamagotchi
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				File deleteJson = new File(Saver.locationSave + selectedJSON);
				deleteJson.delete();
				System.exit(0);
			}
			Scene scene = new Scene(stackPane, 768, 576);
			Stage stageTama = new Stage();
			stageTama.getIcons().add(new Image(String.valueOf(getClass().getResource("textures/logo_honk.png"))));
			stageTama.setTitle("LE " + subsName.toUpperCase());
			stageTama.setScene(scene);
			stageTama.show();
		}
	}
}
