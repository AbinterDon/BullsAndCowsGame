import java.util.Queue;
import java.util.LinkedList;
class D10516220_HowManyAorB_option1_Computer extends Thread{
	static boolean bingo;
	static Queue<Integer> temp;
	static boolean[] box;//���C�w�q�A�s��ƾ�
	static String[] Ans;
	public D10516220_HowManyAorB_option1_Computer(){}
	
 	public D10516220_HowManyAorB_option1_Computer(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans) {//�Ыؽu�{��H�c�y���
  		this.box=box;
  		this.temp=temp;
  		this.bingo = bingo;
  		this.Ans = Ans;
 	}
 	
 	
	@Override
 	public void run(){
 		Computer_feeback();
 	}
 	
 	public void Computer_feeback(){//�q���X�D �q���^�� ���a�q
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr =0;//���a�q������
		while(bingo==false){
			synchronized(temp){
				while(temp.isEmpty()){//�p�G����٨S�q����
					try{
						temp.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				fr++;//�q�X���F�P��
				String keySplit[] = new String[4];//�ˤ��ѵ��ת��}�C
				keySplit = inp.Split(String.valueOf(temp.peek()));//���ι��q���Ʀr
				
				char[] user_ab = execution.result(Ans,keySplit);
				System.out.println(user_ab[0]+"A"+user_ab[1]+"B");//show�o���q���X���G
				if(user_ab[0]=='4')bingo=true;
		
				temp.remove();//�������q���� 
				temp.notify();//�s�L�~��q
			}
		}
		System.out.println("����F�I");
		System.out.println("�`�@��F�G" + fr + "��");
 	}
}

class D10516220_HowManyAorB_option1_User extends Thread{
	public D10516220_HowManyAorB_option1_User(){}
 	
	@Override
 	public void run(){
 		User_guess();
 	}
 	
 	public void User_guess(){//�q���X�D �q���^�� ���a�q
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr = 0;//�q���q������
		while(D10516220_HowManyAorB_option1_Computer.bingo==false){
			synchronized(D10516220_HowManyAorB_option1_Computer.temp){
				while(D10516220_HowManyAorB_option1_Computer.temp.size()==1){//temp�����Ȫ���(����q �٨S�ֹ�)
					try{
						D10516220_HowManyAorB_option1_Computer.temp.wait();	//���ݹ��q
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option1_Computer.bingo==true) {//�p�G���a�q��F
					break;
				}else{
					String[] keySplit = inp.Read("�п�J���q�����Ʀr(�|��ƭ�)�G","answer");//�ˤJ���a�q���ƭ� ��keysplit
					D10516220_HowManyAorB_option1_Computer.temp.add(Integer.parseInt(keySplit[0]+keySplit[1]+keySplit[2]+keySplit[3]));//�N�q���� �[�Jtemp
					D10516220_HowManyAorB_option1_Computer.temp.notify();//�I�s�ֹ�
				}

			}
		}
		
 	}
 	
}