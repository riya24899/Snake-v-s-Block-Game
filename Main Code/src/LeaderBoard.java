import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.TilePane;
//import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaderBoard extends Application {
	
	public void start(Stage primaryStage) {
		
		Label Leader= new Label("LEADERBOARD");	
		Leader.setStyle("-fx-background-color: #CD5C7C; -fx-font-size: 1em; ");

		Label Space1= new Label("--------");
		Space1.setStyle("-fx-background-color: #CD5C7C; -fx-font-size: 1em; ");

		Label Space2= new Label("--------");
		Space2.setStyle("-fx-background-color: #CD5C7C; -fx-font-size: 1em; ");

		
		Label Rank= new Label("Rank");
		Label DateLabel= new Label("Date");
		Label Score= new Label("Score");

		Label S1= new Label("#1"); 
		Label S2= new Label("#2"); 
		Label S3= new Label("#3"); 
		Label S4= new Label("#4"); 
		Label S5= new Label("#5"); 
		Label S6= new Label("#6"); 
		Label S7= new Label("#7"); 
		Label S8= new Label("#8"); 
		Label S9= new Label("#9"); 
		Label S10= new Label("#10");
		
		
		
		Button Menu= new Button("Main Menu");
		Button Exit= new Button("Exit");
		
		Date TempDate= new Date();
		DateFormat DF= new SimpleDateFormat("MM/dd/yyyy");

		Snake TempSnake=new Snake();
		Player Players[]= new Player[10];
		
		Players[0]=new Player(1200, TempDate, TempSnake);
		Players[1]=new Player(100, TempDate, TempSnake);
		Players[2]=new Player(1200, TempDate, TempSnake);
		Players[3]=new Player(100, TempDate, TempSnake);
		Players[4]=new Player(1200, TempDate, TempSnake);
		Players[5]=new Player(100, TempDate, TempSnake);
		Players[6]=new Player(1200, TempDate, TempSnake);
		Players[7]=new Player(100, TempDate, TempSnake);
		Players[8]=new Player(1200, TempDate, TempSnake);
		Players[9]=new Player(100, TempDate, TempSnake);
		
		TilePane LeaderPane= new TilePane();	
		LeaderPane.setAlignment(Pos.CENTER);
		LeaderPane.setPrefColumns(3);
		LeaderPane.setPrefRows(12);
		//LeaderPane.setPadding(new Insets(50,10,50,10));

		
		LeaderPane.getChildren().addAll(Space1, Leader, Space2);
		LeaderPane.getChildren().addAll(Rank, DateLabel, Score);


		LeaderPane.getChildren().addAll(S1, new Label(DF.format(Players[0].GetDate())), new Label(String.valueOf(Players[0].GetScore())));
		LeaderPane.getChildren().addAll(S2, new Label(DF.format(Players[1].GetDate())), new Label(String.valueOf(Players[1].GetScore())));
		LeaderPane.getChildren().addAll(S3, new Label(DF.format(Players[2].GetDate())), new Label(String.valueOf(Players[2].GetScore())));
		LeaderPane.getChildren().addAll(S4, new Label(DF.format(Players[3].GetDate())), new Label(String.valueOf(Players[3].GetScore())));
		LeaderPane.getChildren().addAll(S5, new Label(DF.format(Players[4].GetDate())), new Label(String.valueOf(Players[4].GetScore())));
		LeaderPane.getChildren().addAll(S6, new Label(DF.format(Players[5].GetDate())), new Label(String.valueOf(Players[5].GetScore())));
		LeaderPane.getChildren().addAll(S7, new Label(DF.format(Players[6].GetDate())), new Label(String.valueOf(Players[6].GetScore())));
		LeaderPane.getChildren().addAll(S8, new Label(DF.format(Players[7].GetDate())), new Label(String.valueOf(Players[7].GetScore())));
		LeaderPane.getChildren().addAll(S9, new Label(DF.format(Players[8].GetDate())), new Label(String.valueOf(Players[8].GetScore())));
		LeaderPane.getChildren().addAll(S10, new Label(DF.format(Players[9].GetDate())), new Label(String.valueOf(Players[9].GetScore())));
		
		LeaderPane.getChildren().addAll(Menu, Exit);

		
		Scene LeaderScene= new Scene(LeaderPane, 350, 600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(LeaderScene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
