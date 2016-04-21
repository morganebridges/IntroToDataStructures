import java.util.Queue;

/**
 * This class simulates a single runway. It will keep track of the availability of the runway for arriving and 
 * departing air traffic.
 * @author Morgan Bridges
 *
 */
public class Runway {
	private int secondsForTraffic;
	private int secondsLeft;
	private String trafficType;
	/**
	 * Instantiates a runway object.
	 * @param time
	 * 		Time required for departure or landing.
	 */
	public Runway(int time)
	{
		secondsForTraffic = (time);
		secondsLeft = 0;
		trafficType = null;
	}
	/**
	 * This method determines whether the runway is being used or not.
	 * @return
	 * 		Whether a plane is currently on the runway.
	 */
	public boolean isBusy()
	{
		return (secondsLeft > 0);
	}
	/**
	 * This method reduces the time left by 1 second.
	 * Precondition:
	 * 
	 * Postcondition:
	 * 		The runway's busy times has been reduced.
	 */
	public void reduceTime()
	{
		if(secondsLeft > 0)
			--secondsLeft;
	}
	/**
	 * This allows a new airplane to take off or land on the runway.
	 * Precondition
	 * 		secondsLeft == 0
	 * Postcondition
	 * 		The timer is reset for another plane.
	 */
	public void useRunway(String trafficType)
	{
		if (secondsLeft > 0)
			System.out.println("This runway is busy!");
		else 
		{
			secondsLeft = secondsForTraffic;
			this.trafficType = trafficType;
		}
	}
	/**
	 * A method to return type of traffic (landing or departing) that is using this.
	 * @return trafficType
	 * 		
	 */
	public String getTrafficType()
	{
		return trafficType;
	}
	/**
	 * A method to return the time it takes each plane to complete using the runway.
	 * @return secondsForTraffic
	 * 		
	 */
	public int getSecondsForTraffic() {
		return secondsForTraffic;
	}
	/**
	 * A method to set the time it takes each plane to complete using the runway.
	 * @param secondsForTraffic
	 * 		
	 */
	public void setSecondsForTraffic(int secondsForTraffic) {
		this.secondsForTraffic = secondsForTraffic;
	}
	/**
	 * A method to return the time a plane has left to complete using the runway.
	 * @return secondsLeft
	 * 		
	 */
	public int getSecondsLeft() {
		return secondsLeft;
	}
	/**
	 * A method to set type of traffic (landing or departing) that is using this runway.
	 * @param trafficType
	 * 		
	 */
	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

}
