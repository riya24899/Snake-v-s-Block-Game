import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

class GoToLeaderHandler implements EventHandler<ActionEvent> {
	/** 
	 * This class handles the event for buttons which lead to the Leaderboard screen when pressed
	 */
	public void handle(ActionEvent e) {
		Stage primaryStage=new Stage();
		LeaderBoard L1= new LeaderBoard();
		try {
			L1.start(primaryStage);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}