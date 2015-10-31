
public class Test3 {
	public static void main(String[] args) {

	System.out.println("Creation of a random valid sudoku grid then deletion of useless clues with the grilleMinimale() method: ");
	Generator G=new Generator(3);
	System.out.println("There are "+G.clues+" clues: ");
	G.sudoku.afficher();
	
	G.grilleMinimale();
	System.out.println("After the grilleMinimale() method, there are "+G.clues+" clues: ");
	G.sudoku.afficher();
	
	System.out.println("---------------------");

	System.out.println("Creation of a valid sudoku grid by deleting clues in a complete grid: ");
	Generator G2=new Generator(3);
	G2.bySuppression();
	G2.sudoku.afficher();	
	System.out.println("Il y a "+G2.clues+" indices.");
	}
}
