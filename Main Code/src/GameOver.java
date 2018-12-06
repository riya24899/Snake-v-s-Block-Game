import java.io.IOException;
import java.util.ArrayList;

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
	
	/**
	   *
	   The class is used to create the GUI of the screen that is opened when a game ends.
	   The home screen has buttons to try a new game or go to the leaderboard/main menu.
	   *
	*/
	
	private Player player;
	private int coin;
	
	public GameOver(Player p, int c) {
		player=p;
		coin =c;
		Board B= new Board();
		try {
			B.SerializePlayers(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public void start(Stage primaryStage) {
		
		/**
		   *
		 This method creates the static frame of the game over screen and adds the functionality to the respective buttons.
		   *
		*/
		
		Label Over= new Label("GAME OVER!");
		Over.setStyle("-fx-font-size: 4em;");
		Over.setTextFill(Color.WHITE);
		
		Label Score= new Label("SCORE: "+ this.player.GetScore());
		Score.setStyle("-fx-font-size: 2em;");
		Score.setTextFill(Color.WHITE);


		Button Main= new Button("MAIN MENU");
		Main.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Main.setTextFill(Color.WHITE);
		Main.setPrefWidth(330);
		Main.setOnAction( e -> {
			EntryScreen E= new EntryScreen();
			E.start(primaryStage);
	    });

		Button Try= new Button("TRY AGAIN");
		Try.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Try.setTextFill(Color.WHITE);
		Try.setPrefWidth(330);
		Try.setOnAction( e -> {
			Board B= new Board();
			
			B.start(primaryStage);
	    });
		
		Button Leader= new Button("LEADERBOARD");
		Leader.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Leader.setTextFill(Color.WHITE);
		Leader.setPrefWidth(330);
		Leader.setOnAction( e -> {
			LeaderBoard L= new LeaderBoard();
			try {
			L.start(primaryStage);
			}
			catch (Exception E){
				
			}
	    });
		
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
	
	public void setLeaderBoard() throws ClassNotFoundException, IOException {
		
		/**
		   *
		 This method updates the value of the leaderboard according to the score of the player.
		   *
		*/
		
		//take care of size 10 here
		LeaderBoard L= new LeaderBoard();
		ArrayList<Player> P=L.DeserializePlayers();
		if(P==null) {
			P=new ArrayList<Player>();
			P.add(player);
		}	
		else if (P.size()<10) {
			Player temp=null;
			boolean flag=false;
			for (int i=0;i<P.size();i++) {
				temp = P.get(i);
				if(temp.GetScore()<player.GetScore()) {
					flag=true;
					P.add(i,player);
					break;
				}
			}
			
			if(!flag) {
				P.add(player);
			}
		}
		
		else {
			Player temp=null;
			boolean flag=false;
			for (int i=0;i<P.size();i++) {
				temp = P.get(i);
				if(temp.GetScore()<player.GetScore()) {
					flag=true;
					P.add(i,player);
					break;
				}
			}

			if(flag) {
				P.remove(10);
			}
		}
		
		L.SerializePlayers(P);
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}