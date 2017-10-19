/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 1
 */

public class List{
	
	private class Node{
		// Fields
		int data;
		Node prev;
		Node next;
		
		// Constructor
		Node(int data){
			this.data = data;
			prev = null;
			next = null;
		}
		
		// toString()
		public String toString(){
			return String.valueOf(data);
		}
	}

	// Fields
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int cursorIndex;
	
	// Constructor
	public List(){
		front = back = null;
		length = 0;
		cursorIndex = -1;
	}
	

	// Access functions:
	
	// length()
	// returns length of List
	int length(){
		return length;
	}
	
	// index()
	// If cursor is defined, returns index of the cursor element
	// otherwise return -1
	int index(){
		return cursorIndex;
	}
	
	// front()
	// Returns front element
	// Pre: length()>0
	int front(){
		if(length <= 0){
			throw new RuntimeException("Pre: length>0");
		}
		return front.data;
	}
	
	// back()
	// Returns back element 
	// Pre: length()>0
	int back(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		return back.data;
	}
	
	// get()
	// Returns cursor element
	// Pre: length()>0, index()>=0
	int get(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.index() <= -1){
			throw new RuntimeException("Pre: index()=>0");
		}
		return cursor.data;
	}
	
	// equals()
	// Returns true if this List and L are the same int seq.
	boolean equals(List L){
		boolean eq = true;
		Node N, M;
		N = this.front;
		M = L.front;
		if(this.length == L.length){
			while(eq && N!=null){
				eq = (N.data == M.data);
				N = N.next;
				M = M.next;
			}
			return eq;
		}else{
			return false;
		}
	}
	

	// Manipulation procedures:
	
	// clear()
	// Resets this List to its original empty state
	void clear(){
		Node front = null;
		Node back = null;
		cursor = null;
		length = 0;
		cursorIndex = -1;
	}
	
	// moveFront()
	// Places cursor under front element
	// Pre: length != 0
	void moveFront(){
		if(length() > 0){
			cursor = this.front;
			cursorIndex = 0;
		}
	}
	
	// moveBack()
	// Places cursor under back element
	// Pre: length != 0
	void moveBack(){
		if(length() > 0){
			cursor = this.back;
			cursorIndex = length-1;
		}
	}
	
	// movePrev()
	// Moves cursor one step toward front of list
	// cursor must be defined, does nothing otherwise
	// cursor falls off list when called on front
	void movePrev(){
		if(this.index() != -1 && this.index() > 0){
			cursor = cursor.prev;
			cursorIndex--;
		}else if(this.index() != -1 && this.index() == 0){
			cursor = null;
			cursorIndex = -1;
		}
	}
	
	// moveNext()
	// Moves cursor one step toward back of list
	// cursor must be defined, does nothing otherwise 
	// cursor falls of list when called on back
	void moveNext(){
		if(this.index() != -1 && cursorIndex < this.length()-1){
			cursor = cursor.next;
			cursorIndex++;
		}else if(this.index() != -1 && this.index() == this.length()-1){
			cursor = null;
			cursorIndex = -1;
		}
	}
	
	// prepend()
	// Insert new element into list before front element
	void prepend(int data){
		Node N = new Node(data);
		if(length < 0){
			throw new RuntimeException("Pre: length()>=0");
		}else if(length == 0){
			front = back = N;
			cursor = front;
			length ++;
		}else{
			front.prev = N;
			N.next = front;
			N.prev = null;
			front = N;
			length ++;
		}
		if(index() != -1){
			cursorIndex ++;
		}
	}
	
	// append()
	// Inserts new element into list after back element
	void append(int data){
		Node N = new Node(data);
		// if List is empty 
		if(length == 0){
			front = back = N;
		}else{
			// if List non-empty, insert after back element
			Node P;
			P = back;
			back.next = N;
			back = N;
			N.prev = P;
			P.next = N;
		}
		length++;
	}
	
	// insertBefore()
	// Insert new element before cursor
	// Pre: length()>0, index()>=0
	void insertBefore(int data){

		if (length() <= 0) {
			throw new RuntimeException ("Error: insertBefore() " + 
				"called on an Empty List.");
		} else if (index() < 0) {
			throw new RuntimeException ("Error: Cursor Undefined");
		}
		Node newNode = new Node (data);
		if (index() == 0) {
			prepend(data);
		} else {
			newNode.next = cursor;
			newNode.prev = cursor.prev;
			cursor.prev.next = newNode;
			cursor.prev = newNode;
			cursorIndex++;
			length++;
		}
	}
	
	// insertAfter()
	// Insert new element after cursor
	// Pre: length()>0, index()>=0
	void insertAfter(int data){

		if (length() <= 0) {
			throw new RuntimeException ("Error: Empty List.");
		} else if (index() < 0) {
			throw new RuntimeException ("Error: Cursor Undefined.");
		}
		Node newNode = new Node (data);
		if (index() == length()-1) {
			append(data);
		} else {
			newNode.next  = cursor.next;
			newNode.prev = cursor;
			cursor.next = newNode;
			cursorIndex++;
			length++;
		}
	}
	
	// deleteFront()
	// Deletes the front element
	// Pre: length()>0
	void deleteFront(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.length > 1){
			front = front.next;
			front.prev = null;
			cursorIndex--;
		}else{
			front = back = null;
			cursorIndex = -1;
		}
		length--;
	}
	
	// deleteBack()
	// Deletes the back element
	// Pre: length()>0
	void deleteBack(){
		if(length <= 0){
			throw new RuntimeException("Pre: length()>0");
		}
		if(this.length > 1 && this.index() != length-1){
			back = back.prev;
			back.next = null;
		}else if(this.length() == 1){
			front = back = null;
			cursorIndex = -1;
		}else if(this.length > 1 && this.index() == length-1){
			back = back.prev;
			back.next = null;
			cursorIndex = -1;
		}
		length--;
	}
	
	// delete()
	// Deletes the cursor element, making cursor undef
	// Pre: length()>0, index()>=0
	void delete(){
		if (index() == -1) {
			throw new RuntimeException ("Error: delete()" +
				"called on list with an undefined cursor.");
		}
		Node temp = cursor;
		if (cursor == back) {
			deleteBack();
		} else if (cursor == front) {
			deleteFront();
		} else{
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			cursor = null;
			temp = null;
			cursorIndex = -1;
		}
	}
	
	
	// Other methods:
	
	// toString()
	// Overrides Object's toString method
	// Returns a String representation of this List
	public String toString(){
		Node N = front;
		String str = "";
		while(N != null){
			str += N.data+" ";
			N = N.next;
		}
		return str;
	}
	
	// copy()
	// Returns a new List representing of the same integer sequence of this List
	// Post: cursor in List is undefined. List is unchanged
	List copy(){
		List copy = new List();
		Node N = front;
		for(int i = 0; i<length; i++){
			int n = N.data;
			copy.append(n);
			N = N.next;
		}
		//Post:
		copy.cursorIndex = -1;
		copy.cursor = null;
		
		return copy;
	}
	
	// concat()
	// Returns a new List which is concatenation of this List followed by L
	// Post: cursor is undefined. State of this List and L are unchanged
	List concat(List L){
		List concat = copy();
		Node curr = L.front;
		while(curr != null) {
			concat.append(curr.data);
			curr = curr.next;
		}
		concat.length = length + L.length;
		cursor = null;
		cursorIndex = -1;
		return concat;
	}
}