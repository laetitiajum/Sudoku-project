import java.util.LinkedList;


public class DancingLinksSolver {
	public int[][] sudoku;
	public LinkedList<Sudoku> solutions;
	int n;
	int N;
	int nsol;

	//Constructor
	public DancingLinksSolver(int n, int[][] s) {
		this.sudoku=s;
		this.n=n;
		this.N=n*n;
		this.solutions=new LinkedList<Sudoku>();
		this.nsol=0;
	}

	//Return the column that contains the smallest number of nodes
	//Return root if the CoverMatrix is empty (no more constraint)
	Node choosecolumn(Node root) {
		int min=N*N;
		Node col= root.right;
		Node COL=root.right; 
		
		while(col!=root.left) {

			if(min>col.val) {
				min=col.val;
				COL=col;
			}
			col=col.right;
		}
		return COL;
	}
	
	//Delete a column in the CoverMatrix
	public void covercolumn(Node header) {
		
		//Deletion of the header
		header.left.right=header.right;
		header.right.left=header.left;
		
		Node nodecol=header.down;
		//we go through the column to delete the associated lines
		while(nodecol!=header) { 
			Node nodeline=nodecol.right;
			//we go through the line to delete it
			while (nodeline!=nodecol) { 
				nodeline.up.down=nodeline.down;
				nodeline.down.up=nodeline.up;
				nodeline.col.setSize(-1);
				nodeline=nodeline.right;
			}
			nodecol=nodecol.down;
		}
	}
	
	//Put a column back in the CoverMatrix
	public void uncovercolumn(Node header) {
		Node nodecol=header.up;
		//We go through the column
		while(nodecol!=header) {
			Node nodeline=nodecol.left;
			//We go through the line
			while (nodeline!=nodecol) {
				nodeline.up.down=nodeline;
				nodeline.down.up=nodeline;
				nodeline.col.setSize(1);
				nodeline=nodeline.left;
			}
			nodecol=nodecol.up;
		}
		//we update the header
		header.left.right=header;
		header.right.left=header;
		}
	
	//Now, we solve a sudoku grid using the DancingLinks method (see below)
	
	//Solve and display the MAX first solutions
	public void solveAfficher(int MAX) { 
		this.nsol=0;
		
		//We create the CoverMatrix containing all the constraints of our sudoku
		CoverMatrix M=new CoverMatrix(n, sudoku);
		
		//We copy the sudoku into the solution array
		int[][] solution=new int[N][N];
		for (int i=0;i<N;i++){
			for (int j=0;j<N;j++){
				if (sudoku[i][j]!=0){
					solution[i][j]=sudoku[i][j];
				}
			}
		}
		
		//We solve the sudoku and fill in the solution array
		solve(M.root,solution,MAX);
			if (this.nsol==MAX){
				System.out.println("This soduku has at least "+MAX+" solution(s)");
			}
			else if(this.nsol>0){
				System.out.println("This sudoku has "+ this.nsol+" solution(s)");
			}
			else {
			System.out.println("This sudoku does not have any solution");
			}
			for(Sudoku s:this.solutions) {
				s.afficher();
				System.out.println("---------------------");
			}
	}

	//Solve and display the list of solutions
	public LinkedList<Sudoku> solve(int MAX) {
		this.nsol=0;
		
		//We create the CoverMatrix containing the constraints
		CoverMatrix M=new CoverMatrix(n, sudoku);
		int[][] solution=new int[N][N];
		for (int i=0;i<N;i++){
			for (int j=0;j<N;j++){
				if (sudoku[i][j]!=0){
					solution[i][j]=sudoku[i][j];
				}
			}
		}
		
		//We solve the sudoku and copy the solution in the solution array
		solve(M.root,solution,MAX);
		if (this.nsol==MAX){
			System.out.println("This soduku has at least "+MAX+" solution(s)");
		}
		else if(this.nsol>0){
			System.out.println("This soduku has "+ this.nsol+" solution(s)");
		}
		else {
		System.out.println("This sudoku does not have any solution");
		}
		return this.solutions;
	}
	
	
	//Dancing Links method that solves the sudoku
	boolean solve(Node root, int[][] solution,int MAX) {
		boolean solved=false;
		
		Node columnheader=choosecolumn(root);
		//The CoverMatrix is empty, so the sudoku is solved
		if(columnheader==root) {
			if (solution!=null){   //when we input solution==null, it allows us to only count the solutions
				this.solutions.addFirst(new Sudoku(solution, n));
			}
			if (this.nsol==MAX){
				return true;
			}
			this.nsol++;
			solved=true;
		}
		
		covercolumn(columnheader);
		Node nodecol=columnheader.down; //we add the line of nodecol to the partial solution
		if (nodecol!=columnheader){
			if (solution!=null){
			solution[nodecol.cell/N][nodecol.cell%N]=nodecol.val;
			}
		}
		
		while (nodecol!=columnheader) { //we remove all the columns that have a 1 on the line of nodecol
			Node nodeline=nodecol.right;
			while (nodeline!=nodecol) {
				covercolumn(nodeline.col);
				nodeline=nodeline.right;
			}
			
			solved=solve(root,solution,MAX); //recursive call to the function solve. If it is solved, we call return if we have more solutions that we want. Else, we keep looking
			if(solved) { 
				if (this.nsol>=MAX){
					return true;
				}
			}
			nodeline=nodecol.left; //we uncover the line
			while(nodeline!=nodecol) {
				uncovercolumn(nodeline.col);
				nodeline=nodeline.left;
			}
//			if (solution!=null){
//				solution[nodecol.cell/N][nodecol.cell%N]=0;
//			}
			nodecol=nodecol.down;
			if (nodecol!=columnheader){
				if (solution!=null){
					solution[nodecol.cell/N][nodecol.cell%N]=nodecol.val;
				}
			}
		}
		uncovercolumn(columnheader);
		
		return solved;
	}
	
	
	
