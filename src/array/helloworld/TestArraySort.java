package array.helloworld;

public class TestArraySort {
	public static void main(String[] args){
 		int[] arr = new int[]{ 5, 2, 3, 4, 8, 10, 6};
 		/*
		for(int i = 0; i < arr.length - 1; i++ ){
			for(int j = i, k = j + 1; k < arr.length; k++){
				if(arr[j] > arr[k]){
					int temp = arr[j];
					arr[j] = arr[k];
					arr[k] = temp;
				}
			}
		}
 		*/
 		for(int i = 0; i < arr.length - 1; i++){
 			for(int k = i + 1; k < arr.length; k++){
 				if(arr[i] > arr[k]){
					int temp = arr[i];
					arr[i] = arr[k];
					arr[k] = temp;
 				}
 			}
 		}
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	}
	
}
