// File: IntBalancedSet2.java from the package edu.colorado.linked

// The implementation of most methods in this file is left as a student
// exercise from Section 10.2 of "Data Structures and Other Objects Using Java"

/******************************************************************************
* This class is a homework assignment;
* An <CODE>IntBalancedSet2</CODE> is a set of int numbers, stored in a B-tree. 
*
* <dt><b>Outline of Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/collections/IntTreeBag.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/IntTreeBag.java
*   </A>
*
* <dt><b>Note:</b><dd>
*   This file contains mostly blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 1999
*
* @see IntArrayBag
* @see IntLinkedBag
* @see IntTreeBag
******************************************************************************/
public class IntBalancedSet2 implements Cloneable
{
   // Invariant of the IntBalancedSet2 class:
   //   1. The elements of the Set are stored in a B-tree, satisfying the six
   //      B-tree rules.
   //   2. The number of elements in the tree's root is in the instance
   //      variable dataCount, and the number of subtrees of the root is stored
   //      stored in the instance variable childCount.
   //   3. The root's elements are stored in data[0] through data[dataCount-1].
   //   4. If the root has subtrees, then subtree[0] through 
   //      subtree[childCount-1] are references to these subtrees.
   private int minimum = 2;
   private int maximum = minimum * 2;
   int dataCount;
   int[] data;
   int childCount;
   IntBalancedSet2[ ] subset = new IntBalancedSet2[maximum + 2]; 


   /**
   * Initialize an empty set.
   * @param - none
   * <dt><b>Postcondition:</b><dd>
   *   This set is empty.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for adding a new element.
   **/
   public IntBalancedSet2()
   {
      //this.minimum = min;
      //maximum = 2 * min;
      data = new int[maximum + 1];
	  dataCount = 0;
      childCount = 0;
     
   }


   /**
   * Add a new element to this set.
   * @param  <CODE>element</CODE>
   *   the new element that is being added
   * <dt><b>Postcondition:</b><dd>
   *   If the element was already in this set, then there is no change.
   *   Otherwise, the element has been added to this set.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for adding a new element.
   **/
   public void add(int element)
   {
       // add data, then check to see if root needs to be split.
	   looseAdd(element);
	   
	   if (dataCount > maximum ) 
	   {
		   // System.out.println("Splitting");
		   // create the new child
		   IntBalancedSet2 child = new IntBalancedSet2();
	   
		   // transfer data to new child:
		   for (int x = 0; x < dataCount; x++)
		   child.data[x] = data[x];
		   for (int y = 0; y < childCount; y++)
		   child.subset[y] = subset[y];
		   child.childCount = childCount;
		   child.dataCount = dataCount;
		   
		   // reset current root as empty, with 1 child
	       childCount = 1;
		   for (int i = 0; i < data.length; i++)
		   data[i] = 0;
		   dataCount = 0;
		   
		   // make new child subset of current node
		   subset[0] = child;
	  
		   // fix problem of empty root node
		   fixExcess(0);
   	   }
    }
      
   /**
   * Generate a copy of this set.
   * @param - none
   * @return
   *   The return value is a copy of this set. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>IntBalancedSet2</CODE> before it 
   *   can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone( )
   {  
	   IntBalancedSet2 clone = new IntBalancedSet2();
	   IntBalancedSet2 clone2 = new IntBalancedSet2();
	   IntBalancedSet2[] subClone = new IntBalancedSet2[childCount];
	   int[] recursiveData = new int[data.length];
	   int localChildCount = 0;
	   int localDataCount = 0;
	   try
	   {
		   clone =(IntBalancedSet2)super.clone();
	   }
	   catch (CloneNotSupportedException e)
	   {
		   throw new RuntimeException("Clones not supported.");
	   }
	   
	   
	    for(int i = 0; i < dataCount; i++)
		   clone2.add(data[i]);
	   
	   for (int i = 0; i < childCount; i++)
	   {
		    
		   
		    subClone[i] = (IntBalancedSet2)subset[i].clone();
		    clone2.insertSubset(i, subClone[i]);   
		   
	   }
	   localDataCount = dataCount;
	   
	   
	   
	   //lone.dataCount = localDataCount;
	   return clone2;
	   
   }


   /**
   * Accessor method to determine whether a particular element is in this set.
   * @param <CODE>target</CODE>
   *   an element that may or may not be in this set
   * @return
   *   <CODE>true</CODE> if this set contains <CODE>target</CODE>;
   *   otherwise <CODE>false</CODE>
   **/
   public boolean contains(int target)
   {
	   {
			  boolean contains = false;
		   	  //set i to the first item in data[] >= to the target
			  int i = firstGE(target);
		      //return true if we find the data
		      if(data[i] == target)
		    	  contains = true;
		      else if(isLeaf())
		      {
		    	  contains = false;
		      }
		      //recursive call to search the subtree
		      else if (subset[i] != null)
		    	  contains = subset[i].contains(target);
		      return contains;
		   }
   }


