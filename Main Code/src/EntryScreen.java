import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class EntryScreen extends Application {
	
	/**
	   *
	   The class is used to create the GUI of the home screen.
	   The home screen has buttons to access the instruction page and leaderboard.
	   It also had buttons to start a new game or resume a saved game.
	   *
	*/
	
	public void start(Stage primaryStage) {
		
		/**
		   *
		 This method creates the static frame of the home screen and adds the functionality to the respective buttons.
		   *
		*/
		
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
			try {
				B.SerializePlayers(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			B.start(primaryStage);
	    });
		
		Player checker=null;
		try {
			Board B=new Board();
			checker=B.DeserializePlayers();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		Button Resume=null;
		if(checker!=null) {
			
			Resume= new Button("RESUME");
			Resume.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
			Resume.setTextFill(Color.WHITE);
			Resume.setPrefWidth(330);
			Resume.setOnAction( e -> {
				Board B= new Board();
				
				try {
					Player temp=B.DeserializePlayers();
					temp.GetSnake().SetBody(new ArrayList<Circle>());
					B.setPlayer(temp);
					B.start(primaryStage);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    });
		}	
			
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
		
		Button Inst= new Button("INSTRUCTIONS");
		Inst.setStyle("-fx-background-color: #2e2759; -fx-font-size: 3em; ");
		Inst.setTextFill(Color.WHITE);
		Inst.setPrefWidth(330);
		Inst.setOnAction(e -> {
			Instruction I= new Instruction();
			I.start(primaryStage);
		});
		

		
		VBox VerticalPane= new VBox();
	    Background Purple = new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	   
		VerticalPane.setSpacing(20);
		VerticalPane.setAlignment(Pos.CENTER);
		VerticalPane.setBackground(Purple);
		if (checker!=null) {
		VerticalPane.getChildren().addAll(Heading1, Heading2, Heading3, StartNew, Resume, Leader, Inst);
		}
		
		if (checker==null) {
			VerticalPane.getChildren().addAll(Heading1, Heading2, Heading3, StartNew, Leader, Inst);
		}
		
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