import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Eason
 * @since 15-3-8 上午11:45
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>(2);

        map.put("key1",1);
        map.put("key2",2);
        map.put("key3",3);
        map.put("key4",4);
        map.put("key5",5);


        System.out.println(map);

    }
}
