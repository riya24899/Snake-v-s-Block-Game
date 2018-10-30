import java.util.Date;

public class Snake extends Player{
	
private int Length;
 private int Speed;
 private int Score;
 private int HeadPosition;
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
 
 public void SetHead(int P) {
	 this.HeadPosition=P;
 }
 
 public int GetHead() {
	 return this.HeadPosition;
 }
 
 public void SetAlive(boolean B) {
	 this.IsAlive=B;
 }
 
 public boolean GetAlive() {
	 return this.IsAlive;
 }
 
 Snake(int S, Date D, Snake CS) {
		super(S, D, CS);
	}
 
 Snake() {
	 super();
 }

}
