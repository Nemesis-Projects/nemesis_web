package webservice.service;

import webservice.model.Coupon;

import java.util.List;

public interface CouponService {
    public List<Coupon> couponsList();
    public List<Coupon> searchForCouponsByCode (String searchTerm);
    public List<Coupon> getActiveCoupons( boolean active);
    public Coupon getCouponForCode(String code);
}
