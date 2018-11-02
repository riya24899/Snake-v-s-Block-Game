import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;
import java.lang.Math;
import javafx.scene.control.ComboBox;
import javafx.scene.text.*;

public class Board extends Application {
	public static Group MainGrid;
	private static Object [][] Screen= new Object[5][9];
	private static Shape [][] Shapes= new Shape[5][9];
	private static Image magnetImage= new Image("/magnetism.png");
	private static Image coinImage= new Image("/coin.png");
	private static Random randomiser= new Random();
	private static Image shieldImage= new Image("/shield.png");
	private static ComboBox PauseOptions= new ComboBox();
	private static Player player;
	
	
	public void start(Stage primaryStage) {
		
		MainGrid= new Group();
		Snake Snake1= new Snake();
		Snake1.SetLength(4);
		Board.DrawSnake(Snake1);
		player=new Player(0,new java.util.Date(),Snake1);
		setBoard(MainGrid);
		Scene EntryScene= new Scene(MainGrid, 353, 660, Color.DARKSLATEBLUE);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(EntryScene);
		primaryStage.show();
		
	
	
		EntryScene.setOnKeyPressed(ke -> {
	        KeyCode keyCode = ke.getCode();
	        if (keyCode.equals(KeyCode.LEFT)) {
	            player.GetSnake().moveSnake(1);
	            return;
	        }
	        if (keyCode.equals(KeyCode.RIGHT)) {
	        	 player.GetSnake().moveSnake(2);
		            return;
	        }
	    });
		
//		moveBoard();
		
			
	}
	
	
//	public static void moveBoard() {
//		
//		for (int i=0;i<Screen.length;i++) {
//			
//			for (int j=0;j<Screen[i].length;j++) {
//				 TranslateTransition tt= new TranslateTransition(Duration.millis(2000),Shapes[i][j]);
////			        double x=c.ir.getCenterX();
////			        System.out.print(x);
//			       if (Shapes[i][j] instanceof Circle) {
//			    	   
//			        Circle temp= (Circle)Shapes[i][j];
//					tt.setFromY(temp.getCenterY());
//					tt.setToY(temp.getCenterY()+20);
//					tt.setCycleCount(1);
//					tt.setAutoReverse(false);
//					tt.play();
////					this.SnakeBody.get(i).setCenterX(this.SnakeBody.get(i).getCenterX()+35);
//			       }
//				}
//			}
//		}
	
	public static void setBoard(Group MainGrid) {
		
		PauseOptions.getItems().addAll("PAUSE", "LEADERBOARD", "EXIT");
		PauseOptions.setValue("PAUSE");
		PauseOptions.setPrefWidth(353);
		PauseOptions.setPrefHeight(30);
		PauseOptions.setStyle("-fx-background-color: #aba6ca; -fx-font-size: 1em; -fx-text-fill: #2e2759;");
		//PauseOptions.getOnMouseClicked()
		
		MainGrid.getChildren().add(PauseOptions);
		
		int freqMagnet=randomiser.nextInt(2);
		int freqShield=randomiser.nextInt(2);
		int freqCoin=randomiser.nextInt(2);
		int blockRow=randomiser.nextInt(5)+1;
		int freqBall=2+Math.max(freqMagnet, freqShield);
		
		for (int i=0;i<5;i++) {
			int value=randomiser.nextInt(3)+1;
			Block B= new Block(i+1,blockRow, Screen, value); 
			Board.DrawBlock(B,i,blockRow-1);
			Screen[i][blockRow-1]=B;
		}
		
		for (int i=0;i<2;i++) {
			int value=randomiser.nextInt(3)+1;
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(5)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			if(column!=1 && Screen[row-1][column-2]!=null) {
				i--;
				continue;
			}
			if (Screen[row-1][column]!=null) {
				i--;
				continue;
			}
			Block B= new Block(row,column, Screen, value); 
			Board.DrawBlock(B,i,blockRow-1);
			Screen[row-1][column-1]=B;
		}
		
		for (int i=0;i<4;i++) {

			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(5)+1;
			int length=randomiser.nextInt(2)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			if (length==2 && Screen[row-1][column]!=null) {
				i--;
				continue;
			}
			Wall W= new Wall(row,column,Screen,length);
			Board.DrawWall(W,row-1,column-1);
			Screen[row-1][column-1]=W;
			Screen[row-1][column]=W;
		}
		
		for (int i=0;i<freqBall;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(6)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Ball B= new Ball(row,column, Screen);
			int V= randomiser.nextInt(5)+1;
			B.SetValue(V);
			Board.DrawBall(B,row-1,column-1);
			Screen[row-1][column-1]=B;
		}
		
		for (int i=0;i<freqMagnet;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(6)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Magnet M= new Magnet(row,column, Screen); 
			Board.DrawMagnet(M,row-1,column-1);
			Screen[row-1][column-1]=M;
		}
		
		for (int i=0;i<freqShield;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(6)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Shield S= new Shield(row,column, Screen); 
			Board.DrawShield(S,row-1,column-1);
			Screen[row-1][column-1]=S;
		}
		
		for (int i=0;i<freqCoin;i++) {
			int row=randomiser.nextInt(5)+1;
			int column=randomiser.nextInt(6)+1;
			if (Screen[row-1][column-1]!=null) {
				i--;
				continue;
			}
			Coin C= new Coin(row,column, Screen); 
			Board.DrawCoin(C,row-1,column-1);
			Screen[row-1][column-1]=C;
		}
		
	}
	
