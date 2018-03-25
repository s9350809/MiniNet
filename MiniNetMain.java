import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class MiniNetMain is the central of all classes. 
 * All the classes will be call and run through from this class.
 *  @Author Da Teng
 */

public class MiniNetMain {
	private Keypad keypad;
	private String name;
	private String image;
	private String status;
	private int age;
	private Scanner mainConsole;
	private static ProfileDataBase pdb = new ProfileDataBase();
	
//	Hard coding a few initial profiles
	Profile a1 = new  Adult("Kavinda","No Image","Coding",36);
	Profile a2 = new  Adult("Susan","No Image","Relaxing",33);
	Profile a3 = new  Adult("Adam","No Image","Working",20);
	Profile a4 = new  Adult("Chen","No Image","On a Break",21);
	Profile a5 = new  Adult("Hamid","No Image","Driving",22);
	Profile c1 = new  Child("Gayan","No Image","Playing",10,((Adult)a1),((Adult)a2));
	Profile c2 = new  Child("Li","No Image","Home Work",12,((Adult)a1),((Adult)a2));
	Profile c3 = new  Child("Andy","No Image","Home Work",12,((Adult)a3),((Adult)a4));

	/*
	 * Constructor method
	 */
	
	public MiniNetMain() {
		image = "No Image";
		status = "No Status";
		keypad = new Keypad();
		mainConsole = new Scanner(System.in);
		pdb.addProfile(a1);
		pdb.addProfile(a2);
		pdb.addProfile(a3);
		pdb.addProfile(a4);
		pdb.addProfile(a5); 
		pdb.addProfile(c1);
		pdb.addProfile(c2);
		pdb.addProfile(c3);		
	}
	
	/*
	 * to get an instance of the database for use in other classes
	 */
	
	public static ProfileDataBase getPDB() {
		return pdb;
	}
	
	/*
	 * This method is to run the application for the first time
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
		
	}

	
	/*
	 * Display main menu
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
	 * Function method to add adult to the social network
	 */
	
	public void addAdult() {
		System.out.println("Enter Name");
		name = keypad.getString();
		System.out.println("Enter Age");
		age = keypad.getInt();
		System.out.println("Enter Status");
		status = keypad.getString();
		System.out.println(pdb.containsProfile(name));	
		if(pdb.containsProfile(name) == false) {
			Profile profile = new Adult(name,image,status,age);
			pdb.addProfile(profile);
			System.out.println("New profile created");
		}else {
			System.out.println("A profile with name " + name + " already exists.");
		}
		run();
	}	
	
	/*
	 * Function method to add child to the social network
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
		if(pdb.containsProfile(parent1)) {
			parentOne = pdb.getProfile(parent1);
		}
		System.out.println("Enter name of parent two");
		parent2 = keypad.getString();
		if(pdb.containsProfile(parent2)) {
			parentTwo = pdb.getProfile(parent2);
		}
		if(pdb.containsProfile(name) == false) {
			Child child = new Child(name,image,status,age,(Adult)parentOne,(Adult)parentTwo);
			System.out.println("after child created");
			pdb.addProfile(child);
			System.out.println("New profile created");
		}else {
			System.out.println("A profile with name " + name + " already exists.");
		}
		resultsList = ((Child)pdb.getProfile(name)).checkParents((Adult)parentOne, (Adult)parentTwo);
		if(resultsList.contains("false")) {
			pdb.deleteProfile(name);
			System.out.println("One of the parents has a child with another adult");
		}
		else
			System.out.println("New child profile created");
		
		run();
			
	}
	
	/*
	 * Function method to add baby to the social network
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
			mainConsole.nextLine();
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
				System.out.println("b4 child created");
				Profile profile = new Child(name,image,status,age,(Adult)parentOne,(Adult)parentTwo);
				System.out.println("after child created");
				pdb.addProfile(profile);
				System.out.println("New profile created");
			}
			else {
				System.out.println("A profile with name " + name + " already exists.");
			}
			resultsList = ((Child)pdb.getProfile(name)).checkParents((Adult)parentOne, (Adult)parentTwo);
			resultsList.remove(name);
			if(resultsList.contains("false")) {
				pdb.deleteProfile(name);
				System.out.println("One of the parents has a child with another adult");
			}else
				System.out.println("New child   profile created");
			
			run();			
		}

	/*
	 * This method to run the menu for option three in main menu
	 */
	
	public void selectProfile() {
		System.out.println("-------------------");
		selectProfileMunuGetName();
		int choice = selectProfileMenu();

		switch(choice) {
		case 1:
			System.out.println();
			pdb.getProfile(name).displayProfileInfo();
			choice = selectProfileMenu();
			break;
		case 2:
			System.out.println();
			updateProfileInfo();
			break;
		case 3:
			System.out.println();
			break;
		case 4:
			System.out.println();
			checkFriendStatus(name);
			run();
			break;
		case 5:
			System.out.println();
			addFriend();
			run();
			break;
		case 6:
			System.out.println();
			getParentsNames();
			selectProfile();
			break;
		case 7:
			System.out.println();
			getChildrenNames();
			selectProfile();
			break;
		case 8:
			System.out.println();
			run();
			break;
		default:
			System.out.println("\nYou enter invalid choice. Try again");
			run();
			break;
		}
    }
	
