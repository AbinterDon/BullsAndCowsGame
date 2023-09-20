import java.util.LinkedList;
import java.util.Queue;
class D10516220_HowManyAorB{
	public static void main(String arg[]){//�ƪ��G�� D10516220 �d�F��
		try{
			D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();//Ū�����O
			D10516220_HowManyAorB_execution execution = new D10516220_HowManyAorB_execution();//�������O
			System.out.println("�d�F�� �XA�XB HowManyAorB");
			System.out.println("\n�п�ܹC���Ҧ� \n1�� �q���X�D ���a�q \n2�� ���a�X�D �q���q \n3�� �q�����q \n4�� ���}�C��");
			System.out.println("\n�п�ܹC���Ҧ��G");
			String type = inp.gameType();
			while(type.equals("1")==false && type.equals("2")==false && type.equals("3")==false && type.equals("4")==false){
				System.out.println("��J���~�A�п�J 1 �� 2 �� 3 �� 4");
				type = inp.gameType();
			}
			if(type.equals("1")){//���q���X�D���a�q
				System.out.println("--------���a�q---------");
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 �إߩҦ����i�઺�ƦC�զX�}�C true=����ΩάO�ιL false=�i�H��
				D10516220_HowManyAorB_execution.confirmed(box);//��ƦC�զX�����ƪ��令true �N�����				
				String[] Ans = new String[4];//�˳̲׵��ת��}�C
				
				int temp_guess = execution.produce(box)+1023;//�üƲ��͵���
				Ans = inp.Split(Integer.toString(temp_guess));
				System.out.println(Ans[0]+Ans[1]+Ans[2]+Ans[3]);//����

				System.out.println("---------�}�l----------");
				
				Queue<Integer> temp = new LinkedList<Integer>();
				D10516220_HowManyAorB_option1_Computer option1_Computer = new D10516220_HowManyAorB_option1_Computer(box,temp,false,Ans);
				D10516220_HowManyAorB_option1_User option1_User = new D10516220_HowManyAorB_option1_User();
				option1_Computer.start();//�C���}�l �}�l�q��
				option1_User.start();
				
			}else if(type.equals("2")){//�����a�X���q���q
				System.out.println("--------�q���q---------");
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 �إߩҦ����i�઺�ƦC�զX�}�C true=����ΩάO�ιL false=�i�H��
				D10516220_HowManyAorB_execution.confirmed(box);//��ƦC�զX�����ƪ��令true �N�����
				String[] Ans = new String[4];//�˳̲׵��ת��}�C
				Ans = inp.Read("�п�J����(4��Ƥ����Ƽƭ�)�G","answer");//�����ѨϥΪ̳]�w�̲׵���
							
				System.out.println("---------�}�l----------");
				
				Queue<Integer> temp = new LinkedList<Integer>();				
				D10516220_HowManyAorB_option2_User option2_User = new D10516220_HowManyAorB_option2_User(box,temp,false,Ans,2);
				D10516220_HowManyAorB_option2_Computer option2_Computer = new D10516220_HowManyAorB_option2_Computer();				
				option2_Computer.start();//�C���}�l �}�l�q��
				option2_User.start();
			}else if(type.equals("3")){//�q�����q
				System.out.println("--------�q�����q---------");
				
				boolean[] box = new boolean[8854];//9876-1023=8853+1=8854 �إߩҦ����i�઺�ƦC�զX�}�C true=����ΩάO�ιL false=�i�H��
				D10516220_HowManyAorB_execution.confirmed(box);//��ƦC�զX�����ƪ��令true �N�����
				String[] Ans = new String[4];//�˳̲׵��ת��}�C
				
				int temp_guess = execution.produce(box)+1023;//�üƲ��͵���
				Ans = inp.Split(Integer.toString(temp_guess));
				System.out.print("�q��1���סG"+Ans[0]+Ans[1]+Ans[2]+Ans[3]);//����
				
				boolean[] box2 = new boolean[8854];//9876-1023=8853+1=8854 �إߩҦ����i�઺�ƦC�զX�}�C true=����ΩάO�ιL false=�i�H��
				D10516220_HowManyAorB_execution.confirmed(box2);//��ƦC�զX�����ƪ��令true �N�����				
				String[] Ans2 = new String[4];//�˳̲׵��ת��}�C
				
				int temp_guess2 = execution.produce(box2)+1023;//�üƲ��͵���
				Ans2 = inp.Split(Integer.toString(temp_guess2));
				System.out.println("\t�q��2���סG"+Ans2[0]+Ans2[1]+Ans2[2]+Ans2[3]);//����
							
				System.out.println("---------�}�l----------");
				Queue<Integer> temp = new LinkedList<Integer>();//�q��2�q����		
				Queue<Integer> temp2 = new LinkedList<Integer>();//�q��1�q����
						
				D10516220_HowManyAorB_option3_Computer1 option3_Computer1 = new D10516220_HowManyAorB_option3_Computer1(box,temp,false,Ans);//�q��1 �t�d�^�������O
				D10516220_HowManyAorB_option3_Computer2 option3_Computer2 = new D10516220_HowManyAorB_option3_Computer2(box2,temp2,false,Ans2);//�q��2 �t�d�^�������O
				D10516220_HowManyAorB_option3_Computer1_guess option3_Computer1_guess = new D10516220_HowManyAorB_option3_Computer1_guess();//�q��1 �t�d�q�����O
				D10516220_HowManyAorB_option3_Computer2_guess option3_Computer2_guess = new D10516220_HowManyAorB_option3_Computer2_guess();//�q��2 �t�d�^�������O
					
				option3_Computer1_guess.start();//�C���}�l �}�l�q��
				option3_Computer1.start();
				option3_Computer2_guess.start();			
				option3_Computer2.start();
			}else{
				System.out.println("���±z������I");
			}
		}catch (Exception e){
			System.out.println("Error�I");
		}
	}
}