# cardmarket4j
> A Java wrapper for the official <a href="https://api.cardmarket.com/ws/documentation/API_2.0:Main_Page">Cardmarket API</a>
## Features
cardmarket4j tries to implement every feature offered by the official REST API in form of services.

All methods are accessed through the `CardMarketService` class.
* AccountService
  * Get Account details
  * Get Messages
  * Set Vacation Status
* AuthenticationService
  * Authenticate your connection using OAuth2
* MarketplaceService
  * Get Products based on productId or ProductFilter
  * Get Articles based on productId or ArticleFilter
* OrderService
  * Get a list of your orders (sales, purchases)
* StockService
  * Get a list of your listed articles
  * Add articles to your stock
  * Remove articles from your stock
  * Edit your articles (Quantity, Price, Condition, Comment...)
## Example
### Getting access to your cardmarket account
```java
CardMarketService cardMarket = new CardMarketService("wnyJdqZJHKy2eQ28", "Jh7aj2jiR5RsFMbeKn2VijlEpjwK1nf3",
				"YHEpS1HRHkIjixlFJAHfdqHVw3r3ZS7C", "VtADsKQR7OUgN77QP8IX2DkKetxo5kU9");
```
### Get all conversations using the AccountService
```java
List<Conversation> listConversations = cardMarket.getAccountService().getMessages();
```
### Get a list of all products on Cardmarket matching your search criteria using the MarketplaceService
```java
ProductFilter productFilter = new ProductFilter("Liliana");
productFilter.setGame(Game.MTG);
productFilter.setLanguage(LanguageCode.en);
Set<Product> setProducts = cardMarket.getMarketplaceService().getProduct(productFilter);
		
for (Product product : setProducts) {
	System.out.println(product);
}
```
### Get a list of all paid sales waiting for shipping using the OrderService
```java
List<Order> listPaidSales = cardMarket.getOrderService().getOrders(OrderType.SALE, OrderState.PAID, 10);
		listPaidSales.get(0).getBuyer().getAddress();
```
### Get a list of all articles in your stock using the StockService
```java
List<Article> listStock = cardMarket.getStockService().getStock();
```
