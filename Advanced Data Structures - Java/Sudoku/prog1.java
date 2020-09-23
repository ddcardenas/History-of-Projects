//Eclipse IDE package to allow for multiple classes on different .java files to compile
//MAY NEED TO BE DELETED FOR ASSIGNMENT TURN IN
package assignment1pkg;

// 	Daniel Cardenas
// 	COMP282 Monday/Wednesday Class
// 	Assignment #1
// 	9/11/18 Turned In
// 	Contained in this file are the classes Spot and sudoku which implement an algorithm
//		for solving a Sudoku puzzle.

class Spot {
	private int row, col;
	//Spot constructor takes in row and column to identify spot
	public Spot(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public void setRow(int row){
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row; 
	}
	public int getCol() {
		return col;
	}
}

//the sudoku class creates a 9x9 grid made of integers
class sudoku {
	private int[][]  board;
	
	// default constructor -- I never seem to use it....
	public sudoku() {
		board = new int[9][9];
		//cycle through every spot on board
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				//assign 0 to every spot on board
				board[row][col] = 0;
			}
		}
	}
	
	// Construct a new sudoku puzzle from a string
	public sudoku(String s[]) {
		board = new int[9][9];
		//cycle through every spot on board[][]
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				//assign actual int value of char to respective spot on board[][]
				board[row][col] = (int) (s[row].charAt(col + col/3)) - 48;
			}
		}	
	}

	// Copy Constructor
	public sudoku(sudoku p) {
		board = new int[9][9];
		//cycle through every spot on board[][]
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				//int value assigned to board[][] from corresponding spot on p.board[][]
				board[row][col] = p.board[row][col];		
	}
	
	// prints board on screen with borders between numbers and 3x3 grids
	public String toString() {
		String result = new String();
		
		//cycle through board[][]
		for (int row = 0; row < 9; row++) {
			//create horizontal border after each third row
			if (row%3 == 0)
				result += "------------\n";
			for (int col = 0; col < 9; col++) {
				//create border between each third column
				if (col%3 == 0)
					result += "|";
				result += String.valueOf(board[row][col]);
				//create new line at end of each row
				if (col == 8)
					result += "\n";
			}
		}	
		return result;
	}
	
	// for easy checking of your answers
	public String toString2() {
		String result = new String();
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				result = result + String.valueOf(board[row][col]);
			}
		}
		return result;
	}
	
	// create rotated sudoku puzzle - used by my test programs
	public void rotate() {
		int[][] temp = new int[9][9];
		int row, col;
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				temp[col][8-row] = board[row][col];
			}
		}
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 9; col++) {
				board[row][col] = temp[row][col];
			}
		}
	}
	
	// Does the current board satisfy all the sudoku rules?
	/*
	 * 1) numbers on board must range 0 thru 9 inclusive
	 * 2) no repeated numbers in each row
	 * 3) no repeated numbers in each column
	 * 4) no repeated numbers in each box
	 */
	public boolean isValid() {
		boolean result = true;
		
		//cycle through board checking range
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (!(board[row][col] >= 0 && board[row][col] <= 9))
					result = false;  
			}
		}
		//cycle through each row checking for duplicates of non-zeroes
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				for (int index = col+1; index < 9; index++)
					if (board[row][col] == board[row][index] && board[row][col] != 0)
						result = false;
			}
		}	
		//cycle through each column checking for duplicates of non-zeroes
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				for (int index = row+1; index < 9; index++) {
					if (board[row][col] == board[index][col] && board[row][col] != 0)
						result = false;
				}
			}
		}
		//cycle through each box and check for duplicates of non-zeroes
		for (int row = 0; row < 9; row +=3) {
			for (int col = 0; col < 9; col +=3) {
				for (int val = 1; val < 10; val++) {
					int counter = 0;
					for (int r = (row/3)*3; r < ((row/3)*3) + 3; r++){
						for (int c = (col/3)*3; c < ((col/3)*3) + 3; c++) {
							if (board[r][c] == val) {
								counter++;
							}
						}
					}
					if (counter > 1)
						result = false;
				}		
			}
		}
		
		return result;
	}
	
	// Is this a solved sudoku?
	public boolean isComplete() {
		boolean result = true;
		//cycle through board to check for zeroes
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				//if a spot contains a zero, then board is not complete
				if (board[row][col] == 0)
					result = false;
			}
		}
		//check if valid board
		if (!isValid())
			result = false;
		
		return result;
	}
	
	
	//return true if val appears in the row of the puzzle
	private boolean doesRowContain(int row, int val) {
		boolean result = false;
		//cycle through row
		for (int col = 0; col < 9; col++) {
			if (board[row][col] == val)
				result =  true;
		}
		return result;
	}
	
	//return true if val appears in col (column) of the puzzle
	private boolean doesColContain(int col, int val) {
		boolean result = false;
		//cycle through column
		for (int row = 0; row < 9; row++) {
			if (board[row][col] == val)
				result = true;
		}
		return result;
	}
	
	// return true if val appears in the 3 x 3 box
	private boolean doesBoxContain(int row, int col, int val) {
		boolean result = false;
		//cycle through the box the position is located
		for (int r = (row/3)*3; r < (((row/3)*3) + 3); r++){
			for (int c = (col/3)*3; c < (((col/3)*3) + 3); c++) {
				if (board[r][c] == val) {
					result = true;
				}
			}
		}
		return result;
	}
	
	// return n if n is the only possible value for this spot
	// return 0 otherwise 
	private int fillSpot(Spot sq) {
		int row = sq.getRow();
		int col = sq.getCol();
		int counter = 0;
		int resultval = 0;
		int tempval = 0;
		//if spot is eligible to be changed
		if (board[row][col] == 0) {
			//cycle through each value 1-9
			for (int val = 1; val < 10; val++) {
				//if the current value is not in row, column or box...
				if (!doesRowContain(row, val) &&
						!doesColContain(col, val) &&
						!doesBoxContain(row, col, val)) {
					//...then the value is a possibility
					counter++;
					tempval = val;
				}
			}
		}
		//if only one possible value found, return value
		if (counter == 1)
			resultval = tempval;
		else
			resultval = 0;
		
		return resultval;
	}
		
	// return a valid spot if only one possibility for val in row
	// return null otherwise
	private Spot rowFill(int row, int val) {
		int counter = 0;
		Spot solvedspot = new Spot(0,0);
		
		//if row doesn't contain value, then we can continue checking
		if(!doesRowContain(row,val)) {
			//cycle through each spot of row
			for (int col = 0; col < 9; col++) {
				//check if column that spot is in does not have val and
				//	check if box that spot is in does not have val and
				//	check if spot itself is a zero.
				//	if all are true than spot is eligible
				if (board[row][col] == 0 && 
						!doesColContain(col, val) && 
						!doesBoxContain(row, col, val)) {
					counter++;
					solvedspot.setRow(row);
					solvedspot.setCol(col);
				}
			}
		}
		
		if (!(counter == 1))
			solvedspot = null;
		
		return solvedspot;
	}
	
	// return a valid spot if only one possibility for val in col (column)
	// return null otherwise
	private Spot colFill(int col, int val) {
		int counter = 0;
		Spot solvedspot = new Spot(0,0);
		
		//if column doesn't contain value, then we can continue checking
		if(!doesColContain(col,val)) {
			//cycle through each spot of column
			for (int row = 0; row < 9; row++) {
				//check if row that spot is in does not have val and
				//	check if box that spot is in does not have val and
				//	check if spot itself is a zero.
				//	if all are true than spot is eligible
				if (board[row][col] == 0 && 
						!doesRowContain(row, val) && 
						!doesBoxContain(row, col, val)) {
					counter++;
					solvedspot.setRow(row);
					solvedspot.setCol(col);
				}
			}
		}
		
		if (!(counter == 1))
			solvedspot = null;
		
		return solvedspot;
	}
	
	// return a valid spot if only one possibility for val in the box
	// return null otherwise 
	private Spot boxFill(int rowbox, int colbox, int val) {
		int counter = 0;
		Spot solvedspot = new Spot(0,0);
		
		if (!doesBoxContain(rowbox, colbox, val)) {
			for (int row = (rowbox/3)*3; row < (((rowbox/3)*3) + 3); row++) {
				for (int col = (colbox/3)*3; col < (((colbox/3)*3) + 3); col++) {
					//check if row that spot is in doesn't have val and
					//	check if column that spot is in doesn't have val and
					//	check if spot itself is a zero.
					//	if all are true than spot is eligible
					if ((board[row][col] == 0) 
							&& !doesRowContain(row, val) 
							&& !doesColContain(col, val)) {
								counter++;
								solvedspot.setRow(row);
								solvedspot.setCol(col);
					}
				}
			}
		}

		if (counter != 1)
			solvedspot = null;
		
		return solvedspot;	
	}	
	
	//solve sudoku
	public void solve() {
		boolean anychanges = true; 	//true if any change (a value is applied to board) occurs
		boolean rowchanges = true;  //true if rowFill applies a value to board
		boolean colchanges = true;  //true if colFill applies a value to board
		boolean boxchanges = true;  //true if boxFill applies a value to board
		boolean spotchanges = true; //true if fillSpot applies a value to board
		Spot spotcheck = new Spot(0,0);	//holds spot for fillSpot to use
		Spot s = null;	//placeholder for return of rowFill, colFill, boxFill 
						//	s will hold the spot they return, otherwise s will hold null
		
		//perform Fills so long as a change occurs
		while (anychanges) {
			anychanges = false;
			//perform rowFill so long as a change occurs
			rowchanges = true;	
			while (rowchanges) {
				rowchanges = false;
				//rowFill check on every row
				for (int row = 0; row < 9; row++) {
					for (int val = 1; val < 10; val++) {
						s = rowFill(row, val);
						if (s != null) {
							rowchanges = true;
							anychanges = true;
							board[row][s.getCol()] = val;
						}
					}
				}
			}
			//perform colFill so long as a change occurs
			colchanges = true;
			while (colchanges) {
				colchanges = false;
				//colFill check on every column
				for (int col = 0; col < 9; col++) {
					for (int val = 1; val < 10; val++) {
						s = colFill(col, val);
						if (s != null) {
							colchanges = true;
							anychanges = true;
							board[s.getRow()][col] = val;
						}
					}
				}
			} 
			//perform boxFill so long as a change occurs
			boxchanges = true;
			while (boxchanges)	{
				boxchanges = false;
				//boxFill check in each box (9 total)
				for (int row = 0; row < 9; row += 3) {
					for (int col = 0; col < 9; col += 3) {
						for (int val = 1; val < 10; val++) {
							s = boxFill(row, col, val);
							if (s != null) {
								boxchanges = true;
								anychanges = true;
								board[s.getRow()][s.getCol()] = val;
							}
						}
					}
				}
			} 
			//perform fillSpot so long as a change occurs
			spotchanges = true;
			while (spotchanges) {
				spotchanges = false;
				//fillspot check on every spot on board[][]
				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 9; col++) {
						spotcheck.setRow(row);
						spotcheck.setCol(col);
						//if fillSpot returns a value, then apply value to spot on board[][]
						if(fillSpot(spotcheck) != 0) {
							board[row][col] = fillSpot(spotcheck);
							spotchanges = true;
							anychanges = true;
						} 
					}
				}
			} 
		} 		
	}
	
	public static String myName() {
		return "Daniel D. Cardenas";
	}
}

