public class D10516220_HowManyAorB_execution{//�������O
	public static char[] result(String[] Ans,String[] KeyWord){//�p��q�� �^�����Xa�Xb
		char[] ab = new char[]{'0','0'};
		for(int i=0;i<4;i++){
			for(int x = 0;x<4;x++){
				if(Ans[i].equals(KeyWord[x]) && i==x){//��m�ۦP �ƭȬۦP
					ab[0]++;
				}else if(Ans[i].equals(KeyWord[x])){//�ƭȬۦP
					ab[1]++;
				}
			}
		}
		return ab;
	}
	
	public static char[] result(String[] keyword){//�p��user �^�����Xa�Xb
		char[] ab = new char[]{'0','0'};
		ab[0] = keyword[0].charAt(0);
		ab[1] = keyword[2].charAt(0);
		return ab;
	}

	public static int produce(boolean[] box){//���Ͷü� 
		int number = 0;
		do{
			number = (int)(Math.random()*8853+1);//����1~8853���������X 8853+1023=9876
		}while(box[number]==true);
		return number;
	}

	public static void confirmed(boolean[] box){//�⦳���ƪ��ƦC�զX���令true �N�����
		D10516220_HowManyAorB_rule regular = new D10516220_HowManyAorB_rule();
		for(int i = 0;i<8854;i++){
			if(regular.repeatCheck(Integer.toString(i+1023)))box[i]=true;
		}
	}
	
	public static void confirmed(boolean[] box,String[] keySplit,char a,char b){//�⦳�i�઺�ƦC�զX�d�U�� ���i�઺�令true
		D10516220_HowManyAorB_input inp = new D10516220_HowManyAorB_input();
		for(int i = 0;i<8854;i++){//8853+1023=9876
			if(box[i]==false){
				char[] temp = result(keySplit,inp.Split(Integer.toString(i+1023)));
				if(temp[0]!=a  || temp[1]!=b)box[i]=true;//�p�G�Xa�Xb���ۦP �N�N��o�ӼƦr���i��O���� �ҥH�令true
			}	
		}
	}
}