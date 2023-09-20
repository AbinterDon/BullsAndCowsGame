public class D10516220_HowManyAorB_rule{//規則類別
	public static boolean read_rule(String keyword){//回答的鍵值是否符合規則
		if(isNumeric(keyword)==false)return true;//是否為數字
		if(zeroCheck(Integer.parseInt(keyword))) return true;//是否為0開頭
		if(lengthCheck(keyword))return true; //若超過四位數	
		if(repeatCheck(keyword))return true;//若有重複
		return false;
	}
	
	public static boolean read_AB_rule(String keyword){//幾a幾b回答 是否符合規則
		if(isNumeric(keyword.substring(0,1))==false||isNumeric(keyword.substring(2,3))==false)return true;//是否第一個跟第三個是數字
		if(lengthCheck(keyword))return true; //若超過四位數	
		if(keyword.charAt(1)!='a' && keyword.charAt(1)!='A')return true;//若第二個字 不是aorA
		if(keyword.charAt(3)!='b' && keyword.charAt(3)!='B')return true;//若第四個字 不是borB
		return false;
	}
	
	private static boolean isNumeric(String str){//是否為數字
 	 for (int i = str.length();--i>=0;){    
  	 	if (!Character.isDigit(str.charAt(i)))return false;  
 	 }  
 	 return true;  
	}   
	
	private static boolean zeroCheck(int key){//判斷開頭是否為0
		if(key < 1000) return true;
		return false;
	}
	
	private static boolean lengthCheck(String key){//判斷是否超過4位數
		if(key.length() !=4) return true;
		return false;
	}
	
	public static boolean repeatCheck(String key){//判斷是否重複
		for(int i = 0;i<4;i++){
			String temp=key.substring(i,i+1);
			for(int x=i+1;x<4;x++){
				if(Integer.parseInt(key.substring(x,x+1))==Integer.parseInt(temp)) return true;//有重複 立刻回傳true
			}
		}
		return false;
	}
}