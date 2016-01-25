package webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.model.Coupon;
import webservice.model.CouponRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final AtomicLong counter = new AtomicLong();
    
    @Override
    public List<Coupon> couponsList() {
        Coupon coupon = new Coupon();
        final String nameCounter = "CNXT-" + counter.incrementAndGet();
        coupon.setCode(nameCounter);
        if(isDBError(coupon))
        {
            return new ArrayList<Coupon>();
        }
        //not persistent entity error when find something
        List<Coupon> couponList = couponRepository.findAll();
        return couponList;
    }

    private boolean isDBError(final Coupon coupon)
    {
        try
        {
            couponRepository.save(coupon);
            return Boolean.FALSE;
        } catch (Exception e)
        {

        }
        return Boolean.TRUE;
    }

    @Inject
    public CouponServiceImpl(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
}