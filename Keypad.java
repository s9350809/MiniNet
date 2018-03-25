import java.util.InputMismatchException;
import java.util.Scanner;
//This class to handle input from user
public class Keypad {
	private Scanner input;
	
	public Keypad() {
		input = new Scanner(System.in);
	}
	
	public String getString() {
		boolean again = true;
		String userInput;
		do{
			userInput = input.nextLine();
			if(isInteger(userInput)==true) {
				System.out.print("Invalid input. Don't enter number.\nPlease enter again: ");
				
			}else {
				again = false;
			}	
			
		}while(again==true);
	
		return userInput;
	}
	
	public int getInt() {
		boolean again = true;
		int num=0;
		do {
		   try {
			 num=input.nextInt();
			 again = false;
		   }catch(Exception e) {
			   System.out.print("Invalid input. Please enter a number : ");
			   
		   }
		   input.nextLine();
		  
		}while(again==true);
		
		return num;
		
	}
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			return false;
		}catch(NullPointerException e) {
			return false;
		}
	   
		return true;
		
	}
}

