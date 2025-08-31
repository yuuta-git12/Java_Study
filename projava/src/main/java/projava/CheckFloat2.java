package projava;

public class CheckFloat2 {
    enum FloatState{ // 浮動小数点数の状態を表す列挙型
        START,
        INT,
        FRAC_START,
        FRAC,
        ZERO,
        FRAC_ZERO,
        MINUS
    } 

    static boolean check(String data){
        var state = FloatState.START;
        for(char ch : data.toCharArray()){
            switch(state){
                case START -> {
                    if(ch == '0'){
                        state = FloatState.ZERO;
                    }else if(ch >= '1' && ch <= '9'){
                        state = FloatState.INT;
                    }else if(ch == '-'){
                        state = FloatState.MINUS;
                    }else{
                        return false;
                    }
                }
                case ZERO -> {
                    if(ch == '.'){
                        state = FloatState.FRAC_START;
                    }else{
                        return false;
                    }
                }
                case INT -> {
                    if(ch >= '0' && ch <= '9'){
                        state = FloatState.INT;
                    }else if(ch == '.'){
                        state = FloatState.FRAC_START;
                    }else{
                        return false;
                    }
                }
                case FRAC_START,FRAC,FRAC_ZERO -> {
                    if(ch == '0'){
                        state = FloatState.FRAC_ZERO;
                    }else if(ch >= '1' && ch <= '9'){
                        state = FloatState.FRAC;
                    }else{
                        return false;
                    }
                }
                case MINUS -> {
                    if(ch == '0'){
                        state = FloatState.ZERO;
                    }else if(ch >= '1' && ch <= '9'){
                        state = FloatState.INT;
                    }else{
                        return false;
                    }
                }
            }
        }
        return switch(state) {
            case ZERO, INT, FRAC -> true;
            default -> false;
        };
    }

    public static void main(String[] args) {
        System.out.println(check(""));  // false
        System.out.println(check("012"));  // false
        System.out.println(check(".12"));  // false
        System.out.println(check("12."));  // false 
        System.out.println(check("1.2.3"));  // false
        System.out.println(check("1..3"));  // false
        System.out.println(check("0"));  // true
        System.out.println(check("12"));  // true
        System.out.println(check("12.3"));  // true
        System.out.println(check("0.3"));  // true
        System.out.println(check("12.30"));  // false
        System.out.println(check("12.0"));  // false
        System.out.println(check("12.033"));  // true
        System.out.println(check("-123"));  // true
        System.out.println(check("-12.3"));  // true
        System.out.println(check("-12.0"));  // false
        System.out.println(check("-12.033"));  // true
        System.out.println(check("-12.033.0"));  // false
        System.out.println(check("-0.3"));  // true
        System.out.println(check("-0.0"));  // false
        System.out.println(check("-0.033"));  // true
        System.out.println(check("-0.033.0"));  // false
        System.out.println(check("-0.033.0"));  // false
    }  
}
