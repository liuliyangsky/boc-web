package com.cherrypicks.boc.model;

import java.util.Date;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class CouponQuota extends AbstractEntity {
	/** 
	 * CouponQuota status:
	 * 0-PENDING
	 * 1-ACTIVE
	 * 2-FINISH 
	 * 3-SUSPEND
	 * 4-INCOMPLETE
	 */
	public static final int PENDING = 0;
	public static final int ACTIVE = 1;
	public static final int FINISH = 2;
	public static final int SUSPEND = 3;
	public static final int INCOMPLETE = 4;
	private static final long serialVersionUID = 4287944914262182925L;
	
	private Object couponId;
	
	private Object areaId;
	
	private Object campaignId;
	
	private Date  startDate;
	
	private Integer status; //0 pending,1 approved,2
	
	private Integer quota;
	
	private Integer gn;
	
	private Integer rn;
	
	private Integer isCarryFollow;

	public Object getCouponId() {
		return couponId;
	}

	public void setCouponId(Object couponId) {
		this.couponId = couponId;
	}

	public Object getAreaId() {
		return areaId;
	}

	public void setAreaId(Object areaId) {
		this.areaId = areaId;
	}

	public Object getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Object campaignId) {
		this.campaignId = campaignId;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getIsCarryFollow() {
		return isCarryFollow;
	}

	public void setIsCarryFollow(Integer isCarryFollow) {
		this.isCarryFollow = isCarryFollow;
	}

	public Integer getGn() {
		return gn;
	}

	public void setGn(Integer gn) {
		this.gn = gn;
	}

	public Integer getRn() {
		return rn;
	}

	public void setRn(Integer rn) {
		this.rn = rn;
	}
	
}
