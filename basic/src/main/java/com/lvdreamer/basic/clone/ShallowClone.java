package com.lvdreamer.basic.clone;

public class ShallowClone implements Cloneable {
    private Integer count;

    public ShallowClone(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ShallowClone source = new ShallowClone(10);
        ShallowClone target = (ShallowClone) source.clone();
        System.out.println(source.getCount() == target.getCount());
    }
}
