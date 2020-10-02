/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */
  static int score = 0;
  public static void main(String[] args){


    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    

    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u",
     "d", "jumpright", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown",
     "jd", "pickup", "p", "quit", "q", "replay", "help", "?"};

  
    String playerInput;

    // set up game
    boolean play = true;
    while (play){
      playerInput = UserInput.getValidInput(validCommands);
        if (playerInput.equals("help") || playerInput.equals("?")){
          System.out.println("The commands are:");
          for (String i : validCommands){
            System.out.println(i);
          }
        }
        else if (playerInput.equals("right") || playerInput.equals("r")){
          score += game.movePlayer(m , 0);
          if (game.isTrap(m, 0)){
            game.springTrap(m, 0);
          }
        }
        else if (playerInput.equals("left") || playerInput.equals("l")){
          score += game.movePlayer(-m , 0);
          if (game.isTrap(-m, 0)){
            game.springTrap(-m, 0);
          }
        }
        else if (playerInput.equals("up") || playerInput.equals("u")){
          score += game.movePlayer(0 , -m);
          if (game.isTrap(0, -m)){
            game.springTrap(0, -m);
          }
        }
        else if (playerInput.equals("down") || playerInput.equals("d")){
          score += game.movePlayer(0 , m);
          if (game.isTrap(m, 0)){
            game.springTrap(0 , m);
          }
        }
        //jump commands
        else if (playerInput.equals("jumpright") || playerInput.equals("jr")){
          score += game.movePlayer(2*m , 0);
          if (game.isTrap(m, 0)){
            game.springTrap(m, 0);
          }
        }
        else if (playerInput.equals("jumpleft") || playerInput.equals("jl")){
          score += game.movePlayer(-2*m , 0);
          if (game.isTrap(-m, 0)){
            game.springTrap(-m, 0);
          }
        }
        else if (playerInput.equals("jumpup") || playerInput.equals("ju")){
          score += game.movePlayer(0 , -2*m);
          if (game.isTrap(0 , -m)){
            game.springTrap(0 , -m);
          }
        }
        else if (playerInput.equals("jumpdown") || playerInput.equals("jd")){
          score += game.movePlayer(0, 2*m);
          if (game.isTrap(0 , m)){
            game.springTrap(0 , m);
          }
        }
        else if (playerInput.equals("pickup") || playerInput.equals("p")){
          score += game.pickupPrize();
        }
        else if (playerInput.equals("quit") || playerInput.equals("q")){
          break;
        }
        else if (playerInput.equals("replay")){
          System.out.println("Your score was:  " + game.replay());
        }
        else{
          System.out.println("Now, how did you get to see this, this should not be possible without looking at the code or changing it.");
        }
      /* TODO: get all the commands working */
	    /* Your code here */   
    }
    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        