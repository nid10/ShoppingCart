package shoppingCart;
import java.util.ArrayList;

public class ShoppingCart {
	public static void main(String[] args) {
		System.out.println("====Welcome to Shopping Cart====\n");
		Product apple = new Product("apple", 100.00, 10);
		Product milk = new Product("milk", 50.00, 10);
		Product newspaper = new Product("newspaper", 5.00, 10);
		
		ProductStock productStock = new ProductStock();
		productStock.addProductsToStock(apple, milk, newspaper);
		productStock.display();
		
		Customer Nidhi = new Customer("Nidhi",2000.00);
		Nidhi.addToCart("apple", 2);
		Nidhi.addToCart("newspaper",5);
		Nidhi.checkOut();
		Nidhi.checkBalance();
		}
}


class Product {
	String name;
	double price;
	int quantity;
	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}


class ProductStock {
	public static ArrayList<Product> productsList = new ArrayList<>();
	
	public void addProductsToStock(Product... productObjects) {
		for (Product i : productObjects) {
			productsList.add(i);
		}
	}
	public static void display() {
		System.out.println("Product Name      Price/unit      Quantity");
		for (Product item : ProductStock.productsList) {
			System.out.println(item.name+"              "+item.price+"            "+item.quantity);
		}
		System.out.println("\n");
	}
}


class Customer {
	String name;
	double wallet; 
	ArrayList<CustomerCart> cartList = new ArrayList<>();
	public Customer(String name, double wallet) {
		this.name = name;
		this.wallet = wallet;
	}

	public void addToCart(String productName, int quantity) {
		int flag = 0;
		for (Product i : ProductStock.productsList) {
			if (i.name==productName) {
				flag=1;
				if(i.quantity>=quantity) {
					System.out.println(quantity+" "+productName+" "+"added to cart");
					double totalPrice = (i.price*quantity);
					CustomerCart customerCart = new CustomerCart(productName, quantity, totalPrice);
					cartList.add(customerCart);
				}else {
					System.out.println(i.quantity+"unit of"+i.name+"are left in Stock..Please enter quantity within range");
				}
			if (flag==0) {
				System.out.println("there is no such product named"+productName+"is in Stock");
			}
			}
		}				
	}
	
	public void checkOut() {
		double total = 0;
		for (CustomerCart i : cartList) {
			total+=i.price;
		}

		if (this.wallet >= total) {
			this.wallet-=total;
			for (CustomerCart item : cartList) {
				for (Product product : ProductStock.productsList) {
					if (item.name == product.name) {
						product.quantity-=item.quantity;
					}
				}
			}
			clearCart();
			System.out.println("Order placed successfully");
			System.out.println("Your available balance is = "+this.wallet);
			
		}
		else {
			System.out.println("You do not have enough balance");
			System.out.println("your wallet Balance is = "+this.wallet);
		}
	}
	public void clearCart() {
		cartList.clear();  
	}
	public double checkBalance() {
		return this.wallet;
	}
}

class CustomerCart {
	String name;
	int quantity;
	double price;
	public CustomerCart(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
}