   /**
   * Remove a specified element from this set.
   * @param <CODE>target</CODE>
   *   the element to remove from this set
   * <dt><b>Postcondition:</b><dd>
   *   if <CODE>target</CODE> was found in this set, then it has been removed
   *   and the method returns <CODE>true</CODE>. Otherwise this set remains
   *   unchanged and the method returns <CODE>false</CODE>.
   **/
   public boolean remove(int target)
   {
	   boolean answer = looseRemove(target);
      
	   if ((dataCount == 0) && (childCount == 1))
      {
    	  dataCount = subset[0].dataCount;
    	  childCount = subset[0].childCount;
    	  data = subset[0].data;
    	  subset = subset[0].subset;
      }
      return answer;
   }

   /**
    * Postcondition: The set has been recursively printed.
    * @param indent - the indentation separating level of depth of the tree.
    */
   public void print(int indent)
   // Print a representation of this set's B-tree, useful during debugging.
   {
      final int EXTRA_INDENTATION = 4;
      int i;
      int space;
  
      // Print the indentation and the data from this node
      for (space = 0; space < indent; space++)
         System.out.print(" ");
      for (i = 0; i < dataCount; i++)
         System.out.print(data[i] + " ");
      System.out.println( );
         
      // Print the subtrees
      for (i = 0; i < childCount; i++)
         if(subset[i] != null)
        	 subset[i].print(indent + EXTRA_INDENTATION);
   }
       
       
   // PRIVATE HELPER METHODS 
   // The helper methods are below with precondition/postcondition contracts.
   // Students should implement these methods to help with the other methods.
 
   /** Precondition: 0 <= removeIndex < dataCount.
   // Postcondition: The element at data[removeIndex] has been removed and
   // subsequent elements shifted over to close the gap. Also, dataCount has
   // been decremented by one, and the return value is a copy of the
   // removed element.
    * 
    * @param removeIndex - the index of the data you wish to remove
    * @return - the removed data
    */
   private int deleteData(int removeIndex)

   {
	   int removedData = data[removeIndex];
	   if(removeIndex < data.length)
		 {	
		 
		 for (int i=removeIndex; i < data.length - 1 ; i++){
			data[i]=data[i + 1];
		 }
		 dataCount--;
		 }
	   	 return removedData;
   }
   
   /** Precondition: 0 <= removeIndex < childCount.
   // Postcondition: The element at subset[removeIndex] has been removed and
   // subsequent elements shifted over to close the gap. Also, childCount has
   // been decremented by one, and the return value is a copy of the
   // removed element.
    * 
    * @param removeIndex - the index of the subset you wish to remove
    * @return - the removed set
    */
   private IntBalancedSet2 deleteSubset(int removeIndex)
   
   {
     IntBalancedSet2 deletedSet = subset[removeIndex];
     if(removeIndex < subset.length)
	 {	
	 
	 for (int i=removeIndex; i < subset.length - 1 ; i++){
		subset[i] = subset[i + 1];
	 }
	 childCount--;
	 }
     return deletedSet;
     
   }
   /** Postcondition: The return value, x, is the first location in the root
   // such that data[x] >= target. If there is no such location, then the
   // return value is dataCount.
    * 
    * @param target - the value we are trying to find a root member greater than
    * @return - either the index the first root member greater than the target, otherwise dataCount
    */
   private int firstGE(int target)
   
   
   {
     for (int i = 0; i < dataCount; i++)
     {
    	 if (data[i] >= target)
    		 return i;
     }
     return dataCount;
   }

