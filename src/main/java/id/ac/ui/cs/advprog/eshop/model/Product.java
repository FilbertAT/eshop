package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class Product implements Item {
    private String productId;
    private String productName;
    private int productQuantity;

    @Override
    public String getId() {
        return this.productId;
    }

    @Override
    public void setId(String id) {
        this.productId = id;
    }

    @Override
    public String getName() {
        return this.productName;
    }

    @Override
    public void setName(String name) {
        this.productName = name;
    }

    @Override
    public int getQuantity() {
        return this.productQuantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.productQuantity = quantity;
    }
}