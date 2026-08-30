package org.example;

class InvalidDataException extends Exception{
    public InvalidDataException(String message, Throwable cause){
        super(message, cause);
    }
}

// javac Main_t1_q26.java の場合: コンパイルエラーにならず正常に成功する
//
// 実行結果（出力3行, text = {null, "17", "Duke"} に対応）:
//   java.lang.NullPointerException: Cannot invoke "String.charAt(int)" because "<parameter1>" is null
//   17
//   java.lang.NumberFormatException: For input string: "Duke"
//
// 理由:
//   - s = null の場合: validate内 s.charAt(0) で NullPointerException 発生。
//     catch(NullPointerException | NumberFormatException e) でキャッチし、
//     InvalidDataException(e.getMessage(), e) として再送出（cause=元のNPE）。
//     main側の catch (Exception e) で e.getCause()（＝元のNPE）を println するので
//     NPEのtoString()（Java14+のヘルプフルNPEメッセージ）が出力される。
//   - s = "17" の場合: charAt(0), Integer.parseInt("17") とも例外なく成功し、validateはtrueを返す。
//     main側では例外なく s（"17"）がそのままprintlnされる。
//   - s = "Duke" の場合: charAt(0)は成功するが Integer.parseInt("Duke") で NumberFormatException。
//     同様にInvalidDataExceptionにラップされ、main側でcauseのNumberFormatExceptionが出力される。
//
//   InvalidDataException は checked例外(Exception継承)なので、
//   validateのthrows宣言・main側でのtry-catchが必須（catch漏れがあればコンパイルエラーになる）。
//
// 追加分（text3関連, 続く出力4行目〜6行目）: いずれも true
//   - text3 は """ 〜 """ のテキストブロックだが、開始行と終了行の間に実質的な内容行がなく、
//     インデント除去（incidental white space の削除）の結果、中身は空文字列 "" になる。
//   - エスケープ/埋め込み式を含まないテキストブロックはコンパイル時の定数式として扱われるため、
//     文字列リテラルと同様に文字列プール（intern）される。よって text3 は s2("") と
//     同一インスタンスを指す＝ text3 == s2 は true。
//   - text3 != s1: s1 は null。text3 は null ではない（中身が空文字列の String インスタンス）ので true。
//   - !(s1 == s2): s1(null) と s2("") は別物なので s1 == s2 は false、!false で true。
public class Main_t1_q26 {
    public static void main(String[] args){
        String[] text = {null, "17", "Duke"};
        for(String s : text){
            try{
                if(validate(s)) System.out.println(s);
            }catch (Exception e){
                System.out.println(e.getCause());
            }
        }
        String s1 = null;
        String s2 = "";
        String text3 = """
                                """;
        System.out.println(text3 != s1);    // true
        System.out.println(text3 == s2);    // true
        System.out.println(!(s1 == s2));    // true
    }

    public static boolean validate(String s) throws Exception{
        try{
            char c = s.charAt(0);
//            System.out.println(c);
            // Integer.parseInt(s): 文字列 s を10進数の int 値に変換する。
            // s が数字として解釈できない場合（空文字・数字以外の文字を含む等）は
            // NumberFormatException をスローする（ここでは "Duke" のケースで発生する）。
            // s が null の場合は charAt(0) で先に NullPointerException が発生するため
            // parseInt には到達しない。
            int a = Integer.parseInt(s);
        }catch(NullPointerException | NumberFormatException e){
            throw new InvalidDataException(e.getMessage(), e);
        }
        return true;
    }
}
