package com.liam.demo.jvm.methodCall;

import java.util.Random;

public class ItCompany extends Company{

    public double giveSalary(double salary, Person person){

        if (person.isSmart()){
            double smartIndex = smartIndex();
            printSmartIndex(smartIndex);
            return salary * smartIndex;
        }

        return super.giveSalary(salary, person);
    }

    private void printSmartIndex(double smartIndex){
        System.out.println(smartIndex);
    }

    public static double smartIndex(){
        return new Random().nextDouble() + 1;
    }
}
