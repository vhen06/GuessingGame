import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    private String playerName;
    private int secretNumber;
    private int attemptsUsed;
    private final int MAX_ATTEMPTS = 10;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
    // h. START THE GAME
    public void startGame() {
        System.out.println("=========================================");
        System.out.println("       WELCOME TO THE GUESSING GAME!     ");
        System.out.println("=========================================");
        System.out.print("Enter your name: ");
        this.playerName = scanner.nextLine();

        boolean playAgain = true;
        while (playAgain) {
            playGame();
            displayStats();
            playAgain = askPlayAgain();
        }

        System.out.println("\n=========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("=========================================");
    }
      // a. DISPLAY WELCOME AND RULES
    public void displayWelcome() {
        System.out.println("\n=========================================");
        System.out.println("       WELCOME TO THE GUESSING GAME!     ");
        System.out.println("=========================================");
        System.out.println("Hello, " + playerName + "!");
        System.out.println("\nI'm thinking of a number between 1 and 100.");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println("\nLet's begin!");
        System.out.println("=========================================");
    }
     // b. GENERATE NUMBER BETWEEN MIN AND MAX
    public int generateSecretNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
     // c. USER GUESS
    public int getUserGuess() {
        int guess = -1;
        while (true) {
            System.out.print("Enter your guess (1-100): ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= 1 && guess <= 100) {
                    return guess;
                }
            } else {
                scanner.next(); 
             }
            System.out.println("Invalid! Please enter a number between 1 and 100.");
        }
    }
     // d. HINTS
    public void giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }
      // e. GAME LOGIC
    public void playGame() {
        displayWelcome();
        this.secretNumber = generateSecretNumber(1, 100);
        this.attemptsUsed = 0;
        boolean won = false;
         while (attemptsUsed < MAX_ATTEMPTS) {
            attemptsUsed++;
            System.out.println("\n--- Attempt #" + attemptsUsed + " ---");
            int guess = getUserGuess();

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attemptsUsed + " attempts!");
                won = true;
                break;
            } else {
                giveHint(guess);
            }
        }

        if (!won) {
            attemptsUsed++; 
            System.out.println("\n--- Attempt #11 ---");
            System.out.println("GAME OVER!");
            System.out.println("You've used all " + MAX_ATTEMPTS + " attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
            System.out.println("Better luck next time, " + playerName + "!");
        }
    }
      // f. STATS AND RATING
    public void displayStats() {
        String rating;
        if (attemptsUsed == 1) rating = "Perfect!";
        else if (attemptsUsed <= 3) rating = "Excellent!";
        else if (attemptsUsed <= 6) rating = "Good job!";
        else if (attemptsUsed <= 10) rating = "Nice try!";
        else rating = "Better luck next time!";

        System.out.println("\n=========================================");
        System.out.println("             GAME STATISTICS             ");
        System.out.println("=========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + (attemptsUsed > MAX_ATTEMPTS ?MAX_ATTEMPTS : attemptsUsed));
        System.out.println("Rating: " + rating);
        System.out.println("=========================================");
    }
      // g. PLAY AGAIN
    public boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        String choice = scanner.next().toLowerCase();
        scanner.nextLine(); 
        return choice.startsWith("y");
    }
}