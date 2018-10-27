import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class EntryScreen extends Application {
	
	public void start(Stage primaryStage) {
		Button StartNew= new Button("New Game");
		Button Resume= new Button("Resume");
		Button Leader= new Button("Leaderboard");
	
		
		VBox VerticalPane= new VBox();
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
		VerticalPane.setSpacing(20);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.getChildren().addAll(StartNew, Resume, Leader);
		
		Scene EntryScene= new Scene(VerticalPane, 350, 600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(EntryScene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
