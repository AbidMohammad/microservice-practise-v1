package com.app.springcloud.restclients;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.springcloud.entity.Coupon;

//@FeignClient("GATEWAY-SERVICE")
//@FeignClient("COUPON-SERVICE")
@FeignClient("ZUUL-PROXY-SERVICE")
@RibbonClient("COUPON-SERVICE")
public interface CouponRestClient {

//	@GetMapping("/couponapi/coupons/{code}")
	@GetMapping("/coupon-service/couponapi/coupons/{code}")
	ResponseEntity<Coupon> getCoupon(@PathVariable("code")String code);
}
