package kill.me.palas.classes;

import kill.me.palas.models.Category;

public class CategoryCheck {
    private Category category;
    private int sum;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public CategoryCheck(Category category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public CategoryCheck() {
    }
}
