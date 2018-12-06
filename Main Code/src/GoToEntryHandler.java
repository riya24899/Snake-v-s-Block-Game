import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

class GoToEntryHandler implements EventHandler<ActionEvent> {
	/** 
	 * This class handles the event for buttons which lead to the Entry screen when pressed
	 */
	public void handle(ActionEvent e) {
		Stage primaryStage=new Stage();
		EntryScreen E= new EntryScreen();
		E.start(primaryStage);
		
	}

}
