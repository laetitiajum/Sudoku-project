
class Node {
	Node left;
	Node right;
	
	Node up;
	Node down;
	
	Node col; //Head of the column
	int val; //Value of the cell associated when Node is a node, size of the column when Node is a Head
	int cell; //id of the cell
	
	Node(Node l, Node r, Node u, Node d, Node c, int v, int ce) {
		this.left=l;
		this.right=r; 
		this.up=u;
		this.down=d;
		this.col=c;
		this.val=v;
		this.cell=ce;
	}
	

	public void setSize(int a) {
		this.val=this.val+a;

	}
	

}

