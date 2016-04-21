import java.util.Random;
/**
 * This class will randomly generate values to determine whether a 
 * new plane has arrived to take off or land in a given second.
 * @author Morgan Bridges
 *
 */
public class ProbabilityEngine 
{
	private double arrivalProbability;
	private Random random;
	/**
	 * Instantiate a new engine to generate probability of plane arrivals.
	 * @param probability
	 * 		A double value that represents the likelihood of a plane arriving.
	 * Precondition
	 * 		probability is an appropriate value between 0 and 1
	 * @throws IllegalArgumentException
	 */
	public ProbabilityEngine(double probability, Random random)
	{
		if (probability < 0 || probability > 1)
			throw new IllegalArgumentException("Invalid probability.");
		else arrivalProbability = probability;
		this.random = random;
	}
	/**
	 * This method determines whether a plane has arrived during the given second.
	 * 
	 * @return
	 * 		A boolean value that represents whether a plane has arrived at a particular second.
	 * Precondition:
	 * 		arrivalProbability has been initialized to an appropriate value.
	 */		
	public boolean query()
	{
		return (random.nextDouble() < arrivalProbability);
	}
	

}
