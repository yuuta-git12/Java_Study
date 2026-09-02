package org.example;

/**
 * 従来型（コロン形式）の switch 文における「フォールスルー
 * （fall-through）」の挙動を確認するためのサンプル。
 * <p>
 * Java 14 以降のアロー構文（{@code case "X" -> ...}）と異なり、
 * コロン形式の switch は各 case にマッチした後、break や continue、
 * return などで明示的に抜けない限り、次の case のラベルを無視して
 * そのまま後続の文を実行し続ける（= break がない場合のフォールスルー）。
 * <p>
 * ただしこれは「switch文」としてコロン形式を使った場合の話であり、
 * アロー構文（{@code case "X" -> ...}）を使った switch式・switch文では
 * break が無くてもフォールスルーは発生しない（各 case は独立して実行され、
 * マッチした分岐の処理が終わると自動的に switch を抜ける）。
 * ちなみにswitch式はdefaultの省略ができない
 */
public class Main_t1_q46 {
    /**
     * dayOfWeek 配列の各要素に対して switch 文を実行し、
     * break の有無によるフォールスルーの影響で a, b, x, y の値が
     * どう変化するかを追跡する。
     * <p>
     * 各要素での挙動（初期値 a=1, b=1, x=0, y=0）:
     * <ul>
     *   <li>"Sun" : case "Sat","Sun" にマッチ。x += 2 → x=2。
     *       このケースは switch 内の最後の分岐なので break が無くても
     *       そのまま switch を抜ける（フォールスルーする次の case が存在しない）。</li>
     *   <li>"Mon" : case "Mon","Tue" にマッチ。x += a++ → x=3, a=2。
     *       break が無いため、次の case "Wed" のラベル判定を
     *       スキップしてそのまま ++a（a=3）を実行 → continue で
     *       for ループの次の周へ。</li>
     *   <li>"Wed" : case "Wed" にマッチ。++a → a=4 → continue。</li>
     *   <li>"Fri" : case "Thu","Fri" にマッチ。y = --b → b=0, y=0 → break。</li>
     *   <li>"Sat" : case "Sat","Sun" にマッチ。x += 2 → x=5。
     *       最後の分岐なので break 無しでも問題なく switch を抜ける。</li>
     * </ul>
     * 最終結果: x=5, y=0（出力は "x:5y:0"）。
     * <p>
     * <b>break がないことで起こる事象:</b><br>
     * case "Mon","Tue" に break が付いていないため、本来 "Mon" だけを
     * 処理して switch を抜けるつもりでも、直後の case "Wed" の処理
     * （++a）まで連続して実行されてしまう（意図しないフォールスルー）。
     * これは case ラベルの再判定なしに次の文へ処理が流れ込むために起きる。
     * 一方、case "Sat","Sun" は switch 内の最後の分岐であるため、
     * break が無くてもフォールスルー先が存在せず、実害は出ない
     * （= 最後の case に限り break 省略の影響を受けない）。
     *
     * @param args 未使用
     */
    public static void main(String[] args){
        int a = 1,b = 1, x = 0, y = 0;
        String[] dayOfWeek = {"Sun", "Mon", "Wed", "Fri", "Sat"};
        for(String s: dayOfWeek){
            switch (s){
                case "Mon","Tue":
                    // break が無いため、この後の case "Wed" の処理まで
                    // フォールスルーして実行されてしまう
                    x += a++;
                case "Wed":
                    ++a;
                    continue;
                case "Thu","Fri":
                    y = --b;
                    break;
                case "Sat","Sun":
                    // 最後の分岐なので break が無くてもフォールスルー先がなく問題ない
                    x += 2;
                // switch文の場合はdefaultはなくても良い
            }
        }
        System.out.println("x:" + x + "y:" + y);
    }
}
