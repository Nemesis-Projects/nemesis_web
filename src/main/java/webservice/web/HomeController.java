package webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webservice.model.Coupon;
import webservice.service.CouponService;

import java.util.List;

@Controller
public class HomeController {

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

}
