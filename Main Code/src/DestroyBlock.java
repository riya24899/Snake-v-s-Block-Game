import javafx.scene.shape.Circle;

public class DestroyBlock extends Token{
	
	/**
	   *
	   The class is used to create a circular destroyblock token .
	   The destroyblock has the attribute of value, and a reference to the circle linked with it.
	   *
	*/
	
	
	private Circle DesCircle;
	public DestroyBlock (int a, int b, Circle C) {
		super(a,b,true,0);
		this.DesCircle= C;
	}
	
	public void SetCircle(Circle C) {
		this.DesCircle= C;
	}

	public Circle GetCircle() {
		return this.DesCircle;
	}

}