import javafx.scene.shape.Circle;

public class Coin extends Token {
	
	private Circle CoinCircle;
	public Coin (int a, int b, Circle C) {
		super(a,b,true,0);
		this.CoinCircle=C;
		
	}
	
	public void SetCircle(Circle C) {
		this.CoinCircle= C;
	}

	public Circle GetCircle() {
		return this.CoinCircle;
	}

}
