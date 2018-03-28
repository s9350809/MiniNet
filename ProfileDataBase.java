import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * ProfileDatabase class is the database class of the MiniNet application. It provides 
 * functionality mainly to add a profile, delete a profile and to check whether a certain profile
 * exists in the database. It also has methods to get keys and values in the hashmap.
 * @Author Jalana Jayasinghe
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
		}else{
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
		}else{
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
	 * it's friends and updates partners friend and children lists and deletes children objects. 
	 */
	public void deleteProfile(String name) {
		ArrayList<String>parentsList = new ArrayList<String>();
		ArrayList<String> childrenList =  new ArrayList<String>();
		String husbandOrWife = "";
		String lastChild = "";
		String parentOne = "";
		String parentTwo = "";
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

			
				if(profilesHashMap.containsKey(lastChild))
				if( profilesHashMap.get(lastChild) instanceof Child) {
					Profile lastChildProfile = profilesHashMap.get(lastChild);
					parentsList = ((Child)lastChildProfile).getParentsList();
				}else
					if(profilesHashMap.get(lastChild) instanceof Baby) {
						Profile lastChildProfile = profilesHashMap.get(lastChild);
						parentsList = ((Baby)lastChildProfile).getParentsList();
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
			}else
				if(index == 0) {
				husbandOrWife = parentsList.get(0);
				}
			Profile husbandOrWifeProfile = profilesHashMap.get(husbandOrWife);
			childrenList = ((Adult)husbandOrWifeProfile).getChildrenList();

			for(int i = 0;i < childrenList.size();i++) {
				((Adult)husbandOrWifeProfile).removeChild(childrenList.get(i));
			}
			
			profilesHashMap.remove(name);
			System.out.println("Profile deleted");
			}else if(profileToRemove instanceof Child) {
				for(int i = 0; i < (((Child)profileToRemove).getFriendsList().size()); i++)
					{
						String friendName = ((Child)profileToRemove).getFriendsList().get(i);
						Profile friendsProfile = profilesHashMap.get(friendName);
						((Child)friendsProfile).removeFriend(name);
					}
				parentOne = ((Child)profilesHashMap.get(name)).getMyParent1();
				parentTwo = ((Child)profilesHashMap.get(name)).getMyParent2();

					Profile parentOneProfile = profilesHashMap.get(parentOne);
					((Adult)parentOneProfile).removeChild(name);
					Profile parentsTwoProfile = profilesHashMap.get(parentTwo);
					((Adult)parentsTwoProfile).removeChild(name);
				profilesHashMap.remove(name);
				System.out.println("Profile deleted");
		}else if(profileToRemove instanceof Baby) {
				parentOne = ((Baby)profilesHashMap.get(name)).getParentOne().getName();
				parentTwo = ((Baby)profilesHashMap.get(name)).getParentTwo().getName();
				Profile parentOneProfile = profilesHashMap.get(parentOne);
				((Adult)parentOneProfile).removeChild(name);
				Profile parentsTwoProfile = profilesHashMap.get(parentTwo);
				((Adult)parentsTwoProfile).removeChild(name);

				profilesHashMap.remove(name);
				System.out.println("Profile deleted");
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
}
