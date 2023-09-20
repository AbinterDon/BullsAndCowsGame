import java.util.Queue;
import java.util.LinkedList;
class D10516220_HowManyAorB_option1_Computer extends Thread{
	static boolean bingo;
	static Queue<Integer> temp;
	static boolean[] box;//隊列定義，存放數據
	static String[] Ans;
	public D10516220_HowManyAorB_option1_Computer(){}
	
 	public D10516220_HowManyAorB_option1_Computer(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//創建線程對象構造函數
  		this.box=box;
  		this.temp=temp;
  		this.bingo = bingo;
  		this.Ans = Ans;
 	}
 	
 	
	@Override
 	public void run(){
 		Computer_feeback();
 	}
 	
 	public void Computer_feeback(){//電腦出題 電腦回答 玩家猜
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr =0;//玩家猜的次數
		while(bingo==false){
			synchronized(temp){
				while(temp.isEmpty()){//如果對方還沒猜的話
					try{
						temp.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				fr++;//猜幾次了同學
				String keySplit[] = new String[4];//裝分解答案的陣列
				keySplit = inp.Split(String.valueOf(temp.peek()));//分割對方猜的數字
				
				char[] user_ab = execution.result(Ans,keySplit);
				System.out.println(user_ab[0]+"A"+user_ab[1]+"B");//show這次猜的出結果
				if(user_ab[0]=='4')bingo=true;
		
				temp.remove();//移除對方猜的值 
				temp.notify();//叫他繼續猜
			}
		}
		System.out.println("答對了！");
		System.out.println("總共花了：" + fr + "次");
 	}
}

class D10516220_HowManyAorB_option1_User extends Thread{
	public D10516220_HowManyAorB_option1_User(){}
 	
	@Override
 	public void run(){
 		User_guess();
 	}
 	
 	public void User_guess(){//電腦出題 電腦回答 玩家猜
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr = 0;//電腦猜測次數
		while(D10516220_HowManyAorB_option1_Computer.bingo==false){
			synchronized(D10516220_HowManyAorB_option1_Computer.temp){
				while(D10516220_HowManyAorB_option1_Computer.temp.size()==1){//temp內有值的話(對方剛猜 還沒核對)
					try{
						D10516220_HowManyAorB_option1_Computer.temp.wait();	//等待對方猜
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option1_Computer.bingo==true) {//如果玩家猜對了
					break;
				}else{
					String[] keySplit = inp.Read("請輸入慾猜測的數字(四位數值)：","answer");//裝入玩家猜的數值 到keysplit
					D10516220_HowManyAorB_option1_Computer.temp.add(Integer.parseInt(keySplit[0]+keySplit[1]+keySplit[2]+keySplit[3]));//將猜的值 加入temp
					D10516220_HowManyAorB_option1_Computer.temp.notify();//呼叫核對
				}

			}
		}
		
 	}
 	
}