package utc2.itk62.store.models;

import java.util.List;

public class Menu {
    private int id;
    private String path;
    private String name;
    private List<Auth> authList;

    public Menu(int id, String path, String name, List<Auth> authList) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.authList = authList;
    }

    public Menu() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<Auth> getAuthList() {
        return authList;
    }
}
