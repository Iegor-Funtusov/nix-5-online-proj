package com.nixsolutions.app;

import com.nixsolutions.CrudService;
import com.nixsolutions.CrudFactory;
import java.util.Collection;

public class PearsonService {
    CrudService<Pearson> pearsonCrudService = CrudFactory.getInstance().getCurrent();

    public void create(Pearson pearson) {
        pearsonCrudService.create(pearson);
    }

    public void update(Pearson pearson) {
        pearsonCrudService.update(pearson);
    }

    public void delete(String id) {
        pearsonCrudService.delete(id);
    }

    public Collection<Pearson> read() {
        return pearsonCrudService.read();
    }

    public Pearson read(String id) {
        return pearsonCrudService.read(id);
    }

}
