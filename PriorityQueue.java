import java.util.ArrayList;


public class PriorityQueue {
	
	static long timeAdded = 0;
	static long waitTime = 0;
	static long numberOfJobsExecuted = 0;
	static int starvationCounter = 0;
	static int timer = 0;
	static int priorityChangeCounter =0;
	static long averageProcessWaitingTime = 0;
	static long currentSystemTime = 0;
	static long numberOfJobs = 0;


	private void insertJobsIntoArrayList(Job [] arrayJob, ArrayList <Job> aL) {
		
		numberOfJobs = arrayJob.length;
		for(int i=0;i<arrayJob.length;i++) {
    	   aL.add(i, arrayJob[i]);
       }
	}
	
//Take my array of jobs 
	public void insert(Job [] arrayJob) {
		
	    ArrayList <Job> arrayListOfJobs = new ArrayList();		
		for(int i=0;i<arrayJob.length;i++) {
			arrayJob[i].setEntryTime(i+1);
			timer++;
		}
		//Sort the array with priorities from highest(1) to lowest(40)
		//If they have the same priority then we compare entry time
		arrayJob = sortArray(arrayJob);
		insertJobsIntoArrayList(arrayJob,arrayListOfJobs);
		//Decrement the first Index of the arrayList
		
		while(arrayListOfJobs.isEmpty()==false) 
		{
			timer++;
			currentSystemTime++;
			int indexOfValueWithHighestEntryTime = 0;
			long tempTime = Long.MIN_VALUE;
			
			if(starvationCounter==30) 
			{	
				for(int i=0;i<arrayListOfJobs.size();i++) {
					timer++;
					//Means that the job was never executed
					if(arrayListOfJobs.get(i).getCurrentJobLength()==arrayListOfJobs.get(i).getJobLength()) 
					{	
						if(arrayListOfJobs.get(i).getEntryTime()>tempTime) {
							indexOfValueWithHighestEntryTime = i;
							tempTime = arrayListOfJobs.get(i).getEntryTime();
						}
					}
				}
				priorityChangeCounter++;
				arrayListOfJobs.get(indexOfValueWithHighestEntryTime).setFinalPriority(1);
				Job jobMovedToFront = arrayListOfJobs.remove(indexOfValueWithHighestEntryTime);
				arrayListOfJobs.add(0, jobMovedToFront);
				starvationCounter = 0;
			}
			
			else 
			{
				Job jobHolder = arrayListOfJobs.remove(0);
				jobHolder.decrementCurrentJobLenth();
				numberOfJobsExecuted++;
				System.out.println(jobHolder);
				int sizeOfArrayList = arrayListOfJobs.size();
				
				if(jobHolder.getCurrentJobLength()!=0){	
					if(arrayListOfJobs.isEmpty()==true) {
						arrayListOfJobs.add(jobHolder);
					}
					
					else{	
						for(int i=0;i<sizeOfArrayList;i++){
							if(arrayListOfJobs.get(i).getFinalPriority()>jobHolder.getFinalPriority()) {
								arrayListOfJobs.add(i, jobHolder);
								break;
							}
							//If you get to the end of the list then you add the job 
							if(i==arrayListOfJobs.size()-1 ) {
								arrayListOfJobs.add(jobHolder);
							}
						}
					}
				}
				
				else{
					starvationCounter++;
					long waitTime = timer-jobHolder.getEntryTime()-jobHolder.getJobLength();
					averageProcessWaitingTime += waitTime;
					
					System.out.println("Job " +jobHolder.getJobName()+" has finished processing and has a end time of "
					+ timer +" Cycles and a wait time of "+waitTime+" Cycles");
				}
			}
		}
		averageProcessWaitingTime = averageProcessWaitingTime/numberOfJobs;
		System.out.println("Program Terminated!");
		System.out.println("Current System Time(cycles): "+currentSystemTime);
		System.out.println("Total Number Of Jobs Executed: "+numberOfJobsExecuted);
		System.out.println("Average process waiting time: "+averageProcessWaitingTime);
		System.out.println("Total number of priority changes: "+priorityChangeCounter);
	}
	
 //Sorts by highest priority so 1 being highest coming first and then so forth
 //If two jobs have the same priority we compare entry time
 //If the second job came first(smaller entry Time) then it goes first in the list 	
 //N^2 time complexity 
	public Job[] sortArray(Job [] arrayJob) 
	{
		int highestPriority = 0;
		int highestPriorityIndex = 0;
		long highestPriorityEntryTime = 0;
		
		for(int i=0;i<arrayJob.length;i++) 
		{
			highestPriority = arrayJob[i].getJobPriority();
			highestPriorityIndex = i;
			highestPriorityEntryTime = arrayJob[i].getEntryTime();
			
			for(int j=i;j<arrayJob.length;j++) 
			{
				if(arrayJob[j].getFinalPriority()<highestPriority) 
				{
					highestPriority = arrayJob[j].getFinalPriority();
					highestPriorityIndex = j;
					highestPriorityEntryTime = arrayJob[j].getEntryTime();
				}
				
				else if(arrayJob[j].getFinalPriority()==highestPriority && arrayJob[j].getEntryTime()<highestPriorityEntryTime) 
				{
					highestPriority = arrayJob[j].getFinalPriority();
					highestPriorityIndex = j;
					highestPriorityEntryTime = arrayJob[j].getEntryTime();
				}
			}
			
			if(highestPriority<arrayJob[i].getJobPriority()) 
			{
				Job temp = arrayJob[i];
				arrayJob[i] = arrayJob[highestPriorityIndex];
				arrayJob[highestPriorityIndex] = temp;
			}
			
			if(highestPriority == arrayJob[i].getJobPriority() && arrayJob[i].getEntryTime()<highestPriorityEntryTime) 
			{
				Job temp = arrayJob[i];
				arrayJob[i] = arrayJob[highestPriorityIndex];
				arrayJob[highestPriorityIndex] = temp;
			} 
		}
		return arrayJob;
	}
}
