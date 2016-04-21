/**
 * This Class is for tallying and averaging the types of planes
 * @author Morgan Bridges
 *
 */
public class Tallys 
{
	private int planesLanded;
	private int planesDeparted;
	private int planesCrashed;
	private double departTimeSum;
	private double landTimeSum;
	/**
	 * This method documents a plane that has landed
	 * @param thisTime
	 * 		The time this plane spent in the queue.
	 */
	public void addLanded(int thisTime)
	{
		planesLanded++;
		landTimeSum += thisTime;
	}
	/**
	 * This method documents a plane that has departed
	 * @param thisTime
	 * 		The time this plane spent in the queue.
	 */
	public void addDeparted(int thisTime)
	{
		planesDeparted++;
		departTimeSum += thisTime;
	}
	/**
	 * This method adds a crashed plane to our tallys.
	 */
	public void addCrashed()
	{
		planesCrashed++;
		
	}
	/**
	 * A method that returns the number of planes that have landed.
	 * @return planesLanded
	 * 		
	 */
	public int getPlanesLanded() {
		return planesLanded;
	}
	/**
	 * Returns the number of planes that have departed
	 * @return
	 */
	public int getPlanesDeparted() {
		return planesDeparted;
	}
	/**
	 * Returns the number of planes that have crashed.
	 * @return planesCrashed
	 */
	public int getPlanesCrashed() {
		return planesCrashed;
	}
	/**
	 * This method returns the sum of the departure queue time.
	 * @return departTimeSum
	 */
	public double getDepartTimeSum() {
		return departTimeSum;
	}
	/**
	 * This method returns the sum of the landing queue time.
	 * @return landTimeSum
	 */
	public double getLandTimeSum() {
		return landTimeSum;
	}
	/**
	 * This method returns the average time a plane waited in the departure queue.
	 * @return double value representing average time a plane waited to depart.
	 */
	public double avgDepartingTime()
	{
		if(planesDeparted != 0)
			return((double)(departTimeSum)/(double)(planesDeparted));
		else return 0;
	}
	/**
	 * This method returns the average time a plane waited in the landing queue.
	 * @return double value representing the time a plane spent in the landing queue.
	 */
	public double avgLandingTime()
	{
		if(planesLanded != 0)
			return((double)(landTimeSum)/(double)(planesLanded));
		else return 0;
	}
}
