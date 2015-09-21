import java.util.*;

public class Prep{

	public static void main(String []args){
		int N=10000000;
		int K=1;
		Integer[] newArray=new Integer [N];
		StopWatch sw=new StopWatch();
		while (K<=N){
			newArray=createArray(N,K);
			Sorting obj=new Sorting(newArray);
			newArray=(Integer[]) obj.selectionsort();
			
			System.out.println("The time needed for K="+K+" is "+sw.elapsedTime());
			K=K*2;
			System.out.println("The number of comparisons is "+obj.compareCount()+"; "+"The number of exchanges is "+obj.exchangeCount()+"; "+"The number of copies is "+obj.copyCount()+"; ");
		}
		
		
	}
	
	public static Integer[] createArray(int N,int K){//create array 
		Integer[] myArray= new Integer[N];// N-K sorted array
		Integer tmp;
		int randValue;
		Random rand=new Random();
		int[] randArray=new int[K];	//array of random values
		int[] randArrayInd=new int[K];// array of indexes of random values
		for(int i=0; i<N; i++){//create sorted array
			myArray[i]=i+1;
		}
		for(int i=0;i<K;i++){	//create an array of random values
			randValue=rand.nextInt(N-i)+i;	//pick a random value
			randArrayInd[i]=randValue;		//save a random value as an index to an index array
			randArray[i]=myArray[randValue];	//save, a corresponding to random index, value from initial array
		}
		for(int i=0;i<K;i++){		//set the positions in the array that correspond to randomly picked values to null
			myArray[randArrayInd[i]]=0;
		}
		
		int counter=0;	//counter for a number of sorted values
		for( int i=0;i<N;i++){
				if (counter==N-K)	//if the counter is equal N-K, the task is completed
					break;
				else
					if(myArray[i]!=0){	//if an element of an array is not null, then the array till this element is sorted and we can increase the counter
						myArray[counter]=myArray[i];// leaves the element at the same position if 0 hasn't appeared before, and moves the eleent of the arrayone position left, if the position before if 0
						counter++;
					}	
			}
			int j=0;// iterator for an array of random values
			for(int i=N-K; i<N;i++){
				myArray[i]=randArray[j];// add randomly picked values at the end of the initial array
				j++;
			}
		
		return myArray;
	}
	
 }
	
