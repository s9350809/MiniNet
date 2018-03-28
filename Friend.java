import java.util.Iterator;
import java.util.ArrayList;

/*
 * This interface contains methods common to Adult and Child classes
 * @Author Da Teng 
 */
public interface Friend {
	
	public boolean addFriend(String friend);
	
	public boolean removeFriend(String friend);
	
	public ArrayList <String> getFriendsList();
	

}
