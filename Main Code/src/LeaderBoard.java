import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.Group;
import java.util.ArrayList;
import java.io.*;

public class LeaderBoard extends Application implements Serializable { 
	
	/**
	   *
	   The class is used to create the GUI of the leaderboard screen.
	   The leaderbaord has the top ten high scores saved along with their respective dates.
	   The leaderboard updates after every game.
	   *
	*/

	private static final long serialVersionUID = 932L;

	public void start(Stage primaryStage) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		/**
		   *
		 This method creates the frame for the leaderboard and adds the functionality to its respective buttons.
		   *
		*/

		Group LeaderLayout= new Group();
		Label Leader= new Label("LEADERBOARD");	
		Leader.setStyle("-fx-background-color: #201b3e; -fx-font-size: 3.5em; ");
		Leader.setTextFill(Color.WHITE);
		Leader.setPrefWidth(336);
		Leader.setLayoutX(7);
		Leader.setLayoutY(60);
 
		Label Headings= new Label("  Rank   Date  Score "); 
		Headings.setStyle("-fx-background-color: #2e2759; -fx-font-size: 2em; ");
		Headings.setTextFill(Color.WHITE);
		Headings.setPrefWidth(250);
		Headings.setLayoutX(50);
		Headings.setLayoutY(150);

		Button Menu= new Button("Main Menu"); 
		Menu.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Menu.setTextFill(Color.WHITE);
		Menu.setLayoutX(50);
		Menu.setLayoutY(430);
		Menu.setPrefWidth(250);
		Menu.setOnAction( e -> {
			EntryScreen E= new EntryScreen();
			E.start(primaryStage);
	    });

		DateFormat DF= new SimpleDateFormat("MM/dd/yyyy");
		ArrayList<Player> Players=new ArrayList<Player>();
		Players=DeserializePlayers();

		Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));

		TilePane LeaderPane= new TilePane();	
		LeaderPane.setBackground(Purple);
		LeaderPane.setAlignment(Pos.CENTER);
		LeaderPane.setPrefColumns(3);
		LeaderPane.setPrefRows(12);
		LeaderPane.setPrefWidth(250);
		LeaderPane.setLayoutX(50);
		LeaderPane.setLayoutY(200);
		
		if (Players.size()==0) {
			Label Message=new Label("Sorry, no HighScores yet! :("); 
			Message.setLayoutX(90);
			Message.setLayoutY(170);
			Message.setTextFill(Color.WHITE);
			LeaderLayout.getChildren().addAll(Leader, Message, Menu);
		}

		else {
			
			for (int i=1;i<=Players.size();i++) {
				Label Date1=new Label(DF.format(Players.get(i-1).GetDate()));  Date1.setTextFill(Color.WHITE);
				Label Score1=new Label(String.valueOf(Players.get(i-1).GetScore())); Score1.setTextFill(Color.WHITE);
				Label S1= new Label("#"+i); S1.setTextFill(Color.WHITE);
				LeaderPane.getChildren().addAll(S1, Date1, Score1);
			}
			LeaderLayout.getChildren().addAll(Leader, Headings, LeaderPane, Menu);
		}
		
		

		Scene LeaderScene= new Scene(LeaderLayout, 350, 600, Color.DARKSLATEBLUE);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(LeaderScene);
		primaryStage.show();

	}

	public void SerializePlayers(ArrayList<Player> P) throws IOException {
		
		/**
		   *
		   The class is used to save the top ten high score values and the corresponding dates.
		   *
		*/

		ObjectOutputStream PlayersOutFile =null;
		
			try {
				PlayersOutFile = new ObjectOutputStream(new FileOutputStream("Players.dat"));
				PlayersOutFile.writeObject(P);
				PlayersOutFile.flush();
			}
			catch (Exception e) {
				System.out.println("Serialisation Exception : " + e);
				System.exit(0);
			}

			finally {
				if(PlayersOutFile!=null)
				PlayersOutFile.close();
			}
	}


	public ArrayList<Player> DeserializePlayers() throws IOException, ClassNotFoundException {
		
		/**
		   *
		   The class is used to restore the top ten high score values and the corresponding dates.
		   *
		*/
		
		ArrayList<Player> P=new ArrayList<Player>();
		ObjectInputStream PlayersInFile = null;
			try {
				PlayersInFile = new ObjectInputStream(new FileInputStream("Players.dat"));
				P=(ArrayList<Player>)PlayersInFile.readObject(); 
				return P;
			}
			catch(FileNotFoundException e) {
				return new  ArrayList<Player>();
			}
			catch(Exception e) {

				return null;
			}

			finally {
				if(PlayersInFile!=null)
				PlayersInFile.close();
			}
			
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}