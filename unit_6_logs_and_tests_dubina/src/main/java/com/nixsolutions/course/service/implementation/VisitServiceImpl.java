package com.nixsolutions.course.service.implementation;

import com.nixsolutions.course.dao.VisitDao;
import com.nixsolutions.course.dao.implementation.VisitDaoImpl;
import com.nixsolutions.course.entity.Visit;
import com.nixsolutions.course.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisitServiceImpl implements VisitService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final VisitDao visitDao = new VisitDaoImpl();

    @Override
    public void create(Visit visit) {
        if (isVisitExist(visit)) {
            LOGGER_INFO.info("Start create visit: " + visit.getId());
            visitDao.create(visit);
            LOGGER_INFO.info("End create visit: " + visit.getId());
        }
    }

    @Override
    public void update(Visit visit) {
        if (isVisitExist(visit) && isVisitExistById(visit.getId())) {
            LOGGER_WARN.warn("Start update visit: " + visit.getId());
            visitDao.update(visit);
            LOGGER_WARN.warn("End update visit: " + visit.getId());
        }
    }

    @Override
    public void delete(String id) {
        if (isVisitExistById(id)) {
            LOGGER_WARN.warn(("Start delete visit by ID:" + id));
            visitDao.delete(id);
            LOGGER_WARN.warn(("End delete visit by ID:" + id));
        }
    }

    @Override
    public Visit findById(String id) {
        return visitDao.findById(id);
    }

    @Override
    public Visit[] findAll() {
        return visitDao.findAll();
    }

    private boolean isVisitExist(Visit visit) {
        if (visit != null) {
            if (visit.getDoctor() == null || visit.getPatient() == null) {
                LOGGER_ERROR.error("Visit: patient or doctor doesn't exist.");
                return false;
            }
            return true;
        } else {
            LOGGER_ERROR.error("Visit doesn't exist.");
            return false;
        }
    }

    private boolean isVisitExistById(String id) {
        if (visitDao.findById(id) != null) {
            return true;
        } else {
            LOGGER_ERROR.error("Visit doesn't exist.");
            return false;
        }
    }
}
