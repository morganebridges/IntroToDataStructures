import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * This class simulates an airport.
 * @author Morgan Bridges
 *
 */
public class Airport {
	Tallys tallys;
	Queue<Integer> landingQueue = new LinkedList<Integer>();	
	int planesLanded;
	Queue<Integer> departingQueue = new LinkedList<Integer>();
	int planesDeparted;
	
	ProbabilityEngine arrivalEngine;
	//the amount of time to be simulated total
	public int simulationTime;
	//the amount of time elapsed in this simulation
	private int timeElapsed;
	//the amount of seconds a plane can wait to land before crashing
	private int crashTime;
	
	
	Runway runway;
	
	/**
	 * 
	 * @param totalTime
	 * 		Total time for the simulation.
	 * @param timeToCrash
	 * 		Time a plane can wait to land before crashing.
	 * @param probability
	 * 		Probability a plane arrives for landing or departure.
	 * @param runTime 
	 * 		The amount of time it takes for a plane to use the runway.
	 */
	public Airport(int totalTime, int timeToCrash, double probability, int runTime)
	{
		simulationTime = totalTime;
		crashTime = timeToCrash;
		runway = new Runway(runTime);
		tallys = new Tallys();
		arrivalEngine = new ProbabilityEngine(probability, new Random(1234567));
	}
	/**
	 * This method will handle the advancement of time and the adding of planes to the two Queues.
	 * precondition 
	 * 		The calling Airport has been instantiated with all appropriate data fields initialized.
	 * postcondition
	 * 		The time of this simulation will be advanced one minute.
	 */
	public void advanceTime()
	{
		if (timeElapsed < simulationTime)
		{
			timeElapsed++;
			runway.reduceTime();
			//check for arrivals
			
			
			if(arrivalEngine.query())
				{
					//System.out.println("Entering Landing Queue" + timeElapsed);
					landingQueue.add(timeElapsed);
				}
			if(arrivalEngine.query())
				{
					//System.out.println("Entering Departing Queue" + timeElapsed);
					departingQueue.add(timeElapsed);
				}
			
			checkCurrentActivity();
			//count the plane using the runway at the end of the simulation in totals.
			if (timeElapsed == simulationTime)
			{
				Integer next = 0;
				if (runway.getTrafficType().equals("landing"))
				{
					next = landingQueue.remove();
					runway.setTrafficType(null);
					tallys.addLanded(timeElapsed - next - (runway.getSecondsForTraffic() - runway.getSecondsLeft()));
					//System.out.println("End landing " + timeElapsed + " " + next );
				}
				else if (runway.getTrafficType() == "departing")
				{
					next = departingQueue.remove();
					tallys.addDeparted(timeElapsed - next -  (runway.getSecondsForTraffic() - runway.getSecondsLeft()));
					runway.setTrafficType(null);
					//System.out.println("End arriving: " + timeElapsed+ " " + next );
				}
			}
		}
	}
	/**
	 * This method will control the removal of planes from the Queues and the incrementing of analysis variables
	 * precondition
	 * 		A valid simulation is running within the bounds of its time.
	 */
	private void checkCurrentActivity()
	{
		Integer next;
		
		/*
		 * This block handles a plane that has completed using the runway and now needs to be removed from
		 * the Queue.
		*/
		
		
		
		if ((runway.getTrafficType() != null) && !runway.isBusy())
		{
			if (runway.getTrafficType().equals("landing"))
			{
				next = landingQueue.remove();
				runway.setTrafficType(null);
				tallys.addLanded(timeElapsed - next - runway.getSecondsForTraffic());
				//System.out.println("Plane landing " + timeElapsed + " " + next );
			}
			else if (runway.getTrafficType() == "departing")
			{
				next = departingQueue.remove();
				tallys.addDeparted(timeElapsed - next - runway.getSecondsForTraffic());
				runway.setTrafficType(null);
				//System.out.println("Plane departing: " + timeElapsed+ " " + next );
			}
		}
		/*
		 * This block deals with the assignment of planes to the runway, favoring the landing Queue.
		 */
		if((runway.getTrafficType() == null) && !runway.isBusy())
		{
			if(landingQueue.size() > 0)
			{
				//this statement pops the crashing planes off of the queue
				while (!landingQueue.isEmpty() && timeElapsed - landingQueue.peek() > crashTime)
				
				{
					landingQueue.remove();
					tallys.addCrashed();
				}
				
				if(landingQueue.size() > 0)
					runway.useRunway("landing");
			}
			else if(departingQueue.size() > 0)
			{
				runway.useRunway("departing");
			}
		}
		
	}
	/**
	 * A method for printing the results of our simulation.
	 */
	public void printResults()
	{
		System.out.println("Planes landed: " + tallys.getPlanesLanded());
		System.out.println("Planes departed: " + tallys.getPlanesDeparted());
		System.out.println("Planes crashed: " + tallys.getPlanesCrashed());
		if(tallys.avgLandingTime() != 0)
			System.out.println("Average time in landing queue: " + tallys.avgLandingTime());
		else System.out.println("No average time in landing Queue (no planes landed).");
		
		if(tallys.avgDepartingTime() != 0)
			System.out.println("Average time in departure queue: " + tallys.avgDepartingTime());
		else System.out.println("No average time in departure Queue (no planes departed).");
		
	}
}
