import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.stage.Stage;
//import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;
import java.lang.Math;
//import javafx.geometry.Pos;

public class GameScreen extends Application {
	public static Group MainGrid;
	private Object [][] Screen= new Object[5][9];
	private static Image magnetImage= new Image("/magnetism.png");
	private static Random randomiser= new Random();
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
		
//		Coin C= new Coin(1,1, Screen); GameScreen.DrawCoin(C);
//		Magnet M= new Magnet(2,3, Screen); GameScreen.DrawMagnet(M);
//		Shield S= new Shield(5,9, Screen); GameScreen.DrawShield(S);
		int freqMagnet=randomiser.nextInt(2);
		int freqShield=randomiser.nextInt(2);
		int freqCoin=randomiser.nextInt(2);
		int blockRow=randomiser.nextInt(6)+1;
		int freqBall=2+Math.max(freqMagnet, freqShield);
		
		for (int i=0;i<5;i++) {
			Block B= new Block(i+1,blockRow, Screen); 
			GameScreen.DrawBlock(B);
			Screen[i][blockRow-1]=B;
		}
		
		for (int i=0;i<freqMagnet;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(9)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Magnet M= new Magnet(row,column, Screen); 
			GameScreen.DrawMagnet(M);
			Screen[row-1][column-1]=M;
		}
		
		for (int i=0;i<freqShield;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(9)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Shield S= new Shield(row,column, Screen); 
			GameScreen.DrawShield(S);
			Screen[row-1][column-1]=S;
		}
		
		for (int i=0;i<freqCoin;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(9)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Coin C= new Coin(row,column, Screen); 
			GameScreen.DrawCoin(C);
			Screen[row-1][column-1]=C;
		}
		
		Scene EntryScene= new Scene(MainGrid, 350, 630);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(EntryScene);
		primaryStage.show();
			
	}
	
	public static void DrawCoin(Coin C) {
		
		int r=C.GetPosition().Getx()*70-35;
		int c=C.GetPosition().Gety()*70-35;
		Circle CoinCircle= new Circle(r,c,5,Color.GOLD);
		MainGrid.getChildren().add(CoinCircle);

	}
	
	public static void DrawMagnet(Magnet M) {
		
		int r=M.GetPosition().Getx()*70-35;
		int c=M.GetPosition().Gety()*70-35;
		Circle MagCircle= new Circle(r,c,15);
		MagCircle.setFill(new ImagePattern(magnetImage));
		MainGrid.getChildren().add(MagCircle);

	}
	
	public static void DrawShield (Shield S) {
		
		int r=S.GetPosition().Getx()*70-35;
		int c=S.GetPosition().Gety()*70-35;
		Circle ShieldCircle= new Circle(r,c,14);
		ShieldCircle.setFill(new ImagePattern(shieldImage));
		MainGrid.getChildren().add(ShieldCircle);
		
	}
	
	public static void DrawBlock(Block B) {
		
		int upperleftx=(B.GetPosition().Getx()-1)*70+2;
		int upperlefty=(B.GetPosition().Gety()-1)*70;
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,69,69);
		BlockSquare.setFill(Color.BLUE);
		BlockSquare.setArcHeight(15);
		BlockSquare.setArcWidth(15);
		MainGrid.getChildren().add(BlockSquare);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
