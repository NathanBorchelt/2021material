import java.util.Arrays;
import java.util.Random;

/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of
 * buttons. After wathcing the memory strings appear in the buttons one at a
 * time, the player recreates the sequence from memory.
 */
public class MemoryGame
{
  static int blocks = 3;
  public static void main(String[] args) {

    String[] validLets = new String[36];
    for (int i=0;i<26;i++){
      char validLet = (char)(i+65);
      //System.out.println(validLet);
      validLets[i] = String.valueOf(validLet);
    }
    for (int i = 26;i<36;i++){
      validLets[i]=String.valueOf(i-26);
    }

    //System.out.println(Arrays.toString(validLets));
    MemoryGameGUI game = new MemoryGameGUI();

    

    game.createBoard(blocks, true);
    // Play the game until user wants to quit.
    Random rand = new Random();
    String[] randomLetters = new String[blocks];
    while(game.playAgain()){
      for(int i = 0; i < blocks;i++){
        randomLetters[i] = validLets[rand.nextInt(validLets.length)];
      }
      game.playSequence(randomLetters, .5);
    }
      // Create a new array that will contain the randomly ordered memory strings.

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
}