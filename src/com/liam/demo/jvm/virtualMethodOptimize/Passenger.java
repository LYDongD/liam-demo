package com.liam.demo.jvm.virtualMethodOptimize;

/**
 * The {@code Passenger}
 * <p>
 * 乘客，抽象类，定义虚方法
 *
 * @author liam
 * @version 1.0
 */
public abstract class Passenger {

    public abstract void exit();

    static class ChinesePassenger extends Passenger {

        @Override
        public void exit() {
//            System.out.println("走中国通道");
        }

        public void buy() {
//            System.out.println("买买买");
        }
    }

    static class ForeignerPassenger extends Passenger {

        @Override
        public void exit() {
//            System.out.println("走外国通道");
        }
    }


    /*
     *  java -XX:CompileCommand='dontinline,*.exit' com/liam/demo/jvm/virtualMethodOptimize/Passenger
     *   禁止使用方法内联的情况下, 虚方法exit()被单态内联优化，为ChinesePassenger动态类创建单台缓存，当类型变更
     *   为ForeignerPassenger，退化为超多太劣化，从该类的方法表中取找exit的目标方法，不会更新缓存
     *
     *   因此执行为ForeignerPassenger目标方法所花费的时间更多
     *
     */
    public static void main(String args[]) {
        Passenger chinesePassenger = new ChinesePassenger();
        Passenger foreignerPassenger = new ForeignerPassenger();

        //前1亿次执行动态类chinesePassenger的目标方法，后1亿次执行动态类foreignerPassenger的目标方法，比较执行时间
        long begin = System.currentTimeMillis();
        for (int i = 1; i <= 200000000; i++){
            if (i % 100000000 == 0){
                long tmp = System.currentTimeMillis();
                System.out.println(tmp - begin);
                begin = tmp;
            }
            Passenger passenger = i < 100000000 ? chinesePassenger : foreignerPassenger;
            passenger.exit();
        }
    }
}

