import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.stage.Stage;
//import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
//import javafx.geometry.Pos;

public class GameScreen extends Application {
	public static Group MainGrid;
	private static Image magnetImage= new Image("/magnetism.png");
	private static Image shieldImage= new Image("/shield.png");
	public void start(Stage primaryStage) {
		
//		Button StartNew= new Button("New Game");
//		Button Resume= new Button("Resume");
//		Button Leader= new Button("Leaderboard");
		
		MainGrid= new Group();
		//VerticalPane.setPadding(new Insets(100, 50, 50, 50));
//		MainGrid.setAlignment(Pos.CENTER);
//		MainGrid.setPrefColumns(7);
//		MainGrid.setPrefRows(12);
//		MainGrid.setPrefTileHeight(50);
//		MainGrid.setPrefTileWidth(50);
		//MainGrid.getChildren().addAll(StartNew, Resume, Leader);
		
		ScreenElements Screen= new ScreenElements();
		Coin C= new Coin(1,1, Screen); GameScreen.DrawCoin(C);
		Magnet M= new Magnet(2,3, Screen); GameScreen.DrawMagnet(M);
		Shield S= new Shield(5,9, Screen); GameScreen.DrawShield(S);
		int freqBall=5;
		int freqcoin=1;
		
		//for (int i=0;i<;i)
		
		Scene EntryScene= new Scene(MainGrid, 350, 630);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(EntryScene);
		primaryStage.show();
			
	}
	
	public static void DrawCoin(Coin C) {
		
		Circle CoinCircle= new Circle(C.GetPosition().Getx(),C.GetPosition().Gety(),5,Color.GOLD);
		MainGrid.getChildren().add(CoinCircle);

	}
	
	public static void DrawMagnet(Magnet M) {
		
		Circle MagCircle= new Circle(M.GetPosition().Getx(),M.GetPosition().Gety(),15);
		MagCircle.setFill(new ImagePattern(magnetImage));
		MainGrid.getChildren().add(MagCircle);

	}
	
	public static void DrawShield (Shield S) {
		
		Circle ShieldCircle= new Circle(S.GetPosition().Getx(),S.GetPosition().Gety(),14);
		ShieldCircle.setFill(new ImagePattern(shieldImage));
		MainGrid.getChildren().add(ShieldCircle);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
