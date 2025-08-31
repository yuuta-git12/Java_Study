package projava;

import java.util.stream.IntStream;

public class MethodRefSample {
    public static void main(String[] args) {
        //  メソッド参照を使った書き方
        IntStream.range(0, 3)
                .map(MethodRefSample::twice) // ラムダ式だと .map(x -> MethodRefSample.twice(x))
                .forEach(System.out::println);
    }
    static int twice(int x){
        return x*2;
    }
}
