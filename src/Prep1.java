import java.util.Random;

public class Prep1{

	public static void main(String []args){
		int N=1000;
		int K=1000;
		Integer[] newArray=new Integer [N];
		StopWatch sw=new StopWatch();
		double execTime=0;
		while(execTime<300){
			newArray=createArray(N,K);
			Sorting obj=new Sorting(newArray);
			newArray=(Integer[]) obj.quicksort();
			System.out.println("The time needed for K="+K+" is "+sw.elapsedTime());
			K=K*2;
			N=N*2;
			System.out.println("The number of comparisons is "+obj.compareCount()+"; "+"The number of exchanges is "+obj.exchangeCount()+"; "+"The number of copies is "+obj.copyCount()+"; ");
			execTime=sw.elapsedTime();
		}
		
		
	}
	
	/*static public Integer[] createArray(int N, int K){
		Integer[] myArray= new Integer[N];
		int transNumber=0;
		Random rand=new Random();
		Integer [] randArray=new Integer[K];
		for(int i=0;i<N;i++){
			myArray[i] = i;
		}
		for(int j=0;j<K;j++){
			boolean found = false;
			do  {
			  randArray[j]=rand.nextInt(myArray.length);
			  for(int m=0;m<K;m++){
				  if(randArray[j]==randArray[m])
					found=true;  
					  
			  }
			} while (!found);  
			if(j==0){
				for(int i=randArray[j]-1; i<N-1;i++){
					myArray[i]=myArray[i+1];
				}
			}else{
				transNumber=compareNumbers(randArray,randArray[j],K);
					for(int i=randArray[j]-transNumber-1; i<N;i++){
						if(i==N-1)
							myArray[N-1]=myArray[N-1];
						else
							myArray[i]=myArray[i+1];
					}
				
			}

			//System.out.println(randArray[j]);
			myArray[N-1]=randArray[j];
			for(int i=0;i<N;i++){
				//System.out.print(myArray[i]+" ");
			}
			System.out.println();
	}
		
		return myArray;
	}
	
	static private int compareNumbers(Integer[] array,Integer numbToCompare,int dim){
		int N=dim;
		int transCount=0;
		for(int i=0;i<N;i++){
			if(array[i]!=null)
				if(numbToCompare>array[i])
					transCount++;
		}
		return transCount;
	}

}*/
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
	
