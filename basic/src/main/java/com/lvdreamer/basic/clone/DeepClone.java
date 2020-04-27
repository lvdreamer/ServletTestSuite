package com.lvdreamer.basic.clone;

import java.io.*;

public class DeepClone implements Cloneable, Serializable {
    private Integer count;

    public DeepClone(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepClone cloneObj = null;
        try {
            // 写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(this);
            obs.close();

            // 分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            // 返回生成的新对象
            cloneObj = (DeepClone) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone source = new DeepClone(10);
        DeepClone target = (DeepClone) source.clone();
        System.out.println(source.getCount() != target.getCount());
        System.out.println(new Integer(10) == new Integer(10));
        Integer a=10;
        Integer b=10;
        System.out.println(a==b);
    }
}
