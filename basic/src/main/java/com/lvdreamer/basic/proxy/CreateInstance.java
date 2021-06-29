package com.lvdreamer.basic.proxy;

import java.lang.reflect.Constructor;

public class CreateInstance {


    private static class OriginalFactory<T extends OriginalInterface> {
        private Integer columnNum;
        private Class<T> clazz;

        OriginalFactory(Integer columnNum, Class<T> c) {
            this.columnNum = columnNum;
            this.clazz = c;
        }

        /**
         * 带参数的构造方法反射
         *
         * @return
         */
        public T createConverter() {
            T instance = null;
            try {
                Class<?> resolverClass = Class.forName(clazz.getName());
                Constructor<?> resolverConstructor = resolverClass.getDeclaredConstructor(Integer.class);
                instance = (T) resolverConstructor.newInstance(columnNum);
            } catch (Exception e) {

            }
            return instance;
        }

    }
}
