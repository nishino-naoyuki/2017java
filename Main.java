import java.io.*;

/*
HIGHT & BLOW ゲーム
ルールは、
1.数字は3つ
2.1つの数字は1～6の範囲を持つ
3.数字はそれぞれ別であり、重複しない
4.入力した数字が答えの中にあり、場所もあっている場合にはヒットとして数える
5.入力した数字が答えの中にあるが、場所はあってない場合ブローとして数える
6.3つの数字すべてがヒットになったら、ゲームクリア

*/
public class Main{
	
	public static void main(String args[]){
		
        int[] answer = new int[3];// 答えが入る

        //タイトルの表示
        System.out.println("*** HIGHT & BLOW ゲーム ***");
		
        //ルールの表示
		dispRule();
        
        //ランダムな答えを作成。
		answer = createAnswer();
		
        //ゲーム部
		doGame(answer);

	}
	
	//ルールを表示する
	private static void dispRule(){
        String rule = "隠された3つの数字をあてます。\n"
                    + "1つの数字は1から6の間です。\n"
                    + "3つの答えの中に同じ数字はありません。\n"
                    + "入力した数字の、"
                    + "位置と数字が当たってたらヒット、\n"
                    + "数字だけあってたらブローとカウントします。\n"
                    + "全部当てたら(3つともヒットになったら)"
                    + "終了です\n\n";
					
        System.out.println(rule);
	}
	
	//答えを作成する
	private static int[] createAnswer(){
		int[] answer = new int[3];// 答えが入る
		int[] seed = {1,2,3,4,5,6};
		
		//seedに入った数を50回シャッフルする
		for( int i = 0; i < 50; i++ ){
			//入れ替えるインデクスを取得する
			//idx1番目のデータとidx2番目のデータを入れ替える
			int idx1 = (int) (Math.random() * 6);
			int idx2 = (int) (Math.random() * 6);
			
			//入れ替え作業
			int work = seed[idx1];
			seed[idx1] = seed[idx2];
			seed[idx2] = work;
		}
		
		//シャッフルした結果の最初の３つを答えとする
		for( int i = 0; i < answer.length; i++) {
			answer[i] = seed[i];
		}
		
		return answer;
	}
	
	//ゲームを実行する
	private static void doGame(int[] answer){
		
        int[] input = new int[3];// 入力した答えが入る
		
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in));
			
		 /*
         * hitはhitのカウント用、blowもblowのカウント用。 
         * countは何回目のチャレンジかを数えている。
         */
        int hit = 0, blow = 0, count = 0;
		
        while (true) {
            count++;
            System.out.println("*** "+count + "回目 ***");
            //インプット
			input = getInputNumber(answer.length);
			
            //答え判断
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
            //終了判断　ヒットが3つになったら終了
            System.out.println("ヒット" + hit + " ブロー" + blow);
            if (hit == 3) {
                System.out.println("おめでとー");
                break;
            }else{
                System.out.print("\n");
            }
        }
	}
	
	//答えを入力し配列に入れて返す
	private static int[] getInputNumber(int answerLength){
		int[] input = new int[3];// 入力した答えが入る
		
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in));
			
        //インプット
        for (int i = 0; i < answerLength; i++) {
            System.out.print( (i + 1) + "個目 : ");
            try {
				//入力した値を取得して数値に変換する
                input[i] = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.err.println("数値を入力してください");
                i--;
            } catch (IOException e) {
                System.err.println("もう一度入力してください");
                i--;
            }
        }
		
		return input;
	}
}