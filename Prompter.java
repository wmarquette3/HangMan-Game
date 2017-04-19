import java.util.Scanner;

public class Prompter {
    private Game game;

    public Prompter(Game game) {
        this.game = game;
    }

    public boolean promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;
        boolean isAcceptable = false;

        do {
            System.out.println("Enter a letter");
            String guessInput = scanner.nextLine();

            try {
                isHit = game.applyGuess(guessInput);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s. Please try again %n",
                        iae.getMessage());
            }
        } while(! isAcceptable);
        return isHit;

    }

    public void displayProgress() {
        System.out.printf("You have %d tries left to solve: %s%n",
                game.getRemaningTries(),
                game.getCurrentProgress());
    }

    public void displayOutcome() {
        if (game.isWon()) {
        	System.out.printf("The word was " + game.getAnswer());
        	System.out.println();
            System.out.printf("You won with %d tries remaning. %n",
                    game.getRemaningTries());
        } else {
            System.out.printf("Sorry, you didn't win, the word was %s%n",
                    game.getAnswer());
        }
    }

}
