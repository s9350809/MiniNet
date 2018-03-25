
/*
 * This class is the superclass of adult, children and baby class.
 * @Author Da Teng
 */

import java.util.*;

public abstract class Profile {
	
	/* Private instance variables*/
	private String _name = "";
	private String _image = "";
	private String _status = "No current status";
	private int _age = 0;
	private ArrayList <String> friends = new ArrayList<String>();
	
	/** 
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
	 * This method displays profile information
	 */
	
	public void displayProfileInfo() {
		System.out.println(getName());
		System.out.println(getImage());
		System.out.println(getStatus());
		System.out.println(getAge());
	}
	
	public void updateProfile() {
		System.out.println("Enter");
	}
	
	/*
	 * Accessor method to get name.
	 */
	
	public String getName() {
		return _name;
	}
	
	/*
	 * Mutator method to change name.
	 */
	
	public void setName(String name) {
		_name = name;
		System.out.println(_name);
	}

	/*
	 * Accessor method to get image.
	 */
	
	public String getImage() {
		return _image;
	}
    
	
	/*
	 * Mutator method to change image.
	 */
	
	public void setImage(String image) {
		_image = image;
	}
	
	/*
	 * Accessor method to get status.
	 */
	
	public String getStatus() {
		return _status;
	}
	
	/*
	 * Mutator method to set status
	 */
	
	public void setStatus(String status) {
		_status = status;
	}
	
	/*
	 * Accesor method to get age.
	 */
	
	public int getAge() {
		return _age;
	}
	
	/*
	 * Mutator method to change age.
	 */
	
	public void setAge(int age) {
		_age = age;
	}

	/*
	 * Accesor method to get friendlist.
	 */
	
	public ArrayList <String> getFriendsList() {
		return friends;
	}
}
