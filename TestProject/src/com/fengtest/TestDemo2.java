import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author: frt
 * @Date: 2019/3/25 16:24
 */
public class TestDemo2 {
    public static void main(String[] args){

        try {
            System.out.println("".length() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        //两层循环效率
/*        for(int i = 0 ;i<10000;i++){
            long l[] = new long[100000];

            for(int j = 0 ;j<100000;j++){
                l[j] = i*j;
            }
            System.out.println("l = " + Arrays.toString(l));
        }*/

//        Map<String,Object> map = new HashMap<>();
//
//        map.put("1","");
//
//        System.out.println(map.containsKey("1"));

        System.out.println(1%10);
    }
}
