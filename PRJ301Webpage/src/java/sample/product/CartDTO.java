/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;
import sample.product.ProductDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thanh
 */
public class CartDTO {
    private Map<String, ProductDTO> cart;

    public CartDTO() {

    }

    public CartDTO(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public void add(ProductDTO product) {
        if (this.cart == null) {
            this.cart = new HashMap<String, ProductDTO>();
        }
        if (this.cart.containsKey(product.getProductID())) {
            int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        this.cart.put(product.getProductID(), product);
    }

    public void update(String id, ProductDTO newTea) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, newTea);
        }
    }

    public void remove(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

}
