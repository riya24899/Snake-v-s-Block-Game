import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

class GoToBoardHandler implements EventHandler<ActionEvent> {
	/** 
	 * This class handles the event for buttons which lead to the Board screen when pressed
	 */
	public void handle(ActionEvent e) {
		Stage primaryStage=new Stage();
		Board B1= new Board();
		B1.start(primaryStage);
		
	}

}
