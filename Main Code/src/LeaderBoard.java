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
import java.util.Date;
import java.io.*;

public class LeaderBoard extends Application implements Serializable {

	public static Group LeaderLayout;

	public void start(Stage primaryStage) throws FileNotFoundException, IOException, ClassNotFoundException {

		LeaderLayout= new Group();
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
		Headings.setLayoutY(210);

		Label S1= new Label("#1"); S1.setTextFill(Color.WHITE);
		Label S2= new Label("#2"); S2.setTextFill(Color.WHITE);
		Label S3= new Label("#3"); S3.setTextFill(Color.WHITE);
		Label S4= new Label("#4"); S4.setTextFill(Color.WHITE);
		Label S5= new Label("#5"); S5.setTextFill(Color.WHITE);
		Label S6= new Label("#6"); S6.setTextFill(Color.WHITE);
		Label S7= new Label("#7"); S7.setTextFill(Color.WHITE);
		Label S8= new Label("#8"); S8.setTextFill(Color.WHITE);
		Label S9= new Label("#9"); S9.setTextFill(Color.WHITE);
		Label S10= new Label("#10"); S10.setTextFill(Color.WHITE);

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

		Button Exit= new Button("Exit");
		Exit.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Exit.setTextFill(Color.WHITE);
		Exit.setLayoutX(50);
		Exit.setLayoutY(500);
		Exit.setPrefWidth(250);
		Exit.setOnAction( e -> {
			EntryScreen E= new EntryScreen();
			E.start(primaryStage);
	    });

		Date TempDate= new Date();
		DateFormat DF= new SimpleDateFormat("MM/dd/yyyy");

		Snake TempSnake=new Snake();
		Player Players[]= new Player[10];

		Players[0]=new Player(1000, TempDate, TempSnake);
		Players[1]=new Player(900, TempDate, TempSnake);
		Players[2]=new Player(800, TempDate, TempSnake);
		Players[3]=new Player(700, TempDate, TempSnake);
		Players[4]=new Player(600, TempDate, TempSnake);
		Players[5]=new Player(500, TempDate, TempSnake);
		Players[6]=new Player(400, TempDate, TempSnake);
		Players[7]=new Player(300, TempDate, TempSnake);
		Players[8]=new Player(200, TempDate, TempSnake);
		Players[9]=new Player(100, TempDate, TempSnake);

		SerializePlayers(Players);
		DeserializePlayers(Players);

		Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));

		TilePane LeaderPane= new TilePane();	
		LeaderPane.setBackground(Purple);
		LeaderPane.setAlignment(Pos.CENTER);
		LeaderPane.setPrefColumns(3);
		LeaderPane.setPrefRows(12);
		LeaderPane.setPrefWidth(250);
		LeaderPane.setLayoutX(50);
		LeaderPane.setLayoutY(200);


		Label Date1=new Label(DF.format(Players[0].GetDate()));  Date1.setTextFill(Color.WHITE);
		Label Date2=new Label(DF.format(Players[1].GetDate()));  Date2.setTextFill(Color.WHITE);
		Label Date3=new Label(DF.format(Players[2].GetDate()));  Date3.setTextFill(Color.WHITE);
		Label Date4=new Label(DF.format(Players[3].GetDate()));  Date4.setTextFill(Color.WHITE);
		Label Date5=new Label(DF.format(Players[4].GetDate()));  Date5.setTextFill(Color.WHITE);
		Label Date6=new Label(DF.format(Players[5].GetDate()));  Date6.setTextFill(Color.WHITE);
		Label Date7=new Label(DF.format(Players[6].GetDate()));  Date7.setTextFill(Color.WHITE);
		Label Date8=new Label(DF.format(Players[7].GetDate()));  Date8.setTextFill(Color.WHITE);
		Label Date9=new Label(DF.format(Players[8].GetDate()));  Date9.setTextFill(Color.WHITE);
		Label Date10=new Label(DF.format(Players[9].GetDate()));  Date10.setTextFill(Color.WHITE);
		

		Label Score1=new Label(String.valueOf(Players[0].GetScore())); Score1.setTextFill(Color.WHITE);
		Label Score2=new Label(String.valueOf(Players[1].GetScore())); Score2.setTextFill(Color.WHITE);
		Label Score3=new Label(String.valueOf(Players[2].GetScore())); Score3.setTextFill(Color.WHITE);
		Label Score4=new Label(String.valueOf(Players[3].GetScore())); Score4.setTextFill(Color.WHITE);
		Label Score5=new Label(String.valueOf(Players[4].GetScore())); Score5.setTextFill(Color.WHITE);
		Label Score6=new Label(String.valueOf(Players[5].GetScore())); Score6.setTextFill(Color.WHITE);
		Label Score7=new Label(String.valueOf(Players[6].GetScore())); Score7.setTextFill(Color.WHITE);
		Label Score8=new Label(String.valueOf(Players[7].GetScore())); Score8.setTextFill(Color.WHITE);
		Label Score9=new Label(String.valueOf(Players[8].GetScore())); Score9.setTextFill(Color.WHITE);
		Label Score10=new Label(String.valueOf(Players[9].GetScore())); Score10.setTextFill(Color.WHITE);

		LeaderPane.getChildren().addAll(S1, Date1, Score1);
		LeaderPane.getChildren().addAll(S2, Date2, Score2);
		LeaderPane.getChildren().addAll(S3, Date3, Score3);
		LeaderPane.getChildren().addAll(S4, Date4, Score4);
		LeaderPane.getChildren().addAll(S5, Date5, Score5);
		LeaderPane.getChildren().addAll(S6, Date6, Score6);
		LeaderPane.getChildren().addAll(S7, Date7, Score7);
		LeaderPane.getChildren().addAll(S8, Date8, Score8);
		LeaderPane.getChildren().addAll(S9, Date9, Score9);
		LeaderPane.getChildren().addAll(S10, Date10, Score10);

		
		LeaderLayout.getChildren().addAll(Leader, Headings, LeaderPane, Menu, Exit);

		Scene LeaderScene= new Scene(LeaderLayout, 350, 600, Color.DARKSLATEBLUE);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(LeaderScene);
		primaryStage.show();

	}

	public void SerializePlayers(Player[] P) throws IOException {

		ObjectOutputStream PlayersOutFile = new ObjectOutputStream(new FileOutputStream("Players.dat"));
		for (int i=0; i<10; i++) {
			try {
				PlayersOutFile.writeObject(P[i]);
			}
			catch (IOException e) {

			}

		}
		PlayersOutFile.close();
	}


	public void DeserializePlayers(Player P[]) throws IOException, ClassNotFoundException {
		ObjectInputStream PlayersInFile = new ObjectInputStream(new FileInputStream("Players.dat"));
		for (int i=0; i<10; i++) {
			try {
				P[i]= (Player) PlayersInFile.readObject(); 
			}
			catch (IOException e) {	

			}

		}
		PlayersInFile.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}