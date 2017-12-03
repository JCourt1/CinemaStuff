package application.views.plan.util;

public class FilmSaveException extends Exception {
	
		String str1;
	   
		public FilmSaveException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("MyException Occurred: "+str1) ;
	   }

}
