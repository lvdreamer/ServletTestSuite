package com.lvdreamer.basic;

import java.util.function.Supplier;

public class LazyInit {

    private Supplier<LazyObj> lazyObjSupplier = () -> createLazyObj();

    public LazyObj getLazyObj() {
        return lazyObjSupplier.get();
    }

    private synchronized LazyObj createLazyObj() {
        class LazyObjFactory implements Supplier<LazyObj> {
            private final LazyObj LazyObjInstance = new LazyObj();

            @Override
            public LazyObj get() {
                return LazyObjInstance;
            }
        }
        if (!LazyObjFactory.class.isInstance(lazyObjSupplier)) {
            lazyObjSupplier = new LazyObjFactory();
        }
        return lazyObjSupplier.get();
    }

    public static void main(String[] args) {
        LazyInit lazyInit = new LazyInit();
        System.out.println(System.identityHashCode(lazyInit.getLazyObj()));
        System.out.println(System.identityHashCode(lazyInit.getLazyObj()));
        System.out.println(System.identityHashCode(lazyInit.getLazyObj()));
    }


}

class LazyObj {
    public LazyObj() {
        System.out.println(this + " created");
    }
}