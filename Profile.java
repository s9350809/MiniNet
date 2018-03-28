import java.util.*;

/*
 * This class is the super class of Adult Child and Baby.
 * @Author Jalana Jayasinghe
 */
public abstract class Profile {
	
	/* Private instance variables*/
	private String _name = "";
	private String _image = "";
	private String _status = "No current status";
	private int _age = 0;
	private ArrayList <String> friends = new ArrayList<String>();

	
	/*
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public Profile(String name,String image,String status,int age) {
		_name = name;
		_image = image;
		_status = status;
		_age = age;
	}
	
	/*
	 * Explicitly defining default constructor
	 */
	public Profile() {
		
	}
	
	/*
	 * This method displays the profile information of a profile
	 */
	public void displayProfileInfo() {
		System.out.println(getName());
		System.out.println(getImage());
		System.out.println(getStatus());
		System.out.println(getAge());
	}
	

	/*
	 *  This method returns the name associated with the profile. 
	 */ 
	public String getName() {
		return _name;
	}
	
	/*
	 * This method sets the name of a profile
	 */
	public void setName(String name) {
		_name = name;
		System.out.println(_name);
	}

	/*
	 * This method returns the image string
	 */
	public String getImage() {
		return _image;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(String image) {
		_image = image;
	}
	
	/* 
	 * This method returns the status associated with the profile.
	 */ 
	public String getStatus() {
		return _status;
	}
	
	/*
	 * This method sets the status associated with the profile. 
	 */ 
	public void setStatus(String status) {
		_status = status;
	}
	
	/*
	 * This method returns the age of a profile
	 */
	public int getAge() {
		return _age;
	}
	
	/*
	 * This method sets the age of a profile
	 */
	public void setAge(int age) {
		_age = age;
	}


	/*
	 * This method returns the friend list of a profile
	 */
	public ArrayList <String> getFriendsList() {
		return friends;
	}	
}