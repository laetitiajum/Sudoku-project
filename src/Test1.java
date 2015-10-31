
public class Test1 {

	public static void main(String[] args) {
		int[][] P={
				{0,0,3,3},
				{0,2,0,1},
				{4,0,0,0},
				{0,0,0,0}
			};
		
		int[][] M ={
		        {0,4,5,0,0,7,3,0,0},
		        {0,9,0,3,1,0,0,0,7},
		        {2,0,3,0,9,0,0,0,0},
		        {6,0,0,0,0,8,7,9,1},
		        {9,8,0,0,0,0,0,5,3},
		        {5,2,7,1,0,0,0,0,6},
		        {0,0,0,0,5,0,8,0,4},
		        {7,0,0,0,4,3,0,1,0},
		        {0,0,9,7,0,0,6,3,0}
		    };
		
		int[][] N ={
		        {9,0,0,1,0,0,0,0,5},
		        {0,0,5,0,9,0,2,0,1},
		        {8,0,0,0,4,0,0,0,0},
		        {0,0,0,0,8,0,0,0,0},
		        {0,0,0,7,0,0,0,0,0},
		        {0,0,0,0,2,6,0,0,9},
		        {2,0,0,3,0,0,0,0,6},
		        {0,0,0,2,0,0,9,0,0},
		        {0,0,1,9,0,4,5,7,0}
		    };
		
		int[][] Q ={
		        {5,3,0,0,7,0,0,0,4},
		        {6,0,0,1,9,5,0,0,0},
		        {0,9,8,0,0,0,0,6,0},
		        {8,0,0,0,6,0,0,0,3},
		        {4,0,0,8,0,3,0,0,1},
		        {7,0,0,0,2,0,0,0,6},
		        {0,6,0,0,0,0,2,8,0},
		        {0,0,0,4,1,9,0,0,5},
		        {0,0,0,0,8,0,0,7,9}
		    };
		
		int[][] D ={
		        {0,0,5,0,0,0,0,0,7},
		        {4,0,0,2,0,7,0,0,0},
		        {6,2,0,0,0,0,8,0,0},
		        {0,7,0,0,6,0,0,0,9},
		        {2,6,0,0,9,0,0,5,4},
		        {9,0,0,0,2,0,0,1,0},
		        {0,0,6,0,0,0,0,3,8},
		        {0,0,0,5,0,9,0,0,1},
		        {7,0,0,0,0,0,9,0,0}
		    };
		
		int[][] E = {
			{0, 0, 6, 0, 9, 11, 12, 0, 0, 0, 14, 16, 0, 3, 5, 4}, 
			{0, 3, 2, 0, 0, 4, 0, 0, 10, 8, 0, 9, 7, 1, 12, 0}, 
			{0, 7, 0, 11, 0, 0, 0, 10, 0, 0, 0, 0, 8, 0, 0, 0}, 
			{14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 10},
			{5, 0, 0, 0, 7, 0, 10, 0, 12, 6, 0, 0, 1, 0, 13, 0}, 
			{0, 6, 10, 0, 11, 3, 0, 0, 0, 0, 0, 4, 0, 0, 7, 0}, 
			{0, 15, 0, 0, 0, 0, 0, 9, 5, 0, 0, 3, 4, 0, 0, 0},
			{0, 0, 0, 16, 12, 2, 0, 5, 15, 0, 0, 0, 3, 0, 0, 8},
			{0, 4, 0, 0, 0, 12, 0, 8, 1, 7, 10, 0, 0, 0, 15, 0},
			{10, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 14, 0, 0},
			{15, 0, 0, 0, 1, 0, 4, 2, 0, 0, 0, 0, 10, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 7, 15, 0, 3, 0, 0, 6, 0, 0, 0},
			{13, 14, 0, 0, 0, 0, 9, 11, 0, 0, 8, 0, 15, 0, 6, 1},
			{0, 12, 5, 0, 0, 8, 0, 0, 7, 4, 0, 0, 0, 0, 0, 11}, 
			{0, 0, 0, 1, 0, 0, 16, 0, 0, 0, 3, 0, 0, 0, 9, 0},
			{0, 0, 9, 8, 6, 14, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0,}
		};
		
		
		System.out.println("P is:");
		
		Sudoku p=new Sudoku(P,2);
		p.afficher();
		p.resoudre();
		
		System.out.println("M is:");
		
		Sudoku s=new Sudoku(M,3);
		s.afficher();
		s.resoudre();
		
		System.out.println("N is:");
		
		Sudoku r=new Sudoku(N,3);
		r.afficher();
		r.resoudre();
		
		System.out.println("Q is:");
		
		Sudoku q=new Sudoku(Q,3);
		q.afficher();
		q.resoudre();
		
		long t1= System.currentTimeMillis();
		System.out.println("D is:");
		
		Sudoku d=new Sudoku(D,3);
		d.afficher();
		d.resoudre();
		
		long t2=System.currentTimeMillis();
		System.out.print("It took ");
		System.out.print(t2-t1);
		System.out.print(" milliseconds to solve D");

//		System.out.println("E");
//		
//		Sudoku e=new Sudoku(E,4);
//		e.afficher();
//		e.resoudre();
		//e can not be solve with the naive method
	}

}