	public static void DrawCoin(Coin C, int i, int j) {
		
		int r=C.GetPosition().Getx()*70-35;
		int c=C.GetPosition().Gety()*70-5;
		Circle CoinCircle= new Circle(r,c,15);
		CoinCircle.setFill(new ImagePattern(coinImage));
		MainGrid.getChildren().add(CoinCircle);
		Shapes[i][j]=CoinCircle;

	}
	
	public static void DrawMagnet(Magnet M, int i, int j) {
		
		int r=M.GetPosition().Getx()*70-35;
		int c=M.GetPosition().Gety()*70-5;
		Circle MagCircle= new Circle(r,c,15);
		MagCircle.setFill(new ImagePattern(magnetImage));
		MainGrid.getChildren().add(MagCircle);
		Shapes[i][j]=MagCircle;

	}
	
	public static void DrawShield (Shield S, int i, int j) {
		
		int r=S.GetPosition().Getx()*70-35;
		int c=S.GetPosition().Gety()*70-5;
		Circle ShieldCircle= new Circle(r,c,14);
		ShieldCircle.setFill(new ImagePattern(shieldImage));
		MainGrid.getChildren().add(ShieldCircle);
		Shapes[i][j]=ShieldCircle;
		
	}
	
	public static void DrawBall(Ball B, int i, int j) {
		
		int r=B.GetPosition().Getx()*70-35;
		int c=B.GetPosition().Gety()*70-5;
		Circle BallCircle= new Circle(r,c,5, Color.GOLD);
		
		Text BallLabel= new Text(Integer.toString(B.GetValue()));
		BallLabel.setTextAlignment(TextAlignment.CENTER);
		VBox BallStack= new VBox();
		BallStack.getChildren().addAll(BallLabel, BallCircle);
		BallStack.setLayoutX(r);
		BallStack.setLayoutY(c);
		
		
		MainGrid.getChildren().add(BallStack);
		Shapes[i][j]=BallCircle;
		
	}
	
	public static void DrawBlock(Block B, int i, int j) {
			
		Color ColourList[]= new Color[5];
		ColourList[0]= Color.PALEGOLDENROD;
		ColourList[1]= Color.PALEGREEN;
		ColourList[2]= Color.PALEVIOLETRED;
		ColourList[3]= Color.PALETURQUOISE;
		ColourList[4]= Color.BURLYWOOD;

		int ColourOption=randomiser.nextInt(5);
		
		int upperleftx=(B.GetPosition().Getx()-1)*70+2;
		int upperlefty=(B.GetPosition().Gety()-1)*70+30;
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,69,70);

		Text BlockLabel = new Text(Integer.toString(B.getValue()));
      	StackPane BlockStack = new StackPane();
		BlockStack.getChildren().addAll(BlockSquare, BlockLabel);
		BlockStack.setLayoutX(upperleftx);
		BlockStack.setLayoutY(upperlefty);
		
		BlockSquare.setFill(ColourList[ColourOption]);
		BlockSquare.setArcHeight(15);
		BlockSquare.setArcWidth(15);
		MainGrid.getChildren().add(BlockStack);
		Shapes[i][j]=BlockSquare;
		
	}
	
	public static void DrawWall(Wall W, int i, int j) {
			
			int upperleftx=(W.GetPosition().Getx()-1)*70+33;
			int upperlefty=(W.GetPosition().Gety()-1)*70+32;
			Rectangle Wall= new Rectangle(upperleftx,upperlefty,4,W.getLength()*70-2);
			Wall.setFill(Color.LIGHTSLATEGRAY);
			Wall.setArcHeight(15);
			Wall.setArcWidth(15);
			MainGrid.getChildren().add(Wall);
			Shapes[i][j]=Wall;
			
		}
	
	
    public static void DrawSnake(Snake S) {
    	
		int Cx=175;
		int Cy=490;
		int R=10;
		
		//add circle head
		Circle SnakeHead= new Circle(Cx, Cy, R, Color.RED);
		MainGrid.getChildren().add(SnakeHead);
		S.GetBody().add(SnakeHead);
		
		for (int x=1; x<=S.GetLength(); x++) {
		Circle  C=new Circle(Cx, Cy +(x*R*2), R, Color.ORANGE);
		MainGrid.getChildren().add(C);
		S.GetBody().add(C);
		
		}
	}
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}