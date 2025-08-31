package projava;

public class RunlengthConpression3 {
    public static void main(String[] args) {
        var data = "ab0c1ba2bc9cd1";

        var builder = new StringBuilder();
        var pre = '0';
        for(var ch : data.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                if(pre == '0'){ // 0が出現したとき
                    continue;
                }
                for(int i=0; i<ch-'0'+1; i++){
                    builder.append(pre);
                }
                
            }else{ // 数字以外の文字が出現したとき
                pre = ch;
                builder.append(ch);
            }
        }
        var result = builder.toString();
        System.out.println(data);
        System.out.println(result);
    }
}