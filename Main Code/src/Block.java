public class Block extends Obstruction {
	
	private int value;
	
	public Block (int a, int b, Object[][] Screen,int v) {
		super(a,b,Screen,true);
		this.value=v;
	}
	
	public void setValue(int v) {
		this.value=v;
	}

	public int getValue() {
		return this.value;
	}
}
