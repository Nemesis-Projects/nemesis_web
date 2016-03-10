package webservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Coupon {

    @Id
    private String id;
    @Indexed(unique = true)
    private String code;

    private Long redemptions;

    private Long maxRedemptions;

    private boolean active;

    private Date startDate;

    private Date endDate;

    private boolean enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(Long redemptions) {
        this.redemptions = redemptions;
    }

    public Long getMaxRedemptions() {
        return maxRedemptions;
    }

    public void setMaxRedemptions(Long maxRedemptions) {
        this.maxRedemptions = maxRedemptions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
