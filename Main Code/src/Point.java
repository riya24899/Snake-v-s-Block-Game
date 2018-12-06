import java.io.Serializable;

public class Point implements Serializable{

	/** 
	 * This class is used to create a simple point with x, y coordinates. 
	 */
	private static final long serialVersionUID = 91L;
	private int x;
	private int y;
	
	public void Setx(int a) {
		this.x=a;
	}
	
	public int Getx() {
		return this.x;
	}
	
	public void Sety(int b) {
		this.y=b;
	}
	
	public int Gety() {
		return this.y;
	}
	
	public Point(int a, int b) {
		this.x=a;
		this.y=b;
	}

}