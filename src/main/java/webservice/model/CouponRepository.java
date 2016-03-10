package webservice.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CouponRepository extends MongoRepository<Coupon, String> {

    public List<Coupon> findByCodeLikeIgnoreCase(String code);

    public List<Coupon> findByActive(boolean active);

    public Coupon findByCode(String code);


}
