import java.util.Queue;
import java.util.LinkedList;

class D10516220_HowManyAorB_option3_Computer2_guess extends Thread{//1 電腦2 負責猜測的class
	public D10516220_HowManyAorB_option3_Computer2_guess(){}
	@Override
 	public void run(){
 		Computer_guess2();
 	}
	public void Computer_guess2(){//電腦1出題 電腦1回答 電腦2猜
		while(D10516220_HowManyAorB_option3_Computer1.bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//都還沒有贏家的話 && D10516220_HowManyAorB_option3_Computer2.bingo2 == false
			synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
				while(D10516220_HowManyAorB_option3_Computer1.temp.size()==1 ){//temp內有值的話(電腦2剛猜 電腦1還沒核對)
					try{
						//System.out.println("1");
						if(D10516220_HowManyAorB_option3_Computer1.bingo==true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break; //如果有一方已經勝利了 將不再繼續 || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
						D10516220_HowManyAorB_option3_Computer1.temp.wait();//電腦等待	玩家回答
						//sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option3_Computer1.bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ) {//如果都還沒有贏家的話 && D10516220_HowManyAorB_option3_Computer2.bingo2 == false
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option3_Computer1.box);//隨機產生 電腦2猜的數字
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//延遲時間
					if(D10516220_HowManyAorB_option3_Computer1.bingo==false)System.out.println("\n"+ (D10516220_HowManyAorB_option3_Computer1.fr+1) + ".電腦2猜測：" + (rand_guess+1023));//顯示電腦2猜的數字
					D10516220_HowManyAorB_option3_Computer1.temp.add((rand_guess+1023));//將猜的值 加入temp
					D10516220_HowManyAorB_option3_Computer1.temp.notify();//呼叫電腦1核對
				}
			}
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer1 extends Thread{//2 電腦1 負責回答的class
	static boolean bingo_final;
	static boolean bingo;//電腦2有沒有答對了
	static Queue<Integer> temp;//電腦2猜的值
	static boolean[] box;//隊列定義，存放數據
	static String[] Ans;//電腦1的答案
	static int fr;//電腦2的答案
 	public D10516220_HowManyAorB_option3_Computer1(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//創建線程對象構造函數
  		this.box=box;
  		this.temp=temp;
  		this.bingo = bingo;
  		this.Ans = Ans;
 	}
	@Override
 	public void run(){
 		Computer_feeback1();
 	}
 	public void Computer_feeback1(){//電腦1出題 電腦1回答 電腦2猜
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		fr =0;//玩家猜的次數
		while(bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//如果還沒有贏家//&& D10516220_HowManyAorB_option3_Computer2.bingo2 == false 
			synchronized(temp){
				while(temp.isEmpty()){//如果電腦2還沒猜的話 就在等等
					try{
						//System.out.println("2");
						if(bingo==true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break;//如果已經有贏家了 || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
						temp.wait();
						//sleep(2000);	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(bingo==false  ){//如果還沒有贏家//&& D10516220_HowManyAorB_option3_Computer2.bingo2 == false
					fr++;//猜幾次了同學
					String keySplit[] = new String[4];//裝分解答案的陣列
					keySplit = inp.Split(String.valueOf(temp.peek()));//分割對方猜的數字
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//延遲時間
					char[] user_ab = execution.result(Ans,keySplit);//答案字串分割
					System.out.println(fr +".電腦1回答："+user_ab[0]+"A"+user_ab[1]+"B");//show這次猜的出結果
					
				
					
					
					if( D10516220_HowManyAorB_option3_Computer2.bingo2==true){ //若對方已經先答對了
						if(user_ab[0]=='4'){//答對的話 4A
							bingo=true;
						}
						//System.out.println("1變");
						D10516220_HowManyAorB_option3_Computer1.bingo_final = true;
						synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
							D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //呼叫電腦2 說換你們了
						}
					}else{
						if(user_ab[0]=='4'){//答對的話 4A
							bingo=true;
							//temp.notifyAll(); //呼叫 準備撤退
						}
						synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
							if(D10516220_HowManyAorB_option3_Computer2.temp2.size()==1)D10516220_HowManyAorB_option3_Computer2.temp2.remove();//幫忙移除電腦2的temp 讓他們可以直接繼續
							D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //呼叫電腦2 說換你們了
						}
				
						while(D10516220_HowManyAorB_option3_Computer2.temp2.isEmpty() ){//如果對方還沒猜的話
							try{
								if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true)break;//如果已經有贏家了 D10516220_HowManyAorB_option3_Computer1.bingo==true ||
									//System.out.println("3");
									temp.wait();
									//sleep(2000);	
								}catch(Exception e){
									e.printStackTrace();
							}
						}
						if(D10516220_HowManyAorB_option3_Computer1.bingo_final==false){
							execution.confirmed(box,keySplit,user_ab[0],user_ab[1]);//刪除不可能的答案
							temp.notify();//叫電腦2繼續猜
						}else{
							temp.notifyAll();
							synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
								D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //呼叫電腦2 說換你們了
							}
						}
					}
					
				}
			}
		}
		if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true && bingo == true ){//是否平手
			System.out.println("\t電腦1跟2 都答對了！");
			System.out.println("\t雙方總共花了：" + D10516220_HowManyAorB_option3_Computer2.fr2 + "次");
		}else if(bingo == true  && D10516220_HowManyAorB_option3_Computer1.bingo_final==true){//電腦2有沒有贏
			System.out.println("電腦2答對了！");
			System.out.println("總共花了：" + D10516220_HowManyAorB_option3_Computer2.fr2 + "次");
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer1_guess extends Thread{//3 電腦1 負責猜測的class
	public D10516220_HowManyAorB_option3_Computer1_guess(){}
	@Override
 	public void run(){
 		Computer_guess1();
 	}
 	public void Computer_guess1(){//電腦2出題 電腦2回答 電腦1猜
		while(D10516220_HowManyAorB_option3_Computer2.bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//若還沒有贏家 // && D10516220_HowManyAorB_option3_Computer1.bingo == false
			synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
				while((D10516220_HowManyAorB_option3_Computer2.temp2.size()==1 || D10516220_HowManyAorB_option3_Computer1.temp.isEmpty())){//temp內有值的話(電腦1剛猜 電腦2還沒核對)
					try{
						if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true)break; //如果已經有贏家了 D10516220_HowManyAorB_option3_Computer1.bingo==true ||
						//System.out.println("4");
						D10516220_HowManyAorB_option3_Computer2.temp2.wait();//電腦1等待	等待電腦2回答
						//sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option3_Computer2.bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ) {//如果還沒有贏家的話 && D10516220_HowManyAorB_option3_Computer1.bingo == false
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option3_Computer2.box2);//隨機產生 電腦1猜的數字
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//延遲時間
					if(D10516220_HowManyAorB_option3_Computer2.bingo2==false)System.out.println("\n\t\t"+ (D10516220_HowManyAorB_option3_Computer2.fr2+1) +".電腦1猜測：" + (rand_guess+1023));//顯示電腦1猜的數字
					D10516220_HowManyAorB_option3_Computer2.temp2.add((rand_guess+1023));//將猜的值 加入temp2
					D10516220_HowManyAorB_option3_Computer2.temp2.notify();//呼叫電腦2核對
				}
			}
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer2 extends Thread{//4 電腦2負責回答的class
	static boolean bingo2;//電腦1有沒有猜對了
	static Queue<Integer> temp2;//電腦1猜測的值
	static boolean[] box2;//隊列定義，存放數據
	static String[] Ans2;//電腦2的答案
	static int fr2;//電腦1猜的次數
 	public D10516220_HowManyAorB_option3_Computer2(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//創建線程對象構造函數
  		this.box2=box;
  		this.temp2=temp;
  		this.bingo2 = bingo;
  		this.Ans2 = Ans;
 	}
	@Override
 	public void run(){
 		Computer_feeback2();
 	}
 	public void Computer_feeback2(){//電腦2出題 電腦2回答 電腦1猜
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		fr2 =0;//玩家猜的次數
		while(bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false){//還沒有贏家的話 && D10516220_HowManyAorB_option3_Computer1.bingo == false
			synchronized(temp2){
				while(temp2.isEmpty()){//如果電腦1還沒猜的話
					try{
						if(bingo2 == true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break;//如果還沒有贏家
						//System.out.println("6");
						temp2.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(bingo2==false ){//如果還沒有贏家的話 && D10516220_HowManyAorB_option3_Computer1.bingo == false
					fr2++;//猜幾次了同學
					String keySplit[] = new String[4];//裝分解答案的陣列
					keySplit = inp.Split(String.valueOf(temp2.peek()));//分割對方猜的數字
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//延遲時間
					char[] user_ab = execution.result(Ans2,keySplit);
					System.out.println("\t\t"+ fr2 +".電腦2回答："+user_ab[0]+"A"+user_ab[1]+"B");//show這次猜的出結果
					
					
					if(user_ab[0]=='4'){//如果電腦1猜對了 4a
						bingo2=true;
						D10516220_HowManyAorB_option3_Computer1.bingo_final =true;
						temp2.notifyAll();
						synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
							D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//叫電腦1繼續
						}
					}else{
						if(D10516220_HowManyAorB_option3_Computer1.bingo == true){//如果對方已經答對了
							D10516220_HowManyAorB_option3_Computer1.bingo_final =true;
							temp2.notifyAll();
							synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
								D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//叫電腦1繼續
							}
						}else{
							synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
								if(D10516220_HowManyAorB_option3_Computer1.temp.size()==1)D10516220_HowManyAorB_option3_Computer1.temp.remove();//幫助電腦1的temp移除 讓他們可以繼續
								D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//叫電腦1繼續
							}
							while(D10516220_HowManyAorB_option3_Computer1.temp.isEmpty()){//如果電腦1他們還沒猜完一輪的話
								try{
									if(D10516220_HowManyAorB_option3_Computer1.bingo==true)break;//如果還沒有贏家的話 || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
									//System.out.println("9");
									temp2.wait();
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							execution.confirmed(box2,keySplit,user_ab[0],user_ab[1]);//刪除不可能的答案
							temp2.notify();//叫電腦1繼續猜
						}
						
					}
				}
			}
		}
		if(bingo2 == true && D10516220_HowManyAorB_option3_Computer1.bingo == false){//電腦1有沒有贏
			System.out.println("\n\t\t電腦1答對了！");
			System.out.println("\t\t總共花了：" + D10516220_HowManyAorB_option3_Computer1.fr + "次");
		}
 	}
}

