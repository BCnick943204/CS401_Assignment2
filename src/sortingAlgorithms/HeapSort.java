package sortingAlgorithms;

public class HeapSort implements SortingAlgorithm{

	@Override
	public void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n/2; k >= 1; k--)
            sink(a, k, n);
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
	}

	@Override
	public String getName() {
		return "Heap Sort";
	}
	
	   /***************************************************************************
	    * Helper functions to restore the heap invariant.
	    ***************************************************************************/

	    private static void sink(Comparable[] pq, int k, int n) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(pq, j, j+1)) j++;
	            if (!less(pq, k, j)) break;
	            exch(pq, k, j);
	            k = j;
	        }
	    }

	   /***************************************************************************
	    * Helper functions for comparisons and swaps.
	    * Indices are "off-by-one" to support 1-based indexing.
	    ***************************************************************************/
	    private static boolean less(Comparable[] pq, int i, int j) {
	        return pq[i-1].compareTo(pq[j-1]) < 0;
	    }

	    private static void exch(Object[] pq, int i, int j) {
	        Object swap = pq[i-1];
	        pq[i-1] = pq[j-1];
	        pq[j-1] = swap;
	    }


}
