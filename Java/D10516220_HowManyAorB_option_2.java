import java.util.Queue;
import java.util.LinkedList;
class D10516220_HowManyAorB_option2_Computer extends Thread{
	static boolean bingo;
	static Queue<Integer> temp;
	static boolean[] box;//隊列定義，存放數據
	static String[] Ans;
	public D10516220_HowManyAorB_option2_Computer(){}
 	
	@Override
 	public void run(){
 		Computer_guess();
 	}
 
 	public void Computer_guess(){//玩家出題 玩家回答 電腦猜
		while(D10516220_HowManyAorB_option2_User.bingo2==false){
			synchronized(D10516220_HowManyAorB_option2_User.temp2){
				while(D10516220_HowManyAorB_option2_User.temp2.size()==1){//temp內有值的話(剛猜 還沒核對)
					try{
						D10516220_HowManyAorB_option2_User.temp2.wait();//電腦等待	玩家回答
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option2_User.bingo2==true) {//如果猜中了
					break;
				}else{
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option2_User.box2);//隨機產生 電腦猜的數字
					D10516220_HowManyAorB_option2_User.temp2.add(rand_guess);//將猜的值 加入temp
					D10516220_HowManyAorB_option2_User.temp2.notify();//呼叫核對
				}

			}
		}
 	}
}

class D10516220_HowManyAorB_option2_User extends Thread{
	static boolean bingo2;
	static Queue<Integer> temp2;
	static boolean[] box2;//隊列定義，存放數據
	static String[] Ans2;

	
	public D10516220_HowManyAorB_option2_User(){}
	
 	public D10516220_HowManyAorB_option2_User(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans,int Game_type) {//創建線程對象構造函數
  		this.box2=box;
  		this.temp2=temp;
  		this.bingo2 = bingo;
  		this.Ans2 = Ans;
 	}
 	
	@Override
 	public void run(){
 		User_feeback();
 	}
 	
 	public void User_feeback(){//玩家出題 玩家回答 電腦猜
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr = 0;//電腦猜測次數
		while(bingo2==false){
			synchronized(temp2){
				while(temp2.isEmpty()){//如果對方還沒猜的話
					try{
						temp2.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				String keySplit[] = new String[4];//裝分解答案的陣列
				keySplit = inp.Split(String.valueOf(temp2.peek()+1023));
				char[] computer_ab = execution.result(Ans2,keySplit);//比對電腦輸入的鍵值幾a幾b computer_ab=電腦自己心中幾a幾b的答案 
				fr++;//累加電腦猜的次數
				System.out.print("\n第" + fr + "次");//輸出結果
				System.out.printf("\n電腦猜的是："+ ((temp2.peek()+1023)) + "\n");//列印電腦猜的數字 temp+1023因為排列組合從1023開始
				if(computer_ab[0]=='4'){//如果4A 代表電腦猜對了 跳出迴圈
					bingo2 = true ;// break;
				}else{
					System.out.println("參考答案："+computer_ab[0]+"A"+computer_ab[1]+"B");//輸出結果

					char[] user_ab = execution.result(inp.Read("您的回答是幾a幾b：","reply"));//使用者回答幾a幾b給電腦參考
					while(user_ab[0]!=computer_ab[0] || user_ab[1]!=computer_ab[1]){//判斷 使用者有沒有說錯答案(?A?B)
						System.out.printf("您的回答有誤，請再輸入一次"+"\n");
						user_ab = execution.result(inp.Read("您的回答是幾a幾b：","reply"));//重複做到使用者回答正確
					}
					if(bingo2==false)execution.confirmed(box2,keySplit,user_ab[0],user_ab[1]);
				}
				temp2.remove();//移除對方猜的值 
				temp2.notify();//叫他繼續猜
			}
		}
		System.out.println("電腦答對了！");
		System.out.println("電腦總共花了：" + fr + "次");
 	}
}