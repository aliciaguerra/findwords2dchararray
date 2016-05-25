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
    
   // public static int findWords(char[][] puzzle) {
   // 	return 1;
   // }
	
	public static void readBoard() {
		ArrayList<String> inputBoard = new ArrayList<String>();		
		sc = new Scanner(System.in);
		
		System.out.println("Enter number of rows");
		puzzleRow = Integer.parseInt(sc.nextLine());		
		
		System.out.println("Enter number of columns");
		puzzleColumn = Integer.parseInt(sc.nextLine());      		
		
		System.out.println("Enter the characters in the character array, with a new line for each character.");
		for(int i=0; i<puzzleRow; i++) {
			for(int j=0; j<puzzleColumn; j++) {
				inputBoard.add(sc.nextLine().replaceAll(" ","").toUpperCase());	
		       }
		}
		
		
	    System.out.println("Done with characters");
		
	    //Now that we have the "inputBoard", we know how many rows and cols we have,
	    //so we can allocate and load the char[][] board
	    puzzleRow = inputBoard.size();
	    puzzleColumn = inputBoard.get(0).length();
	    puzzle = new char[puzzleRow][puzzleColumn];
	    
	    for (int row=0; row<puzzleRow; row++) {
			for (int col=0; col<puzzleColumn; col++){
				puzzle[row][col] = inputBoard.get(row).charAt(col);
			}
	    }
	    System.out.println("Done with puzzle");
	    
		//print board
		for(int i=0; i<puzzleRow; i++){
			for(int j=0; j<puzzleColumn; j++){
				System.out.println(puzzle[i][j]);
			}
		}
		
		
		
		ArrayList<String> result = new ArrayList<String>();
		int m = puzzle.length;
		int n = puzzle[0].length;
		int counter=0;
		
		for(String word: DICTIONARY){
			boolean flag = false;
			for(int i=0; i<m; i++){
				for(int j=0; j<n; j++){
					char[][] newBoard = new char[m][n];
					for(int x=0; x<m; x++)
						for(int y=0; y<n; y++)
							newBoard[x][y]=puzzle[x][y];
			if(dfs(newBoard,word,i,j,0)){
				flag=true;
				counter++;
			       }
				}
			}
			
			if(flag) {
				result.add(word);
			}
		}
		
	    System.out.println("print arraylist");
		//print out contents in result arraylist  
		for(String s: result){
			System.out.println(s);
		}
		System.out.println(counter);
		
				
	}
		
	
	 
	public static boolean dfs(char[][] puzzle, String word, int i, int j, int k) {
		int m = getRows();
		int n = getCols();
	 
		if (i < 0 || j < 0 || i >= m || j >= n || k > word.length() - 1) {
			return false;
		}
	 
		if (puzzle[i][j] == word.charAt(k)) {
			char temp = puzzle[i][j];
			puzzle[i][j] = '#';
	 
			if (k == word.length() - 1) {
				return true;
			} else if (dfs(puzzle, word, i - 1, j, k + 1)
					|| dfs(puzzle, word, i + 1, j, k + 1)
					|| dfs(puzzle, word, i, j - 1, k + 1)
					|| dfs(puzzle, word, i, j + 1, k + 1)) {
				puzzle[i][j] = temp;
				return true;
			}
	 
		} else {
			return false;
		}
	 
		return false;
	}
	
	
	
	
	public static int getRows() {
		return puzzle.length;
	}
	
	public static int getCols() {
		return puzzle[0].length;
	}
	

	public static void main(String[] args) {
    	readBoard();
		//findWords(puzzle,DICTIONARY);
	 }
	}
}
