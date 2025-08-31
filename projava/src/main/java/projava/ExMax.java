package projava;

import java.util.Arrays;    //配列を表示するためのクラス

public class ExMax {
    public static void main(String[] args) {
        int[] data = {3,6,9,4,2,1,5};
        
        for(int i = 0; i<data.length; i++){
            if(i != data.length-1){
                if(data[i] < data[i+1]){
                    data[i] = data[i+1];
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
