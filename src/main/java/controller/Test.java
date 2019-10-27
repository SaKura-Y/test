package controller;

public class Test {
    public static void main(String[] args) {
        int i,s=0;
        long startTime=System.currentTimeMillis();
        for (i=0;i<=100000000;i++){
            s*=i;
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
