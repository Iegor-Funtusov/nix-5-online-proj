package app;

import lib.BaseEntity;

public class Point extends BaseEntity {
    private int x;
    private int y;
    private int z;
    private String name;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Point " + name +  " (" + x + ";" + y + ";" + z + ")" + " id:" + super.getId();
    }
}
