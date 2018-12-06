import javafx.scene.shape.Circle;
import javafx.scene.text.*;


public class Ball extends Token {
	
	/**
	   *
	   The class is used to create a circular ball.
	   The ball has the attribute of value, and a reference to the circle and text label linked with it.
	   *
	*/
	
	
	private Circle BallCircle;
	private Text BallText;
	private int Value;
	
	public Ball (int a, int b, Circle C, Text T, int value) {
		super(a,b,true,value);
		BallCircle=C;
		BallText=T;
		this.Value=value;
		
	}
	
	public Ball (int a, int b) {
		super(a,b,true,0);
	}
	
	public Circle GetCircle() {
		return this.BallCircle;
	}
	
	public void SetCircle(Circle C) {
		this.BallCircle=C;
	}
	
	public Text GetText() {
		return this.BallText;
	}

	public int getValue() {
		return Value;
	}

	public void setValue(int value) {
		Value = value;
	}

	public void SetText(Text T) {
		this.BallText=T;
	}

}