import javafx.scene.shape.Rectangle;

public class Wall extends Obstruction {

	/**
	   *
	   The class is used to create a rectangular wall.
	   The wall has the attribute of length, and a reference to the rectangle linked with it.
	   *
	*/
	
	private int length;
	private Rectangle WallRec;
	
	public Wall (int a, int b, int v, Rectangle R) {
		super(a,b,true);
		this.length=v;
		this.WallRec=R;
	}
	
	public void setLength(int v) {
		/**
		   *
		   * The method sets the length attribute of wall
		   *
		*/
		this.length=v;
	}

	public int getLength() {
		/**
		   *
		   * The method returns the length attribute of wall
		   *
		*/
		return this.length;
	}
	
	public void SetRec(Rectangle R) {
		/**
		   *
		   * The method sets the reference to the rectangle linked with the wall.
		   *
		*/
		this.WallRec= R;
	}

	public Rectangle GetRec() {
		/**
		   *
		   * The method returns the reference to the rectangle linked with the wall.
		   *
		*/
		return this.WallRec;
	}

}