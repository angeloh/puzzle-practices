

public class computeSum{
	public computeSum(){
		  
	}
	public static int [] computeSumByK(int arraySize){
		
		int [] S = new int[arraySize];
		S[0] = 1;
		for (int k = 0; k < arraySize; k++) {
			S[k+1] = 1 / ((k * S[k]) / 2);
		}
		
		return S;		
	}
}


