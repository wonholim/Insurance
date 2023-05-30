package controller;

import dao.FSSDAO;
import fss.FSS;

public class FSSController {
    public boolean login(String[] fss) {
        return new FSSDAO().login(fss);
    }

    public boolean register(FSS fss) {
        return new FSSDAO().register(fss);
    }
}
