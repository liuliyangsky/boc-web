package com.cherrypicks.boc.api.db2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.cherrypicks.boc.api.dao.db2.ActivityDaoDB2;
import com.cherrypicks.boc.api.dao.db2.CampaignCouponDaoDB2;
import com.cherrypicks.boc.api.dao.db2.CampaignDaoDB2;
import com.cherrypicks.boc.api.dao.db2.MerchantDaoDB2;
import com.cherrypicks.boc.common.util.SpringTest;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;
import com.cherrypicks.bos.api.model.MerchantDetailModel;
import com.ibm.db2.jcc.t4.ac;

public class TestCampaignDaoDB2 extends SpringTest {
	
	@Autowired
	private CampaignDaoDB2 campaignDaoDB2;
	
	@Autowired
	private CampaignCouponDaoDB2 campaignCouponDaoDB2;
	
	@Autowired
	private MerchantDaoDB2 merchantDaoDB2;
	
	@Autowired
	private ActivityDaoDB2 activityDaoDB2;
	
	@Test
	public void test(){
//		List<CampaignModel> model = campaignDaoDB2.getAllCampaign("zh_HK");
//		for (CampaignModel campaignModel : model) {
//			System.out.println(campaignModel.getCampaignIcon());
//			System.out.println(campaignModel.getCampaignName());
//			System.out.println(campaignModel.getCampaignId());
//			System.out.println(campaignModel.getStartDate()+"");
//			System.out.println(campaignModel.getEndDate()+"");
//		}
		List<CampaignCouponModel> list = campaignCouponDaoDB2.getCouponList("en_US", "1");
		for (CampaignCouponModel campaignCouponModel : list) {
			System.out.println(campaignCouponModel.getCouponIcon());
			System.out.println(campaignCouponModel.getCouponName());
			System.out.println(campaignCouponModel.getCampaignId());
			System.out.println(campaignCouponModel.getCouponId());
			System.out.println(campaignCouponModel.getEndDate()+"");
			System.out.println(campaignCouponModel.getStatus()+"");
			System.out.println(campaignCouponModel.getStartDate());
		}
	}
	
//	@Test
	public void testCampaignDB2(){
 CouponDetailModel list = campaignCouponDaoDB2.getCouponDetail("en_US", "4");
			System.out.println(list.getCouponIcon());
			System.out.println(list.getCouponName());
			System.out.println(list.getCampaignId());
			System.out.println(list.getCouponId());
			System.out.println(list.getEndDate());
			System.out.println(list.getStatus());
			System.out.println(list.getStartDate());
			System.out.println(list.getCouponBanner());
			System.out.println(list.getCouponCode());
			System.out.println(list.getCouponContent());
			System.out.println(list.getCouponDetail());
			System.out.println(list.getCouponTc());
			System.out.println(list.getIsKeew()+"");
			System.out.println(list.getCampaignId());
			
	}
	
//	@Test
	public void testMerchantDB2(){
		MerchantDetailModel detail = merchantDaoDB2.getMerchantDetail("en_US", "1");
		System.out.println(detail.getMerchantContent());
		System.out.println(detail.getMerchantDetail());
		System.out.println(detail.getMerchantName());
		System.out.println(detail.getMerchantPhone());
	}
	
//	@Test
	public void testActivityDB2(){
		List<ActivityModel> list = activityDaoDB2.getActivityList("en_US");
		for (ActivityModel activityModel : list) {
			System.out.println(activityModel.getActivityName());
			System.out.println(activityModel.getActivityTitle());
			System.out.println(activityModel.getEndDate());
			System.out.println(activityModel.getStartDate());
			System.out.println(activityModel.getActivityId());
		}
	}

}
