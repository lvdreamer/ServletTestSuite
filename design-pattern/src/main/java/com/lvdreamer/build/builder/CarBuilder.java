package com.lvdreamer.build.builder;

public class CarBuilder {

    private String wheel;
    private String frame;

    private CarBuilder(Builder builder) {
        this.wheel = builder.wheel;
        this.frame = builder.frame;
    }

    @Override
    public String toString() {
        return "CarBuilder{" +
                "wheel='" + wheel + '\'' +
                ", frame='" + frame + '\'' +
                '}';
    }

    public static class Builder {
        private String wheel;
        private String frame;

        public Builder(String wheel, String frame) {
            this.wheel = wheel;
            this.frame = frame;
        }

        public CarBuilder builder() {
            return new CarBuilder(this);
        }

        public Builder withWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }

        public Builder withFrame(String frame) {
            this.frame = frame;
            return this;
        }
    }
}
