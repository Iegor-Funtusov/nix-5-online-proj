package ua.com.alevel.lib;

import java.util.Objects;
import java.util.Random;

public class BaseEntity {
    private long id;

    public BaseEntity() {
        id = Math.abs(new Random().nextLong());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}