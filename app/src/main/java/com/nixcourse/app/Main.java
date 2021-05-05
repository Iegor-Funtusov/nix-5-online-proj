package com.nixcourse.app;

import com.nixcourse.lib.BaseEntity;

public class Main {

    public static void main(String[] args) {
        BaseEntity base = new BaseEntity();
        base.setId("1");

        System.out.println(base.getId());
    }
}
