package com.lvdreamer.behavior.templatemethod;

public class TemplateMethodMain {
    public static void main(String[] args) {
        AbstractStar mrCai=new MrCai();
        mrCai.talent();
        System.out.println("================================");
        AbstractStar mrLuo=new MrLuo();
        mrLuo.talent();
    }
}