	//Creation of a sudoku grid (we have to provide an empty grid)
	
	//Count the number of solutions
	public int nbsol(int MAX) {
		this.nsol=0;
		CoverMatrix M=new CoverMatrix(n, this.sudoku);
		solve(M.root,null,MAX);
		return this.nsol;
	}
	
	//Choose a random column
	public Node chooseRandomColumn(Node root, int nbcol) {
		int r=(int) (Math.random()*(nbcol));
		Node a=root.right;
		for(int i=1; i<r; i++) {
			a=a.right;
		}
		return a;
	}
	
	//Choose a random node
	public Node chooseRandomNode(Node columnheader){
		int r=(int) (Math.random()*(columnheader.val));
		Node a=columnheader.down;
		for(int i=1; i<r; i++) {
			a=a.down;
		}
		return a;
	}


	//Create a random grid with the int[][] grille method (see below)
	public Sudoku grille(){
		int compteur=0;
		CoverMatrix m=new CoverMatrix(n,this.sudoku);
		Sudoku sud=new Sudoku(this.grille(m,4*N*N, compteur),n);
		return (sud);
	}

	
	//We start with a complete CoverMatrix
	//We randomly choose a column and a node to delete, then count if their is more than 1 solution to the sudoku
	//If so, we continue to randomly delete lines
	//Else, we have created a sudoku
	public int[][] grille(CoverMatrix m,int nbcol, int compteur){
		if (compteur>2000) {
			return (this.grille().M);
		}
		compteur++;
		int MAX=2;
		N=this.sudoku.length; 
		Node columnheader=this.chooseRandomColumn(m.root,nbcol);
		Node cell=this.chooseRandomNode(columnheader);
		while (cell.cell<0){
			columnheader=this.chooseRandomColumn(m.root,nbcol);
			cell=this.chooseRandomNode(columnheader);
		}
		covercolumn(columnheader);
		nbcol--;
		
		this.sudoku[cell.cell/N][cell.cell%N]=cell.val;
		int res=this.nbsol(MAX);
		if (res==1){ //There is one and only one solution, we have created a sudoku 
			return this.sudoku;
		}
		
		else if (res>1){ //There are more than one solution, we continue removing nodes
			return(grille(m, nbcol, compteur));
		}
		
		else { //There is no solution, we uncover the last column we covered
			this.sudoku[cell.cell/N][cell.cell%N]=0;
			uncovercolumn(columnheader);
			nbcol++;
			return(grille(m,nbcol, compteur));
		}
	}

	
	//ATTEMPT to gain in efficiency
	//Maybe we can randomly choose nodes in a less random way (only inside a line) (except if not possible)
	//When filling the sudoku in, we will add one number line after line (rather than choose a random line, which could result in a less spatially harmonized way of filling the sudoku)
	
	public Node chooseRandomNode2(Node columnheader, int k){
		//k is the id of the line in which we look for the cell
		int r=(int) (Math.random()*(columnheader.val));
		Node a=columnheader.down;
		for(int i=1; i<r; i++) {
			a=a.down;
		}
		int iterations=0;
		while (a.cell/N!=k && iterations<columnheader.val) {
			a=a.down;
			iterations++;
		}
		return a;
	}
	
	public Sudoku grille2(){
		int compteur =0;
		CoverMatrix m=new CoverMatrix(n,this.sudoku);
		int k=0;
		Sudoku sud=new Sudoku(this.grille2(m,4*N*N, compteur, k),n);
		return (sud);
	}
	
	public int[][] grille2(CoverMatrix m,int nbcol, int compteur, int k){
		if (compteur>1000) {
			return (this.grille2().M);
		}
		compteur++;
		int MAX=2;
		N=this.sudoku.length;
		Node columnheader=this.chooseRandomColumn(m.root,nbcol);
		Node cell=this.chooseRandomNode2(columnheader, k);
		while (cell.cell<0){
			columnheader=this.chooseRandomColumn(m.root,nbcol);
			cell=this.chooseRandomNode2(columnheader, k);
		}
		covercolumn(columnheader);
		nbcol--;
		k=(k+1)%9;
		
		this.sudoku[cell.cell/N][cell.cell%N]=cell.val;
		
		int res=this.nbsol(MAX);
		if (res==1){ //Sudoku created
			return this.sudoku;
		}
		
		else if (res>1){ //More than one solution, we continue 
			return(grille2(m, nbcol, compteur, k));
		}
		
		else { //No solution, we uncover the last column we covered
			this.sudoku[cell.cell/N][cell.cell%N]=0;
			uncovercolumn(columnheader);
			nbcol++;
			return(grille2(m,nbcol, compteur, k));
		}
	}
	
	

		
	
		
}