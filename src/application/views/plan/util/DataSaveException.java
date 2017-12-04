package application.views.plan.util;

public class DataSaveException extends Exception {
	
		String str1;
	   
		public DataSaveException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("MyException Occurred: "+str1) ;
	   }

}
