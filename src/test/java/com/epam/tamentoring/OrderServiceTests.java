package com.epam.tamentoring;

import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class OrderServiceTests {
    private static final Product product = new Product();

    @BeforeAll
    public static void setUp(){
        product.setId(11);
        product.setName("Cola");
        product.setPrice(15.0);
        product.setQuantity(2);
    }

    @Mock
    OrderService orderServiceMock;

    @Test
    void calledOnesTest(){
        orderServiceMock = Mockito.mock(OrderService.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        UserAccount userAccount = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(productList));
        Mockito.when(orderServiceMock.getOrderPrice(userAccount)).thenReturn(55.0);

        orderServiceMock.getOrderPrice(userAccount);
        verify(orderServiceMock, Mockito.times(1)).getOrderPrice(userAccount);
    }

    @Test
    void noInteractionsTest(){
        orderServiceMock = Mockito.mock(OrderService.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        UserAccount userAccount = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(productList));
        Mockito.when(orderServiceMock.getOrderPrice(userAccount)).thenReturn(55.0);
        orderServiceMock.getDiscountUtility();
        verify(orderServiceMock, Mockito.never()).getOrderPrice(userAccount);
    }

    @Test
    void userDiscountTest(){
        orderServiceMock = Mockito.mock(OrderService.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        UserAccount userAccount = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(productList));
        Mockito.when(orderServiceMock.getOrderPrice(userAccount)).thenReturn(27.0);

        Double totalDiscount = userAccount.getShoppingCart().getCartTotalPrice() - orderServiceMock.getOrderPrice(userAccount);
        assertEquals(3.0, totalDiscount);
    }
}
