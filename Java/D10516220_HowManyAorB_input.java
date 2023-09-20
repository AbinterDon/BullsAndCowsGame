import java.util.*;
public class D10516220_HowManyAorB_input{//讀取類別
	public static String[] Read(String tip,String type){//讀取值input
		D10516220_HowManyAorB_rule regular = new D10516220_HowManyAorB_rule();//載入規則類別
		Scanner inp = new Scanner(System.in);
		String keyword = "";//輸入的值載入
		boolean check = false;//格式是否符合規定
		do{
			if(check==true) System.out.println("輸入格式錯誤，請在檢查一次");
			System.out.print(tip);//印出提示(要輸入甚麼)
			check=false;
			keyword = inp.next();//讀取輸入的鍵值
			if(type == "answer"){
				check = regular.read_rule(keyword);//檢查回答的答案規則
			}else if(type == "reply"){//userans
				check = regular.read_AB_rule(keyword);//檢查回答的幾a幾b規則
			}
		}while(check==true);//check=true代表回答不符合規則 
		return Split(keyword);
	}
	
	public static String[] Split(String keyword){//把數字分割為陣列
		String[] keySplit = new String[4];//輸入值分解成陣列
		for(int i = 0;i<4;i++){//把key分解 裝入keySplit
			keySplit[i] = keyword.substring(i,i+1);
		}
		return keySplit;
	}
	
	public static String gameType(){
		Scanner inp = new Scanner(System.in);
		return inp.next();
	}
}