package org.example;

// 抽象クラスTransport
abstract class Transport {
    private int speed;
    public Transport(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }
    public abstract void move();
}
// 具象クラス
class Airplane extends Transport{
    public Airplane(int speed){ super(speed); }
    @Override
    public void move(){
        System.out.println("Airplane: flying at " + getSpeed() + "km/h");
    }
}
class Ship extends Transport{
    public Ship(int speed){ super(speed);}
    @Override
    public void move(){
        System.out.println("Ship: cruising " + getSpeed() + "knots");
    }
}

//抽象クラスTransportの抽象メソッドをオーバーライドしない場合は
//こちらも抽象クラスにする必要がある
abstract class Car extends Transport{
    public Car(int speed){ super(speed);}
}

class Taxi extends Car{
    public Taxi(int speed){ super(speed);}
    @Override
    public void move(){
        System.out.println("Taxi: driving " + getSpeed() + "km/h");
    }
}

public class Main_abstract {
    public static void main(String[] args){
        new Airplane(900).move();;
        new Ship(20).move();
        new Taxi(100).move();
    }
}
