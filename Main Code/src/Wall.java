import javafx.scene.shape.Rectangle;

public class Wall extends Obstruction {
	
	private int length;
	private Rectangle WallRec;
	
	public Wall (int a, int b, int v, Rectangle R) {
		super(a,b,true);
		this.length=v;
		this.WallRec=R;
	}
	
	public void setLength(int v) {
		this.length=v;
	}

	public int getLength() {
		return this.length;
	}
	
	public void SetRec(Rectangle R) {
		this.WallRec= R;
	}

	public Rectangle GetRec() {
		return this.WallRec;
	}

}
