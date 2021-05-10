
public class Job implements Comparable {
	private String jobName;
	private int jobLength;  //the needed CPU cycles  1 and 70 cycles
	private int currentJobLength; //indicating the remaining length of the job at any given time 
	private int jobPriority; // indicating the initial priority , depends on when it is entered in queue  between 1(Highest) and 40(Lowest)
	private int finalPriority; // indicating the final priority
	private long entryTime; //
	private long endTime;
	private long waitTime;
	private boolean wasExecuted = false;
	private static long currentTime;
	
	public Job(String jobName, int jL, int jP) 
	{
		this.jobName = jobName;
		this.jobLength = jL;
		this.currentJobLength = jL; // upon creation currentJobLength is jobLength
		this.jobPriority = jP;
		this.finalPriority = jP; // upon creation finalPriority is also initial priority
	

	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getJobLength() {
		return jobLength;
	}
	public void setJobLength(int jobLength) {
		this.jobLength = jobLength;
	}
	public int getCurrentJobLength() {
		return currentJobLength;
	}
	public void setCurrentJobLength(int currentJobLength) {
		this.currentJobLength = currentJobLength;
	}
	public int getJobPriority() {
		return jobPriority;
	}
	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}
	public int getFinalPriority() {
		return finalPriority;
	}
	public void setFinalPriority(int finalPriority) {
		this.finalPriority = finalPriority;
	}
	public long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
		this.currentTime = entryTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}
	
	public void decrementCurrentJobLenth() 
	{
		currentJobLength -=1;
	}
	
	public static void incrementCurrentTime() 
	{
		currentTime++;
	}
	
	

	@Override
	//comparing entry time AND finalPriority
	//if two jobs have same finalPriority then we check entry time
	// returns 0 1 -1
	public int compareTo(Object temp)
	{
		Job j1 = (Job) temp;
		
		if(this.finalPriority>j1.getFinalPriority()) {
			return 1;
		}
		
		else if(this.finalPriority<j1.getFinalPriority()) {
			return -1;
		}
		
		else
			return 0;
	}
	
	public String toString() 
	{
		return("Now executing "+jobName+". Job length: "+ jobLength
				+" cycles; Current remaining length: "+currentJobLength
				+"cycles; Initial priority: "+jobPriority+" Current priority: "+finalPriority);
	}
}
