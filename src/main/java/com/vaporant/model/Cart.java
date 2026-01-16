package com.vaporant.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	/*@ spec_public @*/ private final ArrayList<ProductBean> products;
	/*@ spec_public @*/ private double prezzoTotale = 0;

	/*@ 
	  @ public invariant prezzoTotale >= 0;
	  @ public invariant products != null;
	  @*/

	public Cart() {
		products = new ArrayList<>();
	}

	/*@ skipesc @*/
	public double getPrezzoTotale() {
		return Math.round(prezzoTotale * 100.0) / 100.0;
	}

	/*@ 
	  @ requires prezzoTotale >= 0;
	  @ ensures this.prezzoTotale == prezzoTotale;
	  @*/
	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	/*@ 
	  @ ensures \result != null;
	  @*/
	public List<ProductBean> getProducts() {
		return products;
	}

	/*@ 
	  @ public normal_behavior
	  @   requires product != null && product.getPrice() >= 0;
	  @   assignable products, prezzoTotale;
	  @*/
	public void addProduct(ProductBean product) {
		/*@ nullable @*/ ProductBean prod = containsProduct(product);

		if (prod != null) {
			if (prod.getQuantity() < prod.getQuantityStorage()) {
				aggiorna(product, prod.getQuantity() + 1);
			}
		} else {
			products.add(product);
			setPrezzoTotale(prezzoTotale + product.getPrice());
		}
	}

	/*@ 
	  @ public normal_behavior
	  @   requires product != null;
	  @   assignable prezzoTotale, products;
	  @*/
	public void deleteProduct(ProductBean product) {
		for (int i = 0; i < products.size(); i++) {
			ProductBean prod = products.get(i);
			if (prod != null && prod.getCode() == product.getCode()) {
				double newTotal = prezzoTotale - (prod.getPrice() * prod.getQuantity());
				setPrezzoTotale(newTotal >= 0 ? newTotal : 0);
				products.remove(i);
				break;
			}
		}
	}

	/*@ 
	  @ public model nullable ProductBean containsProductModel(ProductBean product);
	  @*/

	/*@ 
	  @ public normal_behavior
	  @   requires product != null;
	  @   ensures \result != null ==> products.contains(\result);
	  @*/
	public /*@ nullable @*/ ProductBean containsProduct(ProductBean product) {
		for (ProductBean pb : products) {
			if (pb != null && pb.getCode() == product.getCode()) {
				return pb;
			}
		}
		return null;
	}

	/*@ 
	  @ requires product != null;
	  @ requires quantita >= 0;
	  @*/
	/*@ 
	  @ public normal_behavior
	  @   requires product != null && quantita >= 0 && product.getPrice() >= 0;
	  @   assignable prezzoTotale, products;
	  @*/
	public void aggiorna(ProductBean product, int quantita) {
		int index;
		for (index = 0; index < products.size(); index++) {
			ProductBean current = products.get(index);
			if (current != null && current.getCode() == product.getCode()) {
				double oldPrice = current.getPrice() * current.getQuantity();
				double newPrice = current.getPrice() * quantita;
				double newTotal = prezzoTotale - oldPrice + newPrice;
				setPrezzoTotale(newTotal >= 0 ? newTotal : 0);
				current.setQuantity(quantita);
				break;
			}
		}
	}
}
