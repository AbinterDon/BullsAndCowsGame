import java.util.LinkedList;
import java.util.Queue;
class D10516220_HowManyAorB{
	public static void main(String arg[]){//數金二甲 D10516220 吳東翰
		try{
			D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();//讀取類別
			D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();//執行類別
			System.out.println("吳東翰 幾A幾B HowManyAorB");
			System.out.println("\n請選擇遊戲模式 \n1為 電腦出題 玩家猜 \n2為 玩家出題 電腦猜 \n3為 電腦互猜 \n4為 離開遊戲");
			System.out.println("\n請選擇遊戲模式：");
			String type = inp.gameType();
			while(type.equals("1")==false && type.equals("2")==false && type.equals("3")==false && type.equals("4")==false){
				System.out.println("輸入錯誤，請輸入 1 或 2 或 3 或 4");
				type = inp.gameType();
			}
			if(type.equals("1")){//為電腦出題玩家猜
				System.out.println("--------玩家猜---------");
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 建立所有有可能的排列組合陣列 true=不能用或是用過 false=可以用
				D10516220_HowManyAorB_execution.confirmed(box);//把排列組合中重複的改成true 代表不能用				
				String[] Ans = new String[4];//裝最終答案的陣列
				
				int temp_guess = execution.produce(box)+1023;//亂數產生答案
				Ans = inp.Split(Integer.toString(temp_guess));
				System.out.println(Ans[0]+Ans[1]+Ans[2]+Ans[3]);//答案

				System.out.println("---------開始----------");
				
				Queue<Integer> temp = new LinkedList<Integer>();
				D10516220_HowManyAorB_option1_Computer option1_Computer = new D10516220_HowManyAorB_option1_Computer(box,temp,false,Ans);
				D10516220_HowManyAorB_option1_User option1_User = new D10516220_HowManyAorB_option1_User();
				option1_Computer.start();//遊戲開始 開始猜測
				option1_User.start();
				
			}else if(type.equals("2")){//為玩家出提電腦猜
				System.out.println("--------電腦猜---------");
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 建立所有有可能的排列組合陣列 true=不能用或是用過 false=可以用
				D10516220_HowManyAorB_execution.confirmed(box);//把排列組合中重複的改成true 代表不能用
				String[] Ans = new String[4];//裝最終答案的陣列
				Ans = inp.Read("請輸入答案(4位數不重複數值)：","answer");//首先由使用者設定最終答案
							
				System.out.println("---------開始----------");
				
				Queue<Integer> temp = new LinkedList<Integer>();				
				D10516220_HowManyAorB_option2_User option2_User = new D10516220_HowManyAorB_option2_User(box,temp,false,Ans,2);
				D10516220_HowManyAorB_option2_Computer option2_Computer = new D10516220_HowManyAorB_option2_Computer();				
				option2_Computer.start();//遊戲開始 開始猜測
				option2_User.start();
			}else if(type.equals("3")){//電腦互猜
				System.out.println("--------電腦互猜---------");
				
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 建立所有有可能的排列組合陣列 true=不能用或是用過 false=可以用
				D10516220_HowManyAorB_execution.confirmed(box);//把排列組合中重複的改成true 代表不能用
				String[] Ans = new String[4];//裝最終答案的陣列
				
				int temp_guess = execution.produce(box)+1023;//亂數產生答案
				Ans = inp.Split(Integer.toString(temp_guess));
				System.out.print("電腦1答案："+Ans[0]+Ans[1]+Ans[2]+Ans[3]);//答案
				
				boolean[] box2 = new boolean[8854];//9876-1023=8853+1=8854 建立所有有可能的排列組合陣列 true=不能用或是用過 false=可以用
				D10516220_HowManyAorB_execution.confirmed(box2);//把排列組合中重複的改成true 代表不能用				
				String[] Ans2 = new String[4];//裝最終答案的陣列
				
				int temp_guess2 = execution.produce(box2)+1023;//亂數產生答案
				Ans2 = inp.Split(Integer.toString(temp_guess2));
				System.out.println("\t電腦2答案："+Ans2[0]+Ans2[1]+Ans2[2]+Ans2[3]);//答案
							
				System.out.println("---------開始----------");
				Queue<Integer> temp = new LinkedList<Integer>();//電腦2猜的值		
				Queue<Integer> temp2 = new LinkedList<Integer>();//電腦1猜的值
						
				D10516220_HowManyAorB_option3_Computer1 option3_Computer1 = new D10516220_HowManyAorB_option3_Computer1(box,temp,false,Ans);//電腦1 負責回答的類別
				D10516220_HowManyAorB_option3_Computer2 option3_Computer2 = new D10516220_HowManyAorB_option3_Computer2(box2,temp2,false,Ans2);//電腦2 負責回答的類別
				D10516220_HowManyAorB_option3_Computer1_guess option3_Computer1_guess = new D10516220_HowManyAorB_option3_Computer1_guess();//電腦1 負責猜的類別
				D10516220_HowManyAorB_option3_Computer2_guess option3_Computer2_guess = new D10516220_HowManyAorB_option3_Computer2_guess();//電腦2 負責回答的類別
					
				option3_Computer1_guess.start();//遊戲開始 開始猜測
				option3_Computer1.start();
				option3_Computer2_guess.start();			
				option3_Computer2.start();
			}else{
				System.out.println("謝謝您的支持！");
			}
		}catch (Exception e){
			System.out.println("Error！");
		}
	}
}