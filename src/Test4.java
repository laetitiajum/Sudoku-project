
public class Test4 {

	public static void main(String[] args) {
		int sommeclues =0;
		int min=23;
		
		for (int i=0; i<100; i++) {
			Generator G=new Generator(3);
			G.grilleMinimale();
			sommeclues=sommeclues+G.clues;
			if (G.clues<min) min=G.clues;	
			//System.out.println(sommeclues);
		}
		System.out.println("The minimum number of clues with found is "+min+". On average, there are "+sommeclues+"/100 clues");
	}
}
