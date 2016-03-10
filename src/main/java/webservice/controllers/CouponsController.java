package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Coupon;
import webservice.service.CouponService;

import java.util.List;


@RestController
public class CouponsController {



    @Autowired
    CouponService couponService;

    //@RequestMapping(method= RequestMethod.GET)
    @RequestMapping("/allcoupons")
    public List<Coupon> couponsList() {
        return couponService.couponsList();
    }

    @RequestMapping("/coupons/search")
    public List<Coupon> searchForCoupons(@RequestParam(value="code", defaultValue="A") String couponCode) {
        return couponService.searchForCouponsByCode(couponCode);
    }


}
