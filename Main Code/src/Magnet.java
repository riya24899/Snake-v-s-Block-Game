import javafx.scene.shape.Circle;

public class Magnet extends Token {
	
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