	/*
	 * Function method to select profile in the social network
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
	 * Menu after selecting a special profile
	 */
	
	public int selectProfileMenu() {	
		System.out.println("\n");
		System.out.println("---------------------\n");
		System.out.println("1. Display Profile");
		System.out.println("2. Update Profile");
		System.out.println("3. Delete Profile");
		System.out.println("4. Check Friend Status");
		System.out.println("5. Add Friend");
		System.out.println("6. Parents' Names");
		System.out.println("7. Children's Names");
		System.out.println("8. Go back to Previous Menu");
		System.out.print("\nEnter your choice (1-8) : ");
		return keypad.getInt();
	}
    
	/*
	 * Menu of updating profile information
	 */
	
	public void updateProfileInfo() {
		System.out.println("\n");
		System.out.println("---------------------\n");
		System.out.println("1. Update Name");
		System.out.println("2. Update Status");
		System.out.println("3. Update Image");
		System.out.println("4. Update Age");
		System.out.println("5. Go Back to Previous Menu");
		System.out.println("6. Go Back to Main Menu");
		System.out.print("Enter your choice (1-5) : ");
		int choice = keypad.getInt();
		switch(choice) {
		case 1:
			updateName();
			break;
			
		case 2:
			updateStatus();
			break;
			
		case 3:
			updateImage();
			break;
			
		case 4:
			updateAge();
			break;
			
		case 5:
			selectProfile();
			break;
		
		case 6:
			run();
		}
	}
	
	/*
	 * Function method to add friend in the social network
	 */
	
	public void addFriend() {
		String friendName;
		System.out.println("Enter name of friend to add");
		friendName = keypad.getString();
		if(pdb.getProfile(name) instanceof Adult && pdb.getProfile(friendName) instanceof Adult) {
			((Adult)pdb.getProfile(name)).addFriend(friendName);
			((Adult)pdb.getProfile(friendName)).addFriend(name);

		}else if(pdb.getProfile(name) instanceof Child && pdb.getProfile(friendName) instanceof Child) {
			((Child)pdb.getProfile(name)).addFriend(friendName);
			((Child)pdb.getProfile(friendName)).addFriend(name);

		}	
	}
    
	/*
	 * Function method to check if a person is a friend
	 */
	
	public void checkFriendStatus(String name) {
		String friendName;
		System.out.println("Enter name to check");
		friendName = keypad.getString();
		if(pdb.getProfile(name).getFriendsList().contains(friendName)){
			System.out.println("Yes "+friendName+" is a friend");
		}else {
			System.out.println("Yes "+friendName+" is not a friend");
		}
	}
	
	/*
	 * Function method to get parents name
	 */
	
	public void getParentsNames() {
		if(pdb.getProfile(name) instanceof Child) {
			System.out.println(((Child)pdb.getProfile(name)).getMyParent1()+" "+
					((Child)pdb.getProfile(name)).getMyParent2());
		}else
			System.out.println("This is an adult");
	}
	
	/*
	 * Function method to get children name
	 */
	
	public void getChildrenNames() {
		ArrayList<Profile> objectsList = pdb.getAllProfilesValues();
		for(int i = 0;i < objectsList.size();i++)
		if(objectsList.get(i) instanceof Child) {
			String my1 = ((Child)objectsList.get(i)).getMyParent1();
			String my2 = ((Child)objectsList.get(i)).getMyParent2();
			if(my1 == name || 
					my2 == name){
				System.out.println(objectsList.get(i).getName());
				}
			}	
	}
	
	/*
	 * Function method to update name of the profile
	 */
	
	public void updateName() {
		String newName;
		System.out.println("Enter New Name");
		newName = keypad.getString();
		while(pdb.containsProfile(newName) == true) {
			System.out.println("Entered name exists, choose a different name");
			newName = keypad.getString();
			}
		pdb.getProfile(name).setName(newName);
		updateProfileInfo();		
	}
	
	/*
	 * Function method to update status of profile
	 */
	
	public void updateStatus() {
		String newStatus;
		System.out.println("Enter New Status");
		newStatus = keypad.getString();
		pdb.getProfile(name).setStatus(newStatus);
		updateProfileInfo();
	}
	
	/*
	 * Function method to update image of profile
	 */
	
	public void updateImage() {
		String newImage;
		System.out.println("Enter New Image");
		newImage = keypad.getString();
		pdb.getProfile(name).setImage(newImage);
		updateProfileInfo();
	}
	
	/*
	 * Function method to update age of profile
	 */
	
	public void updateAge() {
		String newAge;
		System.out.println("Enter New Age - Must be over 16");
		newAge = keypad.getString();
		pdb.getProfile(name).setImage(newAge);
		updateProfileInfo();
	}
	
	/*
	 * Menu of choose profile type before add profile to the social network
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
			break;
			
		case 2:
			addChild();
			break;
			
		}
	}		
}
