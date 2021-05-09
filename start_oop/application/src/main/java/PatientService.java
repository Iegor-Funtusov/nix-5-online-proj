import java.util.Collection;

public class PatientService {

    private CrudService<Patient> patientCrudService = CrudFactory.getInstance().getCurrent();

    public void create(Patient patient) {
        patientCrudService.create(patient);
    }

    public void delete(String id) {
        patientCrudService.delete(id);
    }

    public void update(Patient patient) {
        patientCrudService.update(patient);
    }

    public Patient read(String id) {
        return patientCrudService.read(id);
    }

    public Collection<Patient> read() {
        return patientCrudService.read();
    }
}
