package honk.view;

import javafx.scene.image.Image;

public class Textures {
    private static final String texturesDir = "/textures/";


    //Direction Buttons
    public static final Image UpButtonSpr = new Image(texturesDir + "gauges-and-buttons/UpArrow.png");
    public static final Image LeftButtonSpr = new Image(texturesDir + "gauges-and-buttons/LeftArrow.png");
    public static final Image RightButtonSpr = new Image(texturesDir + "gauges-and-buttons/RightArrow.png");
    public static final Image DownButtonSpr = new Image(texturesDir + "gauges-and-buttons/DownArrow.png");
    
    //Action Buttons
    public static final Image EatActionButtonSpr = new Image(texturesDir + "gauges-and-buttons/EatButton.png");
    public static final Image SleepActionButtonSpr = new Image(texturesDir + "gauges-and-buttons/SleepButton.png");
    public static final Image PlayActionButtonSpr = new Image(texturesDir + "gauges-and-buttons/ToyButton.png");
    public static final Image WalkActionButtonSpr = new Image(texturesDir + "gauges-and-buttons/WalkButtons.png");
    public static final Image WashActionButtonSpr = new Image(texturesDir + "gauges-and-buttons/WashButton.png");

    //Animals Full body
    public static final Image CatTamaSpr = new Image(texturesDir + "animals/CatFull.png");
    public static final Image DogTamaSpr = new Image(texturesDir + "animals/DogFull.png");
    public static final Image RabbitTamaSpr = new Image(texturesDir + "animals/RabbitFull.png");
    public static final Image RobotTamaSpr = new Image(texturesDir + "animals/RobotFull.png");

    //Backgrounds and other ui
    public static final Image BrickWallBGSpr = new Image("/textures/DAWOL.png");

    //Options Buttons

    //Logo
    public static final Image GameLogo = new Image(texturesDir + "logo_honk.png");
}
