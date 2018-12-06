public class Obstruction {

	/**
	 * This class is the parent class for Wall/Blocks. It holds the position and enabled status of these elements.
	 */
	protected Point Position;
	protected boolean Enable;
	
	public void SetPosition(Point P) {
		this.Position=P;
	}
	
	public Point GetPosition() {
		return this.Position;
	}
	
	public void SetEnable(boolean E) {
		this.Enable=E;
	}
	
	public boolean GetEnable() {
		return this.Enable;
	}
	
	public Obstruction(int a, int b, boolean e) {
		this.Position=new Point(a,b);
		this.Enable=e;
	}

}