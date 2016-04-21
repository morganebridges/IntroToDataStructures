import java.util.*;
public class AirportSimulation 
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the airport simulator!");
		runSimulation();
		
		
	}
	public static void runSimulation()
	{
		Scanner input = new Scanner(System.in);
		char runAgain = 'y';
		while (runAgain != 'n')
		{
			//create the airport
			System.out.println("Please enter the running time of the simulation: ");
			int runTime = input.nextInt();
			
			System.out.println("Please enter the amount of time it takes a plane to land: ");
			int landTime = input.nextInt();
			
			System.out.println("Please enter the amount of time it takes a plane to crash: ");
			int crashTime = input.nextInt();
			
			System.out.println("Please enter the the probability (0 < x < 1 ) that a plane arrives"
					+ "in either landing or departure queues: ");
			double probability = input.nextDouble();
			Airport airport = new Airport(runTime, crashTime, probability, landTime);
			// run the simulation
			for(int i = 0; i <= airport.simulationTime; i++)
			{
				airport.advanceTime();
			}
			airport.printResults();
			
			System.out.println("Would you like to run another simulation? y/n ");
			runAgain = input.next().charAt(0);
				
		}
	}
	
}
