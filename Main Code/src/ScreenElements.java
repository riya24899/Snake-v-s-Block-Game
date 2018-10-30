
public class ScreenElements {
	
	private Object [][] ElementPos= new Object[5][9];
	
	public void Add(Token T) {
		
		if (T instanceof Coin || T instanceof Magnet) {
			
			int i=T.GetPosition().Getx();
			int j=T.GetPosition().Gety();
			
			ElementPos[i][j]=T;
			
			T.GetPosition().Setx((i*50)-25);
			T.GetPosition().Sety((j*50)-25);
			
		}

	}

}
