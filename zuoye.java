import com.sun.scenario.Settings;

/**
 * Created by weicong on 17-8-16.
 */
public class zuoye {
    public static void main(String[] args) {
        String[][] strings={{"id","name","age"},
                {"1","xiaoming","12"},
                {"2","xiaohua","15"}};
        String[][] strings1 = new String[4][4];
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               ;
                strings1[j][i]=strings[i][j];

            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(strings[i][j]);
            }
            System.out.println();
        }
        System.out.println(
        );
        System.out.println();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(strings1[i][j]);
            }
            System.out.println();
        }

    }



}
