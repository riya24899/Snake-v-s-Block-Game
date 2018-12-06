import javafx.scene.shape.Circle;

public class Magnet extends Token {
	
	/**
	   *
	   The class is used to create a circular magnet token.
	   The magnet has a reference to the circle and text label linked with it.
	   *
	*/
	
	private Circle MagCircle;
	public Magnet (int a, int b, Circle M) {
		super(a,b,true,0);
		this.MagCircle=M;
	}
	
	public void SetCircle(Circle C) {
		this.MagCircle= C;
	}

	public Circle GetCircle() {
		return this.MagCircle;
	}


}