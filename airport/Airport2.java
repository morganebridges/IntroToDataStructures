import java.util.LinkedList;
	import java.util.Queue;
public class Airport2 {
	
	Queue<Integer> landingQueue = new LinkedList<Integer>();
	Queue<Integer> departingQueue = new LinkedList<Integer>();
	private int simulationTime;
	private int elapsedTime;
	private Runway runway;
	private ProbabilityEngine probabilityEngine;
	private int timeToCrash;
	Tallys tallys = new Tallys();
	int next;
	
	Airport2(int simulationTime, int timeOnRunway, double probability, int timeToCrash)
	{
		this.simulationTime = simulationTime;
		probabilityEngine = new ProbabilityEngine(probability);
		this.timeToCrash = timeToCrash;
		runway = new Runway(timeOnRunway);
		elapsedTime = 0;
	}
	public void advanceTime()
	{
		
		elapsedTime++;
		//method call to check queue arrival
		checkArrivals();
		//check to see if the runway is busy
		if(!runway.isBusy() && (landingQueue.size() > 0))
		{
			if (runway.getTrafficType().equals("landing"))
			{
				next = landingQueue.remove();
			}
			
		}
		
		
		
	}
	/**
	 * This method determines what plane will be able to use the runway next.
	 */
	
	/**
	 * This method checks to see if any planes have arrived in either landing or departure queues.
	 * precondition
	 * 		Time has elapsed since the simulation was started.
	 * postcondition
	 * 		If a query returns true for either conditional, a plane will be added to the appropriate queue.
	 */
	public void checkArrivals()
	{
		if (probabilityEngine.query())
		{
			landingQueue.add(elapsedTime);
		}
		if (probabilityEngine.query())
		{
			departingQueue.add(elapsedTime);
		}
	}
}
