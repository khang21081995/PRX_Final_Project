package db;

/**
 * Created by khang on 6/30/2017.
 */
public class CGV_DAO {

    private DataAccessObject dao = null;

    public CGV_DAO() {
        try {
            dao = new DataAccessObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
