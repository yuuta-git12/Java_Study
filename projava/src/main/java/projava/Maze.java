package projava;

import java.io.IOException;

public class Maze {
    public static void main(String[] args) throws IOException {
        class Position {
            private final int x;
            private final int y;
            
            Position(int x, int y) {
                this.x = x;
                this.y = y;
            }
            
            int x() { return x; }
            int y() { return y; }
            
            @Override
            public boolean equals(Object obj) {
                if (obj instanceof Position) {
                    Position pos = (Position) obj;
                    return x == pos.x && y == pos.y;
                }
                return false;
            }
        }
        
        int[][] map = {
            {1,1,1,1,1,1,1},
            {1,0,1,0,0,0,1}, 
            {1,0,0,0,1,0,1},
            {1,0,1,0,0,0,1},
            {1,0,0,0,0,0,1},
            {1,0,1,1,0,0,1},
            {1,1,1,1,1,1,1}
        };
        var current = new Position(1,3);
        var goal = new Position(4, 1);
        var uppper = false;
        for(;;){
            //迷路の表示
            for(int y = current.y()-2; y<current.y()+2; y++){
                for(int x = current.x()-2; x<current.x()+2; x++){
                    if (y < 0 || y >= map.length || x < 0 || x >= map[y].length) {
                        System.out.print("#");
                    }else if(x == current.x() && y == current.y()){
                        System.out.print(uppper? "O":"o");
                    }else if(map[y][x] == 1){
                        System.out.print("*");
                    }else if( x == goal.x() && y == goal.y()){
                        System.out.print("G");
                    }else{
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            // ゴール判定
            if(current.equals(goal)){
                System.out.println("GOAL!!");
                break;
            }
            //キー入力処理
            int ch = System.in.read();
            if(ch == '\n') continue;
            // 押された方向の座標を取得
            var next = switch(ch){
                case 'l' -> new Position(current.x()-1, current.y()); //左
                case 'u' -> new Position(current.x(), current.y()-1); //上
                case 'r' -> new Position(current.x()+1, current.y()); //右
                case 'd' -> new Position(current.x(), current.y()+1); //下
                default -> current;
            };
            // 押された方向が通路なら進む
            if(map[next.y()][next.x()] == 0){
                if(!current.equals(next)){
                    uppper = !uppper;
                }
                current = next;
            }
            // Enterキーの入力を捨てる
            System.in.read();
        }
    }
}

