package projava;

public class RemoveDuplicate_re {
    public static void main(String[] args) {
        var data = "abcde";

        var builder = new StringBuilder();
        for(int i = 0; i< data.length(); i+=2){
            if(i+1 < data.length()){
                builder.append(data.charAt(i+1));
            }
            builder.append(data.charAt(i));
        }
        var result = builder.toString();
        System.out.println(result);
    }
}
