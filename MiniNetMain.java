import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * This class MiniNetMain is the central of all classes. 
 * All the classes will be call and run through from this class.
 * @ author Da Teng
 */
public class MiniNetMain {
	private String name;
	private String image;
	private String status;
	private int age;
	private Scanner mainConsole = new Scanner(System.in);
	private static ProfileDataBase pdb = new ProfileDataBase();
	private Keypad keypad = new Keypad();
	
//	Hard coding a few initial profiles
	Profile a1 = new  Adult("Kavinda","No Image","Coding",36);
	Profile a2 = new  Adult("Susan","No Image","Relaxing",33);
	Profile a3 = new  Adult("Adam","No Image","Working",20);
	Profile a4 = new  Adult("Chen","No Image","On a Break",21);
	Profile a5 = new  Adult("Hamid","No Image","Driving",22);
	Profile c1 = new  Child("Gayan","No Image","Playing",10,((Adult)a1),((Adult)a2));
	Profile c2 = new  Child("Li","No Image","Home Work",12,((Adult)a1),((Adult)a2));
	Profile c3 = new  Child("Andy","No Image","Home Work",12,((Adult)a3),((Adult)a4));
	Profile b1 = new Baby("Layla","No Image","Crying",1,((Adult)a3),((Adult)a4));
	Profile b2 = new Baby("Fanaz","No Image","Bathing",1,((Adult)a3),((Adult)a4));

	 /*
	 * Constructor adds the hard coded profiles to the database makes initial connections. 
	 */
	public MiniNetMain() {
		image = "No Image";
		status = "No Status";
		pdb.addProfile(a1);
		pdb.addProfile(a2);
		pdb.addProfile(a3);
		pdb.addProfile(a4);
		pdb.addProfile(a5); 
		pdb.addProfile(c1);
		pdb.addProfile(c2);
		pdb.addProfile(c3);
		pdb.addProfile(b1);
		pdb.addProfile(b2);
		
		((Adult)pdb.getProfile("Kavinda")).addFriend("Susan");
		((Adult)pdb.getProfile("Susan")).addFriend("Kavinda");
		((Adult)pdb.getProfile("Adam")).addFriend("Chen");
		((Adult)pdb.getProfile("Chen")).addFriend("Adam");
		((Child)pdb.getProfile("Li")).addFriend("Andy");
		((Child)pdb.getProfile("Andy")).addFriend("Li");
		
		((Adult)pdb.getProfile("Kavinda")).addChild("Gayan");
		((Adult)pdb.getProfile("Kavinda")).addChild("Li");
		((Adult)pdb.getProfile("Susan")).addChild("Gayan");
		((Adult)pdb.getProfile("Susan")).addChild("Li");
		((Adult)pdb.getProfile("Adam")).addChild("Andy");
		((Adult)pdb.getProfile("Adam")).addChild("Layla");
		((Adult)pdb.getProfile("Chen")).addChild("Andy");
		((Adult)pdb.getProfile("Chen")).addChild("Layla");
		
	}
	
	/*
	 * to get an instance of the database for use in other classes
	 */
	public static ProfileDataBase getPDB() {
		return pdb;
	}
	
	/*
	 * This is the main display menu.
	 */
	public int displayMainMenu() {		
		System.out.println("\n=====Welcome to MiniNet=====\n");
		System.out.println("1. List Everyone on MiniNet");
		System.out.println("2. Add Profile");
		System.out.println("3. Select Profile");
		System.out.println("4. Exit the application\n");
		System.out.print("Enter your choice (1-4) : ");
		return keypad.getInt();	
	}
	
	
	/*
	 *This is the method initially called by the MiniNetMain object created in MiniNetApp 
	 */
	public void run() {
		int mainMenuSelection = displayMainMenu();
		switch(mainMenuSelection) {
		case 1 : 
			pdb.getAllProfiles();
			run();
			break;
			
		case 2 : 
			chooseProfileType();
			break;
			
		case 3: 
			selectProfile();
			break;
			
		case 4 :
			System.out.println("Exit the application.");
			break;
			
		default :
			System.out.println("\nYou did not enter a valid selection. Try again");
			run();
			break;
		}
		
	}/*end run()*/
	
//	Case LIST: uses getAllProfiles() method from ProfileDataBase Class

//	Case ADD:
	
