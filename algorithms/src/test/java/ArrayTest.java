import java.util.Random;

/**
 * Description:
 *
 * @author Eason
 * @since 15-3-5 下午11:01
 */
public class ArrayTest {

    public static void main(String[] args) {
        Integer[][] arr = new Integer[200][100];

        Random random = new Random();

        System.out.println(arr.length);

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 100; j++) {
                arr[i][j] = random.nextInt(200);
            }
        }

        System.out.println(arr);

        for (int i = 0; i < 200; i++) {
            Integer temp = arr[i][0];
            for (int k = i + 1; k < 200; k++) {
                Integer a = arr[k][0];
                if(a>temp){
                    System.out.println(a);
                }
            }
        }


//        for(int j = 0; j < 100; j++){
//            for (int i = 0; i < 200; i++) {
//                Integer temp = arr[i][j];
//            }
//        }
//
//        for (int i = 0; i < 200; i++) {
//            for(int j = 0; j < 100; j++){
//                Integer temp = arr[i][j];
//
//            }
//        }


        //
    }
}
