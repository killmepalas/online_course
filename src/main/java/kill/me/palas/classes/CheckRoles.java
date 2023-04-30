package kill.me.palas.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckRoles {
    private List<Integer> admRoles = new ArrayList<Integer>();
    private List<Integer> stuRoles = new ArrayList<Integer>();
    private List<Integer> teachRoles = new ArrayList<Integer>();

    public CheckRoles() {
    }

    public CheckRoles(List<Integer> admRoles, List<Integer> stuRoles, List<Integer> teachRoles) {
        this.admRoles = admRoles;
        this.stuRoles = stuRoles;
        this.teachRoles = teachRoles;
    }

    public List<Integer> getAdmRoles() {
        return admRoles;
    }

    public void setAdmRoles(List<Integer> admRoles) {
        this.admRoles = admRoles;
    }

    public List<Integer> getStuRoles() {
        return stuRoles;
    }

    public void setStuRoles(List<Integer> stuRoles) {
        this.stuRoles = stuRoles;
    }

    public List<Integer> getTeachRoles() {
        return teachRoles;
    }

    public void setTeachRoles(List<Integer> teachRoles) {
        this.teachRoles = teachRoles;
    }
}
