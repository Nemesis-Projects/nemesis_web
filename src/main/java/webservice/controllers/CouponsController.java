package webservice.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Coupon;
import webservice.service.CouponService;
import java.util.List;
import javax.inject.Inject;

@RestController
public class CouponsController {

    private final CouponService couponService;
    private final AtomicLong counter = new AtomicLong();
    
    
    @RequestMapping("/coupons")
    public List<Coupon> couponsList() {
        return couponService.couponsList();
    }

    @Inject
    public CouponsController(final CouponService couponService) {
        this.couponService = couponService;
    }
}