	/*
	 * This menu is called when add profile is chosen from the main menu
	 */
	public void chooseProfileType() {
		System.out.println("\n");
		System.out.println("---------------------\n");
		System.out.println("1. Adult Profile - Over 16 Years");
		System.out.println("2. Child Profile - Over 2 Year Under 16 Years");
		System.out.println("3. Baby Profile - Under 2 Years");
		System.out.print("Enter your choice (1-3) : ");
		int choice = keypad.getInt();
		switch(choice) {
		case 1:
			addAdult();
			selectProfile();
			break;
			
		case 2:
			addChild();
			selectProfile();
			break;
		
		case 3:
			addBaby();
			selectProfile();
			break;
		}
	}

	/*
	 * This method adds an adult profile to the database	
	 */
	public void addAdult() {
	    System.out.println("Enter Name");
		name = keypad.getString();	
		do {
		System.out.println("Enter Age - Must be greater than 16");
		age = keypad.getInt();
		}while(age < 17);
		System.out.println("Enter Status");
		status = keypad.getString();
		if(pdb.containsProfile(name) == false) {
			Profile profile = new Adult(name,image,status,age);
			pdb.addProfile(profile);
			System.out.println("New adult profile created");
		}else {
			System.out.println("A profile with name " + name + " already exists.");
		}
		run();
	}
	
