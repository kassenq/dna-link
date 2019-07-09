/**
 * implementing IDnaStrand efficiently, uses nodes
 * @authors kkq and kn118
 */
public class LinkStrand implements IDnaStrand{
   private class Node {
      String info;
      Node next;
      public Node(String s) {
         info = s;
         next = null;
      }
   }
   //instance variables
   private Node myFirst,myLast;
   private long mySize;
   private int myAppends;
   
   private int myIndex;
   private int myLocalIndex;
   private Node myCurrent;

	/**
	 * Creates a LinkStrand object,
	 * Calling the initialize method on Str
	 * @param String str, the cgat DNA for this strand
	 */
   public LinkStrand(String str) { //constructor
      initialize(str);
   }
   
	/**
	 * default constructor for LinkStrand
	 */
   public LinkStrand() {
      this("");
   }
   
	/**
	 * @return the mySize, the number of elements in the strand
	 */
   @Override
   public long size() {
      return mySize; //returns mySize
   }

   @Override
   public void initialize(String source) {
      Node n = new Node(source);
      myFirst = n;
      myLast = n;
      mySize = source.length();
      myAppends = 0;
      
      myIndex = 0;
      myLocalIndex = 0;
      myCurrent = myFirst;
   }

	/**
	 * Returns a LinkStrand object using source string
	 * @param String source that is used to create a LinkStrand object
	 * @return a LinkStrand object
	 */
   @Override
   public IDnaStrand getInstance(String source) {
      return this;
   }

	/**
	 * Appends a new node, which has the dna information and is efficient
	 * @param String dna is the DNA information of the last Node
	 * @return the strand after dna is added
	 */
   @Override
   //adds a dna string to the end of the LinkList
   public IDnaStrand append(String dna) {
      Node temp = new Node(dna);
      myLast.next = temp;
      myLast = myLast.next;
      mySize += dna.length();
      myAppends++;
      return this;
   }

   @Override
	/**
	 * Creates a new LinkStrand object that is the reverse of the object on which it's called by reversing
	 * the linked list and all the strings in each node.
	 * @return a strand that is the reverse of the object on which it's called
	 */
   public IDnaStrand reverse() {
      
      StringBuilder copy = new StringBuilder(myFirst.info);
      copy.reverse();
      Node newMyFirst = new Node(copy.toString());
      Node curr = myFirst;
      Node newMyLast = newMyFirst;
      
      while (curr.next != null) { //loops through all the DNA strands
         copy = new StringBuilder(curr.next.info);
         copy.reverse();
         Node node = new Node(copy.toString());
         
         node.next = newMyFirst;
         newMyFirst = node;
         curr = curr.next;
      }
      
      LinkStrand s = new LinkStrand("");
      s.myAppends = this.myAppends;
      s.mySize = this.mySize;
      s.myFirst = newMyFirst;
      s.myLast = newMyLast;
      return s;

   }

	/**
	 * @return number of times append is called
	 */
   @Override
   public int getAppendCount() {
      return myAppends;
   }
   
   @Override
	/**
	 * Efficiently retrieves the character at the specified index
	 * @param integer index is the index of the character to be returned
	 * @return the character at index
	 */
    public char charAt(int index) {
	   if(index>=mySize||index<0)
           throw new IndexOutOfBoundsException();
		if (index<myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
//      if(index >= mySize || index < 0) //checks if the index is valid
//         throw new IndexOutOfBoundsException("Index out of bounds");
//
//      int temp;
//      
//      if (index == myIndex)
//         return myCurrent.info.charAt(myLocalIndex);
//      if (index < myIndex) { //resets if the index is less than the current value of myIndex
//         myIndex = 0;
//         myCurrent = myFirst;
//         myLocalIndex = 0;
//      }
//
//      if (index == myIndex)
//         return myCurrent.info.charAt(myLocalIndex);
//      if (index > myIndex) {
//         temp = index - myIndex;
//         while (temp >= myCurrent.info.length() - myLocalIndex) { //moves to the next node
//            temp = temp - myCurrent.info.length() + myLocalIndex;
//            myCurrent = myCurrent.next;
//            myLocalIndex = 0;
//         }
//         myLocalIndex += temp;
//         myIndex = index;
//         return myCurrent.info.charAt(myLocalIndex);
//      }
//      return ' ';
   }
   
   /** loops through all the nodes and builds a string to return
    * @param none
    * @return a string consisting of all the DNA sequences
    */
   public String toString() {
      StringBuilder temp = new StringBuilder();
      Node myNext = myFirst;
      temp.append(myNext.info);
      while(myNext.next != null) {
         myNext = myNext.next;
         temp.append(myNext.info); //builds the string
      }
      return temp.toString();
   }

}