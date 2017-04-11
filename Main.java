import java.io.*;

/*
HIGHT & BLOW �Q�[��
���[���́A
1.������3��
2.1�̐�����1�`6�͈̔͂�����
3.�����͂��ꂼ��ʂł���A�d�����Ȃ�
4.���͂��������������̒��ɂ���A�ꏊ�������Ă���ꍇ�ɂ̓q�b�g�Ƃ��Đ�����
5.���͂��������������̒��ɂ��邪�A�ꏊ�͂����ĂȂ��ꍇ�u���[�Ƃ��Đ�����
6.3�̐������ׂĂ��q�b�g�ɂȂ�����A�Q�[���N���A

*/
public class Main{
	
	public static void main(String args[]){
		
        int[] answer = new int[3];// ����������

        //�^�C�g���̕\��
        System.out.println("*** HIGHT & BLOW �Q�[�� ***");
		
        //���[���̕\��
		dispRule();
        
        //�����_���ȓ������쐬�B
		answer = createAnswer();
		
        //�Q�[����
		doGame(answer);

	}
	
	//���[����\������
	private static void dispRule(){
        String rule = "�B���ꂽ3�̐��������Ă܂��B\n"
                    + "1�̐�����1����6�̊Ԃł��B\n"
                    + "3�̓����̒��ɓ��������͂���܂���B\n"
                    + "���͂��������́A"
                    + "�ʒu�Ɛ������������Ă���q�b�g�A\n"
                    + "�������������Ă���u���[�ƃJ�E���g���܂��B\n"
                    + "�S�����Ă���(3�Ƃ��q�b�g�ɂȂ�����)"
                    + "�I���ł�\n\n";
					
        System.out.println(rule);
	}
	
	//�������쐬����
	private static int[] createAnswer(){
		int[] answer = new int[3];// ����������
		int[] seed = {1,2,3,4,5,6};
		
		//seed�ɓ���������50��V���b�t������
		for( int i = 0; i < 50; i++ ){
			//����ւ���C���f�N�X���擾����
			//idx1�Ԗڂ̃f�[�^��idx2�Ԗڂ̃f�[�^�����ւ���
			int idx1 = (int) (Math.random() * 6);
			int idx2 = (int) (Math.random() * 6);
			
			//����ւ����
			int work = seed[idx1];
			seed[idx1] = seed[idx2];
			seed[idx2] = work;
		}
		
		//�V���b�t���������ʂ̍ŏ��̂R�𓚂��Ƃ���
		for( int i = 0; i < answer.length; i++) {
			answer[i] = seed[i];
		}
		
		return answer;
	}
	
	//�Q�[�������s����
	private static void doGame(int[] answer){
		
        int[] input = new int[3];// ���͂�������������
		
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in));
			
		 /*
         * hit��hit�̃J�E���g�p�Ablow��blow�̃J�E���g�p�B 
         * count�͉���ڂ̃`�������W���𐔂��Ă���B
         */
        int hit = 0, blow = 0, count = 0;
		
        while (true) {
            count++;
            System.out.println("*** "+count + "��� ***");
            //�C���v�b�g
			input = getInputNumber(answer.length);
			
            //�������f
            hit = 0;
            blow = 0;
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < answer.length; j++) {
                    if (i == j && input[i] == answer[j]) {
                        hit++;
                    } else if (input[i] == answer[j]) {
                        blow++;
                    }
                }
            }
            //�I�����f�@�q�b�g��3�ɂȂ�����I��
            System.out.println("�q�b�g" + hit + " �u���[" + blow);
            if (hit == 3) {
                System.out.println("���߂łƁ[");
                break;
            }else{
                System.out.print("\n");
            }
        }
	}
	
	//��������͂��z��ɓ���ĕԂ�
	private static int[] getInputNumber(int answerLength){
		int[] input = new int[3];// ���͂�������������
		
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in));
			
        //�C���v�b�g
        for (int i = 0; i < answerLength; i++) {
            System.out.print( (i + 1) + "�� : ");
            try {
				//���͂����l���擾���Đ��l�ɕϊ�����
                input[i] = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.err.println("���l����͂��Ă�������");
                i--;
            } catch (IOException e) {
                System.err.println("������x���͂��Ă�������");
                i--;
            }
        }
		
		return input;
	}
}