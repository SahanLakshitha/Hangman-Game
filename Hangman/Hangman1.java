
import java.io.*;
import java.util.*;
import java.util.List;

public class Hangman1 {
    
    static String WORDLIST_FILENAME = "words.txt";
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    Random rand = new Random();

    
    public static void chooseRandomWord(String[] allWords) {
		Random rand = new Random();
		
		int rand_num = rand.nextInt(allWords.length);
		String rand_word = allWords[rand_num];
	    hangman(rand_word);
    }

    public static void loadWords() {
        File file = new  File("word.txt");
		try{
			Scanner input =new Scanner(file);
        
			String name = input.nextLine();
			String [] words = name.split(" ");
			System.out.println(words.length + " words loaded.");
				chooseRandomWord(words);
                    }
		catch(IOException ex){
			System.out.printf("error:%s/n",ex);
		}
    }

    
    public static void getGuessedWord(String word, String[] lettersGuessed) {
        String []wordArray = word.split("");
		String [] willGuessWord = new String[wordArray.length];
			for(int i = 0 ; i < wordArray.length ; i++){
				willGuessWord[i] = " _";
			}
			charGuess(willGuessWord, wordArray);
			
    }
	
	public static void charGuess(String[] willGuessWord, String[] wordArray){
		Scanner scan = new Scanner(System.in);
				String alpha = "abcdefghijklmnopqrstuvwxyz" ; 
                String [] alpha2 = alpha.split("");
                List<String>list = new ArrayList<String>(Arrays.asList(alpha2));
                
		int i ;
                int chances = 6 ;
                String []guesedCharas = new String[100] ;
                String [] willGuessWord2 = new String[wordArray.length];
			for(int f = 0 ; f < wordArray.length ; f++){
				willGuessWord2[f] = " _";
			}
                String guessChar = "" ;
                for(i = 0; i < 24 ;i++){
                    
                    for(int v = 0 ; v < wordArray.length; v++){
                        willGuessWord2[v] = willGuessWord[v];
                    }
                    if(Arrays.toString(wordArray).equals(Arrays.toString(willGuessWord))){
                        // game win
                    }
                    else{
                        System.out.println("You have "+ chances +" guesses left");
                        System.out.println("Available letters: " + String.join("", list));
                        System.out.print("Please guess a letter: ");
			guessChar = scan.next();
                        System.out.println("");
                        list.remove(guessChar);
                            for(int x = 0 ; x < wordArray.length; x++){
				if(guessChar.equals(wordArray[x])){
                                    willGuessWord[x] = guessChar ;
				}
                                else{
                                    
                                }
                            }
                        }
                        if(Arrays.asList(guesedCharas).contains(guessChar) &&  !Arrays.toString(wordArray).equals(Arrays.toString(willGuessWord))){
                            System.out.println("Oops! You've already guessed that letter: " +  String.join("", willGuessWord));
                            System.out.println("-------------");
                        }
                        else if( !Arrays.toString(wordArray).equals(Arrays.toString(willGuessWord))){
                            if(Arrays.toString(willGuessWord2).equals(Arrays.toString(willGuessWord))){
                                System.out.println("Oops! That letter is not in my word:" +  String.join("", willGuessWord));
                                System.out.println("-------------");
                                if (chances == 1){
                                    loseGame(wordArray);
                                    break ;
                                }
                                chances -- ;
                            }
                            else {
                                System.out.println("Good guess: " +  String.join("", willGuessWord));
                                System.out.println("-------------");
                            }
                        }
                       guesedCharas[i] = guessChar ;
               }
                if(Arrays.toString(wordArray).equals(Arrays.toString(willGuessWord))){
                                    System.out.println("Good guess: " +  String.join("", willGuessWord));
                                    winGame();
                                    // game win
                                }
        }

    
    public static void hangman(String word) {
        System.out.println("Welcome to Hangman Ultimate Edition");
        System.out.println("I am thinking of a word that is " + word.length() + " letters long");
        System.out.println("-------------");
		
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		getGuessedWord(word, alpha.split(""));

    }

    public static void winGame(){
        System.out.print("Congratulations, you win!!!");
    }
    
     public static void loseGame(String [] x){
        System.out.print("Sorry, you ran out of guesses. The word was: " + String.join("", x));
    }
     
    public static void main(String[] args) {
		System.out.println("Loading word list from file...");
		loadWords();
    }
}
