import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class EntryScreen extends Application {
	
	public void start(Stage primaryStage) {
		
		Label Heading1= new Label("SNAKE");
		Heading1.setStyle("-fx-font-size: 5em; ");
		Heading1.setTextFill(Color.WHITE);
		
		Label Heading2= new Label("vs");
		Heading2.setStyle("-fx-font-size: 3em; ");
		Heading2.setTextFill(Color.WHITE);
		
		Label Heading3= new Label("BLOCK");
		Heading3.setStyle("-fx-font-size: 5em; ");
		Heading3.setTextFill(Color.WHITE);
		
		Button StartNew= new Button("NEW GAME");
		StartNew.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		StartNew.setTextFill(Color.WHITE);
		StartNew.setPrefWidth(330);
		StartNew.setOnAction( e -> {
			Board B= new Board();
			B.start(primaryStage);
	    });

		Button Resume= new Button("RESUME");
		Resume.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Resume.setTextFill(Color.WHITE);
		Resume.setPrefWidth(330);

		Button Leader= new Button("LEADERBOARD");
		Leader.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Leader.setTextFill(Color.WHITE);
		Leader.setPrefWidth(330);
		Leader.setOnAction( e -> {
			LeaderBoard L= new LeaderBoard();
			try {
				L.start(primaryStage);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });

		
		VBox VerticalPane= new VBox();
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
	    Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	   
		VerticalPane.setSpacing(20);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.setBackground(Purple);
		VerticalPane.getChildren().addAll(Heading1, Heading2, Heading3, StartNew, Resume, Leader);

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
