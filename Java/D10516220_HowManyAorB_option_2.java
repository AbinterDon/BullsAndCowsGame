import java.util.Queue;
import java.util.LinkedList;
class D10516220_HowManyAorB_option2_Computer extends Thread{
	static boolean bingo;
	static Queue<Integer> temp;
	static boolean[] box;//���C�w�q�A�s��ƾ�
	static String[] Ans;
	public D10516220_HowManyAorB_option2_Computer(){}
 	
	@Override
 	public void run(){
 		Computer_guess();
 	}
 
 	public void Computer_guess(){//���a�X�D ���a�^�� �q���q
		while(D10516220_HowManyAorB_option2_User.bingo2==false){
			synchronized(D10516220_HowManyAorB_option2_User.temp2){
				while(D10516220_HowManyAorB_option2_User.temp2.size()==1){//temp�����Ȫ���(��q �٨S�ֹ�)
					try{
						D10516220_HowManyAorB_option2_User.temp2.wait();//�q������	���a�^��
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(D10516220_HowManyAorB_option2_User.bingo2==true) {//�p�G�q���F
					break;
				}else{
					int rand_guess = D10516220_HowManyAorB_execution.produce(D10516220_HowManyAorB_option2_User.box2);//�H������ �q���q���Ʀr
					D10516220_HowManyAorB_option2_User.temp2.add(rand_guess);//�N�q���� �[�Jtemp
					D10516220_HowManyAorB_option2_User.temp2.notify();//�I�s�ֹ�
				}

			}
		}
 	}
}

class D10516220_HowManyAorB_option2_User extends Thread{
	static boolean bingo2;
	static Queue<Integer> temp2;
	static boolean[] box2;//���C�w�q�A�s��ƾ�
	static String[] Ans2;

	
	public D10516220_HowManyAorB_option2_User(){}
	
 	public D10516220_HowManyAorB_option2_User(boolean[] box,Queue<Integer> temp,boolean bingo,String[] Ans,int Game_type) {//�Ыؽu�{��H�c�y���
  		this.box2=box;
  		this.temp2=temp;
  		this.bingo2 = bingo;
  		this.Ans2 = Ans;
 	}
 	
	@Override
 	public void run(){
 		User_feeback();
 	}
 	
 	public void User_feeback(){//���a�X�D ���a�^�� �q���q
 		D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();
 		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
 		int fr = 0;//�q���q������
		while(bingo2==false){
			synchronized(temp2){
				while(temp2.isEmpty()){//�p�G����٨S�q����
					try{
						temp2.wait();	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				String keySplit[] = new String[4];//�ˤ��ѵ��ת��}�C
				keySplit = inp.Split(String.valueOf(temp2.peek()+1023));
				char[] computer_ab = execution.result(Ans2,keySplit);//���q����J����ȴXa�Xb computer_ab=�q���ۤv�ߤ��Xa�Xb������ 
				fr++;//�֥[�q���q������
				System.out.print("\n��" + fr + "��");//��X���G
				System.out.printf("\n�q���q���O�G"+ ((temp2.peek()+1023)) + "\n");//�C�L�q���q���Ʀr temp+1023�]���ƦC�զX�q1023�}�l
				if(computer_ab[0]=='4'){//�p�G4A �N��q���q��F ���X�j��
					bingo2 = true ;// break;
				}else{
					System.out.println("�Ѧҵ��סG"+computer_ab[0]+"A"+computer_ab[1]+"B");//��X���G

					char[] user_ab = execution.result(inp.Read("�z���^���O�Xa�Xb�G","reply"));//�ϥΪ̦^���Xa�Xb���q���Ѧ�
					while(user_ab[0]!=computer_ab[0] || user_ab[1]!=computer_ab[1]){//�P�_ �ϥΪ̦��S����������(?A?B)
						System.out.printf("�z���^�����~�A�ЦA��J�@��"+"\n");
						user_ab = execution.result(inp.Read("�z���^���O�Xa�Xb�G","reply"));//���ư���ϥΪ̦^�����T
					}
					if(bingo2==false)execution.confirmed(box2,keySplit,user_ab[0],user_ab[1]);
				}
				temp2.remove();//�������q���� 
				temp2.notify();//�s�L�~��q
			}
		}
		System.out.println("�q������F�I");
		System.out.println("�q���`�@��F�G" + fr + "��");
 	}
}