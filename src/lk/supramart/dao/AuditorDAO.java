package lk.supramart.dao;

import lk.supramart.auditor.Auditor;
import java.util.List;

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
