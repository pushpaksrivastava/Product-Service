package com.example.spring.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring.springcloud.dto.Coupon;
import com.example.spring.springcloud.model.Product;
import com.example.spring.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Value("${couponService.url}")
	private String couponServiceURL;
	
	@RequestMapping(value ="/products",method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL+product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);
	}
	
	@RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
	public Product getCoupon(@PathVariable("name") String name) {
		return repo.findByName(name);
	}
	
}
