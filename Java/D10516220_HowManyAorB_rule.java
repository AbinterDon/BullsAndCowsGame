public class D10516220_HowManyAorB_rule{//�W�h���O
	public static boolean read_rule(String keyword){//�^������ȬO�_�ŦX�W�h
		if(isNumeric(keyword)==false)return true;//�O�_���Ʀr
		if(zeroCheck(Integer.parseInt(keyword))) return true;//�O�_��0�}�Y
		if(lengthCheck(keyword))return true; //�Y�W�L�|���	
		if(repeatCheck(keyword))return true;//�Y������
		return false;
	}
	
	public static boolean read_AB_rule(String keyword){//�Xa�Xb�^�� �O�_�ŦX�W�h
		if(isNumeric(keyword.substring(0,1))==false||isNumeric(keyword.substring(2,3))==false)return true;//�O�_�Ĥ@�Ӹ�ĤT�ӬO�Ʀr
		if(lengthCheck(keyword))return true; //�Y�W�L�|���	
		if(keyword.charAt(1)!='a' && keyword.charAt(1)!='A')return true;//�Y�ĤG�Ӧr ���OaorA
		if(keyword.charAt(3)!='b' && keyword.charAt(3)!='B')return true;//�Y�ĥ|�Ӧr ���OborB
		return false;
	}
	
	private static boolean isNumeric(String str){//�O�_���Ʀr
 	 for (int i = str.length();--i>=0;){    
  	 	if (!Character.isDigit(str.charAt(i)))return false;  
 	 }  
 	 return true;  
	}   
	
	private static boolean zeroCheck(int key){//�P�_�}�Y�O�_��0
		if(key < 1000) return true;
		return false;
	}
	
	private static boolean lengthCheck(String key){//�P�_�O�_�W�L4���
		if(key.length() !=4) return true;
		return false;
	}
	
	public static boolean repeatCheck(String key){//�P�_�O�_����
		for(int i = 0;i<4;i++){
			String temp=key.substring(i,i+1);
			for(int x=i+1;x<4;x++){
				if(Integer.parseInt(key.substring(x,x+1))==Integer.parseInt(temp)) return true;//������ �ߨ�^��true
			}
		}
		return false;
	}
}