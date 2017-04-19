import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hangman {

    public static void main(String[] args) {

        /* if (args.length == 0) {
            System.out.println("Usage: java Hangman <answer>");
            System.out.println("Answer is required");
            System.exit(1);
        }
        */
    	
    	String hmWords = "";
     // TODO Auto-generated method stub
     		try {
     			// 1. Get connection to DB
     			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?verifyServerCertificate=false&useSSL=true", "root", "mykorn666korn");
     			
     			
     			// 2. Create a statement
     			Statement myStmt = myConn.createStatement();
     			
     			// 3. Execute SQL query
     			ResultSet myRs = myStmt.executeQuery("SELECT hm_words FROM hangman ORDER BY RAND() LIMIT 1");
     			
     			// 4. Process the result set
     			while(myRs.next()){
     	            hmWords = myRs.getString("hm_words");
     			}
     			myRs.close();
     		}
     		catch (Exception exc) {
     			exc.printStackTrace();
     		}
        
        
        
        Game game = new Game(hmWords);
        Prompter prompter = new Prompter(game);
        while (game.getRemaningTries() > 0 && !game.isWon()) {
            prompter.displayProgress();
            prompter.promptForGuess();
        }
        prompter.displayOutcome();
    }
}
