import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * ProfileDatabase class is the database class of the MiniNet application. It provides 
 * functionality mainly to add a profile, delete a profile and to check whether a certain profile
 * exists in the database. It also has methods to get keys and values in the hashmap.
 */
public class ProfileDataBase {
	
	private Map<String, Profile> profilesHashMap = new HashMap<String, Profile>();
	private ArrayList<Profile> objectsList = new ArrayList<Profile>(profilesHashMap.values());
	ProfileDataBase pdbFour =  MiniNetMain.getPDB();
	
//	private ArrayList <String> friends = new ArrayList<String>();
	
	/*
	 * Constructor initializes the instance variables to default values.
	 * Only one object of this class is created in MiniNetMain as one
	 * database must be maintained.
	 */
	
	public ProfileDataBase() {
	}
	
	/*
	 * This method returns the list of profiles saved in the database as an array list.
	 */
	public ArrayList<Profile> getObjectsList() {
		return objectsList;
	}

	/*
	 *This profile checks whether a profile with the name variable defined in the profile
	 *exists in the database and adds the profile to the database if the name is unique.
	 */
	public void addProfile(Profile profile) {
		if(!profilesHashMap.containsKey(profile.getName())) {
			profilesHashMap.put(profile.getName(), profile);
		}
		else{
			profilesHashMap.remove(profile.getName());
			profilesHashMap.put(profile.getName(), profile);
		}
	}

	/*
	 * This method returns the profile associated with a given name. 
	 */
	public Profile getProfile(String name) {
		if(profilesHashMap.containsKey(name)) {
			return profilesHashMap.get(name);
		}
		else{
			return null;
		}
		
	}
	
	/*
	 * This method returns the hashmap containing keys and values(the database object).
	 */
	public Map<String, Profile> getProfilesHashMap(){
		return profilesHashMap;
	}
	
	/*
	 * This method removes a profile from the database. Before removing the selected profile
	 * it checks the type of the profile and removes the profile's name from friend list of 
	 * it's friends. 
	 */
	public void deleteProfile(String name) {
		ArrayList<String>parentsList = new ArrayList<String>();
		ArrayList<String> childrenList =  new ArrayList<String>();
		String husbandOrWife = "";
		String lastChild = "";
		int index = 0;
		
		if(profilesHashMap.containsKey(name)) {
			Profile profileToRemove = profilesHashMap.get(name);
			if(profileToRemove instanceof Adult) {
			for(int i = 0; i < (((Adult)profileToRemove).getFriendsList().size()); i++)
				{
					String friendName = ((Adult)profileToRemove).getFriendsList().get(i);
					Profile friendsProfile = profilesHashMap.get(friendName);
					((Adult)friendsProfile).removeFriend(name);
				}
			
			childrenList = (((Adult)profileToRemove).getChildrenList());
			
			
			if(childrenList.isEmpty() == false) {
				
				lastChild = childrenList.get(childrenList.size()-1);
				System.out.println(lastChild);
				if(pdbFour.containsProfile("Layla") == false) {
					System.out.println("Profile  exists");
				}
				if( pdbFour.getProfile(lastChild) instanceof Child) {
					parentsList = ((Child)pdbFour.getProfile(lastChild)).getParentsList();
				}
				else
					if(pdbFour.getProfile(lastChild) instanceof Baby) {
						parentsList = ((Baby)pdbFour.getProfile(lastChild)).getParentsList();
					} 
			for(int i = 0;i < childrenList.size();i++) {
				profilesHashMap.remove(childrenList.get(i));
			}
				
			}
			
			for(int i = 0;i < parentsList.size();i++) {
				if(name.equals(parentsList.get(i))) {
					index = i;
				}
			}
			
			if(index == 0) {
				husbandOrWife = parentsList.get(1);
			}
			else
				if(index == 0) {
				husbandOrWife = parentsList.get(0);
				}
			Profile husbandOrWifeProfile = profilesHashMap.get(husbandOrWife);
			((Adult)husbandOrWifeProfile).getChildrenList().clear();
			
//			modify child and baby cases
			profilesHashMap.remove(name);
			}
			else if(profileToRemove instanceof Child) {
				for(int i = 0; i < (((Child)profileToRemove).getFriendsList().size()); i++)
					{
						String friendName = ((Child)profileToRemove).getFriendsList().get(i);
						Profile friendsProfile = profilesHashMap.get(friendName);
						((Child)friendsProfile).removeFriend(name);
					}
				profilesHashMap.remove(name);
		}
			else if(profileToRemove instanceof Baby) {
				profilesHashMap.remove(name);
		}
	}
}

	/*
	 * This method return true if a profile by a given name exists in the database.
	 */
	public boolean containsProfile(String name) {
		if(profilesHashMap.containsKey(name)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Prints out names of all profiles in the database
	 */
	public void getAllProfiles() {
		profilesHashMap.forEach((key, value) -> {
		      System.out.println(key);
		    });
	}
	
	/*
	 * This method returns all the profiles in the database in an array list.
	 */
	public ArrayList <Profile> getAllProfilesValues() {
		ArrayList <Profile> allProfiles = new ArrayList <Profile>();
		profilesHashMap.forEach((key, value) -> {
			allProfiles.add(value);
		    });
		return allProfiles;
	}
	
//	check if this method is needed
	public ArrayList<String> getAllProfilesTwo() {
		ArrayList <String> allProfileNames = new ArrayList<String>(profilesHashMap.keySet());

//		profilesHashMap.forEach((key, value) -> {
//		      System.out.println(key);
//		      allProfileNames.add(key);
//		      System.out.println("Key : " + key + " Value : " + value);
		for(int i = 0;i<allProfileNames.size();i++) {
			System.out.println(allProfileNames.get(i));
		}
		      return allProfileNames;
	}
	
	
	
	public void iterateHashMap() {
//		for (Profile value : profilesHashMap.values()) {
//		    System.out.println("Value = " + value);
		System.out.println(profilesHashMap.size());
		
	}

}
