package kill.me.palas.classes;

public class ArrayCheckRoles {
    private CheckRoles addRoles;
    private CheckRoles delRoles;

    public ArrayCheckRoles(CheckRoles addRoles, CheckRoles delRoles) {
        this.addRoles = addRoles;
        this.delRoles = delRoles;
    }

    public ArrayCheckRoles() {
    }

    public CheckRoles getAddRoles() {
        return addRoles;
    }

    public void setAddRoles(CheckRoles addRoles) {
        this.addRoles = addRoles;
    }

    public CheckRoles getDelRoles() {
        return delRoles;
    }

    public void setDelRoles(CheckRoles delRoles) {
        this.delRoles = delRoles;
    }
}
