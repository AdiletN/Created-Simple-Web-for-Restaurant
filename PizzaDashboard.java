import java.util.*;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PizzaDashboard{
	public static PizzaStore pizzaStore = new PizzaStore();
	public static void main(String[] args) throws PizzaNotFoundException{
		Scanner sc = new Scanner(System.in);
		PizzaInterface pizzaService = new PizzaService(pizzaStore);
		System.out.println("Hey Buddy, Welcome to SDU's Pizza Spot  :) \n");
		for (;;) {
			System.out.println("Please Choose your Role to login:");
			System.out.println("1) ADMIN");
			System.out.println("2) CUSTOMER");
			System.out.println("3) EXIT");
			try{
				int n = sc.nextInt();
				if(n!=1 && n!=2 && n!=3)
					System.out.println("Please write only 1 or 2 or 3 :/\n");
				switch(n){
				case 1:
					System.out.println("\nWelcome to ADMIN console  :)");
					openAdminRole(sc,pizzaService);
					break;
				case 2:
					System.out.println("\nWelcome to CUSTOMER console  :)");
					openCustomerRole(sc,pizzaService);
					break;
				case 3:
					System.out.println("THANK YOU FOR COOPERATION :)");
					return;
				default:
					break;
				}
			}
			catch(InputMismatchException e){
				System.out.println(e+": Please write your Inputs correct :/");
				return;
			}
		}
	}
	public static void openAdminRole(Scanner sc,PizzaInterface pizzaService) throws PizzaNotFoundException{
		try{
			for (int i = 1;;i++) {
				System.out.println("\nChoose an Option");
				System.out.println("1) Add Pizza");
				System.out.println("2) Update Price");
				System.out.println("3) Delete Pizza");
				System.out.println("4) View All Pizza");
				System.out.println("5) Search Pizza");
				System.out.println("6) Exit");
				int n = sc.nextInt();
				if (n>6 || n<1 )
					System.out.println("\nPlease Choose number between 1 and 6  :/");
				switch(n){
				case 1:
					System.out.println("\n||_ADD NEW PIZZA MENU_||");
					System.out.println("\nEnter Details as name of Topping , spice level(basic,mediate,full),description to add a new Topping...");
					sc.nextLine();
					String details_1 = sc.nextLine();
					String[] details_for_1 = details_1.split(",");
					if(details_for_1.length>3){
						System.out.println("\nPlease write only 3 parameters  :/ \n");
						return;
					}
					if(details_for_1[0].matches(".*\\d.*")==true || !details_for_1[1].toLowerCase().equals("basic") && !details_for_1[1].toLowerCase().equals("mediate") && !details_for_1[1].toLowerCase().equals("full") || !details_for_1[2].toLowerCase().equals("yes") && !details_for_1[2].toLowerCase().equals("no")){
						System.out.println("\nPlease write your parameters correct  :/ \n");
						return;
					}
					Topping topping = new Topping(details_for_1[0].trim(),details_for_1[1].trim(),details_for_1[2].trim());


					System.out.println("Enter Details as name of Base,type(thin/thick),description to add a new PizzaBase...");
					String details_2 = sc.nextLine();
					String[] details_for_2 = details_2.split(",");
					if(details_for_2.length>3){
						System.out.println("Please write only 3 parameters  :/ \n");
						return;
					}
					if(details_for_2[0].matches(".*\\d.*")==true || !details_for_2[1].toLowerCase().equals("thin") && !details_for_2[1].toLowerCase().equals("thick") || !details_for_2[2].toLowerCase().equals("yes") && !details_for_2[2].toLowerCase().equals("no")){
						System.out.println("Please write your parameters correct  :/ \n");
						return;
					}
					PizzaBase pizzaBase = new PizzaBase(details_for_2[0].trim(),details_for_2[1].trim(),details_for_2[2].trim());


					System.out.println("Enter Details as name of Pizza,price,size(small,Medium,large) to add a new Pizza...");
					String details_3 = sc.nextLine();
					String[] details_for_3 = details_3.split(",");
					if(details_for_3.length>3){
						System.out.println("Please write only 3 parameters  :/ \n");
						return;
					}
					if(details_for_3[0].matches(".*\\d.*")==true || details_for_3[1].matches(".*\\d.*")==false || !details_for_3[2].toLowerCase().equals("small") && !details_for_3[2].toLowerCase().equals("medium") && !details_for_3[2].toLowerCase().equals("large")){
						System.out.println("Please write your parameters correct  :/ \n");
						return;
					}
					int id = i;
					Pizza pizza_1 = new Pizza(id,details_for_3[0].trim(),Double.parseDouble(details_for_3[1].trim()),details_for_3[2].trim(),topping,pizzaBase);
					System.out.println(pizzaService.addNewPizza(pizza_1));
					break;	

				case 2:
					System.out.println("\n||_UPDATE PIZZA MENU_||");
					System.out.println("\nEnter Pizza Name: ");
					String name_1 = sc.next();
					Pizza pizza_2 = pizzaService.getPizzaByName(name_1);
					System.out.println("Enter new Price to be updates:");
					double price = sc.nextDouble();
					System.out.println(pizzaService.updatePrice(pizza_2,price));
					break;
				case 3:
					System.out.println("\n||_DELETE PIZZA MENU_||");
					System.out.println("\nEnter Pizza Name: ");
					String name_2 = sc.next();
					Pizza pizza_3 = pizzaService.getPizzaByName(name_2);
					pizzaService.deletePizza(pizza_3);
					break;
				case 4:
					for (int j = 0;j<pizzaService.getAllPizzas().size();j++) {
						int number = j+1;
						System.out.println("\nPizza Number "+number+":");
						System.out.println(pizzaService.getAllPizzas().get(j));
					}
					break;
				case 5:
					searchPizza(sc,pizzaService);
					break;
				case 6:
					System.out.println("THANK YOU ADMIN :) \n");
					return;
				default:
					break;
				}
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			return;
		}
		catch(InputMismatchException e){
			System.out.println(e+": Please write your Inputs correct :/");
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return;			
		}
		catch(PizzaNotFoundException e){
			System.out.println(e+"\n");
			return;
		}
		catch(Exception e){
			System.out.println("Something is wrong: "+e);
		}
	}
	
	public static void openCustomerRole(Scanner sc,PizzaInterface pizzaService){
		try{
			System.out.println("Enter your doorNumber,street,city,district,state...");
			sc.nextLine();
			String details_1 = sc.nextLine();
			String[] details_for_1 = details_1.split(",");
			if(details_for_1[1].matches(".*\\d.*")==true || details_for_1[2].matches(".*\\d.*")==true || details_for_1[3].matches(".*\\d.*")==true || details_for_1[4].matches(".*\\d.*")==true){
						System.out.println("\nPlease write your parameters correct  :/ \n");
						return;
			}
			if(details_for_1.length>5){
				System.out.println("Please write only 5 parameters for Address :/ \n");
				return;
			}
			Address address = new Address(Integer.parseInt(details_for_1[0]),details_for_1[1],details_for_1[2],details_for_1[3],details_for_1[4]);

			System.out.println("Enter your Details as Id,Name,Email,Mobile...");
			String details_2 = sc.nextLine();
			String[] details_for_2 = details_2.split(",");
			if(details_for_2[1].matches(".*\\d.*")==true || details_for_2[2].matches(".*\\d.*")==true){
						System.out.println("\nPlease write your parameters correct  :/ \n");
						return;
			}
			if(details_for_2.length>4){
				System.out.println("Please write only 4 parameters for Information :/ \n");
				return;
			}
			Customer customer = new Customer(Integer.parseInt(details_for_2[0]),details_for_2[1],details_for_2[2],Long.valueOf(details_for_2[3]),address);

			System.out.println("We added you as our New Customer...\n");
			pizzaStore.addCustomer(customer);
			System.out.println(customer+"\n");

			for (;;) {
				System.out.println("Choose an Option: ");
				System.out.println("1)Order Pizza");
				System.out.println("2)Pay Bill");
				System.out.println("3)View All Pizza");
				System.out.println("4)View your Orders");
				System.out.println("5)Search Pizza");
				System.out.println("6)Exit");
				
				int choose = sc.nextInt();
				switch(choose){
				case 1:
					System.out.println("||_ORDER NEW PIZZA MENU_||\n");
					System.out.println("Available Pizzas:\n");
					for (int i = 0;i<pizzaService.getAllPizzas().size();i++) {
						int number = i+1;
						System.out.println("Pizza Number "+number+":");
						System.out.println("["+pizzaService.getAllPizzas().get(i)+"]\n");
					}
					System.out.println("Enter Pizza Name to Place your Order: ");
					String name_5 = sc.next();
					Pizza pizza_7 = pizzaService.getPizzaByName(name_5);
					System.out.println(pizzaService.orderNewPizza(pizza_7,customer)+"\n");
					break;

				case 2:
					System.out.println("\nYour Payable Bill Amount of All your Orders is Rs: "+customer.getPayableAmount()+" INR\n");
					break;

				case 3:
					for (int j = 0;j<pizzaService.getAllPizzas().size();j++) {
						int number = j+1;
						System.out.println("\nPizza Number "+number+":");
						System.out.println(pizzaService.getAllPizzas().get(j)+"\n");
					}
					break;

				case 4:
					System.out.println("\nThis is list of your all Orders: \n");
					for (int i = 0;i<customer.getOrders().size();i++) {
						int num = i+1;
						System.out.println("["+customer.getOrders().get(i));
						System.out.println("Pizza in this specific order: ");
						System.out.println(customer.getOrders().get(i).getPizzas().get(i)+"]\n");
					}
					break;

				case 5:
					searchPizza(sc,pizzaService);
					break;
				case 6:
					System.out.println("THANK YOU CUSTOMER :)\n");
					return;
				}	
			}
		}
		catch(NullPointerException e){
			System.out.println(e); 
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			return;
		}
		catch(InputMismatchException e){
			System.out.println(e+": Please write your Inputs correct :/");
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return;			
		}
		catch(PizzaNotFoundException e){
			System.out.println(e+"\n");
			return;
		}
	}
	public static void searchPizza(Scanner sc,PizzaInterface pizzaService){
		try{
			System.out.println("\n||_SEARCH PIZZA MENU_|| \n");
			System.out.println("Choose your searh type: ");
			System.out.println("1)Search by Name");
			System.out.println("2)Search by Id");
			System.out.println("3)Search by Size");
			int num = sc.nextInt();
			switch(num){
			case 1:
				System.out.println("\nEnter Pizza Name for Search: ");
				String name_3 = sc.next();
				Pizza pizza_4 = pizzaService.getPizzaByName(name_3);
				System.out.println(pizza_4+"\n");
				break;
			case 2:
				System.out.println("\nEnter Pizza Id for Search: ");
				int id_1 = sc.nextInt();
				Pizza pizza_5 = pizzaService.getPizzaById(id_1);
				System.out.println(pizza_5+"\n");
				break;
			case 3:
				System.out.println("\nEnter Pizza Size for Search: ");
				String size_1 = sc.next();
				Pizza pizza_6 = pizzaService.getPizzaBySize(size_1);
				System.out.println(pizza_6+"\n");
				break;
			default:
				break;
			}
		}
		catch(NullPointerException e){
			System.out.println(e); 
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
			return;
		}
		catch(InputMismatchException e){
			System.out.println(e+": Please write your Inputs correct :/");
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return;			
		}
		catch(PizzaNotFoundException e){
			System.out.println(e+"\n");
			return;
		}
	}
}
class Pizza{
	private PizzaBase pizzaBase;
	private Topping topping;
	private String size;
	private double price;
	private String pizzaName;
	private int pizzaId;

	public Pizza(int pizzaId,String pizzaName,double price,String size,Topping topping,PizzaBase pizzaBase){
		this.pizzaId = pizzaId;
		this.pizzaName = pizzaName;
		this.price = price;
		this.size = size;
		this.topping = topping;
		this.pizzaBase = pizzaBase;
	}
	public int getPizzaId(){
		return pizzaId;
	}
	public void setPizzaId(int pizzaId){
		this.pizzaId = pizzaId;
	}
	public String getPizzaName(){
		return pizzaName;
	}
	public void setPizzaName(String pizzaName){
		this.pizzaName = pizzaName;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
		System.out.println("Pizza Details Updated Successfully :) \n");
	}
	public String getSize(){
		return size;
	}
	public void setSize(String size){
		this.size = size;
	}
	public Topping getTopping(){
		return topping;
	}
	public void setTopping(Topping topping){
		this.topping = topping;
	}
	public PizzaBase getPizzaBase(){
		return pizzaBase;
	}
	public void setPizzaBase(PizzaBase pizzaBase){
		this.pizzaBase = pizzaBase;
	}
	@Override
	public String toString(){
		return "Pizza Details --> ID: "+getPizzaId()+" Name: "+getPizzaName()+" Price: "+getPrice()+" Size: "+getSize()+"\n"+getTopping()+"\n"+getPizzaBase();
	}
}
class Customer{
	private ArrayList<Order> orders = new ArrayList<>();
	private Address address;
	private long mobile;
	private String email;
	private String customerName;
	private int customerId;

	public Customer(int customerId,String customerName,String email,long mobile,Address address){
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public void addOrder(Order order){
		orders.add(order);
	}
	public double getPayableAmount(){
		double amount = 0;
		for (int i = 0;i<getOrders().size();i++) {
			amount += getOrders().get(i).getPizzas().get(i).getPrice();
		}
		return amount;
	}
	public int getCustomerId(){
		return customerId;
	}
	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public String getCustomerName(){
		return customerName;
	}
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public long getMobile(){
		return mobile;
	}
	public void setMobile(long mobile){
		this.mobile = mobile;
	}
	public Address getAddress(){
		return address;
	}
	public void setAddress(Address address){
		this.address = address;
	}
	public ArrayList<Order> getOrders(){
		return orders;
	}
	public void setOrders(ArrayList<Order> orders){
		this.orders = orders;
	}
	@Override
	public String toString(){
		return "Customer Details --> Id: "+getCustomerId()+" Name: "+getCustomerName()+" Email: "+getEmail()+" Mobile: "+getMobile()+"\n"+getAddress();
	}
}
class Order{
	Random r = new Random();
	private ArrayList<Pizza> pizzas = new ArrayList<>();
	private String orderDescription;
	private double payableBillAmount;
	private String orderDate;
	private int orderId;

	public Order(){
		String orderDescription = "spicy";
		double payableBillAmount = 0.0;
		String orderDate = "null";
		int orderId = 0;
	}
	public Order(int orderId,String orderDate,double payableBillAmount,String orderDescription){
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.payableBillAmount = payableBillAmount;
		this.orderDescription = orderDescription;
	}
	public void addPizza(Pizza pizza){
		pizzas.add(pizza);
		System.out.println("Your Order added successfully :) \n");
	}
	public int getOrderId(){
		orderId = r.nextInt(100);
		return orderId;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public String getOrderDate(){
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        orderDate = currentDate.format(formatter);
		return orderDate;
	}
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	public double getPayableBillAmount(){
		setPayableBillAmount(getPizzas().get(0).getPrice());
		return payableBillAmount;
		
	}
	public void setPayableBillAmount(double payableBillAmount){
		this.payableBillAmount = payableBillAmount;
	}
	public String getOrderDescription(){
		return orderDescription = "spicy";
	}
	public void setOrderDescription(String orderDescription){
		this.orderDescription = orderDescription;
	}
	public ArrayList<Pizza> getPizzas(){
		return pizzas;
	}
	public void setPizzas(ArrayList<Pizza> pizzas){
		this.pizzas = pizzas;
	}
	@Override
	public String toString(){
		return "Order Details --> Id: "+getOrderId()+" Date: "+getOrderDate()+" Bill Amount: "+getPayableBillAmount()+" Description: "+getOrderDescription();
	}
}
class PizzaStore{
	private ArrayList<Customer> customers = new ArrayList<>();
	private ArrayList<Pizza> pizzas = new ArrayList<>();
	private String storeLocation;
	private String storeName;
	private int storeId;

	public PizzaStore(){
		storeId = 302;
		storeName = "SDU";
		storeLocation = "Abylai khan 1/1";
	}
	public PizzaStore(int storeId,String storeName,String storeLocation){
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeLocation = storeLocation;
	}
	public void addPizza(Pizza pizza){
		pizzas.add(pizza);
		System.out.println("New Pizza Added Successfully  :) \n");
	}
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	
	public void deletePizza(Pizza pizza) throws PizzaNotFoundException{
		if(pizzas.contains(pizza)){
			int index = pizzas.indexOf(pizza);
			pizzas.remove(index);
			System.out.println("Pizza has been deleted");
		}
		else{
			throw new PizzaNotFoundException("Pizza was not Found");
		}
	}
	public int getStoreId(){
		return storeId;
	}
	public void setStoreId(int storeId){
		this.storeId = storeId;
	}
	public String getStoreName(){
		return storeName;
	}
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	public String getStoreLocation(){
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation){
		this.storeLocation = storeLocation;
	}
	public ArrayList<Pizza> getPizzas(){
		return pizzas;
	}
	public void setPizzas(ArrayList<Pizza> pizzas){
		this.pizzas = pizzas;
	}
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers){
		this.customers = customers;
	}
	@Override
	public String toString(){
		return "Pizza Store Details --> Store Id: "+getStoreId()+" Store Name: "+getStoreName()+" Store Location: "+getStoreLocation();
	}
}
class PizzaService implements PizzaInterface{
	private String pizzaNotFoundMessage = "Pizza was not Found";
	private PizzaStore pizzaStore;
	private Order order = new Order();

	public PizzaService(PizzaStore pizzaStore){
		this.pizzaStore = pizzaStore;
	}
	@Override
	public Pizza addNewPizza(Pizza pizza){
		pizzaStore.addPizza(pizza);
		return pizza;
	}
	public void deletePizza(Pizza pizza) throws PizzaNotFoundException{
		pizzaStore.deletePizza(pizza);
	}
	public Pizza updatePrice(Pizza pizza,double newPrice){
		pizza.setPrice(newPrice);
		return pizza;
	}
	public ArrayList<Pizza> getAllPizzas(){
		return pizzaStore.getPizzas();
	}
	public Pizza orderNewPizza(Pizza pizza,Customer customer){
		order.addPizza(pizza);
		customer.addOrder(order);
		return pizza;
	}
	public Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException{
		for (int i = 0;i<pizzaStore.getPizzas().size();i++) {
			if (pizzaStore.getPizzas().get(i).getPizzaName().equals(pizzaName)) {
				return pizzaStore.getPizzas().get(i);				
			}
		}
		throw new PizzaNotFoundException(pizzaNotFoundMessage);
	}
	public Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException{
		for (int i = 0;i<pizzaStore.getPizzas().size();i++) {
			if (pizzaStore.getPizzas().get(i).getPizzaId()==pizzaId){
				return pizzaStore.getPizzas().get(i);				
			}
		}
		throw new PizzaNotFoundException(pizzaNotFoundMessage);
	}
	public Pizza getPizzaBySize(String size) throws PizzaNotFoundException{
		for (int i = 0;i<pizzaStore.getPizzas().size();i++) {
			if (pizzaStore.getPizzas().get(i).getSize().equals(size)) {
				return pizzaStore.getPizzas().get(i);				
			}
		}
		throw new PizzaNotFoundException(pizzaNotFoundMessage);
	}
}
interface PizzaInterface{
	Pizza getPizzaBySize(String size) throws PizzaNotFoundException;
	Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException;
	Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException;
	Pizza orderNewPizza(Pizza pizza,Customer customer);
	ArrayList<Pizza> getAllPizzas();
	Pizza updatePrice(Pizza pizza,double newPrice);
	void deletePizza(Pizza pizza) throws PizzaNotFoundException;
	Pizza addNewPizza(Pizza pizza);
}
class PizzaNotFoundException extends Exception{
	public PizzaNotFoundException(String message) {
        super(message);
    }
}
class Address{
	private String state;
	private String district;
	private String city;
	private String street;
	private int doorNumber;

	public Address(int doorNumber,String street,String city,String district,String state){
		this.doorNumber = doorNumber;
		this.street = street;
		this.city = city;
		this.district = district;
		this.state = state;
	}
	public int getDoorNumber(){
		return doorNumber;
	}
	public void setDoorNumber(int doorNumber){
		this.doorNumber = doorNumber;
	}
	public String getStreet(){
		return street;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district){
		this.district = district;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	@Override
	public String toString(){
		return "Address Details --> Door Number: "+getDoorNumber()+" Street: "+getStreet()+" City: "+getCity()+" District: "+getDistrict()+" State: "+getState();
	}
}
class PizzaBase{
	private String description;
	private String baseType;
	private String baseName;

	public PizzaBase(String baseName,String baseType,String description){
		this.baseName = baseName;
		this.baseType = baseType;
		this.description = description;
	}
	public String getBaseName(){
		return baseName;
	}
	public void setBaseName(String baseName){
		this.baseName = baseName;
	}
	public String getBaseType(){
		return baseType;
	}
	public void setBaseType(String baseType){
		this.baseType = baseType;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	@Override
	public String toString(){
		return "Pizza Base Details --> Base Name: "+getBaseName()+" Type: "+getBaseType()+" Description: "+getDescription();
	}
}
class Topping{
	private String description;
	private String spiceLevel;
	private String toppingName;

	public Topping(String toppingName,String spiceLevel,String description){
		this.toppingName = toppingName;
		this.spiceLevel = spiceLevel;
		this.description = description;
	}
	public String getToppingName(){
		return toppingName;
	}
	public void setToppingName(String toppingName){
		this.toppingName = toppingName;
	}
	public String getSpiceLevel(){
		return spiceLevel;
	}
	public void setSpiceLevel(String spiceLevel){
		this.spiceLevel = spiceLevel;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	@Override
	public String toString(){
		return "Topping Details --> Topping Name: "+getToppingName()+" Spice Level: "+getSpiceLevel()+" Description: "+getDescription();
	}
}