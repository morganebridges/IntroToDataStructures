/**
 * This class allows a user to merge sort a list of integers implemented with a linked list.
 *  I implemented this class myself from scratch. Enjoy!
 * @author Morgan
 *
 */

public class MergeSort {
 /**
  * This main method is responsible for the construction and passing of the linked list into the sorting 
  * methods.
  * @param args
  */
 public static void main(String[] args)
 {
	 //put the first element into the head
	 IntNode list = new IntNode(1000, null);
	 //add the rest of the array values to the linked list
	 int[ ] data = {80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
	 for(int i = data.length-1; i >= 0; i--)
		 list.addNodeAfter(data[i]);
	 

	 //print the original list
	 list.printList();
	 //sort the list
	 list = mergeSort(list);
	 //print the sorted list
	 list.printList();
 }
 /**
  * precondition: A valid list head of type integer has been passed to the method
  * @param head - head of a non-null linked integer list
  * @return - the sorted list
  * postcondition: the list has been fully sorted in ascending order.
  */
 public static IntNode mergeSort(IntNode head)
 { 
	 //System.out.println("sort called");
 
	 if(head == null || head.getLink() == null)
		 return head;
	 IntNode sortedHead = null;
	 //create list1 and list2
	 IntNode list1 = head;
	 IntNode list2 = null;
	 
	 IntNode cursor = head;
	 int manyNodes = 0;
	 
	 //count the nodes in the list
	 while(cursor.getLink() != null)
	 {
		 manyNodes++;
		 cursor = cursor.getLink();
	 }
	 
	 
	 
	 //find the middle point, splitting the list in favor of the first part for odds
	 int middle = manyNodes/2;
	 cursor = head;
	 
	 //set the reference of list 2 to middle, and set the tail of list1 to null
	 
	 for(int i = 0; i < middle; i++)
	 {
		cursor = cursor.getLink(); 
	 }
	 list2 = cursor.getLink();
	 cursor.setLink(null);
	 
	 //recursive call to mergeSort
	 IntNode head1 = mergeSort(list1);
	 IntNode head2 = mergeSort(list2);
	 
	 //call merge with the two lists
	 
	 sortedHead = merge(head1, head2);
	 
	 
	return sortedHead;
 	}
 	/**
 	 * This method deals with actually merging the split lists created by the recursive call in the 
 	 * mergeSort() method.
 	 * precondition: the linked list has been fully split by the mergeSort() method and is ready to be
 	 * sorted and reassembled into a new list
 	 * @param list1 - the first half of a linked integer list
 	 * @param list2 - the second half of a linked integer list
 	 * @return
 	 */
 	public static IntNode merge(IntNode list1, IntNode list2)
 	{
 		
 		//this node is a dummy head as a place holder to anchor the new sorted list
 		IntNode mergedHead = new IntNode(-255, null);
 		
 		//create element pointers for the two list arguments and the merged list
 		IntNode newP = mergedHead;
 		IntNode p1 = list1;
 		IntNode p2 = list2;
 		
 		while(p1 != null || p2 != null)
 		{	
 			//if the pointer to list 1 is null, add the current value of list 2
 			if(p1 == null)
 			{
 				
 				newP.setLink(new IntNode(p2.getData(), null));
 				p2 = p2.getLink();
 				newP = newP.getLink();
 			}
			//if the pointer to list 2 is null, add the current value of list 1
 			else if(p2 == null)
 			{
 				
 				newP.setLink(new IntNode(p1.getData(), null));
 				p1 = p1.getLink();
 				newP = newP.getLink();
 			}
 			//if the pointer to list 1's element is smaller, add this element to the merged list
 			else if(p1.getData() < p2.getData())
 			{	
 				newP.setLink(new IntNode(p1.getData(), null));
 				p1 = p1.getLink();
 				newP = newP.getLink();
 			}
 			//in the case of equal elements from both lists, add this element twice
 			else if(p1.getData() == p2.getData())
 			{	
 				//if p1 == p2 enter both and move the pointers
 				newP.setLink(new IntNode(p1.getData(), null));
 				newP.getLink().setLink(new IntNode(p1.getData(), null));
 				p1 = p1.getLink();
 				p2 = p2.getLink();
 				newP = newP.getLink().getLink();
 			}
 			//if the pointer to list 2's element is smaller, add this element to the merged list
 			else
 			{
 				newP.setLink(new IntNode(p2.getData(), null));
 				p2 = p2.getLink();
 				newP = newP.getLink();
 			}
		
 			} 
 			//Move head forward to the first element of the sorted list, discarding the dummy head.
 			//Return the sorted list.
 			return mergedHead.getLink();
 	}
}
