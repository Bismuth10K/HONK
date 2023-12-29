package honk.honk_code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.launcher();
	}
	
	/**
	 * Pour lancer l'interface graphique.
	 */
	public void launcher() {
		launch();
	}
	
	/**
	 * Quand on lance launch, cette fonction se lance.
	 * Elle crée une scène avec le fxml title screen.
	 * @param stage Stage : pour y mettre le fxml.
	 * @throws IOException
	 */
	@Override
	public void start(Stage stage) throws IOException {
		BorderPane root = new BorderPane();
		FXMLLoader titleScreen = new FXMLLoader(getClass().getResource("title-screen.fxml")); // On charge le fxml.
		root.setCenter(titleScreen.load()); // On place le fxml dans le borderPane
		Scene scene = new Scene(root, 768, 576); // Ce dernier qu'on place dans la scene
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png")))); // On met un logo
		stage.setTitle("H.O.N.K.!"); // Le titre de la fenêtre
		stage.setScene(scene); // La scène est placée dans stage.
		stage.show(); // On affiche le stage.
	}
	
	/**
	 * Pour fermer cette fenêtre.
	 * @param event
	 */
	public void close(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Lors de l'appui du bouton nouvelle partie, créé une scène pour choisir le tamagotchi.
	 * @param event
	 * @throws IOException
	 */
	public void createNewGame(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("choose-new-animal.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Choisissez votre Tamagotchi !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	/**
	 * Lors de l'appui du bouton charger une partie, créé une scène pour choisir la save.
	 * @param event
	 * @throws IOException
	 */
	public void createLoadGame(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Choisissez votre sauvegarde !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	/**
	 * Lors de l'appui du bouton options, créé une scène pour les options.
	 * @param event
	 * @throws IOException
	 */
	public void createOptions(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Les options");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
}