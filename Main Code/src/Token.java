public class Token {

	protected Point Position;
	protected int Value;
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
	
	public void SetValue(int V) {
		this.Value=V;
	}
	
	public int GetValue() {
		return this.Value;
	}
	
	public Token(int a, int b, boolean e, int v) {
		this.Position=new Point(a,b);
		this.Enable=e;
		this.Value=v;
	}

}
