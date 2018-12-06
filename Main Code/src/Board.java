import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Math;
import java.util.Date;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Board extends Application implements Serializable {
	
	/**
	   *
	   The class is used to create the GUI of the gameplay.
	   The board class keeps a reference to all the tokens and obstructions on the screen.
	   It implements the logic of the tokens when it collides with the head of the snake.
	   *
	*/
	

	private static final long serialVersionUID = 28L;
	private Group MainGrid= new Group();
	
	private static final String destroyblockmusicFile = "DestroySound.mp3"; 
	private static final String destroyblockSound = (new File(destroyblockmusicFile).toURI().toString());
	private static final AudioClip DBmediaPlayer = new AudioClip(destroyblockSound);
	
	private static final String mariomusicFile = "Mario.mp3"; 
	private static final String marioSound = (new File(mariomusicFile).toURI().toString());
	private static final AudioClip DBmediaPlayer2 = new AudioClip(marioSound);

	private static int coin=0;
	private int rowcount=0;
	private Object [][] Screen= new Object[5][9];
	private double Time=-1; 
	private int count=0;
	private Label Score;
	private Label LengthS;
	private Label CoinS;
	private ArrayList<Object> ScreenElements=new ArrayList<Object>(); 
	private ArrayList<Object> deletionArray=new ArrayList<Object>();
	private static Image magnetImage= new Image("/magnetism.png");
	private static Image coinImage= new Image("/coin.png");
	private static Random randomiser= new Random();
	private static Image shieldImage= new Image("/shield.png");
	private static Image DestroyBlockImage= new Image("/bomb.png");
	private ComboBox<String> PauseOptions= new ComboBox<String>();
	private double BoardSpeed;
	volatile private Player player;
	private boolean flag=true;
	private int ShieldFlag=0;
	private int ShieldTimeCounter=0;
	private boolean moveLeft=true;
	private boolean moveRight=true;
	
	public void setBoardSpeed(double b) {
		/**
		   *
		  This method sets the value of the BoardSpeed attribute.
		   *
		*/
		
		BoardSpeed=b;
	}
	
	public Board() {
		this.PauseOptions.getItems().addAll("RESTART", "EXIT");
		this.PauseOptions.setValue("PAUSE");
		this.PauseOptions.setPrefWidth(100);
		this.PauseOptions.setPrefHeight(30);
		this.PauseOptions.setLayoutX(4);
		this.PauseOptions.setStyle("-fx-background-color: #aba6ca; -fx-font-size: 1em; -fx-text-fill: #2e2759;");
		this.player=new Player(0, new Date(), new Snake());
		Snake Snake1= this.player.GetSnake();
		Snake1.SetLength(4);
		this.Screen = new Object[5][9];
		this.Time=-1;
		this.count=0;
		this.Score= new Label();
		this.Score.setTextFill(Color.WHITE);
		this.Score.setStyle("-fx-font-size: 1.65em; ");
		this.Score.setLayoutX(300);
		this.Score.setLayoutY(3);
		
		this.LengthS= new Label();
		this.LengthS.setTextFill(Color.PALEGOLDENROD);
		this.LengthS.setStyle("-fx-font-size: 1em; ");
		this.LengthS.setLayoutX(280);
		this.LengthS.setLayoutY(33);
		
		this.CoinS= new Label("Coins: "+ "0");
		this.CoinS.setTextFill(Color.PALETURQUOISE);
		this.CoinS.setStyle("-fx-font-size: 1em; ");
		this.CoinS.setLayoutX(280);
		this.CoinS.setLayoutY(47);

		this.ScreenElements=new ArrayList<Object>(); 
		this.deletionArray=new ArrayList<Object>();
		this.BoardSpeed=1.75;
		this.ShieldFlag=0;
		this.ShieldTimeCounter=0;
		this.moveLeft=true;
		this.moveRight=true;
	}
	
	public void start(Stage primaryStage) {
		
		/**
		   *
		 This method creates and renders every frame of the game.
		   *
		*/
		
		
		Snake Snake1= player.GetSnake();
		Score.setText(Integer.toString(player.GetScore()));
		DrawSnake(175, 490, Snake1);
		MainGrid.getChildren().addAll(PauseOptions, Score, LengthS, CoinS);

		Scene EntryScene= new Scene(MainGrid, 353, 660, Color.DARKSLATEBLUE);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Vs Block");
		primaryStage.setScene(EntryScene);
		primaryStage.show();
	
		EntryScene.setOnKeyPressed(ke -> {
	        KeyCode keyCode = ke.getCode();
	        if (keyCode.equals(KeyCode.LEFT)) {
	        	if(moveLeft)
	            player.GetSnake().moveSnake(1);
	            return;
	        }
	        if (keyCode.equals(KeyCode.RIGHT)) {
	        	if(moveRight) 
	        	player.GetSnake().moveSnake(2);
		        return;
	        }
	    });
		
		AnimationTimer main= new AnimationTimer() {
			@Override
			
			public void handle(long now) {
				
				if (ShieldFlag==1)
				ShieldTimeCounter++;
				
				if (ShieldTimeCounter==300) {
					ShieldFlag=0;
					ShieldTimeCounter=0;
				}
				
				if (coin==5) {
					coin=0;
					CoinS.setText("Coin: "+ coin);
		
					Snake S=player.GetSnake();
					player.GetSnake().SetLength(player.GetSnake().GetLength()+10);
					player.GetSnake().disableSnake(MainGrid);
					double newy= S.GetBody().get(0).getCenterY();
					DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
					LengthS.setText("Length: "+ S.GetLength());
				}
				
				clearBoard();
				flag=true;
				moveLeft=true;
				moveRight=true;
				try {
					flag=checkCollision(primaryStage,this);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag) {
					Time++;
					}
				double divisor= 70/BoardSpeed;
				if(Math.floor(Time%divisor)==0 ) {
				Time=Math.floor(Time%divisor);
				DrawRow(MainGrid); 	
				}
				if (flag) {
				moveBoard();
				}
			}
		};
		
		AnimationTimer checker= new AnimationTimer() {
			@Override
			
			public void handle(long now) {
				if(PauseOptions.getValue().equalsIgnoreCase("RESTART")) {
					Board newGame=new Board();
					this.stop();
					main.stop();
					newGame.start(primaryStage);		
					}
				else if(PauseOptions.getValue().equalsIgnoreCase("EXIT")) {
					EntryScreen exitmenu= new EntryScreen();
					this.stop();
					main.stop();
					try {
					Board.this.SerializePlayers(player);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally {
						exitmenu.start(primaryStage);
					}
				}
				
			}
		};
		
		PauseOptions.setOnMouseClicked(e -> {
			String value=(String)PauseOptions.getValue();
			if (value.equalsIgnoreCase("PAUSE")) {
				count++;
				if(count%2==1)
				   main.stop();
				else {
					main.start();
					count=0;
				}
			}
		});
		
		primaryStage.setOnCloseRequest(e -> {
			try {
				Board.this.SerializePlayers(player);
				}
			catch(Exception e1) {
				e1.printStackTrace();
				}
			});
		
		Thread t1=new Thread(new TaskRunner(checker));
		Thread t2=new Thread(new TaskRunner(main));
		t1.start();
		t2.start();

	}
	

	public void clearBoard() {
		
		/**
		   *
		 This method deletes the elements that have moved out of the screen.
		   *
		*/
		
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
	
	public boolean checkCollision(Stage primaryStage, AnimationTimer tt) throws ClassNotFoundException, IOException {
		/**
		   *
		   This method checks to see if the snake has collided with a token/obstruction and implements corresponding logic if it has.
		   *
		*/
		
		
		boolean flag=true;
		for (int i=0;i<ScreenElements.size();i++) {
			if(ScreenElements.get(i) instanceof Token) {
				if(ScreenElements.get(i) instanceof Ball) {
					Ball B=(Ball)ScreenElements.get(i);
					Circle C=B.GetCircle();
					if (C.intersects(C.sceneToLocal(this.player.GetSnake().GetBody().get(0).localToScene(this.player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						DBmediaPlayer2.play();

						int v=B.GetValue();
						MainGrid.getChildren().remove(B.GetText());
						MainGrid.getChildren().remove(C);
						DrawAnimateToken(B.GetPosition().Getx(), B.GetCircle().getLayoutY());
						ScreenElements.remove(B);
						Snake S= this.player.GetSnake();
						S.disableSnake(MainGrid);
						S.SetLength(S.GetLength()+v);
						double newy= S.GetBody().get(0).getCenterY();
						DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
						BoardSpeed+=0.1*v;
					}
				
				}
				
				else if(ScreenElements.get(i) instanceof DestroyBlock) {
					DestroyBlock D =(DestroyBlock)ScreenElements.get(i);
					Circle C=D.GetCircle();
					
					
					if (C.intersects(C.sceneToLocal(this.player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						DBmediaPlayer.play();

						MainGrid.getChildren().remove(D.GetCircle());
						
						for (int y=0;y<ScreenElements.size();y++) {
							if(ScreenElements.get(y) instanceof Block) {
							
							Block B =(Block)ScreenElements.get(y);
							MainGrid.getChildren().remove(B.GetRec());
							MainGrid.getChildren().remove(B.GetText());
							DrawAnimateBlock(B.GetPosition().Getx(),B.GetRec().getLayoutY());
							ScreenElements.remove(B);
							this.player.SetScore(this.player.GetScore()+B.getValue());
							Score.setText(Integer.toString(this.player.GetScore()));
							}
						}	
					}
				
				}
				
				else if (ScreenElements.get(i) instanceof Magnet) {
					Magnet M= (Magnet)ScreenElements.get(i);
					Circle C=M.GetCircle();
					double X=C.getCenterX();
					double Y=C.getCenterY();
					
					if (C.intersects(C.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						
						DBmediaPlayer2.play();

						MainGrid.getChildren().remove(M.GetCircle());
						DrawAnimateToken(M.GetPosition().Getx(), M.GetCircle().getLayoutY());
						ScreenElements.remove(M);
						for (int y=0;y<ScreenElements.size();y++) {
							if(ScreenElements.get(y) instanceof Ball) {
						
							Ball B =(Ball)ScreenElements.get(y);
							Circle BC= B.GetCircle();
							double xx= BC.getCenterX(); 
							double yy= BC.getCenterY();
							
							if (Math.sqrt(Math.pow(X-xx, (double)2) + Math.pow(Y-yy, (double)2))<=70) {
								int v=B.GetValue();
								MainGrid.getChildren().remove(B.GetText());
								MainGrid.getChildren().remove(BC);
								DrawAnimateToken(B.GetPosition().Getx(), B.GetCircle().getLayoutY());
								ScreenElements.remove(B);
								Snake S= this.player.GetSnake();
								S.disableSnake(MainGrid);
								S.SetLength(S.GetLength()+v);
								double newy= S.GetBody().get(0).getCenterY();
								DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
								BoardSpeed+=0.1*v;
							}
							}
						}	
			
			}
				}
				
else if (ScreenElements.get(i) instanceof Coin) {
					
					Coin S=(Coin)ScreenElements.get(i);
					Circle C=S.GetCircle();
					if (C.intersects(C.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						DBmediaPlayer2.play();
						MainGrid.getChildren().remove(S.GetCircle());
						DrawAnimateToken(S.GetPosition().Getx(), S.GetCircle().getLayoutY()+30);
						coin++;
						System.out.print(coin);
						CoinS.setText("Coin: " + Integer.toString(coin) );
						ScreenElements.remove(S);
						
					}
					
				}
				
				
				else if (ScreenElements.get(i) instanceof Shield) {
					
					Shield S =(Shield)ScreenElements.get(i);
					Circle C=S.GetCircle();
					
				
					if (C.intersects(C.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						DBmediaPlayer2.play();

						MainGrid.getChildren().remove(S.GetCircle());
						DrawAnimateToken(S.GetPosition().Getx(), S.GetCircle().getLayoutY()+30);
						ScreenElements.remove(S);
						ShieldFlag=1;
					}
				
				}
				
				
			}
			
			else if(ScreenElements.get(i) instanceof Obstruction) {
				if(ScreenElements.get(i) instanceof Block) {
					Block B =(Block)ScreenElements.get(i);
					Rectangle R=B.GetRec();
					
//					for (int j=0;j<player.GetSnake().GetBody().size();j++) {
//						if (R.intersects(R.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(j).getBoundsInLocal())))) {
//
//
//							if (R.getX()+2==player.GetSnake().GetBody().get(j).getCenterX()) {
//								flag=false;
//							}
//							else if (R.getX()<player.GetSnake().GetBody().get(j).getCenterX()) {
//								moveLeft=false;																	
//							}
//							
//							else  {
//								moveRight=false;																
//								
//							}
//								
//						}
//				
//					}
					
					if (R.intersects(R.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(0).getBoundsInLocal())))) {
						
						if (B.getValue()<=5) {
							int v = B.getValue();
							DBmediaPlayer.play();
							MainGrid.getChildren().remove(B.GetRec());
							MainGrid.getChildren().remove(B.GetText());
							DrawAnimateBlock(B.GetPosition().Getx());
							ScreenElements.remove(B);
							player.SetScore(player.GetScore()+B.getValue());
							Score.setText(Integer.toString(player.GetScore()));
							Snake S= player.GetSnake();
							
							if(ShieldFlag==0) {
							if (S.GetLength()<v) {
								GameOver goscreen= new GameOver(player, coin);
								tt.stop();
								goscreen.setLeaderBoard();
								goscreen.start(primaryStage);
							}
							S.disableSnake(MainGrid);
							S.SetLength(S.GetLength()-v);
							double newy= S.GetBody().get(0).getCenterY();
							DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
							BoardSpeed-=0.1*v;
							}
						}
						else {
							B.GetText().setText(Integer.toString(B.getValue()-1));
							B.setValue(B.getValue()-1);
							Snake S= player.GetSnake();
							
								
							if(ShieldFlag==0) {
								
								if (S.GetLength()==1) {
									GameOver goscreen= new GameOver(player, coin);
									tt.stop();
									goscreen.setLeaderBoard();
									goscreen.start(primaryStage);
								}
								BoardSpeed-=0.1;
							
							S.disableSnake(MainGrid);
							S.SetLength(S.GetLength()-1);
							double newy= S.GetBody().get(0).getCenterY();
							DrawSnake(S.GetBody().get(0).getCenterX(),newy,S);
							}
							flag=false;
							player.SetScore(player.GetScore()+1);
							Score.setText(Integer.toString(player.GetScore()));
						}
					
					}
				
				}
			}
			
			if(ScreenElements.get(i) instanceof Wall) {
				Wall W =(Wall)ScreenElements.get(i);
				Rectangle R=W.GetRec();
				
				
				for (int j=0;j<player.GetSnake().GetBody().size();j++) {
					if (R.intersects(R.sceneToLocal(player.GetSnake().GetBody().get(0).localToScene(player.GetSnake().GetBody().get(j).getBoundsInLocal())))) {

						if (R.getX()+2==player.GetSnake().GetBody().get(j).getCenterX()) {
							flag=false;
						}
						else if (R.getX()<player.GetSnake().GetBody().get(j).getCenterX()) {
							moveLeft=false;																	//if left
						}
						
						else  {
							moveRight=false;																	//if rihgt
							
						}
							
					}
			
				}	
					
			}
			
		}
		return flag;
	}
	
	
public void moveBoard() {
	
	/**
	   *
	  This method is used to move every element on the screen down by 3 pixels.
	   *
	*/
	
		
		
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
			       
			       else if (ScreenElements.get(j) instanceof Coin) {
			    	   Coin C= (Coin) ScreenElements.get(j);
			    	   C.GetCircle().setLayoutY(C.GetCircle().getLayoutY()+BoardSpeed);
 			    	
			       }
			       
				}   
			}
		}
	
public void DrawRow(Group MainGrid) {
	int j=1;
	rowcount++; rowcount%=8;
	if (rowcount==3){ 
		int col= randomiser.nextInt(5);
		
		for (int k=0; k<=4; k++) { 
			int BlockValue=randomiser.nextInt(player.GetSnake().GetLength()+20)+1;
			if (Screen[k][j]==null) {
				if (k==col) {
				 BlockValue=randomiser.nextInt(player.GetSnake().GetLength()+1)+1;
				}  
		
		DrawBlock(k, j, BlockValue); 
			}
		}
	}
		
		else {
			
				for (int i=0; i<=4; i++) {
					
					int rand= randomiser.nextInt(35)+1;
					if (rand<=5) 
					
					{ int rand2= randomiser.nextInt(15)+1;

						{
							
							if (rand2==1) {
								 if (Screen[i][j]==null) {
										
										DrawDestroyBlock(i, j);
									}
							}
							
							else if (rand2==2) {
								if (Screen[i][j]==null) {
									DrawMagnet(i, j);
									}
						}
							
							else if (rand2==3) {
								if (Screen[i][j]==null) {
									DrawShield(i, j);
									}
							}
							
					}

					}	
						
					else if (rand==6 || rand==7 || rand==8 ) {
						if (Screen[i][j]==null) {
						DrawBall(i, j);
						}
					}
					
					
					else if (rand==9 || rand==10) {
						if (rowcount ==5 || rowcount ==6 || rowcount ==7 || rowcount==1|| rowcount==0) {
						if (Screen[i][j]==null) {
						DrawBlock(i, j, randomiser.nextInt(player.GetSnake().GetLength()+20)+1);
						}
					  }	
					}
					
					else if  (rand==12 || rand==13 || rand==14) {
						
						if (Screen[i][j]==null) {
						DrawWall(i, j);
						}
						}	
					else if (rand==11) {
						
						if (Screen[i][j]==null) {
							DrawCoin(i, j);
							}
							}	
					
				}

			for (int x=0;x<=4;x++) {
				Screen[x][j]=null;
			}
		}
	
}
	

	
	
	public void DrawMagnet(int i, int j) {
		
		/**
		   *
		 This method draws the magnet token onto the screen.
		   *
		*/
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle MagCircle= new Circle(r,c,15);
		MagCircle.setFill(new ImagePattern(magnetImage));
		MainGrid.getChildren().add(MagCircle);
		
		Magnet M= new Magnet(i,j, MagCircle);
		Screen[i][j]=M;
		ScreenElements.add(M);

	}
	
	public void DrawShield (int i, int j) {
		
		/**
		   *
		 This method draws the shield token onto the screen.
		   *
		*/
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle ShieldCircle= new Circle(r,c,14);
		ShieldCircle.setFill(new ImagePattern(shieldImage));
		MainGrid.getChildren().add(ShieldCircle);
		
		Shield S= new Shield(i,j, ShieldCircle);
		Screen[i][j]=S;
		ScreenElements.add(S);
		
	}
	
	public void DrawDestroyBlock (int i, int j) {
		
		/**
		   *
		 This method draws the destroy block token onto the screen.
		   *
		*/
			
			int r=i*70-35; r=r+70;
			int c=j*70-5; //c+=30;
			Circle DBCircle= new Circle(r,c,14);
			DBCircle.setFill(new ImagePattern(DestroyBlockImage));
			MainGrid.getChildren().add(DBCircle);
			
			DestroyBlock D= new DestroyBlock(i,j, DBCircle);
			Screen[i][j]=D;
			ScreenElements.add(D);
			
	}
	
	public void DrawBall(int i, int j) {

		/**
		   *
		 This method draws the ball token onto the screen.
		   *
		*/
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle BallCircle= new Circle(r,c,5, Color.GOLD);
		int value= randomiser.nextInt(5)+1;
		Text BallLabel= new Text(Integer.toString(value));
		
		BallLabel.setLayoutX(r-1);
		BallLabel.setLayoutY(c-7);
		BallLabel.setTextAlignment(TextAlignment.CENTER);

		Ball B= new Ball(i,j,BallCircle, BallLabel, value);
		Screen[i][j]= B;
		ScreenElements.add(B);
		MainGrid.getChildren().addAll(BallCircle,BallLabel);
		
	}
	
	public void DrawBlock(int i, int j, int val) {
		
		/**
		   *
		 This method draws the block obstruction onto the screen.
		   *
		*/
			
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

			Text BlockLabel = new Text(Integer.toString(val));
			BlockLabel.setLayoutX(upperleftx+28);
			BlockLabel.setLayoutY(upperlefty+40);
			BlockLabel.setTextAlignment(TextAlignment.CENTER);
		
			
			MainGrid.getChildren().addAll(BlockSquare, BlockLabel);
			
			
			Block B= new Block(i,j,val,BlockSquare, BlockLabel);
			Screen[i][j]=B;
			ScreenElements.add(B);
			
			
		}
	
public void DrawCoin (int i, int j) {
		
		/**
		   *
		 This method draws a coin onto the screen.
		   *
		*/
		
		int r=i*70-35; r=r+70;
		int c=j*70-5; //c+=30;
		Circle ShieldCircle= new Circle(r,c,14);
		ShieldCircle.setFill(new ImagePattern(coinImage));
		MainGrid.getChildren().add(ShieldCircle);
		
		Coin S= new Coin(i,j, ShieldCircle);
		Screen[i][j]=S;
		ScreenElements.add(S);
		
	}

	public void DrawAnimateSub(double x, double y) {

	    for (int k=0; k<8; k++) {
		int shiftx= randomiser.nextInt(70)+10;
		int shifty= randomiser.nextInt(70)+10;
		double upperleftx=(x+1)*70-45; 
		double upperlefty=y; 
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,6,6);
		BlockSquare.setFill(Color.PALETURQUOISE);
		MainGrid.getChildren().add(BlockSquare);
		
		Path path = new Path(); 
		MoveTo moveTo = new MoveTo(upperleftx, upperlefty);
		
	    int mod= randomiser.nextInt(2);
	    if (mod==0) { mod=-1;}
	    
	double d1=upperleftx+mod*shiftx;
	double d2=upperlefty+shifty;

	double c1=upperleftx+mod*shiftx-mod*8;
	double c2=upperlefty+shifty-8;

	double e1=upperleftx+mod*shiftx-mod*5;
	double e2=upperlefty+shifty-5;

		CubicCurveTo cubicCurveTo = new CubicCurveTo(d1,d2,c1,c2,e1,e2); 
		path.getElements().add(moveTo); 
	path.getElements().add(cubicCurveTo);
	PathTransition pathTransition = new PathTransition(); 
	pathTransition.setDuration(Duration.millis(200)); 
	pathTransition.setNode(BlockSquare); 
	pathTransition.setPath(path);  
	//pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TAN GENT); 
	pathTransition.setCycleCount(1); 
	pathTransition.setAutoReverse(true); 
	pathTransition.play();

	FadeTransition fade= new FadeTransition();
	fade.setDuration(Duration.millis(500));
	fade.setNode(BlockSquare);
	fade.setFromValue(1.0);
	fade.setToValue(0);
	fade.setCycleCount(1);
	fade.setAutoReverse(false);
	fade.play();


	    }
	}

	public void SerializePlayers(Player P) throws IOException {
		
		/**
		   *
		 This method stores the state of the game.
		   *
		*/

		ObjectOutputStream PlayersOutFile =null;
		
			try {
				PlayersOutFile = new ObjectOutputStream(new FileOutputStream("Resume.dat"));
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
	
	
	public Player DeserializePlayers() throws IOException, ClassNotFoundException {
		
		/**
		   *
		 This method is used to restore the saved game state.
		   *
		*/
		
		Player P= null;
		ObjectInputStream BoardInFile = null;
			try {
				BoardInFile = new ObjectInputStream(new FileInputStream("Resume.dat"));
				P=(Player)BoardInFile.readObject(); 
				return P;
			}
			catch(FileNotFoundException e) {
				return new Player(0, new Date(), new Snake());
			}
			catch(Exception e) {

				return null;
			}

			finally {
				if(BoardInFile!=null)
				BoardInFile.close();
			}
			
	}	

	public void setPlayer(Player p) {
		
		/**
		   *
		 This method sets the player attribute of the board, that is the current player of the game.
		   *
		*/
		
		this.player=p;
	}
	public void DrawWall(int i, int j) {
		
		/**
		   *
		 This method draws the wall obstruction onto the screen.
		   *
		*/
			
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
	
	
    public void DrawSnake(double x, double y,Snake S) {
    	
    	/**
		   *
		 This method draws the snake onto the screen.
		   *
		*/
	LengthS.setText("Length: "+ Integer.toString(this.player.GetSnake().GetLength()+1));
    	S.GetBody().clear();
		double Cx=x;
		double Cy=y;
		double R=8;
	
		Circle SnakeHead= new Circle(Cx, Cy, R, Color.RED);
		MainGrid.getChildren().add(SnakeHead);
		S.GetBody().add(SnakeHead);
		
		for (int x1=1; x1<=S.GetLength(); x1++) {
		Circle  C=new Circle(Cx, Cy +(x1*R*2), R, Color.ORANGE);
		MainGrid.getChildren().add(C);
		S.GetBody().add(C);
		
		}
	}
    
    public void DrawAnimateBlock(double x) {
    	
    	/**
		   *
		 This method creates the animation on destroying a block/token onto the screen.
		   *
		*/

    	
	Color ColourList[]= new Color[5];
	ColourList[0]= Color.PALEGOLDENROD;
	ColourList[1]= Color.PALEGREEN;
	ColourList[2]= Color.PALEVIOLETRED;
	ColourList[3]= Color.PALETURQUOISE;
	ColourList[4]= Color.BURLYWOOD;
	
	    for (int k=0; k<10; k++) {

		int ColourOption=randomiser.nextInt(5);
		int shiftx= randomiser.nextInt(70)+20;
		int shifty= randomiser.nextInt(70)+20;
		double upperleftx=(x+1)*70-45; 
		double upperlefty=482; 
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,10,10);
		BlockSquare.setFill(ColourList[ColourOption]);
		MainGrid.getChildren().add(BlockSquare);
		
    	Path path = new Path(); 
    	MoveTo moveTo = new MoveTo(upperleftx, upperlefty);
    	
	    int mod= randomiser.nextInt(2);
	    if (mod==0) { mod=-1;}
	    
    double d1=upperleftx+mod*shiftx;
    double d2=upperlefty-shifty;
    
    double c1=upperleftx+mod*shiftx-mod*10;
    double c2=upperlefty-shifty-10;
    
    double e1=upperleftx+mod*shiftx-mod*5;
    double e2=upperlefty-shifty-5;
    
    CubicCurveTo cubicCurveTo = new CubicCurveTo(d1,d2,c1,c2,e1,e2); 
    path.getElements().add(moveTo); 
    path.getElements().add(cubicCurveTo);
    PathTransition pathTransition = new PathTransition(); 
    pathTransition.setDuration(Duration.millis(200)); 
    pathTransition.setNode(BlockSquare); 
    pathTransition.setPath(path);  
    pathTransition.setCycleCount(1); 
    pathTransition.setAutoReverse(false); 
    pathTransition.play();
    
    FadeTransition fade= new FadeTransition();
    fade.setDuration(Duration.millis(300));
    fade.setNode(BlockSquare);
    fade.setFromValue(1.0);
    fade.setToValue(0);
    fade.setCycleCount(1);
    fade.setAutoReverse(false);
    fade.play();

    
	    }
    }
    
    public void DrawAnimateBlock(double x, double y) {
    	
    	/**
		   *
		 This method creates the animation on destroying a block/token onto the screen.
		   *
		*/


    	
	    Color ColourList[]= new Color[5];
	ColourList[0]= Color.PALEGOLDENROD;
	ColourList[1]= Color.PALEGREEN;
	ColourList[2]= Color.PALEVIOLETRED;
	ColourList[3]= Color.PALETURQUOISE;
	ColourList[4]= Color.BURLYWOOD;
	
	    for (int k=0; k<10; k++) {

		int ColourOption=randomiser.nextInt(5);
		int shiftx= randomiser.nextInt(70)+20;
		int shifty= randomiser.nextInt(70)+20;
		double upperleftx=(x+1)*70-45; 
		double upperlefty=y; 
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,10,10);
		BlockSquare.setFill(ColourList[ColourOption]);
		MainGrid.getChildren().add(BlockSquare);
		
    	Path path = new Path(); 
    	MoveTo moveTo = new MoveTo(upperleftx, upperlefty);
    	
	    int mod= randomiser.nextInt(2);
	    if (mod==0) { mod=-1;}
	    
    double d1=upperleftx+mod*shiftx;
    double d2=upperlefty-shifty;
    
    double c1=upperleftx+mod*shiftx-mod*10;
    double c2=upperlefty-shifty-10;
    
    double e1=upperleftx+mod*shiftx-mod*5;
    double e2=upperlefty-shifty-5;
    
    CubicCurveTo cubicCurveTo = new CubicCurveTo(d1,d2,c1,c2,e1,e2); 
    path.getElements().add(moveTo); 
    path.getElements().add(cubicCurveTo);
    PathTransition pathTransition = new PathTransition(); 
    pathTransition.setDuration(Duration.millis(200)); 
    pathTransition.setNode(BlockSquare); 
    pathTransition.setPath(path);  
    pathTransition.setCycleCount(1); 
    pathTransition.setAutoReverse(false); 
    pathTransition.play();
    
    FadeTransition fade= new FadeTransition();
    fade.setDuration(Duration.millis(300));
    fade.setNode(BlockSquare);
    fade.setFromValue(1.0);
    fade.setToValue(0);
    fade.setCycleCount(1);
    fade.setAutoReverse(false);
    fade.play();

	    }
    }
    
    public void DrawAnimateToken(double x, double y) {
    	
    	/**
		   *
		 This method creates the animation on destroying a block/token onto the screen.
		   *
		*/



	    for (int k=0; k<8; k++) {

		int shiftx= randomiser.nextInt(70)+10;
		int shifty= randomiser.nextInt(70)+10;
		double upperleftx=(x+1)*70-45; 
		double upperlefty=y; 
		Rectangle BlockSquare= new Rectangle(upperleftx,upperlefty,6,6);
		BlockSquare.setFill(Color.PALEGOLDENROD);
		MainGrid.getChildren().add(BlockSquare);
		
    	Path path = new Path(); 
    	MoveTo moveTo = new MoveTo(upperleftx, upperlefty);
    	
	    int mod= randomiser.nextInt(2);
	    if (mod==0) { mod=-1;}
	    
    double d1=upperleftx+mod*shiftx;
    double d2=upperlefty-shifty;
    
    double c1=upperleftx+mod*shiftx-mod*8;
    double c2=upperlefty-shifty-8;
    
    double e1=upperleftx+mod*shiftx-mod*5;
    double e2=upperlefty-shifty-5;
    
    	CubicCurveTo cubicCurveTo = new CubicCurveTo(d1,d2,c1,c2,e1,e2); 
    	path.getElements().add(moveTo); 
    path.getElements().add(cubicCurveTo);
    PathTransition pathTransition = new PathTransition(); 
    pathTransition.setDuration(Duration.millis(200)); 
    pathTransition.setNode(BlockSquare); 
    pathTransition.setPath(path);  
    //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TAN GENT); 
    pathTransition.setCycleCount(1); 
    pathTransition.setAutoReverse(false); 
    pathTransition.play();
    
    FadeTransition fade= new FadeTransition();
    fade.setDuration(Duration.millis(300));
    fade.setNode(BlockSquare);
    fade.setFromValue(1.0);
    fade.setToValue(0);
    fade.setCycleCount(1);
    fade.setAutoReverse(false);
    fade.play();

    
	    }
}
    

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}