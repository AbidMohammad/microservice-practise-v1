package com.app.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.springcloud.entity.Coupon;
import com.app.springcloud.entity.Product;
import com.app.springcloud.repository.ProductRepo;
import com.app.springcloud.restclients.CouponRestClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/productapi")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CouponRestClient couponRestClient;
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@Retry(name="product-api", fallbackMethod = "handleError")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = null;
		ResponseEntity<Coupon> couponData = null;		
		Product created = null;
		Coupon coupon = null;
		try {
			if(product != null) {
				couponData = couponRestClient.getCoupon(product.getCouponCode());
				coupon = couponData.getBody();
				product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
				created = productRepo.save(product);
				responseEntity = new ResponseEntity<Product>(created, HttpStatus.OK);
			}
		} catch(Exception exception) {
			logger.error("Exception while saving Product Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
	
	@RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("name") String name) {
		Product product = null;
		ResponseEntity<Product> responseEntity = null;
		try {
			product = productRepo.findByName(name);
			responseEntity  = new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch(Exception exception) {
			logger.error("Exception while saving Product Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
	
	public ResponseEntity<Product> handleError(Product product, Exception exception){
		logger.info("<============= Executing Fallback Method =============>");
		return new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
