package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import webservice.model.Coupon;
import webservice.model.CouponRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;

    @Override
    public Coupon getCouponForCode(String code) {
        Assert.notNull(code);
        return couponRepository.findByCode(code);
    }

    @Override
    public List<Coupon> couponsList() {
        List<Coupon> couponList = couponRepository.findAll();
        return couponList != null ? couponList : new ArrayList<Coupon>();
    }



    @Override
    public List<Coupon> searchForCouponsByCode (final String searchTerm)
    {
        Assert.notNull(searchTerm);
        return couponRepository.findByCodeLikeIgnoreCase(searchTerm);
    }

    @Override
    public List<Coupon> getActiveCoupons(boolean active) {
        List<Coupon> couponList = couponRepository.findByActive(Boolean.TRUE);
        return couponList != null ? couponList : new ArrayList<Coupon>();
    }


}
