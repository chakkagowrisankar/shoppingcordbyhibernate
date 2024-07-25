package shopping.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shopping.dao.CustomerCurd;
import shopping.dao.PaymentCurd;
import shopping.dao.ProductCurd;
import shopping.dao.ShopCurd;
import shopping.dao.VendorCurd;
import shopping.dto.Cart;
import shopping.dto.Customer;
import shopping.dto.Payment;
import shopping.dto.Product;
import shopping.dto.ProductsOfCustomer;
import shopping.dto.Shop;
import shopping.dto.Vendor;

public class MainUser {
	static Scanner scr = new Scanner(System.in);
	static VendorCurd vc = new VendorCurd();
	static CustomerCurd cc = new CustomerCurd();
	static ShopCurd sc = new ShopCurd();
	static ProductCurd pc = new ProductCurd();
	static PaymentCurd pC = new PaymentCurd();

	public static void main(String[] args) {
		System.out.println("......Wellcome.....");
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Vendor \n 2.Customer \n 3.Exit ");
			String value = scr.next();
			if (value.equals("1")) {
				vendor();
			} else if (value.equals("2")) {
				customer();
			} else if (value.equals("3")) {
				values = false;
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}

		} while (values);
		System.out.println(".....Thank You......");
	}

	private static void customer() {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Register \n 2.Login \n 3.Exit ");
			String value = scr.next();
			if (value.equals("1")) {
				registerCustomer();
			} else if (value.equals("2")) {
				loginCustomer();
			} else if (value.equals("3")) {
				values = false;
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}

		} while (values);
	}

	private static void loginCustomer() {
		System.out.println("Enter the email:");
		String email = scr.next();
		System.out.println("Enter the password:");
		String pwd = scr.next();
		Customer c = null;
		try {
			c = cc.login(email, pwd);
		} catch (Exception e) {
			System.out.println("due to the wrong data you enter please enter the correct data...");
			loginCustomer();
		}
		customerShop(c);

	}

	private static void customerShop(Customer c) {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Profile \n 2.shopping \n 3.Exit ");
			String i = scr.next();
			if (i.equals("1")) {
				boolean v = profile(c);
				if (v) {
					values = false;
				}
			} else if (i.equals("2")) {
				shopping(c);
			} else if (i.equals("3")) {
				values = false;
			} else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}

		} while (values);
	}

	private static void shopping(Customer c) {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Browser \n 2.Cart \n 3.payment \n 4.Exist ");
			String s = scr.next();
			if (s.equals("1")) {
				List<Product> l = pc.fatchAll();
				System.out.println(l);
				cart(c);
			} else if (s.equals("2")) {
				System.out.println(c);
			} else if (s.equals("3")) {
				payment(c);
			} else if (s.equals("4")) {
				values = false;
			} else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}
		} while (values);
	}

	private static void payment(Customer c) {
		List<ProductsOfCustomer> l = c.getL().getP();
		List<ProductsOfCustomer> l1 = new ArrayList<ProductsOfCustomer>();
		List<ProductsOfCustomer> rem = new ArrayList<ProductsOfCustomer>();
		if (l != null) {
			for (ProductsOfCustomer p : l) {
				System.out.println(p);
			}
			boolean val = true;
			System.out.println(
					"you want to buy the any one or more item if you want to remove print 'yes' or you want to buy all the item print 'no'");
			String ans = scr.next();
			if (ans.equalsIgnoreCase("yes")) {

				do {
					System.out.println(
							"you want to buy the any one or more item if you want to remove print 'yes' or you do not went then print 'no'");
					String ans1 = scr.next();
					if (ans1.equalsIgnoreCase("yes")) {
						System.out.println("Enter the id of the item");
						int item = scr.nextInt();
//						c = pc.removeonly(c, item);
						l1.add(pc.find1(item));
						System.out.println(l1);

					} else if (ans1.equalsIgnoreCase("no")) {
						val = false;
					} else {
						System.out.println("due to the wrong data you enter please enter the correct data...");
					}

				} while (val);

			}

			if (!l1.isEmpty()) {
				rem.addAll(l);
//				rem.removeAll(l1);
				l.clear();
				l.addAll(l1);

//				for (ProductsOfCustomer p : rem) {
//					c = pc.removeonly(c, p.getId());
//				}
//				c.getL().setL(l);
//				c=cc.save1(c);
			}
//			
			System.out.println(c);

			double price = 0;
			if (c.getL().getP() != null) {
				for (ProductsOfCustomer p : l) {
					System.out.println(p);
					price += p.getPrice() * p.getNumberOfProduct();
					pc.modify(p.getIdOfP(), p.getNumberOfProduct());
				}

				System.out.println("Enter the which mode you went to pay");
				String mode = scr.next();
				// public Payment(String mode, double bill) {
				Payment p = new Payment(mode, price);
				p = pC.save(p);
				System.out.println(p);
				System.out.println("thank you for your patronage....");
				if (rem != null) {
					for(ProductsOfCustomer p1 :rem) {
						p1.setNumberOfProduct(0);
					}
					c.getL().setL(rem);
					
				}
				pc.remove(c);
				c = cc.find(c.getId());
			}else {
				System.out.println("There is no item to pay so add a item to pay");
			}
		} else {
			System.out.println("there is no product to be pay so add the product to the cart");
		}

	}

	private static void cart(Customer c) {
		boolean val = true;
		int temp1=0;
		List<ProductsOfCustomer> l = new ArrayList();
		do {
			System.out.println(
					"do you went to buy the productes print 'yes' if you went to buy or 'no' if donot went to buy:");
			String s = scr.next();
			if (s.equalsIgnoreCase("yes")) {
				System.out.println("Enter the Product id you went to buy:");
				int i = scr.nextInt();
				Product p = pc.find(i);
				int num = 0;
				boolean temp = true;
				do {
					System.out.println("Enter the number of item you went buy:");
					num = scr.nextInt();
					if (num > p.getNumberOfProduct()) {
						System.out.println("there are not that many item are there in shop ");

					} else {
						temp = false;
					}

				} while (temp);
				if (c.getL().getP() != null) {
					l.addAll(c.getL().getP());
				}
				l.add(new ProductsOfCustomer(p.getName(), num, p.getPrice(), p.getId()));
			} else if (s.equalsIgnoreCase("no")) {
				val = false;
				temp1=1;
			} else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}

		} while (val);
		
			c.getL().setL(l);
			c = cc.save1(c);
			System.out.println(c);
		

	}

	private static boolean profile(Customer c) {
		boolean values = true;
		int temp = 0;
		do {
			System.out.println("Enter your choice \n 1.Update \n 2.delete \n 3.fatch \n 4.Exist ");
			String s = scr.next();
			if (s.equals("1")) {
				c=update(c);
			} else if (s.equals("2")) {
				cc.remove(c);
				System.out.println("Due to the deletion of the account so you have to create new account");
				values = false;
				temp = 1;
			} else if (s.equals("3")) {
				c = cc.find(c.getId());
				System.out.println(c);

			} else if (s.equals("4")) {
				values = false;
			} else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}
		} while (values);
		if (temp == 1) {
			return true;
		}
		return false;
	}

	private static Customer update(Customer c) {
		//public Customer(String name, String email, String password, long phoneNumber, String gender) {
		boolean values=true;
		do {
			System.out.println("Enter your chioce \n 1.name \n 2.email \n 3.password \n 4.phone number \n 5.gender \n 6.Eixt");
			String temp=scr.next();
			if(temp.equals("1")) {
				System.out.println("Enter the name:");
				scr.nextLine();
				c.setName(scr.nextLine());
				
			}else if(temp.equals("2")) {
				System.out.println("Enter the email ");
				c.setEmail(scr.next());
				
			}else if(temp.equals("3")) {
				System.out.println("Enter the password:");
				c.setPassword(scr.next());
				
			}else if(temp.equals("4")) {
				System.out.println("Enter the phone number");
				c.setPhoneNumber(scr.nextLong());
			}else if(temp.equals("5")) {
				System.out.println("Enter the gender ");
				c.setGender(scr.next());
			}else if(temp.equals("6")) {
				values=false;
			}else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}
		}while(values);
		c=cc.save1(c);
		return c;
	}

	private static void registerCustomer() {
		System.out.println("Enter the name :");
		scr.nextLine();
		String name = scr.nextLine();
		System.out.println("Enter the email:");
		String email = scr.nextLine();
		System.out.println("Enter the password:");
		String pwd = scr.nextLine();
		System.out.println("Enter the phone number:");
		long phno = scr.nextLong();
		System.out.println("Enter the gender:");
		String gender = scr.next();
		// public Customer(String name, String email, String password, long phoneNumber,
		// String gender) {
		Customer c = new Customer(name, email, pwd, phno, gender, new Cart());
		try {
			cc.save(c);
		} catch (Exception e) {
			System.out.println("due to the date is already exist so change the email or other data...");
			registerVendor();
		}
		System.out.println("Successfully create the account...");
	}

	private static void registerVendor() {
		System.out.println("Enter the name :");
		scr.nextLine();
		String name = scr.nextLine();
		System.out.println("Enter the email:");
		String email = scr.nextLine();
		System.out.println("Enter the password:");
		String pwd = scr.nextLine();
		System.out.println("Enter the phone number:");
		long phno = scr.nextLong();
		// public Vendor(String name, String email, String password, long phoneNumber) {
		Vendor v = new Vendor(name, email, pwd, phno);
		try {
			vc.save(v);
		} catch (Exception e) {
			System.out.println("due to the date is already exist so change the email or other data...");
			registerVendor();
		}
		System.out.println("Successfully create the account...");
	}

	private static void vendor() {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Register \n 2.Login \n 3.Exit ");
			String value = scr.next();
			if (value.equals("1")) {
				registerVendor();
			} else if (value.equals("2")) {
				loginVendor();
			} else if (value.equals("3")) {
				values = false;
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}

		} while (values);
	}

	private static void loginVendor() {
		System.out.println("Enter the email:");
		String email = scr.next();
		System.out.println("Enter the password:");
		String pwd = scr.next();
		Vendor v = null;
		try {
			v = vc.login(email, pwd);
		} catch (Exception e) {
			System.out.println("due to the wrong data you enter please enter the correct data...");
			loginVendor();
		}
		vendorLogin(v);

	}

	private static void vendorLogin(Vendor v) {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Profile \n 2.Shop \n 3.products \n 4.Exit ");
			String val = scr.next();
			if (val.equals("2")) {
				shopVendor(v);
			} else if (val.equals("3")) {
				ProuctsVendor(v);
			} else if (val.equals("4")) {
				values = false;
			}else if (val.equals("1")) {
				boolean temp = profile(v);
				if (temp) {
					values = false;
				}
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}
		} while (values);
	}

	private static boolean profile(Vendor v) {
		int temp = 0;
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Update \n 2.delete \n 3.fatch \n 4.Exist ");
			String s = scr.next();
			if (s.equals("1")) {
				v = update(v);
			} else if (s.equals("2")) {
				v = vc.find(v.getId());
				vc.delet(v);
				System.out.println("Due to the deletion of the account so you have to create new account");
				values = false;
				temp = 1;
			} else if (s.equals("3")) {
				v = vc.find(v.getId());
				System.out.println(v);

			} else if (s.equals("4")) {
				values = false;
			} else {
				System.out.println("due to the wrong data you enter please enter the correct data...");
			}
		} while (values);
		if (temp == 1) {
			return true;
		}
		return false;
	}

	private static Vendor update(Vendor v) {
		v = vc.find(v.getId());
		System.out.println(v);
		// public Vendor(String name, String email, String password, long phoneNumber) {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.name \n 2.email \n 3.password \n 4.phone number \n 5. Exit");
			String val = scr.next();
			if (val.equals("1")) {
				System.out.println("Enter the name of the vendor:");
				scr.nextLine();
				v.setName(scr.nextLine());
			} else if (val.equals("2")) {
				System.out.println("Enter the email of the vendor");
				v.setEmail(scr.next());

			} else if (val.equals("3")) {
				System.out.println("Enter the password:");
				v.setPassword(scr.next());

			} else if (val.equals("4")) {
				System.out.println("Enter the phone number of the vendor");
				v.setPhoneNumber(scr.nextLong());

			} else if (val.equals("5")) {
				values = false;
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}

		} while (values);
		return vc.save1(v);
	}

	private static void ProuctsVendor(Vendor v) {
		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.fatch All \n 2.Upload \n 3.Update \n 4.Exit ");
			String val = scr.next();
			if (val.equals("1")) {
				v = vc.find(v.getId());
				if (v.getL() != null) {
					for (int i = 0; i < v.getL().size(); i++) {
						List<Product> lp = v.getL().get(i).getP();
						System.out.println("........" + v.getL().get(i).getName() + "........");
						if(lp.isEmpty()) {
							System.out.println("There is no product in this shop");
						}else {
							for (Product p : lp) {
								System.out.println(p);
							}
						}
					}
				} else {
					System.out.println("Enter there is no shop in the in vender");
				}

			} else if (val.equals("2")) {
				System.out.println(v);
				System.out.println("Enter the Shop of the id:");
				int id = scr.nextInt();
				Shop s = sc.find(id);
				List<Product> lp = new ArrayList<Product>();
				if (s == null) {
					System.out.println(
							"due to no shop are there to add the productes so create shop first and add the productes:");
					shopOnly(v);
					System.out.println("Enter the Shop of the id:");
					id = scr.nextInt();
					s = sc.find(id);
				}
				if (s.getP() != null) {
					lp.addAll(s.getP());
				}
				System.out.println("Enter the number of the product are add to the shop:");
				int num = scr.nextInt();
				for (int i = 1; i <= num; i++) {
					// public Product(String name, int numberOfProduct, double price) {
					System.out.println("Enter the name:");
					scr.nextLine();
					String nameP = scr.nextLine();
					System.out.println("Enter the number of the Product present in the shop:");
					int nOP = scr.nextInt();
					System.out.println("Enter the price of the productes:");
					double price = scr.nextDouble();
					lp.add(new Product(nameP, nOP, price));
					s.setP(lp);
					v = sc.saveShop(s, v.getId());
				}

			} else if (val.equals("3")) {
				System.out.println(v);
				System.out.println("Enter the id of the Product you went change:");
				int id = scr.nextInt();
				Product p = pc.find(id);
				boolean value = true;
				do {
					System.out.println("Enter your choice \n 1.name \n 2.numberOfProduct \n 3.price \n 4.Exit ");
					int i = scr.nextInt();
					if (i == 1) {
						System.out.println("Enter the name:");
						p.setName(scr.next());
					} else if (i == 2) {
						System.out.println("Enter the no of Product :");
						p.setNumberOfProduct(scr.nextInt());
					} else if (i == 3) {
						System.out.println("Enter the price of the product:");
						p.setPrice(scr.nextDouble());
					} else if (i == 4) {
						value = false;
					} else {
						System.out.println("wrong choice you make place choice again.......");
					}
				} while (value);
				pc.save(p);

			} else if (val.equals("4")) {
				values = false;
			} else {
				System.out.println("wrong choice you make place choice again.......");
			}
		} while (values);
	}

	private static void shopVendor(Vendor v) {

		boolean values = true;
		do {
			System.out.println("Enter your choice \n 1.Create \n 2.Update \n 3.Delete \n 4.Fetch \n 5.Exit ");
			String val = scr.next();
			if (val.equals("1")) {
				v = createShop(v);
			} else if (val.equals("2")) {
				v = updateShop(v);
			} else if (val.equals("3")) {
				System.out.println(v);
				System.out.println("Enter the id of the shop you went to delete");
				int id = scr.nextInt();
				sc.remove(v, id);

				System.out.println("shop detiales are successfully deleted...");

			} else if (val.equals("4")) {
				System.out.println(v);
			} else if (val.equals("5")) {
				values = false;
			} else {
				System.out.println("wrong choice you make correct choice again.......");
			}
		} while (values);

	}

	private static Vendor updateShop(Vendor v) {
		List<Shop> l = v.getL();
		System.out.println(l);
		System.out.println("Enter the id of the shop you went to update :");
		String id1 = scr.next();
		int id = 0;
		try {
			id = Integer.parseInt(id1);
		} catch (NullPointerException e) {
			System.out.println("due to no shop are the in the account so create a shop first:");
			shopOnly(v);
		} catch (Exception e) {
			System.out.println("wrong date is given ");
			updateShop(v);
		}
		int temp = 0;
		for (Shop s : l) {
			if (s.getId() == id) {
				temp = 1;
				boolean val = true;
				do {
					System.out.println("Enter your choice \n 1.name \n 2.address \n 3.add the productes \n 4.Exist");
					String n = scr.next();
					if (n.equals("1")) {
						System.out.println("Enter the name of the shop:");
						s.setName(scr.next());
					} else if (n.equals("2")) {
						System.out.println();
						scr.nextLine();
						s.setAddress(scr.nextLine());
					} else if (n.equals("3")) {
						List<Product> lp = new ArrayList<Product>();
						if (s.getP() != null) {
							lp.addAll(s.getP());
						}
						System.out.println("Enter the number of the product are add to the shop:");
						int num = scr.nextInt();
						for (int i = 1; i <= num; i++) {
							// public Product(String name, int numberOfProduct, double price) {
							System.out.println("Enter the name:");
							scr.nextLine();
							String nameP = scr.nextLine();
							System.out.println("Enter the number of the Product present in the shop:");
							int nOP = scr.nextInt();
							System.out.println("Enter the price of the productes:");
							double price = scr.nextDouble();
							lp.add(new Product(nameP, nOP, price));
							s.setP(lp);
						}

					} else if (n.equals("4")) {
						val = false;
					} else {
						System.out.println("wrong choice is given so place choice correct choice:");
					}

				} while (val);
				sc.update(v);
			}
		}
		if (temp == 0) {
			System.out.println("there is no id present in the Shop so plase give correct id");
			updateShop(v);
		}
		return v;
	}

	private static Vendor createShop(Vendor v) {
		// public Shop(String name, String address) {
		System.out.println("Enter your choice \n 1.create only shop \n 2.create shop and productes");
		String val = scr.next();
		if (val.equals("1")) {
			v = shopOnly(v);
		} else if (val.equals("2")) {
			v = shopAndProduct(v);
		} else {
			System.out.println("wrong choice you make correct choice again.......");
			createShop(v);
		}
		return v;

	}

	private static Vendor shopAndProduct(Vendor v) {
		System.out.println("Enter the name:");
		scr.nextLine();
		String name = scr.nextLine();
		System.out.println("Enter the address:");
		String address = scr.nextLine();
		System.out.println("Enter the number of product you went to enter:");
		int num = scr.nextInt();
		List<Product> l = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			// public Product(String name, int numberOfProduct, double price) {
			System.out.println("Enter the name:");
			scr.nextLine();
			String nameP = scr.nextLine();
			System.out.println("Enter the number of the Product present in the shop:");
			int nOP = scr.nextInt();
			System.out.println("Enter the price of the productes:");
			double price = scr.nextDouble();
			l.add(new Product(nameP, nOP, price));
		}
		// public Shop(String name, String address, List<Product> p) {
		Shop s = new Shop(name, address, l);
		List<Shop> l1 = new ArrayList<Shop>();
		if (v.getL() != null) {
			l1.addAll(v.getL());
		}
		l1.add(s);
		v.setL(l1);
		sc.save(v);
		v = vc.find(v.getId());
		return v;
	}

	private static Vendor shopOnly(Vendor v) {
		System.out.println("Enter the name:");
		scr.nextLine();
		String name = scr.nextLine();
		System.out.println("Enter the address:");
		String address = scr.nextLine();
		Shop s = new Shop(name, address);
		List<Shop> l = new ArrayList<Shop>();
		if (v.getL() != null) {
			l.addAll(v.getL());
		}
		l.add(s);
		v.setL(l);
		sc.save(v);
		v = vc.find(v.getId());
		return v;
	}

}
