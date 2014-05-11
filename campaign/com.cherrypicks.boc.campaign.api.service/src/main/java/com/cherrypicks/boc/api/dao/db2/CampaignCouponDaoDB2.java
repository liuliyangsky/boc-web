package com.cherrypicks.boc.api.dao.db2;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;

@Repository
public class CampaignCouponDaoDB2 extends AbstractBaseDaoDB2<CampaignCoupon>{

	public List<CampaignCouponModel> getCouponList(String lang,String campaignId) {
		StatementParameter param=new StatementParameter();
		param.setString(lang);
		param.setLong(Long.parseLong(campaignId));
		List<CampaignCouponModel> couponList=this.queryForList("select C.CAMPAIGN_ID campaignId,C.ID couponId,to_char(C.START_TIME,'YYYY-MM-dd') startDate,to_char(C.END_TIME,'YYYY-MM-dd') endDate,L.COUPON_NAME couponName,L.ICON  couponIcon," +
				
		"C.STATUS status from "+DBSchemaEnum.BOC_CAMPAIGN+"."+this.tableName+" C left join "+DBSchemaEnum.BOC_CAMPAIGN+"."+
			
		"CAMPAIGN_COUPON_LANG_MAP L ON L.COUPON_ID = C.ID where L.LANG_CODE=? and C.CAMPAIGN_ID=? order by C.DISPLAY_ORDER", CampaignCouponModel.class,param );
		
		return  couponList;
	}

	public CouponDetailModel getCouponDetail(String lang, String couponId) {
		StatementParameter param=new StatementParameter();
		param.setLong(Long.parseLong(couponId));
		param.setString(lang);
		StringBuilder sb = new StringBuilder();
		sb.append("select C.CAMPAIGN_ID,C.ID COUPON_ID,M.COUPON_NAME,M.COUPON_CONTENT,M.COUPON_DETAIL,M.T_AND_C COUPON_TC,M.ICON COUPON_ICON,M.BANNER COUPON_BANNER,");
		
		sb.append("C.COUPON_CODE,C.COUPON_TYPE,to_char(C.START_TIME,'YYYY-MM-dd') START_DATE,to_char(C.END_TIME,'YYYY-MM-dd') END_DATE,C.STATUS,C.MERCHANT_ID from ");
		
		sb.append(DBSchemaEnum.BOC_CAMPAIGN).append(".").append(this.tableName).append(" C left join ").append(DBSchemaEnum.BOC_CAMPAIGN).append(".");
		
		sb.append("CAMPAIGN_COUPON_LANG_MAP M ON M.COUPON_ID = C.ID where C.ID=? and M.LANG_CODE=?");
		
		CouponDetailModel couponDetailList=this.query(sb.toString(), CouponDetailModel.class, param);
		return  couponDetailList;
	}
	
	public boolean updateCouponGnSum(Long id){
		StatementParameter param=new StatementParameter();
		param.setLong(id);
		
		StringBuilder sql = new StringBuilder();
		//table
		sql.append(" update "+DBSchemaEnum.BOC_CAMPAIGN+".campaign_coupon ");
		//value
		sql.append(" update set GN_SUM=GN_SUM+1");
		//where
		sql.append(" where id=?");
		
		return this.updateForBoolean(sql.toString(), param);
	}
	
	public boolean updateCouponRnSum(Long id){
		StatementParameter param=new StatementParameter();
		param.setLong(id);
		
		StringBuilder sql = new StringBuilder();
		//table
		sql.append(" update "+DBSchemaEnum.BOC_CAMPAIGN+".campaign_coupon ");
		//value
		sql.append(" update set RN_SUM=RN_SUM+1");
		//where
		sql.append(" where id=?");
		
		return this.updateForBoolean(sql.toString(), param);
	}

}
