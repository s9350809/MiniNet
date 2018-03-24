import java.util.ArrayList;
import java.util.Iterator;

/*
 * The main function of this class is to create an object of adult type. It extends Profile
 * and implements Friend. It defines the methods inherited from the Friend interface to add
 * and remove friends and get friends list. 
 * 
 */
public class Adult extends Profile implements Friend {
	private ArrayList <String> children = new ArrayList<String>();

	public Adult(String name,String image,String status,int age) {
		super(name,image,status,age);
	}
	
	/*
	 * Explicitly defining default constructor
	 */
	public Adult() {
		
	}
	
	/*
	 * This method checks whether a given name is contained in the friend list of the 
	 * calling object and adds the name to the list if it doesn't contain the name 
	 * passed in
	 */
	public boolean addFriend(String friend) {
		if(( getFriendsList()).contains(friend)) {
			return false;
		}
		else{
			getFriendsList().add(friend);
			return true;
		}
	}
	
	/*
	 *This method removes the given name from the friend list of the calling object
	 *if it exists.
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
	 * This method returns the list of friends of the calling object.
	 */
	public ArrayList <String> getFriendsList() {
		return super.getFriendsList();
	}
	
	/*
	 * This method checks whether a given name is contained in the children list 
	 * of the calling object and adds the name to the list if it doesn't contain
	 * the name passed in
	 */
	public boolean addChild(String child) {
		if(( getChildrenList()).contains(child)) {
			return false;
		}
		else{
			getChildrenList().add(child);
			return true;
		}
	}
	
	/*
	 *This method removes the given name from the children list of the calling object
	 *if it exists.
	 */
	public boolean removeChild(String child) {

		if(getChildrenList().contains(child)) {
		getChildrenList().remove(getChildrenList().indexOf(child));
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * This method returns the list of children of the calling object.
	 */
	public ArrayList <String> getChildrenList() {
		return children;
	}

}

