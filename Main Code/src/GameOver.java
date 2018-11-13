import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class GameOver extends Application {
	
	public void start(Stage primaryStage) {
		
		Label Over= new Label("GAME OVER!");
		Over.setStyle("-fx-font-size: 4em;");
		Over.setTextFill(Color.WHITE);
		
		Label Score= new Label("SCORE: "+ "196");
		Score.setStyle("-fx-font-size: 2em;");
		Score.setTextFill(Color.WHITE);


		Button Main= new Button("MAIN MENU");
		Main.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Main.setTextFill(Color.WHITE);
		Main.setPrefWidth(330);

		Button Try= new Button("TRY AGAIN");
		Try.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Try.setTextFill(Color.WHITE);
		Try.setPrefWidth(330);
		
		Button Leader= new Button("LEADERBOARD");
		Leader.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Leader.setTextFill(Color.WHITE);
		Leader.setPrefWidth(330);

		
		VBox VerticalPane= new VBox();
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
	    Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	   
		VerticalPane.setSpacing(40);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.setBackground(Purple);
		VerticalPane.getChildren().addAll(Over, Score, Main, Try, Leader);

		Scene EntryScene= new Scene(VerticalPane, 350, 600, Color.DARKSLATEBLUE);
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
