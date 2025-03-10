package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class Product extends AbstractItem {
    private String productId;
    private String productName;
    private Integer productQuantity;

    @Override
    public String getId() {
        return this.productId;
    }

    @Override
    public void setId(String id) {
        this.productId = id;
        super.setId(id);
    }

    @Override
    public String getName() {
        return this.productName;
    }

    @Override
    public void setName(String name) {
        this.productName = name;
        super.setName(name);
    }

    @Override
    public int getQuantity() {
        return this.productQuantity != null ? this.productQuantity : 0;
    }

    @Override
    public void setQuantity(int quantity) {
        this.productQuantity = quantity;
        super.setQuantity(quantity);
    }
}