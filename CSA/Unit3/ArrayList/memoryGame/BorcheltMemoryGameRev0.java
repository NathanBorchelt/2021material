import java.util.Arrays;
import java.util.Random;

/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of
 * buttons. After wathcing the memory strings appear in the buttons one at a
 * time, the player recreates the sequence from memory.
 */
public class BorcheltMemoryGameRev0
{
  static int blocks = 3;
  public static void main(String[] args) {
    int correct = 0;
    int gamesPlayed = 0;

    char[] validLets = new char[26];
    for (int i=0;i<26;i++){
      char validLet = (char)(i+65);
      //System.out.println(validLet);
      validLets[i] = validLet;
    }
    /*
    for (int i = 26;i<36;i++){
      validLets[i]= (char)(i-26+48);
    }
    */
    //System.out.println(Arrays.toString(validLets));
    MemoryGameGUI game = new MemoryGameGUI();
    String answerOut = "";  

    game.createBoard(blocks, true);
    // Play the game until user wants to quit.
    Random rand = new Random();
    char[] randomLetters = new char[blocks];
    int i = 0;
    while(i < blocks){
      char randString = validLets[rand.nextInt(validLets.length)];
      // Create a new array that will contain the randomly ordered memory strings.
      try{
        if(randomLetters[i-1]!=randString){
          randomLetters[i] = randString;
          i++;
        }
      }
      catch(Exception e){randomLetters[i] = randString; i++;}
    }
    String key = String.valueOf(randomLetters);
    String[] randStrLetters = String.valueOf(randomLetters).split("");
    answerOut = game.playSequence(randStrLetters, .25);

    if (answerOut.equals(key)){
      game.matched();
      correct++;
    }
    else{
      game.tryAgain();
    }
    gamesPlayed++;

    boolean again = game.playAgain();

    while(again){
      i = 0;
      char[] randLets = new char[blocks];
      while(i < blocks){
        char randString = validLets[rand.nextInt(validLets.length)];
        // Create a new array that will contain the randomly ordered memory strings.
        try{
          if(randLets[i-1]!=randString){
            randLets[i] = randString;
            i++;
          }
        }
        catch(Exception e){randLets[i] = randString; i++;}
      }
      key = String.valueOf(randLets);
      randStrLetters = String.valueOf(randLets).split("");
      answerOut = game.playSequence(randStrLetters, .5);
      answerOut = cleanString(answerOut);

      if (answerOut.equals(key)){
        game.matched();
        correct++;
      }
      else{
        game.tryAgain();
      }
      gamesPlayed++;
      again = game.playAgain();
      
    }
    game.showScore(correct, gamesPlayed);
    game.quit();
      // Create a list of randomly ordered integers with no repeats, the length
      // of memory strings. Use it to create a random sequence of the memory strings.
      // - OR -
      // Overload the next method in RandomPermutation to create a random sequence 
      // of the memory strings, passed as a parameter.

      // Play one sequence, delaying half a second for the strings to show
      // in the buttons. Save the player's guess. 
      // (Later, you can speed up or slow down the game.)

      // Determine if player's guess matches all elements of the random sequence.
      
        // Cleanup the guess - remove commas and spaces. Refer to a new String method 
        // replace to make this easy.
        
        // iterate to determine if guess matches sequence

        // If match, increase score, and signal a match, otherwise, try again.

      // Ask if user wants to play another round of the game 
      // and track the number of games played.
   
    // When done playing, show score and end the game.
  }

  public static String cleanString(String stringIn){
    stringIn.replace(" ","");
    stringIn.replace(",","");
    stringIn.replace("-","");
    stringIn = stringIn.toUpperCase();
    return stringIn;
  }
}