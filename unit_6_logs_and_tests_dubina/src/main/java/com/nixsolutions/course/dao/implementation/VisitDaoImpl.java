package com.nixsolutions.course.dao.implementation;

import com.nixsolutions.course.dao.VisitDao;
import com.nixsolutions.course.entity.Visit;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.UUID;

public class VisitDaoImpl implements VisitDao {
    private static int sizeOfEntities = 8;
    private int indexOfEntities = 0;

    private static Visit[] entities = new Visit[sizeOfEntities];

    @Override
    public void create(Visit visit) {
        if (indexOfEntities + 1 > entities.length) {
            increaseEntities();
        }
        visit.setId(generateId(UUID.randomUUID().toString()));
        entities[indexOfEntities] = visit;
        indexOfEntities++;
    }


    @Override
    public void update(Visit visit) {
        Visit current = findById(visit.getId());
        try {
            BeanUtils.copyProperties(current, visit);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        Visit visit = findById(id);
        visit.setDeleted(true);
    }

    @Override
    public Visit findById(String id) {
        for (Visit visit : entities) {
            if (visit != null) {
                if (!visit.isDeleted() && visit.getId().equals(id)) {
                    return visit;
                }
            }
        }
        return null;
    }

    @Override
    public Visit[] findAll() {
        return Arrays.stream(entities)
                .filter(e -> e != null && !e.isDeleted())
                .toArray(Visit[]::new);
    }

    private void increaseEntities() {
        sizeOfEntities = entities.length * 2;
        entities = Arrays.copyOf(entities, sizeOfEntities);
    }

    private String generateId(String id) {
        if (Arrays.stream(entities).filter(e -> e != null).anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
