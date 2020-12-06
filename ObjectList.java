/**
   A class that implements a list of objects using a doubly linked list.  
   The list is never full.
   @author Hanadi Jusufovic and TJ
*/
public class ObjectList<T> implements ObjectListInterface<T>{
	
	private Node firstNode; // Reference to the first node or head
	private Node lastNode; // Reference to the last node or tail
	private Node currentNode; // Reference to the current node
	private int numberOfEntries;
	private int currentPosition;
	
	public ObjectList() {
		this.firstNode = null;
		this.lastNode = null;
		this.currentNode = null;
		this.numberOfEntries = 0;
		this.currentPosition = 0;
	} // end default constructor
	
	private class Node {
		private T data;
		private Node next;
		private Node previous;
		
		private Node(T dataPortion) {
			this(dataPortion, null, null);
		} // end constructor
		
		private Node(T dataPortion, Node nextNode, Node previousNode) {
			this.data = dataPortion;
			this.next = nextNode;
			this.previous = previousNode;
		} // end constructor
	} // end Node

	/** Returns the current value from the list
	 	@return the data stored in the object currentNode
	 */
	public T getCurrent() {
		if(numberOfEntries != 0) {
			return currentNode.data;	
	  }
		return null;
	} // end getCurrent
	
	/** Moves to the beggining of the list and returns the associated value 
	 	(if any).
	    @return The value of the first object in the list
	 */
    public T getFirst() {
    	if(numberOfEntries != 0) {
    		currentPosition = 0;
    		currentNode = firstNode;
    		return currentNode.data;
    	}
    	return null;
    } // end getFirst
    
    /** Moves to the next position in the list and returns the value.
      	If we are at the end of the list, returns the value of the 
      	last element. If the list is empty, returns null.
     	@return The value of the next object in the list
     */
    public T getNext() {
    	if(numberOfEntries == 0) {
    		return null;
    	}
    	if(currentNode.next != null) {
    		currentNode = currentNode.next;
    		currentPosition++;
    		return currentNode.data;
    	}
    	else {
    	return currentNode.data;
    	}
    } // end getNext
    
    /** Moves to the previous position in the list and returns the value.
    	If we are at the begining of the list, returns the value of the
     	first element. If the list is empty, returns null.
 		@return The value of the previous object in the list
     */
    public T getPrevious() {
    	if(numberOfEntries == 0) {
    		return null;
    	}
    	if(currentNode.previous != null) {
    		currentNode = currentNode.previous;
    		currentPosition--;
    		return currentNode.data;
    	}
    	else {
    		return currentNode.data;
    	}
    } // end getPrevious
    
    /** Moves to the end of the list and returns the associated value (if any)
    @return The value of the last object in the list
    */
    public T getLast() {
    	if(numberOfEntries != 0) {
    		currentPosition = numberOfEntries  - 1;
    		currentNode = lastNode;
    		return currentNode.data;
    	}
    	return null;
    } // end getLast
    
    /** Adds a new value at the end of the list, the new element
      	becomes the current position.
        @param newObject the object to be added as a new entry
        @return returns true, since the addition is always succesfull
     */
    public boolean append(T newObject) {
    	Node newNode = new Node(newObject);
    	currentNode = newNode;
    	if(firstNode == null) {
    		firstNode = newNode;
    	}
    	newNode.previous = lastNode;
    	lastNode = newNode;
    	
    	if(newNode.previous != null) {
    	    lastNode.previous.next = newNode;
    	}
    	numberOfEntries++;
    	currentPosition = numberOfEntries - 1;
    	return true;
    } // end append
    
    /** Inserts the value into the list before the current position.
     	If the list is empty, insert as the first value.
     	@param newObject an object to be inserted
     	@return true since the insert will always be succesful
     */
    public boolean insert(T newObject) {
    	if(numberOfEntries == 0) {
    		append(newObject);
    		return true;
    	}
    	Node newNode = new Node(newObject);
    	if(currentNode == firstNode) {
    		newNode.next = currentNode;
    		currentNode.previous = newNode;
    		firstNode = newNode;
    		currentNode = firstNode;
    		numberOfEntries++;
    		currentPosition = 0;
    	}
    	else {
    		currentNode.previous.next = newNode;
    		newNode.previous = currentNode.previous;
    		newNode.next = currentNode;
    		currentNode.previous = newNode;
    		currentNode = newNode;
    		numberOfEntries++;	
    	}
    	return true;
    } // end insert
    
    /** Removes the current value. The previous value should 
    	then become the current value.
     	@return The value removed, or null if the list is empty.
     */
    public T remove() {
    	T result = null;
    	if(numberOfEntries == 0) {
    		return result;
    	}
    	else {
    		result = currentNode.data;
    		if(numberOfEntries == 1) {
    			firstNode = null;
    			lastNode = null;
    			currentNode = null;
    			currentPosition = 0;
    		}
    		else if(currentNode == firstNode) {
    		firstNode = currentNode.next;
    		currentNode.next.previous = null;
    		currentNode = firstNode;
    		currentPosition = 0;
    		}
    	    else if(currentNode == lastNode) {
    		lastNode = currentNode.previous;
    		currentNode.previous.next = null;
    		currentNode = lastNode;
    		currentPosition = numberOfEntries - 2;
    	    }
    	    else {
    		currentNode.previous.next = currentNode.next;
    		currentNode.next.previous = currentNode.previous;
    		currentNode = currentNode.previous;
    		currentPosition--;
    	}	
    }
    	numberOfEntries--;
    	return result;
    } // end remove
    	
    /** Replaces the current value with the provided value
     	@param newObject an object to replace current value
     	@return returns true since the replace is always successful
     */
    public boolean replace(T newObject) {
    	Node newNode = new Node(newObject);
    	if(numberOfEntries == 0) { // Not necessary but handles the case
    							   //when list is empty and replace is invoked
    		append(newObject);
    		return true;
    	}
    	else {
    	if(currentNode == lastNode) {
    		newNode.next = currentNode.next;
    		newNode.previous = currentNode.previous;
    		currentNode.previous.next = newNode;
    		lastNode = newNode;
    		currentNode = newNode;	
    	}
    	else if(currentNode == firstNode) {
    		newNode.next = currentNode.next;
    		newNode.previous = currentNode.previous;
    		currentNode.next.previous = newNode;
    		firstNode = newNode;
    		currentNode = newNode;
    	}
    	else if(numberOfEntries == 1) {
    		currentNode = newNode;
    		lastNode = newNode;
    		firstNode = newNode;
    	}
    	else {
    		newNode.next = currentNode.next;
    		newNode.previous = currentNode.previous;
    		currentNode.next.previous = newNode;
    		currentNode.previous.next = newNode;
    		currentNode = newNode;
    	}
      }
    	return true;
    } // end replace

    /** Removes all the values from the list
      	@return true since the clear is always succesful
     */
    public boolean clear() {
    	while(numberOfEntries != 0) {
    		remove();
    	}
    	currentPosition = 0;
    	return true;
    } // end clear
    
    /** Returns the number of entries in the list
     	@return Integer whose value is the number of entries in the list.
     */
    public int getLength() {
    	return numberOfEntries;
    } // end getLength
    
    /** Returns the integer value of the index of the current position 
     	between 0 and n-1. Returns 0 if the list  is empty.
     	@return int of the index of the current position.
     */
    public int getCurrentPosition() {
    	return currentPosition;
    } // end getCurrentPosition
} // end ObjectList