   /** Precondition: 
   //   (i < childCount) and the entire B-tree is valid EXCEPT that
   //   subset[i] has MAXIMUM + 1 entries. Also, the root is allowed to have
   //   zero entries and one child.
   // Postcondition: 
   //   The tree has been rearranged so that the entire B-tree is valid EXCEPT
   //   that the number of entries in the root of this set might be one more than
   //   the allowed maximum.
    * 
    * @param i - index of the child node with excessive data members
    */
   public void fixExcess(int i)
   {
	   //child is the node to be split
	   IntBalancedSet2 child = subset[i];
	   //left and right children will replace the child node
	   IntBalancedSet2 left = new IntBalancedSet2();
	   IntBalancedSet2 right = new IntBalancedSet2();
	   
	   //this is the element to be passed back to the root
	   int element = child.data[minimum];
	   
	   //left and right will both contain minimum elements
	   left.dataCount = minimum;
	   System.arraycopy(child.data, 0, left.data, 0, minimum);
	   if (!child.isLeaf())
		   left.childCount = minimum + 1;
	   System.arraycopy(child.subset, 0, left.subset, 0, minimum + 1);
	   
	   right.dataCount = minimum;
	   System.arraycopy(child.data, minimum + 1, right.data, 0, minimum);
	   if (!child.isLeaf())
		   right.childCount = minimum + 1;
	   System.arraycopy(child.subset, minimum + 1, right.subset, 0, minimum + 1);
	   
	   subset[i] = right;
	   insertSubset(i, left);
	   insertData(i, element);
	  


	   }
   
   /** Precondition: 
   //   (i < childCount) and the entire B-tree is valid EXCEPT that
   //   subset[i] has only minimum - 1 entries.
   // Postcondition: 
   //   The tree has been rearranged so that the entire B-tree is valid EXCEPT
   //   that the number of entries in the root of this set might be one less than
   //   the allowed minimum.
    * 
    * @param i - the index of the subset with the shortage
    */
   public void fixShortage(int i)
   
   {
	   //System.out.println("fixShortCalled");
     //this case deals with a shifting an element from the root to the right and moving a subset
	 if(i > 0 && subset[i-1].dataCount > minimum)
     {
    	 transferRight(i);
    	//System.out.println("Transfer-Right used");
    	
     }
	 //this case deals with a shifting an element from the root to the left and moving a subset
     else if(i < dataCount-1 && subset[i+1].dataCount > minimum)
     {
    	 transferLeft(i);
    	//System.out.println("Transfer-Left used");
     }
	 //this merges the previous subset with the selected set
     else if(i > 0 && subset[i-1].dataCount == minimum)
     {
        mergeWithNextSubset(i-1);
       //System.out.println("Merge i-1 with I");
     }
	 //this merges the selected subset with the next subset
     else //if(i < childCount-1 && subset[i+1].dataCount <= minimum)
     {
    	mergeWithNextSubset(i);
    	//System.out.println("Merge i with i + 1");
    	
     }

     
   }      

   /** Precondition: 0 <= insertIndex <= dataCount <= MAXIMUM.
   // Postcondition: The entry has been inserted at data[insertIndex] with
   // subsequent elements shifted right to make room. Also, dataCount has
   // been incremented by one.
    * 
    * @param insertIndex - the position for inserted data
    * 		
    * @param entry - the data to be inserted
    * 		
    */
   private void insertData(int insertIndex, int entry)
   {
	   	//make sure data is positioned correctly for the insert
	    if(insertIndex < data.length)
		{	
		for (int i=(data.length-1);i>insertIndex;i--){
			data[i]=data[i-1];
		}
		data[insertIndex] = entry;
		dataCount++;
		//System.out.println(entry + " added.");
		
		}
		
   }
	
   /** Precondition: 0 <= insertIndex <= childCount <= maximum+1.
   // Postcondition: The set has been inserted at subset[insertIndex] with
   // subsequent elements shifted right to make room. Also, childCount has
   // been incremented by one.
    * @param - insertIndex - the index for insertion in the subset array
    * 		   set - the set to be inserted
    * 
    */
   private void insertSubset(int insertIndex, IntBalancedSet2 set)
   
