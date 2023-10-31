import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Launcher {
	public static void main(String[] args) throws Exception {
		Chat cat = new Chat();
		cat.printListeActions();
		Robot rob = new Robot();
		rob.printListeActions();
		
		Maison house = new Maison();
		System.out.println(house.getPiece().getPiece());
		Random random = new Random();
		for (int i = 0; i <= 10; i++){
			switch(random.nextInt(4)){
				case 0:
					System.out.print("going up to ");
					house.goHaut();
					break;
				case 1:
					System.out.print("going down to ");
					house.goBas();
					break;
				case 2:
					System.out.print("going left to ");
					house.goGauche();
					break;
				case 3:
					System.out.print("going right to ");
					house.goDroite();
					break;
			}
			System.out.println(house.getPiece().getPiece());
		}
	}
}