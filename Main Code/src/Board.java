import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;
import java.lang.Math;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.*;

public class Board extends Application {
	public static Group MainGrid;
	private static Object [][] Screen= new Object[5][9];
	private static double Time=-1; 
	private static int count=0;
	private static Label Score;
	private static ArrayList<Object> ScreenElements=new ArrayList<Object>(); 
	private static ArrayList<Object> deletionArray=new ArrayList<Object>();
	private static Image magnetImage= new Image("/magnetism.png");
	private static Image coinImage= new Image("/coin.png");
	private static Random randomiser= new Random();
	private static Image shieldImage= new Image("/shield.png");
	private static Image DestroyBlockImage= new Image("/bomb.png");
	private static ComboBox PauseOptions= new ComboBox();
	private static double BoardSpeed;
	private static Player player=new Player(0, new Date(), new Snake());
	
	
	public void start(Stage primaryStage) {
		
		MainGrid= new Group();
		Snake Snake1= player.GetSnake();
		Snake1.SetLength(4);
		Board.DrawSnake(175, 560, Snake1);
		Score= new Label(Integer.toString(player.GetScore()));
		Score.setTextFill(Color.WHITE);
		Score.setStyle("-fx-font-size: 1.65em; ");
		Score.setLayoutX(300);
		Score.setLayoutY(3);
		player=new Player(0,new java.util.Date(),Snake1);
		PauseOptions.getItems().addAll("RESTART", "EXIT");
		PauseOptions.setValue("PAUSE");
		PauseOptions.setPrefWidth(100);
		PauseOptions.setPrefHeight(30);
		PauseOptions.setLayoutX(4);
		PauseOptions.setStyle("-fx-background-color: #aba6ca; -fx-font-size: 1em; -fx-text-fill: #2e2759;");
		//PauseOptions.getOnMouseClicked()
		
		MainGrid.getChildren().addAll(PauseOptions, Score);
		
		StackPane Test= new StackPane();
		Rectangle Square= new Rectangle(10,10,69,70);

		Text BlockLabel = new Text("Hello");
      	StackPane BlockStack = new StackPane();
		BlockStack.getChildren().addAll(Square, BlockLabel);
		
		

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
		
		
		BoardSpeed=1.75; 	
		
		AnimationTimer main= new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				boolean flag=true;
				clearBoard();
				flag=checkCollision();
				if (flag) {
					Time++;
					}
				double divisor= 70/BoardSpeed;
				if(Math.floor(Time%divisor)==0 ) {
				Time=Math.floor(Time%divisor);
				Board.DrawRow(MainGrid);
				System.out.println(ScreenElements.size());
					
				}
				if (flag) {
				moveBoard();
				}
			}
		};
		main.start();

	}
	
	
	public static void clearBoard() {
		for (int i=0; i<ScreenElements.size();i++) {
			Object o= ScreenElements.get(i);
			if(o instanceof Block) {
				
				Block B= (Block)o;
				if (B.GetRec().getLayoutY()>=660) {
					B.SetEnable(false);
					deletionArray.add(B);
						}
				}
				else if(o instanceof Wall) {
					Wall W= (Wall)o;
					if (W.GetRec().getLayoutY()>=660) {
						W.SetEnable(false);
						deletionArray.add(W);
						
						}
				}	
				else if(o instanceof Ball) {
					Ball B1= (Ball)o;
					if (B1.GetCircle().getLayoutY()>=665) {
						B1.SetEnable(false);
						deletionArray.add(B1);
				}
				else if(o instanceof Magnet) {
					Magnet M= (Magnet)o;
					if (M.GetCircle().getLayoutY()>=665) {
						M.SetEnable(false);
						deletionArray.add(M);
					}
				}
				else if(o instanceof Shield) {
						Shield S= (Shield)o;
						if (S.GetCircle().getLayoutY()>=665) {
							S.SetEnable(false);
							deletionArray.add(S);
					}				
				}	
						
				else if(o instanceof DestroyBlock) {
						DestroyBlock D= (DestroyBlock)o;
						if (D.GetCircle().getLayoutY()>=665) {
							D.SetEnable(false);
							deletionArray.add(D);
					}			
				}
					
				else if(o instanceof Coin) {
						Coin C= (Coin)o;
						if (C.GetCircle().getLayoutY()>=665) {
							C.SetEnable(false);
							deletionArray.add(C);
					}
				}	
			}
		}
		ScreenElements.removeAll(deletionArray);
		deletionArray.clear();
		
	}
	
	public static boolean checkCollision() {
		boolean flag=true;
		for (int i=0;i<ScreenElements.size();i++) {
			if(ScreenElements.get(i) instanceof Token) {
				if(ScreenElements.get(i) instanceof Ball) {
					Ball B=(Ball)ScreenElements.get(i);
					Circle C=B.GetCircle();
					if (C.intersects(C.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						int v=B.GetValue();
						MainGrid.getChildren().remove(B.GetText());
						MainGrid.getChildren().remove(B.GetCircle());
						Snake S= player.GetSnake();
						S.disableSnake(MainGrid);
						S.SetLength(S.GetLength()+v);
						double newy= S.GetBody().get(0).getCenterY()-(v*16);
						DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
						BoardSpeed*=1+0.05*v;
					}
				
				}
				
				if(ScreenElements.get(i) instanceof DestroyBlock) {
					DestroyBlock D =(DestroyBlock)ScreenElements.get(i);
					Circle C=D.GetCircle();
					if (C.intersects(C.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						MainGrid.getChildren().remove(D.GetCircle());
						for (int y=0;y<ScreenElements.size();y++) {
							if(ScreenElements.get(y) instanceof Block) {
						
							Block B =(Block)ScreenElements.get(y);
							Rectangle R=B.GetRec();
							MainGrid.getChildren().remove(B.GetRec());
							MainGrid.getChildren().remove(B.GetText());
							ScreenElements.remove(B);
							player.SetScore(player.GetScore()+B.getValue());
							Score.setText(Integer.toString(player.GetScore()));
							//Add animation
							}
						}	
						//
						//
						//Destroy all blocks on screen 
						//
						//
					}
				
				}
				
				
			}
			
			if(ScreenElements.get(i) instanceof Obstruction) {
				if(ScreenElements.get(i) instanceof Block) {
					Block B =(Block)ScreenElements.get(i);
					Rectangle R=B.GetRec();
					if (R.intersects(R.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						if (B.getValue()<=5) {
							MainGrid.getChildren().remove(B.GetRec());
							MainGrid.getChildren().remove(B.GetText());
							ScreenElements.remove(B);
							player.SetScore(player.GetScore()+B.getValue());
							Score.setText(Integer.toString(player.GetScore()));
						}
						else {
							B.GetText().setText(Integer.toString(B.getValue()-1));
							B.setValue(B.getValue()-1);
							//give pause
							PauseTransition p = new PauseTransition(Duration.seconds(2));
							p.play();
							flag=false;
							player.SetScore(player.GetScore()+1);
							Score.setText(Integer.toString(player.GetScore()));
						}
						
						
						//
						//
						//Animation 
						//
						//
					}
				
				}
			}
			
		}
		return flag;
	}
	
	
public static void moveBoard() {
		
		
			for (int j=0;j<ScreenElements.size();j++) {
				
				if (ScreenElements.get(j)!=null) {

			       if (ScreenElements.get(j) instanceof Ball) {
			    	   Ball B= (Ball) ScreenElements.get(j);
			    	   B.GetCircle().setLayoutY(B.GetCircle().getLayoutY()+BoardSpeed);
			    	   B.GetText().setLayoutY(B.GetText().getLayoutY()+BoardSpeed);
			    }
			       else if (ScreenElements.get(j) instanceof Block) {
			    	   Block B= (Block) ScreenElements.get(j);
			    	   B.GetRec().setLayoutY(B.GetRec().getLayoutY()+BoardSpeed);
			    	   B.GetText().setLayoutY(B.GetText().getLayoutY()+BoardSpeed);
			    }
			       else if (ScreenElements.get(j) instanceof Coin) {
			    	   Coin C= (Coin) ScreenElements.get(j);
			    	   C.GetCircle().setLayoutY(C.GetCircle().getLayoutY()+BoardSpeed);
 			    	
			       }
			       
			       else if (ScreenElements.get(j) instanceof Magnet) {
			    	   Magnet M= (Magnet) ScreenElements.get(j);
			    	   M.GetCircle().setLayoutY(M.GetCircle().getLayoutY()+BoardSpeed);
 			    	
			       }
			       
			       else if (ScreenElements.get(j) instanceof DestroyBlock) {
			    	   DestroyBlock D= (DestroyBlock) ScreenElements.get(j);
			    	   D.GetCircle().setLayoutY(D.GetCircle().getLayoutY()+BoardSpeed);
 			    	
			       }

			       else if (ScreenElements.get(j) instanceof Shield) {
			    	   Shield S= (Shield) ScreenElements.get(j);
			    	   S.GetCircle().setLayoutY(S.GetCircle().getLayoutY()+BoardSpeed);
 			    	
			       }
			       
			       else if (ScreenElements.get(j) instanceof Wall) {
			    	   Wall W= (Wall) ScreenElements.get(j);
			    	   W.GetRec().setLayoutY(W.GetRec().getLayoutY()+BoardSpeed);
 			    	
			       }
			       
				}   
			}
		}
	
	public static void DrawRow(Group MainGrid) {
		
			int j=1;
		
				for (int i=0; i<=4; i++) {
					
					int rand= randomiser.nextInt(40)+1;
					if (rand==1) {
						int rand2= randomiser.nextInt(4)+1;
						if (rand2==1) {
							
							if (Screen[i][j]==null) {
								
								Board.DrawDestroyBlock(i, j);
							}
							
						}
						else if (rand2==2) {
							
							if (Screen[i][j]==null) {
							Board.DrawMagnet(i, j);
							}
						}
						else if (rand2==3) {
							if (Screen[i][j]==null) {
							Board.DrawShield(i, j);
							}
						}
						else if (rand2==4) {
							if (Screen[i][j]==null) {
							Board.DrawCoin(i, j);
						}
						}
					}
					
					else if (rand==2 || rand==3) {
						if (Screen[i][j]==null) {
						Board.DrawBall(i, j);
						}
					}
					
					
					else if (rand==5) {
						if (Screen[i][j]==null) {
						Board.DrawBlock(i, j);
						}
					}
					
					else if (rand==6 || rand==7 || rand==8) {
						if (Screen[i][j]==null) {
						Board.DrawWall(i, j);
						}
					}
					
					else if (i==0 && (rand>=9 && rand<=13)){ // || rand==9)){
						for (int k=0; k<=4; k++) { 
							if (Screen[k][j]==null) {

						Board.DrawBlock(k, j); 
							}
						}
						i=5;
						}
				}

			for (int x=0;x<=4;x++) {
				Screen[x][j]=null;
			}
	}
	
	

	
	public static void DrawCoin(int i, int j) {
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle CoinCircle= new Circle(r,c,15);
		CoinCircle.setFill(new ImagePattern(coinImage));
		MainGrid.getChildren().add(CoinCircle);
		
		Coin C= new Coin(i,j, CoinCircle);
		Screen[i][j]= C;
		ScreenElements.add(C);

	}
	
	public static void DrawMagnet(int i, int j) {
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle MagCircle= new Circle(r,c,15);
		MagCircle.setFill(new ImagePattern(magnetImage));
		MainGrid.getChildren().add(MagCircle);
		
		Magnet M= new Magnet(i,j, MagCircle);
		Screen[i][j]=M;
		ScreenElements.add(M);

	}
	
	public static void DrawShield (int i, int j) {
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle ShieldCircle= new Circle(r,c,14);
		ShieldCircle.setFill(new ImagePattern(shieldImage));
		MainGrid.getChildren().add(ShieldCircle);
		
		Shield S= new Shield(i,j, ShieldCircle);
		Screen[i][j]=S;
		ScreenElements.add(S);
		
	}
	
	public static void DrawDestroyBlock (int i, int j) {
			
			int r=i*70-35; r=r+70;
			int c=j*70-5; //c+=30;
			Circle DBCircle= new Circle(r,c,14);
			DBCircle.setFill(new ImagePattern(DestroyBlockImage));
			MainGrid.getChildren().add(DBCircle);
			
			DestroyBlock D= new DestroyBlock(i,j, DBCircle);
			Screen[i][j]=D;
			ScreenElements.add(D);
			
	}
	
	public static void DrawBall(int i, int j) {

		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle BallCircle= new Circle(r,c,5, Color.GOLD);
		int value= randomiser.nextInt(5)+1;
		Text BallLabel= new Text(Integer.toString(value));
		
		BallLabel.setLayoutX(r-1);
		BallLabel.setLayoutY(c-7);
		BallLabel.setTextAlignment(TextAlignment.CENTER);
//		VBox BallStack= new VBox();
//		BallStack.getChildren().addAll(BallLabel, BallCircle);
//		BallStack.setLayoutX(r);
//		BallStack.setLayoutY(c);
//		Object BallArray[] = new Object[2];
//		BallArray[0]= BallCircle;
//		BallArray[1]= BallLabel;
		
		Ball B= new Ball(i,j,BallCircle, BallLabel, value);
		Screen[i][j]= B;
		ScreenElements.add(B);
		MainGrid.getChildren().addAll(BallCircle,BallLabel);
//		Object BallElements[]= new Object[2];
//		BallElements[0]=BallCircle;
//		BallElements[1]=BallLabel;
		
		
	}
	
	public static void DrawBlock(int i, int j) {
			
		Color ColourList[]= new Color[5];
		ColourList[0]= Color.PALEGOLDENROD;
		ColourList[1]= Color.PALEGREEN;
		ColourList[2]= Color.PALEVIOLETRED;
		ColourList[3]= Color.PALETURQUOISE;
		ColourList[4]= Color.BURLYWOOD;

		int ColourOption=randomiser.nextInt(5);
		
		int upperleftx=(i-1)*70+2; upperleftx+=70;
		int upperlefty=(j-1)*70+30; //upperlefty+=30;
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,69,70);
		BlockSquare.setFill(ColourList[ColourOption]);
		BlockSquare.setArcHeight(25);
		BlockSquare.setArcWidth(25);

		int BlockValue=100;
		Text BlockLabel = new Text(Integer.toString(BlockValue));
		BlockLabel.setLayoutX(upperleftx+28);
		BlockLabel.setLayoutY(upperlefty+40);
		BlockLabel.setTextAlignment(TextAlignment.CENTER);
		
		
//      	StackPane BlockStack = new StackPane();
//		BlockStack.getChildren().addAll(BlockSquare, BlockLabel);
//		BlockStack.setLayoutX(upperleftx);
//		BlockStack.setLayoutY(upperlefty);
//		MainGrid.getChildren().addAll(BlockStack);
		
		MainGrid.getChildren().addAll(BlockSquare, BlockLabel);
		
		
		Block B= new Block(i,j,BlockValue,BlockSquare, BlockLabel);
		Screen[i][j]=B;
		ScreenElements.add(B);
		
		
	}
	
	public static void DrawWall(int i, int j) {
			
			int upperleftx=(i-1)*70+33; upperleftx+=70;
			int upperlefty=(j-1)*70+30; //upperlefty+=30;
			Rectangle WallRec= new Rectangle(upperleftx,upperlefty,4,70);
			WallRec.setFill(Color.LIGHTSLATEGRAY);
			//Wall.setArcHeight(15);
			//Wall.setArcWidth(15);
			MainGrid.getChildren().add(WallRec);
			
			Wall W= new Wall(i,j,1,WallRec);
			Screen[i][j]=W;
			ScreenElements.add(W);
		}
	
	
    public static void DrawSnake(double x, double y,Snake S) {
    	
    	S.GetBody().clear();
		double Cx=x;
		double Cy=y;
		double R=8;
		
		//add circle head
		Circle SnakeHead= new Circle(Cx, Cy, R, Color.RED);
		MainGrid.getChildren().add(SnakeHead);
		S.GetBody().add(SnakeHead);
		
		for (int x1=1; x1<=S.GetLength(); x1++) {
		Circle  C=new Circle(Cx, Cy +(x1*R*2), R, Color.ORANGE);
		MainGrid.getChildren().add(C);
		S.GetBody().add(C);
		
		}
	}
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}