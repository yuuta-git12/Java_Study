// for文による配列の要素の処理
package projava;

public class ForEachSample {
    public static void main(String[] args) {
        var nums = new int[]{2, 3, 4, 5};
        for(int num : nums){
            System.out.println(num);
        }
        var names = new String[]{"yusuke", "kis", "sugiyama"};
        for(String name : names){
            System.out.println(name);
        }
    }
}
