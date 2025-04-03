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
    	String b4 = str.substring(0,index);
    	String after = str.substring(index);
        return b4 + newChar + after;
    }
    
    public static String delete(String str, int startIndex, int endIndex) {
        String sigma = str.substring(startIndex, endIndex);
		return str.replace(sigma, "");
    }
}