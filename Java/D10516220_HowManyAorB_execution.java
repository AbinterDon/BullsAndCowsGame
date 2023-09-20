public class D10516220_HowManyAorB_execution{//執行類別
	public static char[] result(String[] Ans,String[] KeyWord){//計算電腦 回答的幾a幾b
		char[] ab = new char[]{'0','0'};
		for(int i=0;i<4;i++){
			for(int x = 0;x<4;x++){
				if(Ans[i].equals(KeyWord[x]) && i==x){//位置相同 數值相同
					ab[0]++;
				}else if(Ans[i].equals(KeyWord[x])){//數值相同
					ab[1]++;
				}
			}
		}
		return ab;
	}
	
	public static char[] result(String[] keyword){//計算user 回答的幾a幾b
		char[] ab = new char[]{'0','0'};
		ab[0] = keyword[0].charAt(0);
		ab[1] = keyword[2].charAt(0);
		return ab;
	}

	public static int produce(boolean[] box){//產生亂數 
		int number = 0;
		do{
			number = (int)(Math.random()*8853+1);//產生1~8853之間的號碼 8853+1023=9876
		}while(box[number]==true);
		return number;
	}

	public static void confirmed(boolean[] box){//把有重複的排列組合的改成true 代表不能用
		D10516220_HowManyAorB_rule regular = new D10516220_HowManyAorB_rule();
		for(int i = 0;i<8854;i++){
			if(regular.repeatCheck(Integer.toString(i+1023)))box[i]=true;
		}
	}
	
	public static void confirmed(boolean[] box,String[] keySplit,char a,char b){//把有可能的排列組合留下來 不可能的改成true
		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
		for(int i = 0;i<8854;i++){//8853+1023=9876
			if(box[i]==false){
				char[] temp = result(keySplit,inp.Split(Integer.toString(i+1023)));
				if(temp[0]!=a  || temp[1]!=b)box[i]=true;//如果幾a幾b不相同 就代表這個數字不可能是答案 所以改成true
			}	
		}
	}
}