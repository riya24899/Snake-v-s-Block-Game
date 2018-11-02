public class Shield extends Token{
	
	private int Time =5;
	public Shield (int a, int b, Object[][] Screen) {
		super(a,b,Screen,true,0);
	}
	
	public void setTime(int t) {
		this.Time=t;
	}
	
	public int getTime() {
		return this.Time;
	}
}
