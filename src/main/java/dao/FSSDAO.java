package dao;

import connector.Database;
import domain.fss.FSS;

public class FSSDAO extends Database {
    public FSSDAO() {
        super.initConnection();
    }
    public boolean register(FSS fss) {
        String query = "INSERT INTO FSS values ('" + fss.getID() + "', '" + fss.getPassword() + "');";
        if(super.create(query)) return true;
        return false;
    }

    public boolean login(String[] user) {
        String query = "SELECT FSSID, FSSPassword FROM FSS WHERE FSSID = ? AND FSSPassword = ?";
        if(super.authentication(query, user)) return true;
        return false;
    }
}
