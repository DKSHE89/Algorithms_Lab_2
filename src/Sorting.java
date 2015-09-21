import java.util.*;
import java.lang.*;

public class Sorting{
	
		double exchN=0;
		double compN=0;
		double copyN=0;
		
		private Comparable [] array;
		private int N;
		public Sorting(Comparable[] myarray){//class constructor
			N=myarray.length;
			this.array=myarray;
				
		}
	
		public Comparable[] insertsort(){// insertsorting function
		for (int i=0; i<N; i++){
			for (int j=i; j>0; j--){
				if (less(array[j] ,array[j-1])){
					exch(array,j,j-1);
				}
				else break;
			}
		}
		return array;
		}
		
		private boolean less ( Comparable v, Comparable w)//comparing two values
		{ 
			compN++;
			boolean less=false;
			int result=v.compareTo(w); 
			if (result<0)
				less=true;
			return less;
			}
		
		private void exch(Comparable[] a,int i,int j)//exchanging places of the array elements
		{
			Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		exchN++;
		}
		
		public Comparable[] selectionsort()// selectsorting function
		{
			for (int i=0; i<N; i++)
			{
				int min = i;
				for (int j=i+1; j < N; j++)
					if ( less(array[j] ,array[min]) )
						min = j;
				exch(array,i,min);
			}
			return array;
		}
		
		public Comparable[] shellsort(){//shellsorting function
			
			 for (int gap = array.length / 2; gap > 0; gap /= 2) {
			        // do the insertion sort
			        for (int i = gap; i < array.length; i++) {
			            Comparable val = array[i];
			            int j;
			            for (j = i; j >= gap && array[j - gap].compareTo(val) > 0; j -= gap) {
			            	compN++;
			                array[j] = array[j - gap];
			            }
			            array[j] = val;
			        }
			    }
			return array;
		}
		
		public void merge ( Comparable a[] , Comparable aux[] ,int L, int M, int R)//merging two arrays
				{
					for (int k = L; k<R; k++) {
						aux[k] = a[k]; // copy
						copyN++;
					}
						int i=L, j=M;
							for (int k=L; k<R; k++) { // merge
								if (i>=M)
									{
										a[k] = aux[j++]; // left empty
										
									}
								else if (j>=R) 
									{
										a[k] = aux[i++]; // right empty
										
									}
								else if (less(aux[j],aux[i])){
										a[k] = aux[j++];
										
								}
								else
									{
									a[k] = aux[i++];
									
									}
				}
				}
		public Comparable[] mergesort( )//mergesorting function
		{
		Comparable[] aux = new Comparable[array.length];
		sort(array,aux,0 ,array.length);
		return array;
		}
		
		private void sort(Comparable a[],Comparable aux[] ,int L, int R)//sorting parts of the array
		{
			if (R-L<=1) return; // less than 2 elements
				int m = L+(R-L)/2;
					sort(a,aux,L,m);
					sort(a,aux,m,R);
					merge(a,aux,L,m,R);
		}
		
		public Comparable[] quicksort()//quicksorting function
		{
			shuffle(array);
			sort(array,0 ,array.length -1);
			return array;
		}
		protected void shuffle(Comparable a[])//shuffle the array
		{
			Random rnd = new Random();
			for (int i=0; i<a.length; i++)
				exch(a,i,rnd.nextInt(a.length));
		}
		protected void sort(Comparable a[] , int L, int R)//sorting parts of the array
		{
			if (R-L<=0) return; // less than 2 elements
				int m = partition(a,L,R);
			sort(a,L,m-1);
			sort(a,m+1 ,R);
		}
		
		protected int partition ( Comparable a[] , int L, int R)//splitting the array
		{
			int i = L-1;
			int j = R;
			while (true)
			{
					while (less(a[++i] ,a[R]))// find item on left
						if ( i==R ) break; // to swap
			
							while (less(a[R] ,a[ - -j]))// find item on right
								if ( j==L ) break; // to swap
									if ( i>=j ) break; // do pointers cross?
										exch(a,i,j); // otherwise do swap
			}
			exch(a,i,R); // final swap
			return i; // return index of cross point
		}
		

		private void merge_bottomUp ( Comparable a[] , Comparable aux[] ,int L, int M, int R){//merge parts of the array		{
			for (int i = L; i<M; i++) 
				{
					aux[i] = a[i]; // copy
					copyN++;
				}
				for (int j = M; j<R; j++) {
						aux[j] = a[M+R-1 -j]; // copy
						copyN++;
				}
					int i=L, j=R-1;
						for (int k=L; k<R; k++) { // merge
							if (less(aux[j],aux[i])) {
								a[k] = aux[j--];
								
							}
							else {
								a[k] = aux[i++];
								
							}
						}
		}
		
		public Comparable [] bottomup_mergesort()//bottom-up mergesorting function
		{
			Comparable[] aux = new Comparable[N];
				for ( int m=1; m<N; m*=2 ) // m = half size of subarray
					for (int i=0; i<N-m; i+=2*m) // go through all subarrays
						merge(array,aux,i,i+m,Math.min(i+2*m,N));
			return array;
		}
		
		public double copyCount(){//get the number of copies
			return copyN;
		}
		
		public double exchangeCount(){//get the number of exchanges
			return exchN;
		}
		public double compareCount(){//get the number of comparisons
			return compN;
		}

}
