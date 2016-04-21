/**
 * This is A test Class developed to create and test a balanced integer B-Tree implemented with arrays.
 * Note: For the purposes of testing, a number of normally private methods have been switched to public so 
 * that specific demonstrations could be made in this class.
 * @author Morgan Bridges
 *
 */
public class BalancedSetDriver2 {
	public static void main(String[] args)
	{
		IntBalancedSet2 set = new IntBalancedSet2();
		IntBalancedSet2 set2 = new IntBalancedSet2();
		
		/*//Set 2 will be a controlled subset populated manually based on the b-tree on page 547 of the text
		set2.data[0] = 10;
		set2.data[1] = 28;
		set2.childCount = 3;
		set2.dataCount = 2;
		
		
		set2.subset[0] = new IntBalancedSet2();
		set2.subset[0].data[0] = 2;
		set2.subset[0].data[1] = 5;
		set2.subset[0].dataCount = 2;
		set2.subset[0].childCount = 2;
		
		set2.subset[0].subset[0] = new IntBalancedSet2();
		set2.subset[0].subset[1] = new IntBalancedSet2();
		set2.subset[0].subset[2] = new IntBalancedSet2();
		set2.subset[0].subset[0].data[0] = 0;
		set2.subset[0].subset[0].data[1] = 1;
		set2.subset[0].subset[0].dataCount = 2;
		
		
		set2.subset[0].subset[1].data[0] = 3;
		set2.subset[0].subset[1].data[1] = 4;
		set2.subset[0].subset[1].dataCount = 2;
		
		set2.subset[0].subset[2].data[0] = 6;
		set2.subset[0].subset[2].data[1] = 7;
		set2.subset[0].subset[2].data[2] = 8;
		set2.subset[0].subset[2].dataCount = 3;
				
	    set2.subset[1] = new IntBalancedSet2();	
		set2.subset[1].subset[0] = new IntBalancedSet2();
		set2.subset[1].subset[1] = new IntBalancedSet2();
		set2.subset[1].subset[2] = new IntBalancedSet2();
		set2.subset[1].subset[3] = new IntBalancedSet2();
		set2.subset[1].subset[4] = new IntBalancedSet2();
		set2.subset[1].dataCount = 3;
		set2.subset[1].childCount = 3;
		set2.subset[1].data[0] = 13;
		set2.subset[1].data[1] = 16;
		set2.subset[1].data[2] = 19;
		//set2.subset[1].data[3] = 22;
		
		set2.subset[1].subset[0].dataCount = 2;
	    set2.subset[1].subset[0].data[0] = 11;
		set2.subset[1].subset[0].data[1] = 12;
		
		set2.subset[1].subset[0].dataCount = 2;
		set2.subset[1].subset[0].data[0] = 14;
		set2.subset[1].subset[0].data[1] = 15;
		
		set2.subset[1].subset[1].dataCount = 2;
		set2.subset[1].subset[1].data[0] = 17;
		set2.subset[1].subset[1].data[1] = 18;
		
		set2.subset[1].subset[2].dataCount = 2;
		set2.subset[1].subset[2].data[0] = 20;
		set2.subset[1].subset[2].data[1] = 21;
		
		set2.subset[1].subset[4].dataCount = 3;
		set2.subset[1].subset[4].data[0] = 23;
		set2.subset[1].subset[4].data[1] = 24;
		set2.subset[1].subset[4].data[2] = 26;
		
		set2.subset[2] = new IntBalancedSet2();
		set2.subset[2].data[0] = 33;
		set2.subset[2].childCount = 2;
		set2.subset[2].dataCount = 1;
		
		set2.subset[2].subset[0] = new IntBalancedSet2();
		set2.subset[2].subset[0].dataCount = 2;
		set2.subset[2].subset[0].data[0] = 31;
		set2.subset[2].subset[0].data[1] = 32;
		
		set2.subset[2].subset[1] = new IntBalancedSet2();
		set2.subset[2].subset[1].dataCount = 2;
		set2.subset[2].subset[1].data[0] = 34;
		set2.subset[2].subset[1].data[1] = 35;
		
		//Test the cloning method and create a before and after test of the merge and transfer methods.
		IntBalancedSet2 setForTransferRight = new IntBalancedSet2();
		setForTransferRight = (IntBalancedSet2)set2.clone();
		
		IntBalancedSet2 setForTransferLeft = (IntBalancedSet2)set2.clone();
		IntBalancedSet2 setForMergeNext = (IntBalancedSet2)set2.clone();
		System.out.println("Test of Transfer Right Method");
		
		//Show the original Set
		setForTransferRight.print(1);
		System.out.println("**************");
		setForTransferRight.transferRight(2);
		setForTransferRight.print(1);
		
		
        System.out.println("**************");
        System.out.println("Test of Transfer left Method");
        
        //Show the original set
        setForTransferLeft.print(2);
        System.out.println("**************");
        setForTransferLeft.transferLeft(0);
		setForTransferLeft.print(2);
		System.out.println("**************");
		System.out.println("Test of merge next subset method");
		
		//note that set2 (the originally cloned object) is still unchanged by the deep cloning process.
		set2.print(1);
		System.out.println("**************");
		setForMergeNext.subset[1].dataCount--;
		setForMergeNext.mergeWithNextSubset(1);
		
		setForMergeNext.print(2);
		System.out.println("**************");
		
		//add/remove excess/shortage tests to see if the tree remains balanced.
		System.out.println("Add/Remove, excess/shortage tests");
		System.out.println("**************");
		set.print(2);
		//This series of adds and removes shows the use of excess and shortage methods.
		
		//These are more demonstrations of a new set manipulated using only add and remove public methods.
		//The set always stays balanced and in adherence with B-Tree rules.
		*/
		
		IntBalancedSet2 set4 = new IntBalancedSet2();
		set4.add(2);
		set4.add(5);
		set4.add(0);
		set4.add(1);
		set4.add(3);
		set4.add(4);
		set4.add(6);
		set4.add(7);
		set4.add(8);
		
		set4.add(10);
		set4.add(28);
		set4.add(13);
		set4.add(16);
		set4.add(19);
		set4.add(22);
		set4.add(11);
		set4.add(12);
		set4.add(14);
		set4.add(15);
		set4.add(17);
		set4.add(18);
		set4.add(20);
		set4.add(21);
		set4.add(23);
		set4.add(24);
		set4.add(26);
		
		set4.add(33);
		set4.add(31);
		set4.add(32);
		set4.add(34);
		set4.add(35);
		
		set4.print(2);
		System.out.println("Remove a root from the first subset.(12)");
		set4.remove(12);
		set4.print(3);
		System.out.println("**************");
		System.out.println("Remove both values from a leaf (28,31)");
		set4.remove(28);
		set4.remove(31);
		set4.print(3);
		System.out.println("**************");
		System.out.println("Remove nodes from the root.");
		set4.remove(18);
		set4.remove(19);
		set4.print(3);
	IntBalancedSet2 set5 = new IntBalancedSet2();
		set5.add(56);
		set5.add(7);
		set5.add(2);
		set5.add(8);
		set5.add(66);
		set5.add(63);
		set5.add(71);
		set5.add(93);
		set5.add(82);
		set5.print(1);
		
		
	}
	
}