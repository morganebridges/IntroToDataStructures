import java.util.Scanner;
/**
 * This is a test class for the FinalTable hashtable class. For the purposes of this demo,
 * clarity, and simplicity, I have chosen to use elements as the keys so it is easy to see where 
 * they should be falling given our chosen hash function and table rules. Valid powers of two must
 * be used for table size.
 * @author Morgan
 *
 */
public class HashDemo {
	public static void main(String[] args)
	{
		//Initialize to size 10, we will discard this object for the real one (power of 2).
		FinalTable table1 = new FinalTable(10);
		Scanner input = new Scanner(System.in);
		
		System.out.println("*****_____^^^^^_____*****");
		System.out.println(" Hash Map Demonstration");
		System.out.println("*****_____^^^^^_____*****");
		System.out.println("Please enter a valid power of 2 for the length of your hashmap: ");
		int mapLength;
	    boolean validLength = false;
	    while(validLength == false)
		{
	    	mapLength = input.nextInt();
	    	if((mapLength & (mapLength-1)) == 0)
	    	{	
	    		table1 = new FinalTable(mapLength);
	    		validLength = true;
	    	}
	    	else 
	    		System.out.println("Not a power of two, please try again.");
	    	
		}
	    input.nextLine();
	    Integer nextItem;
	   while((table1.size() < table1.length()))
	    
	    {
	    	System.out.println("Please enter a value to put in your table.");
	    	nextItem = new Integer(input.nextInt());
	    	table1.put(nextItem, nextItem);
	    	System.out.println();
	    	
	    	
	    	System.out.println("Do you want to enter another value? y/n");
	    	if(input.next().charAt(0) == 'n')
	    	{
	    		System.out.println("Your Table:");
	    		table1.printData();
	    		System.out.println("*****_____^^^^^_____*****");
	    		break;
	    	}	
	    }	
	   while(table1.size() > 0)
	   {
		   System.out.println("Check to see if a key value is contained in the table :");
		   int searchValue = input.nextInt();
		   boolean contains = table1.containsKey(searchValue);
		   if(contains)
			   System.out.println(searchValue + " is in the table.");
		   else System.out.println(searchValue + " is not in the table.");
		   System.out.println("Do you want to search for another value? y/n");
	    	if(input.next().charAt(0) == 'n')
	    		break;
	    		
	    		
		   
		   
	   }
	   System.out.println("*****_____^^^^^_____*****");
	   while(table1.size() > 0)
	   {
		   System.out.println("Do you want to remove a value from the table? y/n");
		   if(input.next().charAt(0) == 'n')
	    		break;
		   System.out.println("Choose a key value to remove from the table: ");
		   int removeValue = input.nextInt();
		   Integer removeKey = new Integer(removeValue);
		   Object removed = table1.remove(removeKey);
		   if(removed == null)
			   System.out.println("The value was not contained in the table!");
		   else System.out.println(removeValue + " was removed from the table.");
		   System.out.println();
		   System.out.println("Your table:");
		   table1.printData();
	   }
	   System.out.println("*****_____^^^^^_____*****"); 
	   System.out.println("Thank you for using this hash table application!");
		
		
	}
}
