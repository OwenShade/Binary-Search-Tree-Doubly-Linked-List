
public class DoublyLinkedList {
	
	private static class Node{
		private int data;
		private Node next;
		private Node prev;

		Node(int x){
			data = x;
		}
	} 

	Node head,tail = null;

	public void add(Integer x) {
		Node newNode = new Node(x);
		//If value is already in the list, don't add
		if(IsElement(x)){
			return;
		}
		//if the list has elements, add the new node by pointing the head to it
		if (head != null){
			newNode.next = head;
			head = newNode;
			return;
		}
		//if the list is empty, make both the head and tail point to the new node.
		else{
			head = newNode;
			tail = newNode;
			tail.next = null;
			return;
		}
	}	

	public void Remove(int x) {
		Node current = head;
		Node prev = null;
		//if the element doesn't exist, don't remove
		if(!IsElement(x)){
			System.out.println("Element does not exist in list!");
			return;
		}
		//if the current node has the value to be removed, remove it
		if(current != null && current.data == x){
			head = current.next;
			return;
		}
		//iterate through each node in the list
		while(current != null && current.data != x){
			prev = current;
			current = current.next;
		}

		prev.next = current.next;
	}

	public boolean IsElement(int x) {
		boolean found = false;
		Node current = head;
		//if the list is empty, return false
		if(head == null){
			return found;
		}
		//iterate through each node
		while(current!=null){
			//if the data is the data to find, set found to true and break to return
			if(current.data == x){
				found = true;
				break;
			}
			//otherwise, updated the current node pointer
			current = current.next;
		}
		return found;
	}


	public boolean SetEmpty() {
		//if the head pointer is null, the list is empty
		if(head == null){
			return true;
		}
		return false;
	}

	public int SetSize() {
		Node current = head;
		int i = 0;
		//if the list is empty return 0
		if(head == null){
			return i;
		}
		//iterate through each node updating i by 1 each time 
		while(current != null){
			i++;
			current = current.next;
		}
		return i;
	}

	public static DoublyLinkedList Union(DoublyLinkedList list1, DoublyLinkedList list2){
		DoublyLinkedList union = new DoublyLinkedList();

		Node node1 = list1.head;
		Node node2 = list2.head;

		//Base Case: if both sets are empty, then return the empty set
		if(node1 == null && node2 == null){
			return union;
		}
		//iterate through the first list and add each node to the new list
		while (node1 != null){
			union.add(node1.data);
			node1 = node1.next;
		}
		//iterate through the second list and add each node to the new list if it doesn't already exist in the new list
		while (node2 != null){
			if(!union.IsElement(node2.data)){
				union.add(node2.data);
			}
			node2 = node2.next;
		}
		//return the newly created union
		return union;
	}

	public static DoublyLinkedList Intersection(DoublyLinkedList list1, DoublyLinkedList list2){
		DoublyLinkedList intersection = new DoublyLinkedList();

		Node node1 = list1.head;
		Node node2 = list2.head;
		
		//Base Case: if both lists are empty return an empty list
		if(node1 == null || node2 == null){
			return intersection;
		}
		//iterate through each node in the first list
		while(node1 != null){
			//add the node value to the new list if it exists in the second list
			if(list2.IsElement(node1.data)){
				intersection.add(node1.data);
			}
			//update the node pointer to point to the next in the list
			node1 = node1.next;
		}
		return intersection;
	}

	public static DoublyLinkedList Difference(DoublyLinkedList list1, DoublyLinkedList list2){
		DoublyLinkedList difference = new DoublyLinkedList();

		Node node1 = list1.head;
		Node node2 = list2.head;

		//Base Case: If both Sets are empty(head pointers of each are null), then return the empty set.
		if(node1 == null && node2 == null){
			return difference;
		}
		//iterate through each node in the first list
		while(node1 != null){
			//if the current data doesn't exist in the second list, add to the new list
			if(!list2.IsElement(node1.data)){
				difference.add(node1.data);
			}
			//point to the next node
			node1 = node1.next;
		}
		//iterate through each node in the second list
		while(node2 != null){
			//if the current data doesn't exist in the first list, add to the new list
			if(!list1.IsElement(node2.data)){
				difference.add(node2.data);
			}
			//point to the next node
			node2 = node2.next;
			
		}
		
		return difference;
	}

	public static boolean Subset(DoublyLinkedList list1, DoublyLinkedList list2){
		
		Node node1 = list1.head;
		Node node2 = list2.head;
		boolean subset = false;

		//If both sets are the empty set, then they list1 is not a subset of list2 
		if(node1 == null && node2 == null){
			return false;
		}

		while(node1 != null){
			if(list2.IsElement(node1.data)){
				node1 = node1.next;
				subset = true;
			}
			else{
				subset = false;
				break;
			}
		}
		return subset;
	}
}

