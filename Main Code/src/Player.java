import java.util.Date;

public class Player {
	
	protected int Score;
	protected Date CurrDate;
	protected Snake CurrSnake;
 
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
     
     Player(){
    	 
     }
	 
}
