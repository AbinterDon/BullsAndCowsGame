import java.util.Queue;
import java.util.LinkedList;

class D10516220_HowManyAorB_option3_Computer2_guess extends Thread{//1 �q��2 �t�d�q����class
	public D10516220_HowManyAorB_option3_Computer2_guess(){}
	@Override
 	public void run(){
 		Computer_guess2();
 	}
	public void Computer_guess2(){//�q��1�X�D �q��1�^�� �q��2�q
		while(D10516220_HowManyAorB_option3_Computer1.bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//���٨S��Ĺ�a���� && D10516220_HowManyAorB_option3_Computer2.bingo2 == false
			synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
				while(D10516220_HowManyAorB_option3_Computer1.temp.size()==1 ){//temp�����Ȫ���(�q��2��q �q��1�٨S�ֹ�)
					try{
						//System.out.println("1");
						if(D10516220_HowManyAorB_option3_Computer1.bingo==true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break; //�p�G���@��w�g�ӧQ�F �N���A�~�� || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
						D10516220_HowManyAorB_option3_Computer1.temp.wait();//�q������	���a�^��
						//sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option3_Computer1.bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ) {//�p�G���٨S��Ĺ�a���� && D10516220_HowManyAorB_option3_Computer2.bingo2 == false
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option3_Computer1.box);//�H������ �q��2�q���Ʀr
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//����ɶ�
					if(D10516220_HowManyAorB_option3_Computer1.bingo==false)System.out.println("\n"+ (D10516220_HowManyAorB_option3_Computer1.fr+1) + ".�q��2�q���G" + (rand_guess+1023));//��ܹq��2�q���Ʀr
					D10516220_HowManyAorB_option3_Computer1.temp.add((rand_guess+1023));//�N�q���� �[�Jtemp
					D10516220_HowManyAorB_option3_Computer1.temp.notify();//�I�s�q��1�ֹ�
				}
			}
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer1 extends Thread{//2 �q��1 �t�d�^����class
	static boolean bingo_final;
	static boolean bingo;//�q��2���S������F
	static Queue<Integer> temp;//�q��2�q����
	static boolean[] box;//���C�w�q�A�s��ƾ�
	static String[] Ans;//�q��1������
	static int fr;//�q��2������
 	public D10516220_HowManyAorB_option3_Computer1(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//�Ыؽu�{��H�c�y���
  		this.box=box;
  		this.temp=temp;
  		this.bingo = bingo;
  		this.Ans = Ans;
 	}
	@Override
 	public void run(){
 		Computer_feeback1();
 	}
 	public void Computer_feeback1(){//�q��1�X�D �q��1�^�� �q��2�q
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		fr =0;//���a�q������
		while(bingo==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//�p�G�٨S��Ĺ�a//&& D10516220_HowManyAorB_option3_Computer2.bingo2 == false 
			synchronized(temp){
				while(temp.isEmpty()){//�p�G�q��2�٨S�q���� �N�b����
					try{
						//System.out.println("2");
						if(bingo==true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break;//�p�G�w�g��Ĺ�a�F || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
						temp.wait();
						//sleep(2000);	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(bingo==false  ){//�p�G�٨S��Ĺ�a//&& D10516220_HowManyAorB_option3_Computer2.bingo2 == false
					fr++;//�q�X���F�P��
					String keySplit[] = new String[4];//�ˤ��ѵ��ת��}�C
					keySplit = inp.Split(String.valueOf(temp.peek()));//���ι��q���Ʀr
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//����ɶ�
					char[] user_ab = execution.result(Ans,keySplit);//���צr�����
					System.out.println(fr +".�q��1�^���G"+user_ab[0]+"A"+user_ab[1]+"B");//show�o���q���X���G
					
				
					
					
					if( D10516220_HowManyAorB_option3_Computer2.bingo2==true){ //�Y���w�g������F
						if(user_ab[0]=='4'){//���諸�� 4A
							bingo=true;
						}
						//System.out.println("1��");
						D10516220_HowManyAorB_option3_Computer1.bingo_final = true;
						synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
							D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //�I�s�q��2 �����A�̤F
						}
					}else{
						if(user_ab[0]=='4'){//���諸�� 4A
							bingo=true;
							//temp.notifyAll(); //�I�s �ǳƺM�h
						}
						synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
							if(D10516220_HowManyAorB_option3_Computer2.temp2.size()==1)D10516220_HowManyAorB_option3_Computer2.temp2.remove();//���������q��2��temp ���L�̥i�H�����~��
							D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //�I�s�q��2 �����A�̤F
						}
				
						while(D10516220_HowManyAorB_option3_Computer2.temp2.isEmpty() ){//�p�G����٨S�q����
							try{
								if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true)break;//�p�G�w�g��Ĺ�a�F D10516220_HowManyAorB_option3_Computer1.bingo==true ||
									//System.out.println("3");
									temp.wait();
									//sleep(2000);	
								}catch(Exception e){
									e.printStackTrace();
							}
						}
						if(D10516220_HowManyAorB_option3_Computer1.bingo_final==false){
							execution.confirmed(box,keySplit,user_ab[0],user_ab[1]);//�R�����i�઺����
							temp.notify();//�s�q��2�~��q
						}else{
							temp.notifyAll();
							synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
								D10516220_HowManyAorB_option3_Computer2.temp2.notifyAll(); //�I�s�q��2 �����A�̤F
							}
						}
					}
					
				}
			}
		}
		if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true && bingo == true ){//�O�_����
			System.out.println("\t�q��1��2 ������F�I");
			System.out.println("\t�����`�@��F�G" + D10516220_HowManyAorB_option3_Computer2.fr2 + "��");
		}else if(bingo == true  && D10516220_HowManyAorB_option3_Computer1.bingo_final==true){//�q��2���S��Ĺ
			System.out.println("�q��2����F�I");
			System.out.println("�`�@��F�G" + D10516220_HowManyAorB_option3_Computer2.fr2 + "��");
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer1_guess extends Thread{//3 �q��1 �t�d�q����class
	public D10516220_HowManyAorB_option3_Computer1_guess(){}
	@Override
 	public void run(){
 		Computer_guess1();
 	}
 	public void Computer_guess1(){//�q��2�X�D �q��2�^�� �q��1�q
		while(D10516220_HowManyAorB_option3_Computer2.bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ){//�Y�٨S��Ĺ�a // && D10516220_HowManyAorB_option3_Computer1.bingo == false
			synchronized(D10516220_HowManyAorB_option3_Computer2.temp2){
				while((D10516220_HowManyAorB_option3_Computer2.temp2.size()==1 || D10516220_HowManyAorB_option3_Computer1.temp.isEmpty())){//temp�����Ȫ���(�q��1��q �q��2�٨S�ֹ�)
					try{
						if(D10516220_HowManyAorB_option3_Computer2.bingo2 == true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true)break; //�p�G�w�g��Ĺ�a�F D10516220_HowManyAorB_option3_Computer1.bingo==true ||
						//System.out.println("4");
						D10516220_HowManyAorB_option3_Computer2.temp2.wait();//�q��1����	���ݹq��2�^��
						//sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option3_Computer2.bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false ) {//�p�G�٨S��Ĺ�a���� && D10516220_HowManyAorB_option3_Computer1.bingo == false
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option3_Computer2.box2);//�H������ �q��1�q���Ʀr
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//����ɶ�
					if(D10516220_HowManyAorB_option3_Computer2.bingo2==false)System.out.println("\n\t\t"+ (D10516220_HowManyAorB_option3_Computer2.fr2+1) +".�q��1�q���G" + (rand_guess+1023));//��ܹq��1�q���Ʀr
					D10516220_HowManyAorB_option3_Computer2.temp2.add((rand_guess+1023));//�N�q���� �[�Jtemp2
					D10516220_HowManyAorB_option3_Computer2.temp2.notify();//�I�s�q��2�ֹ�
				}
			}
		}
 	}
}

class D10516220_HowManyAorB_option3_Computer2 extends Thread{//4 �q��2�t�d�^����class
	static boolean bingo2;//�q��1���S���q��F
	static Queue<Integer> temp2;//�q��1�q������
	static boolean[] box2;//���C�w�q�A�s��ƾ�
	static String[] Ans2;//�q��2������
	static int fr2;//�q��1�q������
 	public D10516220_HowManyAorB_option3_Computer2(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//�Ыؽu�{��H�c�y���
  		this.box2=box;
  		this.temp2=temp;
  		this.bingo2 = bingo;
  		this.Ans2 = Ans;
 	}
	@Override
 	public void run(){
 		Computer_feeback2();
 	}
 	public void Computer_feeback2(){//�q��2�X�D �q��2�^�� �q��1�q
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		fr2 =0;//���a�q������
		while(bingo2==false && D10516220_HowManyAorB_option3_Computer1.bingo_final==false){//�٨S��Ĺ�a���� && D10516220_HowManyAorB_option3_Computer1.bingo == false
			synchronized(temp2){
				while(temp2.isEmpty()){//�p�G�q��1�٨S�q����
					try{
						if(bingo2 == true || D10516220_HowManyAorB_option3_Computer1.bingo_final==true )break;//�p�G�٨S��Ĺ�a
						//System.out.println("6");
						temp2.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(bingo2==false ){//�p�G�٨S��Ĺ�a���� && D10516220_HowManyAorB_option3_Computer1.bingo == false
					fr2++;//�q�X���F�P��
					String keySplit[] = new String[4];//�ˤ��ѵ��ת��}�C
					keySplit = inp.Split(String.valueOf(temp2.peek()));//���ι��q���Ʀr
					//try{sleep(1000);}catch(Exception e){e.printStackTrace();}//����ɶ�
					char[] user_ab = execution.result(Ans2,keySplit);
					System.out.println("\t\t"+ fr2 +".�q��2�^���G"+user_ab[0]+"A"+user_ab[1]+"B");//show�o���q���X���G
					
					
					if(user_ab[0]=='4'){//�p�G�q��1�q��F 4a
						bingo2=true;
						D10516220_HowManyAorB_option3_Computer1.bingo_final =true;
						temp2.notifyAll();
						synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
							D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//�s�q��1�~��
						}
					}else{
						if(D10516220_HowManyAorB_option3_Computer1.bingo == true){//�p�G���w�g����F
							D10516220_HowManyAorB_option3_Computer1.bingo_final =true;
							temp2.notifyAll();
							synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
								D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//�s�q��1�~��
							}
						}else{
							synchronized(D10516220_HowManyAorB_option3_Computer1.temp){
								if(D10516220_HowManyAorB_option3_Computer1.temp.size()==1)D10516220_HowManyAorB_option3_Computer1.temp.remove();//���U�q��1��temp���� ���L�̥i�H�~��
								D10516220_HowManyAorB_option3_Computer1.temp.notifyAll();//�s�q��1�~��
							}
							while(D10516220_HowManyAorB_option3_Computer1.temp.isEmpty()){//�p�G�q��1�L���٨S�q���@������
								try{
									if(D10516220_HowManyAorB_option3_Computer1.bingo==true)break;//�p�G�٨S��Ĺ�a���� || D10516220_HowManyAorB_option3_Computer2.bingo2 == true
									//System.out.println("9");
									temp2.wait();
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							execution.confirmed(box2,keySplit,user_ab[0],user_ab[1]);//�R�����i�઺����
							temp2.notify();//�s�q��1�~��q
						}
						
					}
				}
			}
		}
		if(bingo2 == true && D10516220_HowManyAorB_option3_Computer1.bingo == false){//�q��1���S��Ĺ
			System.out.println("\n\t\t�q��1����F�I");
			System.out.println("\t\t�`�@��F�G" + D10516220_HowManyAorB_option3_Computer1.fr + "��");
		}
 	}
}

