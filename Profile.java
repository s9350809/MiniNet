
import java.util.*;

public abstract class Profile {
	
	/* Private instance variables*/
	private String name = "";
	private String image = "";
	private String status = "No current status";
	private int age = 0;
	private ArrayList <String> friends = new ArrayList<String>();
	private ArrayList <String> children = new ArrayList<String>();
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public Profile(String name, String image, String status, int age, ArrayList<String> friends) {
		super();
		this.name = name;
		this.image = image;
		this.status = status;
		this.age = age;
		this.friends = friends;
	}
	
	public Profile(String name, String image, String status, int age, ArrayList<String> friends, ArrayList <String> children ) {
		super();
		this.name = name;
		this.image = image;
		this.status = status;
		this.age = age;
		this.friends = friends;
		this.children = children;
	}


	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return name;
	}

	

	/** 
	 * This method returns the image associated with the profile.  
	 * If there is no image associated with the profile, the method
	 * returns null. */ 
	public String getImage() {
		return image;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(String image) {
		this.image = image;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		return status;
	}
	
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
//	public ArrayList getFriends()

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	/*public boolean addFriend(String friend) {
		if(friends.contains(friend)) {
			return false;
		}
		else{
			friends.add(friend);
			return true;
		}
	} */

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	/*public boolean removeFriend(String friend) {
		if(friends.contains(friend)) {
			friends.remove(friends.indexOf(friend));
			return true;
		}
		else{
			return false;
		}
	} */

	public ArrayList <String> getFriendsList() {
		return friends;
	}
	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	/*public Iterator<String> getFriendsIterator() {
		return friends.iterator();
	} */

	public ArrayList <String> getChildren() {
		return children;
	}

	public void setChildren(ArrayList <String> children) {
		this.children = children;
	}
	
    public void addFriend(String friend) {
    	    
    }
	
	public void addFriend(String friend, ProfileDataBase profile) {
		
	}
	
	public void checkFriends(String friend) {
		
	}
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	/*public String toString() {
		String profile = Name + " (" + Status + "): ";
		Iterator<String>it = friends.iterator();
		while(it.hasNext()) {
			profile += it.next() + ", ";
		}
		return profile;
	} */ 
	
}