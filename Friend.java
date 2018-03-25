import java.util.*;

/*
 * This class defines all the functional method of adding friends into social network.
 *  @Author Da Teng
 */

public interface Friend {
	public boolean addFriend(String friend);
	
	public boolean removeFriend(String friend);
	
	public ArrayList <String> getFriendsList();

}
