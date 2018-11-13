import javafx.scene.shape.Circle;

public class DestroyBlock extends Token{
	private Circle DesCircle;
	private int Time =5;
	public DestroyBlock (int a, int b, Circle C) {
		super(a,b,true,0);
		this.DesCircle= C;
	}
	
	public void setTime(int t) {
		this.Time=t;
	}
	
	public int getTime() {
		return this.Time;
	}
	
	public void SetCircle(Circle C) {
		this.DesCircle= C;
	}

	public Circle GetCircle() {
		return this.DesCircle;
	}

}
