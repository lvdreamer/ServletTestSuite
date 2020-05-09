package com.lvdreamer.structure.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        SourceObj sorceObj = new SourceObj();
        SourceInterface sourceInterface = new SourceProxy(sorceObj);
        sourceInterface.work();
        sorceObj.setBusy(true);
        sourceInterface.work();

    }

}
