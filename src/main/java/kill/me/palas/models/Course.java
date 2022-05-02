package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите название")
    @Size(min = 2, max = 30, message = "Название не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Пожалуйста, введите описание")
    @Size(max = 1000, message = "Описание не должно быть длиннее 1000 символов")
    @Column(name = "description")
    private String description;
    @NotEmpty(message = "Пожалуйста, введите стоимость")
    @Min(value = 1000, message = "Минимальная стоимость - 1 рубль")
    @Column(name = "price")
    private int price;
    @ManyToMany
    private List<Account> accounts;

    public Course(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Course(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
