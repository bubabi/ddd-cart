# trendyol-cart

## [_I regret to state that a very large part of the project has not been completed._]

## Cart Bounded Context
![cart](https://i.ibb.co/xs83sgz/cart.png)

## Domain Driven Design
![ddd](https://i.ibb.co/QccWbj4/ddd.png)

### Configurable Campaigns

#### Shipping Campaign

```java
public class ShippingCampaign implements Campaign {

    private final BigDecimal SHIPPING_DISCOUNT_LIMIT = BigDecimal.valueOf(100);

    @Override
    public double discount(Cart cart) {
        BigDecimal currentCartCost = cart.totalCost().amount();

        if (currentCartCost.compareTo(SHIPPING_DISCOUNT_LIMIT) > 0) {
            return cart.shippingCost().amount().doubleValue();
        } else {
            return 0;
        }
    }
}
``` 

#### User Level Campaign

```java
@Test
    void testUserLevelCampaign() throws Exception {

        Cart cart = new Cart(UUID.randomUUID(), Level.ELITE, Arrays.asList(
                new CartItem(CartFixture.p1, 1),
                new CartItem(CartFixture.p2, 1),
                new CartItem(CartFixture.p3, 1)));

        Campaign shippingCampaign = new ShippingCampaign();
        Campaign levelCampaign = new LevelCampaign();

        cart.totalCost(shippingCampaign);
        cart.totalCost(levelCampaign);

        double totalCostAfterLevelCampaign = cart.totalCost().amount().doubleValue();

        assertThat(totalCostAfterLevelCampaign).isEqualTo(161.5);
    }
``` 

##### Test Shipping Campaign


```java
@Test
    void testShippingCampaign() throws Exception {

        Cart cart = new Cart(UUID.randomUUID(), Level.NORMAL, CartFixture.cartItemsMinWorthAppliesShippingCampaign());

        Campaign shippingCampaign = new ShippingCampaign();

        Money totalCostAfterShippingCampaign = cart.totalCost(shippingCampaign);

        assertThat(totalCostAfterShippingCampaign.amount().doubleValue()).isEqualTo(100.0);
    }
``` 

```java
public class CartFixture {

    static Product p1 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(40),
            "Product1",
            "imageURL1");

    static Product p2 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(50),
            "Product2",
            "imageURL2");

    static Product p3 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(100),
            "Product3",
            "imageURL3");

    public CartFixture() throws UnknownCurrencyCodeException {
    }

    public static List<CartItem> cartItemsMinWorthAppliesShippingCampaign() {
        return Collections.singletonList(new CartItem(p3, 1));
    }
}
``` 
