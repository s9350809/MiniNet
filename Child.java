import java.util.ArrayList;
import java.util.Iterator;
/*
 * The main function of this class is to create an object of type child. It extends Adult and
 * implements Friend. It also provides functionality to get the names of the parents and to 
 * check whether one of the parents passed in has children with another adult. The methods 
 * inherited from the Friend interface are also defined.
 */
public class Child extends Profile implements Friend{
	
	private Adult parentOne;
	private Adult parentTwo;
	private ProfileDataBase pdbTwo = MiniNetMain.getPDB();
	private ArrayList<String>parentsList = new ArrayList<String>();
	private String myParent1;
	private String myParent2;

/*
 * The constructor initializes the variables in the super class as well as variables defined in 
 * this class. It also runs the createParentsList() to create a list of parents during the
 * process of creation of an object.
  */
	public Child(String name,String image, String status,int age,Adult parentOne,Adult parentTwo) {
		super(name,image,status,age);
		this.parentOne = parentOne;
		this.parentTwo = parentTwo;
		createParentsList();
	}
	
	/*
	 * This method creates a list of parents.
	 */
	public void createParentsList() {
	    myParent1 = getParentOne().getName();
		myParent2 = getParentTwo().getName();
		parentsList.add(myParent1);
		parentsList.add(myParent2);
	}
	
	
	/*
	 * This method returns the list of parents created in createParentsList()
	 */
	public ArrayList<String> getParentsList() {
		return parentsList;
	}

	/*
	 * This method returns the name of the first parent of the object
	 */
	public String getMyParent1() {
		return myParent1;
	}

	/*
	 * This method returns the name of the second parent of the object
	 */
	public String getMyParent2() {
		return myParent1;
	}
	
//	see if this is necessary
	public ProfileDataBase getPDBTwo() {
		return pdbTwo;
	}
	
	/*
	 * This method checks whether the parents passed in during the creation of the object
	 * has other children. It checks the names of the parents against parents of all 
	 * other children. It returns the results in an array list of type string containing
	 * words true and false. True indicates that the two parents are mutually exclusive
	 * and false indicates that they are not[This method is used after the creation of
	 * a child object and the object is removed if exclusivity is not established - see
	 * addChild() in MiniNetMain for clarification].
	 */
	public ArrayList<String> checkParents(Adult parentOne,Adult parentTwo) {
		ArrayList<Profile> objectsList = pdbTwo.getAllProfilesValues();
		ArrayList<String> resultsList = new ArrayList<String>();
		
		for(int i = 0;i<objectsList.size();i++) {
			if(objectsList.get(i) instanceof Child) {
				if(parentOne == ((Child)(objectsList.get(i))).getParentOne() ||
						parentOne == ((Child)(objectsList.get(i))).getParentTwo())
					
					if(parentTwo == ((Child)(objectsList.get(i))).getParentOne() ||
							parentTwo == ((Child)(objectsList.get(i))).getParentTwo()) 
						resultsList.add("true");
					
					else 
						resultsList.add("false");
					
					 
				else if(parentTwo == ((Child)(objectsList.get(i))).getParentOne() ||
							parentTwo == ((Child)(objectsList.get(i))).getParentTwo())
							
							if((parentOne == ((Child)(objectsList.get(i))).getParentOne() ||
								parentOne == ((Child)(objectsList.get(i))).getParentTwo()))
						
								resultsList.add("true");
							else
								resultsList.add("false");
					
					 else if(parentOne != ((Child)(objectsList.get(i))).getParentOne() ||
								parentOne != ((Child)(objectsList.get(i))).getParentTwo()) {
							if(parentTwo != ((Child)(objectsList.get(i))).getParentOne() ||
									parentTwo != ((Child)(objectsList.get(i))).getParentTwo()) 
								resultsList.add("true");
							 
				}
						
				
			}
			 
		}
		return resultsList;
	}
	
	/*
	 * This method returns the object of parent one.
	 */
	public Adult getParentOne() {
		return parentOne;
	}
	
	/*
	 * This method returns the object of parent two.
	 */
	public Adult getParentTwo() {
		return parentTwo;
	}
	
	/*
	 *This method overrides the method inherited from Friend. It checks whether parents of
	 *a child object are the same as the parents of the calling child object. If the two
	 *children are not of the same parents it adds the child called to the friend list.
	 */
	public boolean addFriend(String friend) {
//		String childParentOne = getParentOne().getName();
//		String childParentTwo = getParentTwo().getName();;

		if(pdbTwo.getProfile(friend) instanceof Child){
			
			for(int i = 0;i < parentsList.size();i++) {
			}
			String parent1 = ((Child)pdbTwo.getProfile(friend)).getParentOne().getName();
			String parent2 = ((Child)pdbTwo.getProfile(friend)).getParentTwo().getName();
			if(parentsList.contains(parent1)&&parentsList.contains(parent2)) {
				System.out.println("You can not add your siblings as a friend");
				return false;
			}else {
				if(( getFriendsList()).contains(friend)) {
					return false;
				}
				else{
					getFriendsList().add(friend);
//					System.out.println("successful");
					return true;
				}
			}	
			
		}else {
			return false;
		}
	}
	
//	public boolean addFriend(String friend) {
//		if(( getFriendsList()).contains(friend)) {
//			return false;
//		}
//		else{
//			getFriendsList().add(friend);
//			return true;
//		}
//	}
//
//	 if(newFriend instanceof Child) {
// 	    ArrayList<String> newParents = ((Child) newFriend).getParents();
// 	    for(String p : currentParents) {
//	    	   if(newParents.contains(p)) {
//	    		   System.out.println("You can not add your siblings as friends");
//	    	    }else {
//	    	    	    getFriendsList().add(friend);
//	    	    }
//	    }
// } 	  	
		
	/*
	 *This method removes the friend's name from the calling objects friend list.
	 */
	public boolean removeFriend(String friend) {
		if(getFriendsList().contains(friend)) {
			getFriendsList().remove(getFriendsList().indexOf(friend));
		return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * This method returns the list of friends.
	 */
	public ArrayList <String> getFriendsList() {
		return super.getFriendsList();
	}
	
	/*
	 * Checks whether a given name is of type child
	 */
	public boolean isBaby(String name) {
		if(pdbTwo.getProfile(name) instanceof Child) {
			return true;
		}
		else
			return false;
	}
	
	

}

