import javafx.scene.shape.Circle;

public class Shield extends Token{
	
	private int Time =5;
	private Circle ShieldCircle;
	
	public Shield (int a, int b, Circle S) {
		super(a,b,true,0);
		this.ShieldCircle=S;
	}
	
	public void setTime(int t) {
		this.Time=t;
	}
	
	public int getTime() {
		return this.Time;
	}
	
	public void SetCircle(Circle C) {
		this.ShieldCircle= C;
	}

	public Circle GetCircle() {
		return this.ShieldCircle;
	}

}
