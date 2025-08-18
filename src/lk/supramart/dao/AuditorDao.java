package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Auditor;

/**
 *
 * @author Shenu
 */
public interface AuditorDAO {

    Auditor getAuditorById(int id);

    List<Auditor> getAllAuditors();

    boolean addAuditor(Auditor auditor);

    boolean updateAuditor(Auditor auditor);

    boolean deleteAuditor(int id);
}
