package com.cherrypicks.boc.api.dao.db2;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.UserDeviceCoupon;

@Repository
public class UserDeviceCouponDaoDB2 extends AbstractBaseDaoDB2<UserDeviceCoupon>{
	
	public Integer getByCouponAndUser(Long couponId,String userId,Integer actionType) throws SQLException{
		StatementParameter param=new StatementParameter();
		
		StringBuffer sql=new StringBuffer("select * from ");
		//table
		sql.append(" "+DBSchemaEnum.BOC_CAMPAIGN+".user_device_coupon ");
		//where
		
		if(couponId!=null){
			param.setObject(couponId);
			sql.append(" where coupon_id=? ");
		}
		if(userId!=null){
			param.setString(userId);
			sql.append(" and user_id=? ");
		}
		if(actionType!=null){
			param.setInt(actionType);
			sql.append(" and action_type=? ");
		}
		
		int cq=this.queryForInt(sql.toString(), param);

		return cq;
	}
	
}
