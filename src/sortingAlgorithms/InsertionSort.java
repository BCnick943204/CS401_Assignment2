package sortingAlgorithms;

public class InsertionSort implements SortingAlgorithm{

	@Override
	public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}
	  /***************************************************************************
	    *  Helper sorting functions.
	    ***************************************************************************/
	    
	    // is v < w ?
	    private static boolean less(Comparable v, Comparable w) {
	        return v.compareTo(w) < 0;
	    }

	    // exchange a[i] and a[j]
	    private static void exch(Object[] a, int i, int j) {
	        Object swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }

	    
	   /***************************************************************************
	    *  Check if array is sorted - useful for debugging.
	    ***************************************************************************/
	    private static boolean isSorted(Comparable[] a) {
	        return isSorted(a, 0, a.length);
	    }

	    // is the array a[lo..hi) sorted
	    private static boolean isSorted(Comparable[] a, int lo, int hi) {
	        for (int i = lo + 1; i < hi; i++)
	            if (less(a[i], a[i-1])) return false;
	        return true;
	    }

}
