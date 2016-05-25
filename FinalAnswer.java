import java.util.*;

public class PuzzleSolver {

	public static String[] DICTIONARY = {"OX","CAT","TOY","AT","DOG","CATAPULT","T"};
    public static String[] INPUT;
    public static char[][] puzzle;
    private static ArrayList<String> words = new ArrayList<>();
    private static ArrayList<String> count = new ArrayList<String>();
    
    static boolean isWord(String testWord){
        if (Arrays.asList(DICTIONARY).contains(testWord))
            return true;
            return false;
    }
    
    public static void readInPuzzle(){
    	System.out.println("Scan in a word puzzle with each row separated by commas (with no spaces in between).\n" +
    	  "For example, if the word puzzle is:\nC A T\nX Z T\nY O T\n"
    		+"Type in, 'CAT,XZT,YOT'");
    	Scanner sc = new Scanner(System.in);
    	String line = sc.nextLine();  	
    	INPUT = line.split(","); 
    }

    public static char[][] turnTo2DCharArray(String[] INPUT){
        puzzle = new char[INPUT.length][];
        for (int i = 0; i < INPUT.length; i++) {
            puzzle[i] = INPUT[i].toCharArray();
        }
        return puzzle;
    }    
    
    public static int findWords(char[][] puzzle){
        getHorizontal(puzzle);
        getVertical(puzzle);
        getDiagonal(puzzle);
        
        addWordMultipleChar(count);
        add1CharWord(count,puzzle);
        return count.size();
    }

    private static void getHorizontal(char[][] puzzle){
        for(char[] c: puzzle){
            String s = new String(c);
            words.add(s);
        } 
    }
    
    private static void getVertical(char[][] puzzle){
        String s; 
        for(int i = 0; i < puzzle[0].length;i++){
            s = new String(); 
            for(int j = 0; j < puzzle.length; j++){
                s += puzzle[j][i]; 
            }
            words.add(s);
        }
    }

    private static void getDiagonal(char[][] puzzle){
        String s;
        for(int i = 0; i < puzzle[0].length;i++){
            s = new String();
            for(int j = 0, k=i; j < puzzle.length && k >= 0; j++, k--){
                s += puzzle[j][k];
            }
            if(s.length()>1){
                words.add(s);
            }
        }   
        for(int i = 1; i < puzzle.length;i++){
            s = new String();
            for(int j = puzzle[0].length-1, k=i; j >= 0  &&  k < puzzle.length ; j--, k++){
                s += puzzle[k][j];
            }
            if(s.length()>1){
                words.add(s);
            }
        }      
        for(int i = 0; i < puzzle[0].length; i++){
            s = new String();
            for(int j = puzzle.length-1, k = i; j >= 0 && k >= 0; j--, k--){
                s += puzzle[j][k];
            }
            if(s.length()>1){
                words.add(s);
            }
        }
       
        for(int i = 1; i >=0 ;i--){
            s = new String();
            for(int j = puzzle[0].length-1, k=i; j >= 0  &&  k >=0 ; j--, k--){
                s += puzzle[k][j];
            }
            if(s.length()>1){
                words.add(s);
            }
        }        
    } 

    private static void addWordMultipleChar(ArrayList<String> list){
        String s;
        for(int i = 0; i<words.size(); i++){
            getWords(list, words.get(i)); 
            s = new StringBuffer(words.get(i)).reverse().toString();
            getReverseWords(list, s); 
        }
    }
   
    private static void  add1CharWord(ArrayList<String> list, char[][] puzzle){
        for (char[] character : puzzle) {
            for (char c : character){
                String s = ""+c;
                if(isWord(s )){
                    list.add(s );
                }
            }
        }
    }
   
    private static void getWords(ArrayList<String> list, String s){
        while(s.length() >1){
            addWords(list,s);  
            s = removeLastChar(s);
        }
    }
   
    private static void getReverseWords(ArrayList<String> list, String s){
        while(s.length() >1){
            addReversedWords(list,s); 
            s = removeLastChar(s); 
        }
    }

    private static String removeLastChar(String s){
        return (s.substring(0, s.length()-1)); 
    }
  
    private static void addWords(ArrayList<String> list, String s){
        if(s.length() > 1){ 
           if(isWord(s)){
            list.add(s); 
            }
            addWords(list,s.substring(1));     
        }
    }

    private static void addReversedWords(ArrayList<String> list, String s){
        String reverseString = new StringBuffer(s).reverse().toString();
        if(!reverseString.equals(s) && isWord(s)){
            list.add(s);
        }
        if(s.length() > 1){             
            addReversedWords(list,s.substring(1));  
        } 
    }    
    
    public static void printWords(){
        System.out.print("(");
        for(int i = 0; i < count.size(); i++){
            System.out.print(count.get(i) + ",");
        }
        System.out.println(")");
    }
        
    public static void main(String[] args){ 	
    	readInPuzzle();
        char[][] puzzle = turnTo2DCharArray(INPUT); 
        PuzzleSolver p = new PuzzleSolver();
        int numWords = findWords(puzzle);        
        System.out.println(numWords); 
        printWords();
    }
    
}
