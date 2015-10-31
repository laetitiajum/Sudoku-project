public class Sudoku {
	int[][] M;
	int n;
	int[][] l,c;
	int[][][] s;
	
	public Sudoku(int[][] M,int n){
		this.M=new int[n*n][n*n];
		this.n=n;
		this.s=new int[n][n][n*n+1];
		this.l=new int[n*n][n*n+1];
		this.c=new int[n*n][n*n+1];	
			
		for (int i=0;i<n*n;i++){
			for (int j=0;j<n*n;j++){
				this.M[i][j]=M[i][j];
				if (M[i][j]!=0){
					if (l[i][M[i][j]]==1 || c[j][M[i][j]]==1 || s[i/n][j/n][M[i][j]]==1){
						System.out.println("Non valid grid");
					}
					l[i][M[i][j]]=1;
					c[j][M[i][j]]=1;
					s[i/n][j/n][M[i][j]]=1;
				}
			}
		}
		
	}
	
	//Add a constraint
	public void ajouterContrainte(int i,int j,int v){
		this.l[i][v]=1;
		this.c[j][v]=1;
		int[] pos=trouverSquare(i,j,n);
		this.s[pos[0]][pos[1]][v]=1;
	}
	
	//Remove a constraint
	public void annulerDerniereContrainte(int i,int j,int v){
		this.l[i][v]=0;
		this.c[j][v]=0;
		int[] pos=trouverSquare(i,j,n);
		this.s[pos[0]][pos[1]][v]=0;
	}
	
	//Find the region (n*n square)
	public static int[] trouverSquare(int i,int j,int n){
		int[] pos=new int[2];
		pos[0]=i/n;
		pos[1]=j/n;
		return pos;
	}
	
	//Find an empty cell
	public int[] trouverCaseVide(int m, int l){
		int[] res=new int[2];
		for (int j=l;j<n*n;j++){
			if (M[m][j]==0){
				res[0]=m;
				res[1]=j;
				return res;
			}
		}
		for (int i=m+1;i<n*n;i++){
			for (int j=0;j<n*n;j++){
				if (M[i][j]==0){
					res[0]=i;
					res[1]=j;
					return res;
				}
			}
		}
		return null;
		
	}
	
	//Naive exploration of a sudoku
	public boolean explorationNaive(int i, int j){
		int[] vide=trouverCaseVide(i,j);
		
		if (vide==null) return true;
		else {
			int[] pos=trouverSquare(vide[0],vide[1],n);
			for (int k=1;k<=n*n;k++){
				if (l[vide[0]][k]==0 && c[vide[1]][k]==0 && s[pos[0]][pos[1]][k]==0){
					M[vide[0]][vide[1]]=k;
					ajouterContrainte(vide[0],vide[1],k);
					if (explorationNaive(vide[0],vide[1])==true){
						return true;
					}
					else{
						this.annulerDerniereContrainte(vide[0],vide[1],M[vide[0]][vide[1]]);
						M[vide[0]][vide[1]]=0;
					}
				}
			}
			return false;
		}
	}
	
	//Display the sudoku
	public void afficher(){
		int v;
		for (int i=0;i<n;i++){
			for (int l=0; l<n; l++) {
				System.out.print(" | ");
				for (int j=0;j<n;j++){
					for (int k=0; k<n; k++) {
						v=M[i*n+l][j*n+k];
						if(v==0){System.out.print(" ");
						}
						else{
							System.out.print(v);
						}
					}
					System.out.print(" | ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	//Solve the sudoku
	public void resoudre(){;
		if (this.explorationNaive(0,0)){
			System.out.println("The solution is:");
			this.afficher();
		}
		else {
			System.out.println("This sudoku grid can not be solved.");
		}
	}
	
}