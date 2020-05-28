package com;

public class Test03 {
    public volatile static Boolean isFasle =true;
    public  volatile static int conut=0;

    public static void main(String[] args) {
        Test04 test04 = new Test04();
        Test05 test05 = new Test05();
        Thread T1 = new Thread(test04);
        Thread T2 = new Thread(test05);
        T1.start();
        T2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(conut);
    }
}

//一线程
class Test04 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            synchronized (Test04.class)
            {
                if(i % 2 == 0 && i % 3 ==0 && i % 5 == 0 && i % 7 == 0)
                {
                    try {
                        Test03.isFasle=true;
                        Test04.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    }
//二线程
class Test05 extends Test04{
    @Override
    public void run() {
        while (true)
        {
            if(Test03.isFasle=true)
            {
                synchronized (Test04.class)
                {
                    Test03.conut++;
                    Test03.isFasle=false;
                    Test04.class.notify();
                }
            }
        }
    }
}