package mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import mySampleApplication.client.Manager;
import mySampleApplication.client.MySampleApplicationService;

import java.util.ArrayList;
import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    private static List<Manager> managers = null;
    static {
        managers = new ArrayList<>();
        managers.add(new Manager("Николаев", "Клим", "NK-01", 20));
        managers.add(new Manager("Таланков", "Владислав", "TV-20", 4));
        managers.add(new Manager("Виноградов", "Андрей", "VA-15", 8));
        managers.add(new Manager("Киреев", "Александр", "KA-03", 17));
        managers.add(new Manager("Айрапетов", "Давид", "AD-04", 13));
    }

    @Override
    public List<Manager> getManagersList() {
        return managers;
    }
}