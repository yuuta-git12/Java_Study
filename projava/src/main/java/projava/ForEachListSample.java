// 拡張for文によるListの要素の処理
package projava;

import java.util.ArrayList;
import java.util.List;

public class ForEachListSample {
    public static void main(String[] args) {
        var strs = List.of("apple","banana","greape");
        
        var result = strs.stream()  // Streamソース
                .filter(str -> str.length() == 5) // 中間処理（値を操作する）
                .peek(System.out::println) 
                .toList(); // 終端処理(値をまとめる)
        
        var cnt = result.size();
        var result_flag = result.stream()
                .noneMatch(str -> !str.contains("p"));

        System.out.println(result);
        System.out.println(cnt);
        System.out.println(result_flag);

        var names = List.of("yusuke","kis","sugiyama");
        names.forEach(name -> {
            // System.out.println(name);
        });
    }
}
