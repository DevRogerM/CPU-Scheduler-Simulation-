import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Driver {

	public static void main(String[] args)
	{
		
		int nb_jobs = 50;
		String jobTitle = "";
		
		Random ran_JobLength = new Random();
		int range_JobLength = 70 - 7 + 1;
		int JobLength;
		
		Random ran_JobPriority = new Random();
		int range_Priority = 40 - 1 + 1;
		int JobPriority;
		
		
		
		

		Job jobArray[] = new Job[nb_jobs];

		for(int i=0;i<jobArray.length;i++) {
			JobLength =  ran_JobLength.nextInt(range_JobLength) + 7;
			JobPriority = ran_JobPriority.nextInt(range_Priority) + 1;
			
			
			jobArray[i] = new Job("Job"+(i+1),JobLength,JobPriority);
		}
		
		PriorityQueue pq = new PriorityQueue();
		pq.insert(jobArray);
		
	}
	
public static void mergeElements(int a1[], int a2[]) {
		
		int i = 0;
		int j = 0;
		int index = 0;
		int[] newArray = new int [8];
		
		//Compare the two arrays left to right
		//When one array has all its elements given to newArray
		//Then we continue from the next array
		//We have an index following the newArray to keep track of our positioning
		
		while( i<a1.length && j<a2.length)
		{
			if(a1[i]<a2[j])
			{
				newArray[index] = a1[i];
				i++;
				index++;
			}
			else
			{
				newArray[index] = a2[j];
				j++;
				index++;
			}
		}
		//Getting to here means either a1 OR a2 has had their elements transported
		//Assuming that a1 did not have all its elements transported
		while(i<a1.length) {
			newArray[index] = a1[i];
			i++;
			index++;
		}
		//Assuming a2 did not have all its elements transported
		while(j<a2.length) {
			newArray[index] = a2[j];
			j++;
			index++;
		}	
		//To prove the algorithm worked we display the new Array
		for(int display=0;display<newArray.length;display++) {
			System.out.print(newArray[display]);
		}	
	}
}
