
public class CoverMatrix {
	Node root;
	int n;
	int N;
	Node[] headers;
	
	CoverMatrix(int n, int[][] s) {
		this.n=n;
		this.N=n*n;
		this.headers=new Node[4*N*N];
		this.root = new Node(null, null, null, null, null, N*N, -4*N*N);
		this.root.left=this.root;
		this.root.right=this.root;
		this.root.up=this.root.down=this.root.col=this.root;

		
		//initialization of the headers
		headers[0]=new Node(null, null, null, null, null, 0, -0);
		headers[4*N*N-1]=new Node(null, null, null, null, null, 0, -4*N*N-1);
		
		for (int i=1; i<4*N*N-1; i++) {
			headers[i]=new Node(headers[i-1], null, null, null, null, 0, -i);
		}
		for (int i=1; i<4*N*N-1; i++) {
			headers[i].right=headers[i+1];
			headers[i].left=headers[i-1];
			headers[i].up=headers[i].down=headers[i];
			headers[i].col=headers[i];
		}
		headers[0].right=headers[1];
		headers[0].left=this.root;
		headers[4*N*N-1].left=headers[4*N*N-2];
		headers[4*N*N-1].right=this.root;
		headers[0].col=headers[0];
		
		headers[0].up=headers[0].down=headers[0];
		headers[4*N*N-1].up=headers[4*N*N-1].down=headers[4*N*N-1];
		headers[4*N*N-1].col=headers[4*N*N-1];

				
		//we update root
		root.left=headers[4*N*N-1];
		root.right=headers[0];

		
		//we go through the Sudoku to solve, and we create the nodes of the CoverMatrix
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				
				if (s[i][j]==0) { //the cell is empty, we add N rows to the CoverMatrix (because the cell can potentially take N values)
					
					for (int value=1; value<N+1; value++) {
						//the cell (i, j) has the id (i*N+j) 
						//we create the first node
						Node node1=new Node(null, null, headers[i*N+j].up, headers[i*N+j], headers[i*N+j], value, i*N+j);
						node1.left=node1;
						node1.right=node1;
						
						//we update the neighbors of node1
						node1.up.down=node1;
						node1.down.up=node1;
						headers[i*N+j].setSize(1);
						
						//we create node2 and update its neighbors
						Node node2=new Node(node1, node1, headers[(N*N)+i*N+value-1].up, headers[(N*N)+i*N+value-1], headers[(N*N)+i*N+value-1], value, i*(n*n)+j);
						node2.up.down=node2;
						node2.left.right=node2;
						node2.down.up=node2;
						node1.left=node2;
						headers[(N*N)+i*N+value-1].setSize(1);
						
						//Node3
						Node node3=new Node(node2, node1, headers[(N*N)*2+j*N+value-1].up, headers[(N*N)*2+j*N+value-1], headers[(N*N)*2+j*N+value-1], value, i*(n*n)+j);
						node3.up.down=node3;
						node3.left.right=node3;
						node3.down.up=node3;
						node3.up.down=node3;
						node1.left=node3;
						headers[(N*N)*2+j*N+value-1].setSize(1);
						
						//Node4
						int r = (i/n)*n+j/n; //number of the region (one N*N square)
						Node node4=new Node(node3, node1, headers[(N*N)*3+r*N+value-1].up, headers[(N*N)*3+r*N+value-1], headers[(N*N)*3+r*N+value-1], value, i*(n*n)+j);
						node4.up.down=node4;
						node4.left.right=node4;
						node4.down.up=node4;
						node1.left=node4;
						headers[(N*N)*3+r*N+value-1].setSize(1);
					}
					
				}
					
					
				else { //the cell has a number, we add one line to the CoverMatrix
					int value=s[i][j];
					
					//the cell (i, j) has the id (i*N+j) 
					//we create the first node
					Node node1=new Node(null, null, headers[i*N+j].up, headers[i*N+j], headers[i*N+j], value, i*N+j);
					node1.left=node1;
					node1.right=node1;
					
					//we update the neighbors of node1
					node1.up.down=node1;
					node1.down.up=node1;
					headers[i*N+j].setSize(1);
					
					//we create node2 and update its neighbors
					Node node2=new Node(node1, node1, headers[(N*N)+i*N+value-1].up, headers[(N*N)+i*N+value-1], headers[(N*N)+i*N+value-1], value, i*(n*n)+j);
					node2.up.down=node2;
					node2.left.right=node2;
					node2.down.up=node2;
					node1.left=node2;
					headers[(N*N)+i*N+value-1].setSize(1);
					
					//Node3
					Node node3=new Node(node2, node1, headers[(N*N)*2+j*N+value-1].up, headers[(N*N)*2+j*N+value-1], headers[(N*N)*2+j*N+value-1], value, i*(n*n)+j);
					node3.up.down=node3;
					node3.left.right=node3;
					node3.down.up=node3;
					node3.up.down=node3;
					node1.left=node3;
					headers[(N*N)*2+j*N+value-1].setSize(1);
					
					//Node4
					int r = (i/n)*n+j/n; //number of the region
					Node node4=new Node(node3, node1, headers[(N*N)*3+r*N+value-1].up, headers[(N*N)*3+r*N+value-1], headers[(N*N)*3+r*N+value-1], value, i*(n*n)+j);
					node4.up.down=node4;
					node4.left.right=node4;
					node4.down.up=node4;
					node1.left=node4;
					headers[(N*N)*3+r*N+value-1].setSize(1);
				}

				
			}
		}
		
	}
	
	
}
