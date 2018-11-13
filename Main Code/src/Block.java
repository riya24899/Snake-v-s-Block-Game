import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Block extends Obstruction {
	
	private int value;
	private Rectangle BlockRec;
	private Text BlockText;
	
	public Block (int a, int b, int v, Rectangle R, Text T) {
		super(a,b,true);
		this.value=v;
		this.BlockRec=R;
		this.BlockText=T;
	}
	
	public Block (int a, int b, int v) {
		super(a,b,true);
		this.value=v;
	}
	
	
	public void setValue(int v) {
		this.value=v;
	}

	public int getValue() {
		return this.value;
	}
	
	public Rectangle GetRec() {
		return this.BlockRec;
	}
	
	public void SetRec(Rectangle R) {
		this.BlockRec=R;
	}
	
	public Text GetText() {
		return this.BlockText;
	}
	
	public void SetText(Text T) {
		this.BlockText=T;
	}
}
