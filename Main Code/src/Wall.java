public class Wall extends Obstruction {
	
	private int length;
	
	public Wall (int a, int b, Object[][] Screen,int v) {
		super(a,b,Screen,true);
		this.length=v;
	}
	
	public void setLength(int v) {
		this.length=v;
	}

	public int getLength() {
		return this.length;
	}
}
