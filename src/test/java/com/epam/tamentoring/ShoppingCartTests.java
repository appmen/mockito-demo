package com.epam.tamentoring;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTests {
    private static final Product product = new Product();

    @BeforeAll
    public static void setUp(){
        product.setId(11);
        product.setName("Cola");
        product.setPrice(15);
        product.setQuantity(1);
    }

    @Test
    void addToCartTest(){
        List<Product> emptyProduct = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(emptyProduct);
        assertEquals(0, shoppingCart.getProducts().size());

        shoppingCart.addProductToCart(product);
        assertEquals(1, shoppingCart.getProducts().size());
        assertEquals(11, shoppingCart.getProducts().get(0).getId());
    }

    @Test
    void removeFromCartTest(){
        List<Product> products = new ArrayList<>();
        products.add(product);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        assertEquals(1, shoppingCart.getProducts().size());

        shoppingCart.removeProductFromCart(product);
        assertEquals(0, shoppingCart.getProducts().size());
    }

    @Test
    void getTotalPriceTest(){
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(new Product(13, "Pepsi", 16, 2));
        ShoppingCart shoppingCart = new ShoppingCart(products);
        assertEquals(47, shoppingCart.getCartTotalPrice());
    }


}
