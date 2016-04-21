/**
 * This is A test Class developed to create and test a balanced integer B-Tree implemented with arrays.
 * @author Morgan Bridges
 *
 */
public class BalancedSetDriver2 {
	public static void main(String[] args)
	{
		IntBalancedSet2 set = new IntBalancedSet2();
		IntBalancedSet2 set2 = new IntBalancedSet2();
		
		//Set 2 will be a controlled subset populated manually based on the b-tree on page 547 of the text
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
		
		set.add(2);
		set.add(5);
		set.add(0);
		set.add(1);
		set.add(3);
		set.add(4);
		set.add(6);
		set.add(7);
		set.add(8);
		set.add(22);
		set.add(13);
		set.add(16);
		set.add(19);
		set.add(22);
		set.add(14);
		set.add(15);
		set.add(17);
		set.add(18);
		set.add(21);
		set.add(23);
		set.add(24);
		set.add(26);
		set.add(33);
		set.add(31);
		set.add(32);
		set.add(33);
		set.add(34);
		set.add(35);
		set.add(36);
		set.add(9);
		set.add(10);
		set.add(11);
		set.add(12);
		set.add(27);
		set.add(28);
		set.add(29);
		set.print(2);
		System.out.println("This shows the removal of a non-leaf node's values.(2,5)");
		System.out.println("**************");
		set.remove(2);
		set.remove(5);
		set.print(2);
		
		System.out.println("**************");
		System.out.println("This shows the removal of a root's values");
		set.print(2);
		//and this shows the removal of the root data
		set.remove(8);
		set.remove(23);
		System.out.println("**************");
		set.print(2);
		for(int i = 0; i < 10; i++)
			set.remove(i);
		System.out.println("**************");
		System.out.println("After removing 1-9");
		
		set.add(7);
		set.add(8);
		set.add(2);
		set.print(2);
		;
		set.print(2);
		
		
		
	}
	
}