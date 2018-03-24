import java.util.ArrayList;

/*
 * The main function of this class is to create an object of baby type. It extends
 * Child. It also contains functionality to get names of parents and check the mutual 
 * exclusivity of the parents of the calling object. 
 */
public class Baby extends Profile{
	private Adult parentOne;
	private Adult parentTwo;
	ProfileDataBase pdbThree =  MiniNetMain.getPDB();
	private ArrayList<String>parentsList = new ArrayList<String>();
	private String myParent1;
	private String myParent2;
	
	public Baby(String name, String image, String status, int age, Adult parentOne, Adult parentTwo) {
		super(name, image, status, age);
		this.parentOne = parentOne;
		this.parentTwo = parentTwo;
		createParentsList();

	}
	
	/*
	 * This method returns the object of parent one.
	 */
	public Adult getParentOne() {
		return parentOne;
	}
	
	/*
	 * This method returns the object of parent two.
	 */
	public Adult getParentTwo() {
		return parentTwo;
	}
	
	/*
	 * This method creates a list of parents.
	 */
	public void createParentsList() {
	    myParent1 = getParentOne().getName();
		myParent2 = getParentTwo().getName();
		parentsList.add(myParent1);
		parentsList.add(myParent2);
	}
	
	/*
	 * This method returns the list of parents created in createParentsList()
	 */
	public ArrayList<String> getParentsList() {
		return parentsList;
	}

	/*
	 * Checks whether a given name is of type baby
	 */
	public boolean isBaby(String name) {
		if(pdbThree.getProfile(name) instanceof Baby) {
			return true;
		}
		else
			return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see Child#checkParents(Adult, Adult)
	 * This method overrides the checkParents() method in Child to adapt it to a Baby
	 * object. 
	 */
	public ArrayList<String> checkParents(Adult parentOne,Adult parentTwo) {
		ArrayList<Profile> objectsList = pdbThree.getAllProfilesValues();
		ArrayList<String> resultsList = new ArrayList<String>();
		
		for(int i = 0;i<objectsList.size();i++) {
			if(objectsList.get(i) instanceof Baby) {
				if(parentOne == ((Baby)(objectsList.get(i))).getParentOne() ||
						parentOne == ((Baby)(objectsList.get(i))).getParentTwo())
					
					if(parentTwo == ((Baby)(objectsList.get(i))).getParentOne() ||
							parentTwo == ((Baby)(objectsList.get(i))).getParentTwo()) 
						resultsList.add("true");
					
					else 
						resultsList.add("false");
					
					 
				else if(parentTwo == ((Baby)(objectsList.get(i))).getParentOne() ||
							parentTwo == ((Baby)(objectsList.get(i))).getParentTwo())
							
							if((parentOne == ((Baby)(objectsList.get(i))).getParentOne() ||
								parentOne == ((Baby)(objectsList.get(i))).getParentTwo()))
						
								resultsList.add("true");
							else
								resultsList.add("false");
					
					 else if(parentOne != ((Baby)(objectsList.get(i))).getParentOne() ||
								parentOne != ((Baby)(objectsList.get(i))).getParentTwo()) {
							if(parentTwo != ((Baby)(objectsList.get(i))).getParentOne() ||
									parentTwo != ((Baby)(objectsList.get(i))).getParentTwo()) 
								resultsList.add("true");
							 
				}
						
				
			}
			 
		}
		return resultsList;
	}
	

}

