import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class Instruction extends Application {
	/**
	 * This class is creates the instructions page for the game, explaining the purpose of every action and element in the game.
	 */
	public void start(Stage primaryStage) {
	    Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));

	    
		Label Over= new Label("Instructions");
		Over.setStyle("-fx-font-size: 3em;");
		Over.setTextFill(Color.PALEGOLDENROD);
			
		Rectangle ShieldCircle= new Rectangle(355,450);
		ShieldCircle.setFill(new ImagePattern(new Image("/ins.jpg")));

		Button Back= new Button("BACK TO HOME");
		Back.setStyle("-fx-background-color: #2e2759; -fx-font-size: 2em; ");
		Back.setTextFill(Color.WHITE);
		Back.setPrefWidth(330);
		Back.setOnAction( e -> {
			EntryScreen E= new EntryScreen();
			E.start(primaryStage);
	    });
		
		Label CoinL=new Label("BONUS: Collect 5 coins to increase snake length by 10");
		CoinL.setStyle("-fx-font-size: 1em;");
		CoinL.setTextFill(Color.WHITE);
		VBox VerticalPane= new VBox();
		
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
	   
		VerticalPane.setSpacing(10);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.setBackground(Purple);
		VerticalPane.getChildren().addAll(Over, ShieldCircle, CoinL, Back);

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