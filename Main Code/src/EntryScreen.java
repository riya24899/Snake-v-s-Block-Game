import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class EntryScreen extends Application {
	
	public void start(Stage primaryStage) {
		
		Label Heading= new Label("SNAKE vs BLOCK");
		Heading.setStyle("-fx-background-color: #CD5C7C; -fx-font-size: 2em; ");
		
		Button StartNew= new Button("New Game");
		StartNew.setStyle("-fx-background-color: #CD5C9C; -fx-font-size: 3em; ");

		Button Resume= new Button("Resume");
		Resume.setStyle("-fx-background-color: #CD5C7C; -fx-font-size: 3em; ");

		Button Leader= new Button("Leaderboard");
		Leader.setStyle("-fx-background-color: #CD5C9C; -fx-font-size: 3em; ");

	
		
		VBox VerticalPane= new VBox();
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
		VerticalPane.setSpacing(40);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.getChildren().addAll(Heading, StartNew, Resume, Leader);
		
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
