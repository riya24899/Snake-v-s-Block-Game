public class Obstruction {

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
	
	public Obstruction(int a, int b, Object[][] Screen, boolean e) {
		this.Position=new Point(a,b);
		this.Enable=e;
	}

}