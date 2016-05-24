import java.util.*;

public class Puzzle {
	
	//PuzzleSolver must be an inner class b/c outer classes cannot be static in Java.
	public static class PuzzleSolver { 

    public static String[] DICTIONARY = {"OX", "CAT", "TOY", "AT", "DOG", "CATAPULT", "T"}; 
    //We read in the input as an ArrayList<String> since we don't know how many rows to read in
    //ahead of time, but then we convert that input to a 2D array of characters (puzzle) once
    //we know what dimensions you use.
    private static char[][] puzzle; 
    private static int puzzleRow, puzzleColumn;
	private static Scanner sc;
	
	static boolean IsWord(String testWord) {
		//In Java, you cannot invoke Contains(String) on a String array.
		//Therefore, "if(DICTIONARY.Contains(testWord))" is not valid in Java. We have to rewrite this method.
        //Another (more efficient) manner of checking the array is to use a binary search.
		int index = Arrays.binarySearch(DICTIONARY, testWord);
		return index >= 0;
	}
    
    public static int findWords(char[][] puzzle) {
    	return 1;
    }
	
	public static void readBoard() {
	
		ArrayList<String> inputBoard = new ArrayList<String>();
				
		sc = new Scanner(System.in);
		
		System.out.println("Enter number of rows");
		puzzleRow = Integer.parseInt(sc.nextLine());
		puzzleRow = inputBoard.size();
		
		System.out.println("Enter number of columns");
		puzzleColumn = Integer.parseInt(sc.nextLine());
        in
		
		puzzle = new char[puzzleRow][puzzleColumn];
		System.out.println("Enter the characters in the character array on the same line seperated by spaces.");
		for(int i=0; i<puzzleRow; i++){
			for(int j=0; j<puzzleColumn; j++) {
				puzzle[i][j]=sc.next().charAt(0);
				puzzle[i][j]=inputBoard.get(i).charAt(j);
			}
		 }
	}
		

	
	/*
	public static void printBoard() {
		for(int i=0; i<puzzleRow; i++){
			for(int j=0; j<puzzleColumn; j++){
				System.out.print("\t" + puzzle[i][j]);
			}
			System.out.println();
		}
	}
	*/
	
	public static int getRows() {
		return puzzle.length;
	}
	
	public static int getCols() {
		return puzzle[0].length;
	}
	
 	
 	public static void processWords() {
 		while (sc.hasNext()) {
	 		String word = sc.next();
	 		findWord(word);
 		}
 	}
 	
 	public static void findWord(String word) {
 		int rows = getRows();
 		int cols = getCols();
 		for (int row=0; row<rows; row++)
 			for (int col=0; col<cols; col++)
 				findWord(word,row,col);
 	}
 	
 	public static void findWord(String word, int row, int col) {
 		for (int drow=-1; drow<=1; drow++)
 			for (int dcol=-1; dcol<=1; dcol++)
 				findWord(word,row,col,drow,dcol);
 	}
 	
 	private static String[][] directionNames =
 		{	{ "Up-Left" ,  "Up"  , "Up-Right"   },
 			{ "Left"    ,  ""    , "Right"      },
 			{ "Down-Left", "Down", "Down-Right" }
 		};

 	public static String getDirectionName(int drow, int dcol) {
 		
 		return directionNames[drow+1][dcol+1];
 	}

 	public static void findWord(String word, int row, int col, int drow, int dcol) {
 		int rows = getRows();
 		int cols = getCols();
 		for (int offset=0; offset<word.length(); offset++) {
			int targetRow = row + offset*drow;
			int targetCol = col + offset*dcol;
			if ((targetRow < 0) ||
				(targetRow >= rows) ||
				(targetCol < 0) ||
				(targetCol >= cols))
				return;
			char boardChar = puzzle[targetRow][targetCol];
			char wordChar  = word.charAt(offset);
			if (boardChar != wordChar)
				return;
 		}
 		
 		System.out.printf("%s at %d,%d heading %s\n",
 						   word, row, col, getDirectionName(drow,dcol));
 	}

	public static void main(String[] args) {
    	readBoard();
    	//printBoard();
    	processWords();
		findWords(puzzle);
	 }
	}
}
