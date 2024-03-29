import java.util.Date;
import java.io.*;

public class Player implements Serializable {
	
	/**
	 * This class is used to create the current player and keep track of her snake length, score, etc. 
	 */
	
	private static final long serialVersionUID = 527L;
	volatile protected int Score;
	protected Date CurrDate;
	volatile protected Snake CurrSnake;
 
	public void SetScore(int S) {
		 this.Score=S;
	 }
	 
	 public int GetScore() {
		 return this.Score;
	 }
	 
	 public void SetDate(Date D) {
		 this.CurrDate=D;
	 }
	 
	 public Date GetDate() {
		 return this.CurrDate;
	 }
	 public void SetSnake(Snake S) {
		 this.CurrSnake=S;
	 }
	 
	 public Snake GetSnake() {
		 return this.CurrSnake;
	 }
	 
     Player(int S, Date D, Snake CS) {
		 this.Score=S;
		 this.CurrDate=D;
		 this.CurrSnake=CS;
	 }
	 
}