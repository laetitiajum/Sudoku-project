# Sudoku-project
Solve sudokus and create new ones

In the Sudoku class, we see how to solve sudokus in a naive way. We use a simple backtracking algorithm.

In the DancingLinksSolver class, we solve sudokus in a more efficient way. We approach the problem as an exact cover problem. The CoverMatrix class defines the cover matrix, which contains all the constraints of our sudoku. In the DancingLinksSolver class, we use the dancing link technique to implement Donald Knuth’s Algorithm X.

Then, we tried several methods to create sudoku grids with the minimum number of clues at the beginning.