   {
      if(insertIndex < subset.length)
      {
    	  for (int i=(subset.length-1);i>insertIndex;i--){
  			subset[i]=subset[i-1];
  		}
  		subset[insertIndex] = set;
  		childCount++;
  		
      }
   }
   
   
   private boolean isLeaf( )
   // Return value is true if and only if the B-tree has only a root.
   {
      return (childCount == 0);
   }
   
   /** Precondition:
   //   The entire B-tree is valid.
   // Postcondition:
   //   If entry was already in the set, then the set is unchanged. Otherwise,
   //   entry has been added to the set, and the entire B-tree is still valid
   //   EXCEPT that the number of entries in the root of this set might be one
   //   more than the allowed maximum.
    * @param - The value to be added to the tree.
    */
  
   private void looseAdd(int entry)
   {
		// Follow steps 1 and 2 given on p. 538
		// Note the recursive call to looseAdd in the else clause of step 2c
		
		int i = firstGE(entry);
		//the entry is already in root, so we do nothing
		if ((i<dataCount) && (data[i]==entry))
		{
			return;
		} else if (isLeaf()){
			insertData(i, entry);
			//System.out.println("LooseAdd isLeaf");
		  
		} else 
		{
		    if(subset[i] == null)
				subset[i] = new IntBalancedSet2();
			subset[i].looseAdd(entry);
			if(subset[i].dataCount>2*minimum)
				fixExcess(i);
			;
			
			
		}
		
	}
   /** Precondition:
   //   The entire B-tree is valid.
   // Postcondition:
   //   If target was in the set, then it has been removed from the set and the
   //   method returns true; otherwise the set is unchanged and the method 
   //   returns false. The entire B-tree is still valid EXCEPT that the
   //   number of entries in the root of this set might be one less than the
   //   allowed minimum.
    * 
    * @param target - the target element to be removed
    * @return - boolean value stating whether the element was added
    */
   private boolean looseRemove(int target)

   {
	   boolean removed = false;
	   int i = firstGE(target);
	   if(i < dataCount && isLeaf() && data[i] != target)
	   {
		   removed = false;
	   }
	   else if(i < dataCount && isLeaf() && data[i] == target )
	   {    
    	
		   deleteData(i);
		   
    	   removed = true;
    	  
    		 
	   }
	   else if(!isLeaf() &&  subset[i] != null && data[i] != target)
	    {
		    
		  removed = subset[i].looseRemove(target);
		  if(subset[i].dataCount < minimum) 	 
			 fixShortage(i);
		 	  
	   }
	  
	   else if(subset[i] != null && data[i] == target)
	   {
		// if(subset[i] != null) 
		   data[i] = subset[i].removeBiggest();
		   if(subset[i].dataCount < minimum )
			   fixShortage(i);
		   removed = true;
	   }
	  
	   return removed;
   }
   /** Precondition: 
   //   (i+1 < childCount) and the entire B-tree is valid EXCEPT that the total
   //   number of entries in subset[i] and subset[i+1] is 2*minimum - 1.
   // Postcondition: 
   //   subset[i] and subset[i+1] have been merged into one subset (now at
   //   subset[i]), and data[i] has been passed down to be the median entry of the
   //   new subset[i]. As a result, the entire B-tree is valid EXCEPT that the
   //   number of entries in the root of this set might be one less than the
   //   allowed minimum.
    * 
    * @param i - the index of the subset that will absorb subset[i + 1]
    */
   public void mergeWithNextSubset(int i)

