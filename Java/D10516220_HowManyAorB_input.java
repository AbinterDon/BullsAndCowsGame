import java.util.*;
public class D10516220_HowManyAorB_input{//Ū�����O
	public static String[] Read(String tip,String type){//Ū����input
		D10516220_HowManyAorB_rule regular = new D10516220_HowManyAorB_rule();//���J�W�h���O
		Scanner inp = new Scanner(System.in);
		String keyword = "";//��J���ȸ��J
		boolean check = false;//�榡�O�_�ŦX�W�w
		do{
			if(check==true) System.out.println("��J�榡���~�A�Цb�ˬd�@��");
			System.out.print(tip);//�L�X����(�n��J�ƻ�)
			check=false;
			keyword = inp.next();//Ū����J�����
			if(type == "answer"){
				check = regular.read_rule(keyword);//�ˬd�^�������׳W�h
			}else if(type == "reply"){//userans
				check = regular.read_AB_rule(keyword);//�ˬd�^�����Xa�Xb�W�h
			}
		}while(check==true);//check=true�N��^�����ŦX�W�h 
		return Split(keyword);
	}
	
	public static String[] Split(String keyword){//��Ʀr���ά��}�C
		String[] keySplit = new String[4];//��J�Ȥ��Ѧ��}�C
		for(int i = 0;i<4;i++){//��key���� �ˤJkeySplit
			keySplit[i] = keyword.substring(i,i+1);
		}
		return keySplit;
	}
	
	public static String gameType(){
		Scanner inp = new Scanner(System.in);
		return inp.next();
	}
}