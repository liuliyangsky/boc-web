package com.cherrypicks.boc.api.dao.db2;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.UserDeviceCoupon;
import com.cherrypicks.bos.api.model.CouponListModel;
@Repository
public class CouponListDaoDB2 extends AbstractBaseDaoDB2<UserDeviceCoupon> {

	public List<CouponListModel> getMyounponList(String userId, String lang) {
	  StatementParameter st=new StatementParameter();
	 st.setString(lang); 
	 st.setString(userId);
	  StringBuffer sb=new StringBuffer();
	  sb.append("select c.CAMPAIGN_ID campaignid ,  u.ID userCouponId,u.COUPON_ID couponId,to_char(c.START_TIME ,'YYYY-MM-dd') startDate,to_char(c.END_TIME ,'YYYY-MM-dd') endDate,c.STATUS status ,");
	  sb.append("(case when m.COUPON_NAME is null then m1.COUPON_NAME else m.COUPON_NAME END) as couponName,");
	  sb.append("(case when m.ICON is null then m1.ICON else m.ICON  END) as couponIcon ");
	  sb.append("from "+DBSchemaEnum.BOC_CAMPAIGN+"."+this.tableName+" u ");
	  sb.append("left join "+DBSchemaEnum.BOC_CAMPAIGN+".CAMPAIGN_COUPON c on u.COUPON_ID =c.ID  ");	
	  sb.append("left join "+DBSchemaEnum.BOC_CAMPAIGN+".CAMPAIGN_COUPON_LANG_MAP m on c.ID = m.COUPON_ID and m.LANG_CODE = ? ");
	  sb.append("left join "+DBSchemaEnum.BOC_CAMPAIGN+".CAMPAIGN_COUPON_LANG_MAP m1 on c.ID = m1.COUPON_ID and m1.LANG_CODE = 'en_US' ");
	  sb.append("where u.USER_ID = ?");
	  List <CouponListModel> mycouponList =this.queryForList(sb.toString(),CouponListModel.class,st);
		return mycouponList;
	}

	
	
}
