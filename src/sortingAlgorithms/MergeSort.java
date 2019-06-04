package sortingAlgorithms;

public class MergeSort implements SortingAlgorithm{

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
	@Override
	public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);		
	}

	@Override
	public String getName() {
		return "Merge Sort";
	}
	   /***************************************************************************
	    *  Helper sorting function.
	    ***************************************************************************/
	    
	    // is v < w ?
	    private static boolean less(Comparable v, Comparable w) {
	        return v.compareTo(w) < 0;
	    }
	        
	   /***************************************************************************
	    *  Check if array is sorted - useful for debugging.
	    ***************************************************************************/
	    private static boolean isSorted(Comparable[] a) {
	        return isSorted(a, 0, a.length - 1);
	    }

	    private static boolean isSorted(Comparable[] a, int lo, int hi) {
	        for (int i = lo + 1; i <= hi; i++)
	            if (less(a[i], a[i-1])) return false;
	        return true;
	    }


	   /***************************************************************************
	    *  Index mergesort.
	    ***************************************************************************/
	    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
	    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

	        // copy to aux[]
	        for (int k = lo; k <= hi; k++) {
	            aux[k] = index[k]; 
	        }

	        // merge back to a[]
	        int i = lo, j = mid+1;
	        for (int k = lo; k <= hi; k++) {
	            if      (i > mid)                    index[k] = aux[j++];
	            else if (j > hi)                     index[k] = aux[i++];
	            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
	            else                                 index[k] = aux[i++];
	        }
	    }
	

    
}


