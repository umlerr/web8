package mySampleApplication.client;

import java.io.Serializable;

public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String id;
    private int seniority;

    public Manager(String name, String surname, String id, int seniority) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.seniority = seniority;
    }

    public Manager(){}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public int getSeniority() {
        return seniority;
    }
}
