
public class ScreenElements {
	
	private Object [][] ElementPos= new Object[5][9];
	
	public void Add(Token T) {
		
		if (T instanceof Token) {
			
			int i=T.GetPosition().Getx();
			int j=T.GetPosition().Gety();
			
			ElementPos[i-1][j-1]=T;
			
			T.GetPosition().Setx((i*70)-35);
			T.GetPosition().Sety((j*70)-35);
			
		}

	}

}
