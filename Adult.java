import java.util.*;

public class Adult extends Profile implements Friend {
	
	public Adult(String name,String image,String status,int age) {
		super(name,image,status,age);
	}
	
	/*
	 * Explicitly defining default constructor
	 */
	public Adult() {
		
	}
	
	public boolean addFriend(String friend) {
		if(( getFriendsList()).contains(friend)) {
			return false;
		}
		else{
			getFriendsList().add(friend);
			return true;
		}
	}
	
	public boolean removeFriend(String friend) {

		if(getFriendsList().contains(friend)) {
		getFriendsList().remove(getFriendsList().indexOf(friend));
			return true;
		}
		else{
			return false;
		}
	}
	
	public Iterator<String> getFriendsIterator() {
		return getFriendsList().iterator();
	}

}