   {
       //choose the next set to be merged with current subset
	   IntBalancedSet2 mergedSet = deleteSubset(i+1);
	   //select the data from the root to be dropped into the subset
	   int dataFromRoot = deleteData(i);
	   //insert the data from root into the end of the subset[i]
	   subset[i].insertData(subset[i].dataCount, dataFromRoot);
	   
	   System.arraycopy(mergedSet.data, 0, subset[i].data, subset[i].dataCount, mergedSet.dataCount);
	   System.arraycopy(mergedSet.subset, 0, subset[i].subset, subset[i].childCount,mergedSet.childCount );
	   for (int k = 0; k < mergedSet.dataCount; k++)
		   subset[i].dataCount++;
	   for (int k = 0; k < mergedSet.childCount; k++)
		   subset[i].childCount++;
   }
   /** Precondition: 
   //   (dataCount > 0) and the entire B-tree is valid.
   // Postcondition:
   //   The largest item in the set has been removed, and the value of this
   //   item is the return value. The B-tree is still valid EXCEPT
   //   that the number of entries in the root of this set might be one less than
   //   the allowed minimum.
    * 
    * @return - the removed element
    */
   private int removeBiggest( )

   {
      
     int biggestElement  = 0;
     
     if(isLeaf())
    	 return data[--dataCount];
     
    	 
     else 
    	{
    		biggestElement = subset[childCount - 1].removeBiggest();
    		if (subset[childCount - 1].dataCount < minimum)
    			fixShortage(childCount - 1);
    		return biggestElement;
    	}
    
     }
     
   

   /** Precondition: 
   //   Tree is valid except that
   //   subset[i] has only minimum - 1 entries.
   // Postcondition:
   //   One entry has been shifted from the front of subset[i+1] up to
   //   data[i], and the original data[i] has been shifted down to the last
   //   entry of subset[i]. Also, if subset[i] is not a leaf, then its first
   //   subset has been transfered over to be the last subset of subset[i].
   //   As a result, the entire B-tree is now valid.
    * 
    * @param i - the index to transfer the value of the root and additional subset  to
    */
   public void transferLeft(int i)

   {
	   IntBalancedSet2 transferSet = new IntBalancedSet2();
		 
		 //choose the element to remove from the root and give to subset[i]
		 int fromRootToI = deleteData(i);
		 //select the smallest subset of subset[i+1] for transfer
		 if (!subset[i+1].isLeaf())
			 transferSet = subset[i+1].deleteSubset(0);
	     //insert the removed root data into the subset[i]
		 subset[i].insertData(subset[i].dataCount, fromRootToI);
	     //move the first element of subset[i+1] and place it at the start of the root.
		 insertData(i, subset[i+1].deleteData(i));
	     subset[i].insertSubset(childCount-1, transferSet);	
   }
   
   /** Precondition: 
   //   (i+1 < childCount) and (subset[i]->dataCount > minimum)
   //   and the entire B-tree is valid EXCEPT that
   //   subset[i] has only (minimum - 1) entries.
   // Postcondition: One entry has been shifted from the end of subset[i-1] up to
   //   data[i-1], and the original data[i-1] has been shifted down to the first entry
   //   of subset[i]. Also, if subset[i-1] is not a leaf, then its last subset has
   //   been transfered over to be the first subset of subset[i].
   //   As a result, the entire B-tree is now valid.
    * 
    * @param i - index of the subset to give the value to
    */
   public void transferRight(int i)
   
   {
    
	   IntBalancedSet2 transferSet = new IntBalancedSet2();
		 //choose the element to remove from the root and give to subset[i]
		 int fromRootToI = deleteData(i-1);
		 //select the largest subset of subset[i-1] for transfer
		 if (!subset[i-1].isLeaf())
			 transferSet = subset[i-1].deleteSubset(subset[i-1].childCount-1);
	     //insert the removed root data into the subset[i]
		 subset[i].insertData(0, fromRootToI);
	     //move the last element of subset[i-1] and place it at the end of the root.
		 insertData(i-1, subset[i-1].deleteData(subset[i-1].dataCount - 1));
	     subset[i].insertSubset(0, transferSet);	 
     
   }
  /* public int[] getIndicies(int target)
   {
	   int[] inAr = new int[maximum + 2];
	   boolean found = false;
	   int depth = 0;
	   int i = 0;
	   int k = firstGE(target);
	   //while(i < dataCount)
	   {
	        if(data[k] == target)
	        {
	        	inAr[k] = k;
	        	depth++;
	        	i++;
	        	found = true;
	        	System.out.println("found it");
	        	return inAr;
	        }
	        System.out.println(depth);
	   }
	   if(found == false)
	   {
		   subset[k].getIndicies(target);
		   
	   }
	   return inAr;
   }*/
   
}