package utc2.itk62.store.models;

public class Auth {
    private int id;
    private Position position;
    private Menu menu;

    public Auth() {
    }

    public Auth(Position position, Menu menu) {
        this.position = position;
        this.menu = menu;
    }

    public Auth(int id, Position position, Menu menu) {
        this.id = id;
        this.position = position;
        this.menu = menu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public Menu getMenu() {
        return menu;
    }
}
