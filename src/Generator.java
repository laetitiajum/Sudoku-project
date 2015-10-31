
public class Generator {
	final int[][] vide;
	Sudoku sudoku;
	final int n;
	final int N;
	int clues;
	static int MAX=2;
	
	public Generator(int n) {
		this.n=n;
		this.N=n*n;
		this.vide=new int[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				vide[i][j]=0;
			}
		}
		DancingLinksSolver v = new DancingLinksSolver(n, vide);
		this.sudoku=v.grille();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (this.sudoku.M[i][j]!=0) {
					this.clues++;
				}
			}
		}
	}
	
	//Update the number of assigned cells in the sudoku
	public void setClues() {
		this.clues=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (this.sudoku.M[i][j]!=0) {
					this.clues++;
				}
			}
		}
	}
	
	//Select numbers one by one and check if the number can be remove (that is to say, if it is necessary to obtain one and only one solution)
	//If so, the method removes this number
	public void grilleMinimale() {
		
		for (int i=0; i<N/2+1; i++) { //We go through 1/4 of the grid
			for (int j=0; j<N/2+1; j++) {
				if (this.sudoku.M[i][j]!=0) {
					int val = this.sudoku.M[i][j];
					this.sudoku.M[i][j]=0;
					DancingLinksSolver dls = new DancingLinksSolver(n, this.sudoku.M);
					int res = dls.nbsol(MAX);
					if(res!=1) {
						this.sudoku.M[i][j]=val;
					}
				}
				
				if (this.sudoku.M[i][N-1-j]!=0) {
					int val = this.sudoku.M[i][N-1-j];
					this.sudoku.M[i][N-1-j]=0;
					DancingLinksSolver dls = new DancingLinksSolver(n, this.sudoku.M);
					int res = dls.nbsol(MAX);
					if(res!=1) {
						this.sudoku.M[i][N-1-j]=val;
					}
				}
				
				if (this.sudoku.M[N-1-i][j]!=0) { //Then we check in the other 3/4 of the grid
					int val = this.sudoku.M[N-1-i][j];
					this.sudoku.M[N-1-i][j]=0;
					DancingLinksSolver dls = new DancingLinksSolver(n, this.sudoku.M);
					int res = dls.nbsol(MAX);
					if(res!=1) {
						this.sudoku.M[N-1-i][j]=val;
					}
				}
				
				if (this.sudoku.M[N-1-i][N-1-j]!=0) {
					int val = this.sudoku.M[N-1-i][N-1-j];
					this.sudoku.M[N-1-i][N-1-j]=0;
					DancingLinksSolver dls = new DancingLinksSolver(n, this.sudoku.M);
					int res = dls.nbsol(MAX);
					if(res!=1) {
						this.sudoku.M[N-1-i][N-1-j]=val;
					}
				}
			}
		}
		setClues();
		//We update the number of given clues
	}
	
	
	//This method builds a complete grid and removes one number and its 3 symmetric numbers randomly (see in the method for the location of those numbers).
	//Then after this artificial method, we use the method grilleMinimale() to delete the last useless numbers
	
	public void bySuppression() {
		DancingLinksSolver dls = new DancingLinksSolver(n, this.sudoku.M);
		this.sudoku = dls.solve(2).getFirst();
		int val1=0;
		int val2=0;
		int val3=0;
		int val4=0;
		int i;
		int j;
		for (int k=0; k<8; k++) {
			i=(int) (Math.random()*N);
			j=(int) (Math.random()*N);
			while (this.sudoku.M[i][j]==0){
				i=(int) (Math.random()*N);
				j=(int) (Math.random()*N);
			}
				val1=this.sudoku.M[i][j]; //The randomly chosen number
				this.sudoku.M[i][j]=0;
				val2=this.sudoku.M[N-1-i][N-1-j]; //Its symmetric 1
				this.sudoku.M[N-1-i][N-1-j]=0;
				val3=this.sudoku.M[N-1-i][j]; //Its symmetric 2
				this.sudoku.M[N-1-i][j]=0;
				val4=this.sudoku.M[i][N-1-j]; //Its symmetric 3
				this.sudoku.M[i][N-1-j]=0;
				dls=new DancingLinksSolver(n, this.sudoku.M);
				if (dls.nbsol(2)!=1) {
					this.sudoku.M[i][j]=val1;
					this.sudoku.M[N-1-i][N-1-j]=val2;
					this.sudoku.M[N-1-i][j]=val3;
					this.sudoku.M[i][N-1-j]=val4;
				}
		}
		setClues();
		this.grilleMinimale();
	}
}
