package org.example;

public class Main_label {
    public static void main(String[] args){
        outer:
        for(int i = 0; true; i++){
            for(int j = 0; j < 5; j++){
                if(j == 3){
                    System.out.println("i:" + i + "skip!");
                    continue outer;
                }
                if(i == 3){
                    System.out.println("break");
                    break outer;
                }
                System.out.println("i:" + i + " j:" + j);
            }
        }
    }
}