	/*
	 * This method validates if a name entered is in the database
	 */
	public boolean validate(String inputName) {
		if(pdb.getProfilesHashMap().containsKey(inputName)==false){
			System.out.println("You input the wrong name, please reinput the name.");
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * This method validates if a name entered is in the data base and if it is an instance of Adult
	 */
	public boolean validateChildBaby(String name) {
		if(pdb.getProfilesHashMap().containsKey(name)==false && pdb.getProfilesHashMap().get(name) instanceof Adult == false){
			System.out.println("You input the wrong name, please reinput the name.");
			return false;
		}else {
			return true;
		}
		
	}
	
	/*
	 * This method validates if parentname entered is in the data base and if it is an instance of Adult
	 */
	public boolean validateAddChildBaby(String parentName) {
		if(pdb.getProfilesHashMap().containsKey(parentName) == false && pdb.getProfile(parentName) instanceof Adult == false) {
			System.out.println("You input the wrong name, please reinput the name.");
			return false;
		}else {
			return true;
		}
	}

	
	/*
	 * This method adds a child to the database
	 */
	public void addChild() {
		String parent1;
		String parent2;
		Profile parentOne = new Adult("","","",100);
		Profile parentTwo = new Adult("","","",101);
		ArrayList<String> resultsList = new ArrayList<String>();
		System.out.println("Enter Name");
		name = keypad.getString();
		System.out.println("Enter Age - Must be less than 16 Greater than 2");
		age = keypad.getInt();
		System.out.println("Enter Status");
		status = keypad.getString();
		System.out.println("Enter name of parent one");
		parent1 = keypad.getString();
		if(pdb.getProfilesHashMap().containsKey(parent1)) {
			parentOne = pdb.getProfile(parent1);
		}
		System.out.println("Enter name of parent two");
		parent2 = keypad.getString();
		if(pdb.getProfilesHashMap().containsKey(parent2)) {
			parentTwo = pdb.getProfile(parent2);
		}
//		A new child profile is created
		if(pdb.getProfilesHashMap().containsKey(name) == false) {
			Profile profile = new Child(name,image,status,age,(Adult)parentOne,(Adult)parentTwo);
			pdb.addProfile(profile);	
		}
		else {
			 /*If the new child has parents that have kids with other adults	*/
			resultsList = ((Child)pdb.getProfile(name)).checkParents((Adult)parentOne, (Adult)parentTwo);
			if(resultsList.contains("false")) {
				/*The newly created profile is deleted*/
				pdb.deleteProfile(name);    
				System.out.println("One of the parents has a child with another adult");
			}else {
				 /*Else the new child is added to the children lists of the parents*/
				System.out.println("New child   profile created"); 
				((Adult)parentOne).addChild(name);
				((Adult)parentTwo).addChild(name);
			}
		}
		run();			
	}
	
	/*
	 * This method adds a baby to the database
	 */
	public void addBaby() {
			String parent1;
			String parent2;
			Profile parentOne = new Adult("","","",100);
			Profile parentTwo = new Adult("","","",101);
			ArrayList<String> resultsList = new ArrayList<String>();
			System.out.println("Enter Name");
			name = keypad.getString();
			System.out.println("Enter Age - Must be less than 2");
			age = keypad.getInt();
			System.out.println("Enter Status");
			status = keypad.getString();
			System.out.println("Enter name of parent one");
			parent1 = keypad.getString();
			if(pdb.containsProfile(parent1)) {
				parentOne = pdb.getProfile(parent1);
			}
			System.out.println("Enter name of parent two");
			parent2 = keypad.getString();
			if(pdb.containsProfile(parent2)) {
				parentTwo = pdb.getProfile(parent2);
			}
			if(pdb.containsProfile(name) == false) {
				Profile profile = new Baby(name,image,status,age,(Adult)parentOne,(Adult)parentTwo);
				pdb.addProfile(profile);
			}else {
			}
			resultsList = ((Baby)pdb.getProfile(name)).checkParents((Adult)parentOne, (Adult)parentTwo);
			if(resultsList.contains("false")) {
				pdb.deleteProfile(name);
				System.out.println("One of the parents has a child with another adult");
			}else {
				System.out.println("New Baby profile created");
				((Adult)parentOne).addChild(name);
				((Adult)parentTwo).addChild(name);
			}
			run();	
		}


	/*
	 * This method to run the menu for option three in main menu
	 */
	public void selectProfileMunuGetName() {
		do {
		System.out.println("Enter Name");
		name = keypad.getString();
			if(pdb.containsProfile(name) == true) {
				break;
			}
		}while(pdb.containsProfile(name) == false);
	}
	


		
	/*
	 * Once a profile is selected this menu is displayed
	 */
	public int selectProfileMenu() {
		System.out.println("\n");
		System.out.println("---------------------\n");
		System.out.println("1. Display Profile");
		System.out.println("2. Update Profile");
		System.out.println("3. Delete Profile");
		System.out.println("4. Check Friend Status");
		System.out.println("5. Get Friend List");
		System.out.println("6. Add Friend");
		System.out.println("7. Remove Friend");
		System.out.println("8. Parents' Names");
		System.out.println("9. Children's Names");
		System.out.println("10. Go back to Previous Menu");
		System.out.print("\nEnter your choice (1-10) : ");
		return keypad.getInt();
    }
	public void selectProfile() {
		System.out.println("-------------------");
		selectProfileMunuGetName();
		int choice = selectProfileMenu();
		switch(choice) {
		case 1:
			System.out.println();
			pdb.getProfile(name).displayProfileInfo();
			run();
			break;
			
		case 2:
			System.out.println();
			updateProfileInfo();
			break;
			
		case 3:
			System.out.println();
			deleteProfile();
			run();
			break;
			
		case 4:
			System.out.println();
			checkFriendStatus(name);
			run();
			break;
			
		case 5:
			System.out.println();
			getFriendList();
			run();
			break;
			
		case 6:
			System.out.println();
			addFriend();
			run();
			break;
			
		case 7:
			System.out.println();
			removeFriend();
			run();
			break;
			
		case 8:
			System.out.println();
			getParentsNames();
			run();
			break;
			
		case 9:
			System.out.println();
			getChildrenNames();
			run();
			break;
			
		case 10:
			System.out.println();
			run();
			break;
			
		default:
			System.out.println("\nYou enter invalid choice. Try again");
			run();
			break;
		}
	}//end selectProfile()
	
	/*
	 * This method displays options for updating a profile
	 */
	public void updateProfileInfo() {
		System.out.println("\n");
		System.out.println("---------------------\n");
		System.out.println("1. Update Status");
		System.out.println("2. Update Name");
		System.out.println("3. Update Image");
		System.out.println("4. Update Age");
		System.out.println("5. Go Back to Main Menu");
		System.out.print("Enter your choice (1-5) : ");
		int choice = keypad.getInt();
		
		switch(choice) {
		case 1:
			updateStatus();
			updateProfileInfo();
			break;
			
		case 2:
			updateName();
			updateProfileInfo();
			break;
			
		case 3:
			updateImage();
			updateProfileInfo();
			break;
			
		case 4:
			updateAge();
			updateProfileInfo();
			break;
			
		case 5:
			run();
			break;
		
		default:
			System.out.println("\nYou enter invalid choice. Try again");
			updateProfileInfo();
			break;
		}
	}//end updateProfileInfo()

//	Select profile case 2:
	/*
	 * This method updates name
	 */
	public void updateName() {
		String newName;
		System.out.println("Enter New Name");
		newName = keypad.getString();

		while(pdb.containsProfile(newName) == true) {
			System.out.println("Entered name exists, choose a different name");
			newName = keypad.getString();
			}
		pdb.getProfile(name).setName(newName);//
		
	}

//	Select profile case 1:
	/*
	 * This method updates status
	 */
	public void updateStatus() {
		String newStatus;
		System.out.println("Enter New Status");
		newStatus = keypad.getString();
		pdb.getProfile(name).setStatus(newStatus);
	}
	
	/*
	 * This method updates image
	 */
	public void updateImage() {
		String newImage;
		System.out.println("Enter New Image");
		newImage = keypad.getString();
		pdb.getProfile(name).setImage(newImage);
	}
	
	/*
	 * This method updates age
	 */
	public void updateAge() {
		int newAge;
		System.out.println("Enter New Age");
		newAge = keypad.getInt();
		pdb.getProfile(name).setAge(newAge);
	}
	
//	Select profile case 3:
	/*
	 * This method deletes a profile
	 */
	public void deleteProfile() {
		pdb.deleteProfile(name);
	}

//	Select profile case 4:
	/*
	 * This method checks friend status with another profile
	 */
	public void checkFriendStatus(String name) {
		String friendName;
		System.out.println("Enter name to check");
		friendName = keypad.getString();
		if(pdb.getProfile(name).getFriendsList().contains(friendName)){
			System.out.println("Yes "+friendName+" is a friend");
		}else {
			System.out.println("No "+friendName+" is not a friend");
		}
	}

//	Select profile case 5:
	/*
	 * This method displays the list of friends a profile has
	 */
	public void getFriendList() {
		if(pdb.getProfile(name) instanceof Adult) {
		    if(pdb.getProfile(name).getFriendsList().isEmpty() == true){
			System.out.println(name+" has no friends");
		    }else {
			    ArrayList<String> friends = ((Adult)pdb.getProfile(name)).getFriendsList();
			    for(int i = 0;i < friends.size();i++) {
				System.out.println(friends.get(i));
			    }
		     }
		}else {
		if(pdb.getProfile(name) instanceof Child) {
			if(pdb.getProfile(name).getFriendsList().isEmpty() == true){
				System.out.println(name+" has no friends");
			}else {
			ArrayList<String> friends = ((Child)pdb.getProfile(name)).getFriendsList();
			for(int i = 0;i < friends.size();i++) {
					System.out.println(friends.get(i));
			}
		}
		}
		if(pdb.getProfile(name) instanceof Baby) {
				System.out.println("Babies can't have friends");
		}
		}				
	}

	
//	Select profile case 6:
	/*
	 * This method adds a friend to the friend list of a profile
	 */
	public void addFriend() {
		String friendName = "";
		boolean flag;
		if(pdb.getProfile(name) instanceof Adult) {
			do {
				System.out.println("Enter name of friend to add");
				friendName = keypad.getString();
				flag = validate(friendName);
			}while(flag == false);
			((Adult)pdb.getProfile(name)).addFriend(friendName);
			((Adult)pdb.getProfile(friendName)).addFriend(name);
		}
		if(pdb.getProfile(name) instanceof Child) {
			do {
				System.out.println("Enter name of friend to add");
				friendName = keypad.getString();
				flag = validateChildBaby(friendName);
			}while(flag == false);
			((Child)pdb.getProfile(name)).addFriend(friendName);
			((Child)pdb.getProfile(friendName)).addFriend(name);
		}
		else if(pdb.getProfile(name) instanceof Baby && pdb.getProfile(friendName) instanceof Baby){
		System.out.println("Babies can't have friends");
		}
		else if(pdb.getProfile(name) instanceof Adult && pdb.getProfile(friendName) instanceof Child) {
			System.out.println("Children can only have children as friends on MiniNet");
		}
	    else if(pdb.getProfile(name) instanceof Adult && pdb.getProfile(friendName) instanceof Baby) {
			System.out.println("Babies can't have friends");
		}
		else if(pdb.getProfile(name) instanceof Child && pdb.getProfile(friendName) instanceof Adult) {
			System.out.println("Children can only have children as friends on MiniNet");
		}
		else if(pdb.getProfile(name) instanceof Child && pdb.getProfile(friendName) instanceof Baby) {
			System.out.println("Children can only have children as friends on MiniNet");
			System.out.println("Babies can't have friends");
		}
		else if(pdb.getProfile(name) instanceof Baby && pdb.getProfile(friendName) instanceof Child) {
			System.out.println("Children can only have children as friends on MiniNet");
			System.out.println("Babies can't have friends");
		}
		else if(pdb.getProfile(name) instanceof Baby && pdb.getProfile(friendName) instanceof Adult) {
			System.out.println("Babies can't have friends");
		}
		
		
	}
//	Select profile case 7:
	/*
	 * This method removes a friend from a friend list of a profile
	 */
	public void removeFriend() {
		String friendName;
		System.out.println("Enter name of friend to remove");
		friendName = keypad.getString();
		if(pdb.getProfile(name) instanceof Adult && pdb.getProfile(friendName) instanceof Adult) {
			((Adult)pdb.getProfile(name)).removeFriend(friendName);
			((Adult)pdb.getProfile(friendName)).removeFriend(name);
			System.out.println(name+" and "+friendName+" are not friends anymore");
		}
		else if(pdb.getProfile(name) instanceof Child && pdb.getProfile(friendName) instanceof Child) {
			((Child)pdb.getProfile(name)).removeFriend(friendName);
			((Child)pdb.getProfile(friendName)).removeFriend(name);
			System.out.println(name+" and "+friendName+" are not friends anymore");
		}
		else if(pdb.getProfile(name) instanceof Baby) {
		System.out.println("Babies don't have friends");
		}
	}
	
//	Select profile case 8:
	/*
	 * This method gets the names of parents of a child or baby object
	 */
	public void getParentsNames() {
		if(pdb.getProfile(name) instanceof Child) {
			System.out.println(((Child)pdb.getProfile(name)).getParentOne().getName()+" "+
					((Child)pdb.getProfile(name)).getParentTwo().getName());
		}
		else if(pdb.getProfile(name) instanceof Baby) {
			System.out.println(((Baby)pdb.getProfile(name)).getParentOne().getName()+" "+
					((Baby)pdb.getProfile(name)).getParentTwo().getName());
		}
		else
			System.out.println("This is an adult");
	}
	
//	Select profile case 9:
	/*
	 * This method gets the names of children of an adult object
	 */
	public void getChildrenNames() {
		ArrayList<Profile> objectsList = pdb.getAllProfilesValues();
		if(pdb.getProfile(name) instanceof Adult) {
			for(int i = 0;i < objectsList.size();i++)
				if((objectsList.get(i) instanceof Child)) {
					String my1 = ((Child)objectsList.get(i)).getParentOne().getName();
					String my2 = ((Child)objectsList.get(i)).getParentTwo().getName();
					if((my1.equals(name) || 
							(my2.equals(name))))
						System.out.println(objectsList.get(i).getName());
				}else 
					if((objectsList.get(i) instanceof Baby)) {
						String my1 = ((Baby)objectsList.get(i)).getParentOne().getName();
						String my2 = ((Baby)objectsList.get(i)).getParentTwo().getName();
						if((my1.equals(name) || 
								(my2.equals(name))))
							System.out.println(objectsList.get(i).getName());	
					}
		}else 
			if(pdb.getProfile(name) instanceof Child) {
				System.out.println(name+" is a child");
			}else
			if(pdb.getProfile(name) instanceof Baby) {
				System.out.println(name+" is a baby");
			}	
	}	
}

