package _01_StringBuilder;


public class _03_StringBuilder {
    
    public static String append(String str, char[] characters) {
		String g = str;
    	for(char k : characters) {
		g += k;
		}
    	
    	System.out.println(g);
		return g;
		
    	
    }
    
    public static String reverse(String str) {
    	  String reversed = "";
          int w = str.length() - 1;
          
      
        for(int i = w; i >= 0; i--) {
        	reversed+=str.charAt(i);
        }
		return reversed;
        
      }
  
    
    public static String insert(String str, int index, char newChar) {
        return null;
    }
    
    public static String delete(String str, int startIndex, int endIndex) {
        return null;
    }
}