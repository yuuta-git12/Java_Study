package projava;

import java.util.ArrayList;
import java.util.List;

public class StreamSample1 {
    public static void main(String[] args) {
        var data = List.of("yamamoto", "kis", "sugiyama");

        var result = new ArrayList<String>();
        var result_cnt = 0;
        for(String s : data){
            if(s.length() >= 5){
                result.add(s);
                result_cnt++;
            }
        }
        System.out.println(result);
        System.out.println(result_cnt);
    }
}
