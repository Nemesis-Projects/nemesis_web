package webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import webservice.model.Coupon;
import webservice.model.CouponRepository;

import java.util.*;

/**
 * Created by neeadepu on 2/9/16.
 */
@Component
public class ApplicationBootStrap implements CommandLineRunner {

    @Autowired
    CouponRepository couponRepository;
    @Override
    public void run(String... args) throws Exception {

        createEssentialDataCoupons();

    }

    private void createEssentialDataCoupons()
    {
        if(couponRepository.count() == 0 ) {
            List<Coupon> coupons = couponRepository.save(createCoupons());
            System.out.println("Succesfully created " + coupons.size() + " coupons ");
        }

    }

    private List<Coupon> createCoupons()
    {
        List<Coupon> coupons = new ArrayList<Coupon>();
        for (int i = 0 ; i < 10 ; i++)
        {
            Calendar cal = new GregorianCalendar();
            Coupon coupon = new Coupon();
            coupon.setCode(UUID.randomUUID().toString());
            if( i%3 == 0) {
                coupon.setActive(Boolean.FALSE);
                coupon.setRedemptions(Long.valueOf(100 + 10*i));
                int year = 52 - i*5;
                cal.add(Calendar.WEEK_OF_YEAR, -year);
                coupon.setStartDate(cal.getTime());
                cal.add(Calendar.WEEK_OF_YEAR, -year + 10);
                coupon.setEndDate(cal.getTime());
                coupon.setEnabled(Boolean.FALSE);
            }
            else
            {
                int year = 52 - i*5;
                cal.add(Calendar.WEEK_OF_YEAR, -year);
                coupon.setStartDate(cal.getTime());
                cal.add(Calendar.WEEK_OF_YEAR, +year);
                coupon.setEndDate(cal.getTime());
                coupon.setActive(Boolean.TRUE);
                coupon.setRedemptions(Long.valueOf(0));
                coupon.setEnabled(Boolean.TRUE);
            }
            coupon.setMaxRedemptions(Long.valueOf(1000 + 100 * i));
            coupons.add(coupon);
        }

        return coupons;
    }
}

