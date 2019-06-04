package sortingAlgorithms;

//Adapted from pseudocode found in this article:
//https://en.wikipedia.org/wiki/Bubble_sort

public class BubbleSort implements SortingAlgorithm{

	@Override
	public void sort(Comparable[] a) {

		boolean swap = true;
		
		while(swap) { //continue until the array is passed through with no swaps
			
			swap = false;
			
			for(int i=1; i<a.length;i++) {
				if(a[i].compareTo(a[i-1]) < 0) {
					swap(a, (i-1),i);
					swap = true;
				}
			}
			
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Bubble Sort";
	}
	
	private static void swap(Comparable[] a, int i1, int i2) {
		Comparable temp = a[i2];
		a[i2] = a[i1];
		a[i1] = temp;
	}

}
