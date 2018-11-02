import java.util.ArrayList;
import java.io.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;


public class Snake implements Serializable {
	
	private static final long serialVersionUID = 376L;
    private int Length;
    private int Speed;
    private int Score;
    private ArrayList<Circle> SnakeBody= new ArrayList<Circle>();
    private Point HeadPosition;
    private boolean IsAlive;
 
 public void SetLength(int L) {
	 this.Length=L;
 }
 
 public int GetLength() {
	 return this.Length;
 }
 
 public void SetSpeed(int S) {
	 this.Speed=S;
 }
 
 public int GetSpeed() {
	 return this.Speed;
 }
 
 public void SetScore(int S) {
	 this.Score=S;
 }
 
 public int GetScore() {
	 return this.Score;
 }
 
 public void SetHead(Point P) {
	 this.HeadPosition=P;
 }
 
 public Point GetHead() {
	 return this.HeadPosition;
 }
 
 public void SetAlive(boolean B) {
	 this.IsAlive=B;
 }
 
 public boolean GetAlive() {
	 return this.IsAlive;
 }
 
 public void SetBody(ArrayList<Circle> C) {
	 this.SnakeBody=C;
 }
 
 public ArrayList<Circle> GetBody() {
	 return this.SnakeBody;
 }
 
 public void moveSnake(int dir) {
	 
		if (dir==1) {
		for (int i=0; i<=this.GetLength(); i++) {
	
	        TranslateTransition tt= new TranslateTransition(Duration.millis(5),this.SnakeBody.get(i));
	        if(this.SnakeBody.get(i).getCenterX()!=105) {
			tt.setFromX(this.SnakeBody.get(i).getCenterX()-175);
			tt.setToX(this.SnakeBody.get(i).getCenterX()-176);
			tt.setCycleCount(1);
			tt.setAutoReverse(false);
			tt.play();
			this.SnakeBody.get(i).setCenterX(this.SnakeBody.get(i).getCenterX()-1);
	        }
		}

		}
		else if (dir==2) {
			for (int i=0; i<=this.GetLength(); i++) {
				
		        TranslateTransition tt= new TranslateTransition(Duration.millis(5),this.SnakeBody.get(i));
		        if(this.SnakeBody.get(i).getCenterX()!=245) {
				tt.setFromX(this.SnakeBody.get(i).getCenterX()-175);
				tt.setToX(this.SnakeBody.get(i).getCenterX()-174);
				tt.setCycleCount(1);
				tt.setAutoReverse(false);
				tt.play();
				this.SnakeBody.get(i).setCenterX(this.SnakeBody.get(i).getCenterX()+1);
		        }
			}

			}
		
 }
 
 Snake() {
		this.HeadPosition= new Point(3,6);
	}

}
