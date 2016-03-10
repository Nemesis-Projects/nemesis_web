package webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webservice.model.Coupon;
import webservice.service.CouponService;
import org.springframework.boot.CommandLineRunner;
import java.util.*;


@Controller
public class HomeController implements CommandLineRunner {

    private static final String COUPONS_LIST_PAGE = "coupons";
    private static final String COUPON_ADD_PAGE = "addCoupon";

    @Autowired
    CouponService couponService;

    @RequestMapping("/")
    public String index(@RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,Model model) {
    	
    	model.addAttribute("browser", userAgent);
        return "home";
    }

    @RequestMapping("/coupons")
    public String couponsList(Model model) {
        List<Coupon> coupons =  couponService.getActiveCoupons(Boolean.TRUE);
        model.addAttribute("coupons",coupons);
        return COUPONS_LIST_PAGE;
    }

    @RequestMapping(value="/coupon", method= RequestMethod.GET)
    public String couponCreateForm(Model model) {
        model.addAttribute("coupon", new Coupon());
        return COUPON_ADD_PAGE;
    }
    
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
