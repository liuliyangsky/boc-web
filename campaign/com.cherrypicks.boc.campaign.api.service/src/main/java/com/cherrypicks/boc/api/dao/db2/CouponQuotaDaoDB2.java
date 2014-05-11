package com.cherrypicks.boc.api.dao.db2;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.CouponQuota;

@Repository
public class CouponQuotaDaoDB2 extends AbstractBaseDaoDB2<CouponQuota>{
	
	public CouponQuota getByCouponIdAndDate(Long couponId,String startDate) throws SQLException{
		StatementParameter param=new StatementParameter();
		param.setObject(couponId);
		param.setString(startDate);
		param.setInt(CouponQuota.ACTIVE);
		
		StringBuffer sql=new StringBuffer("select * from ");
		//table
		sql.append(" "+DBSchemaEnum.BOC_CAMPAIGN+".coupon_quota ");
		//where
		sql.append(" where coupon_id=? and start_date=? and status=?");
		
		CouponQuota cq=this.query(sql.toString(), CouponQuota.class,param);
		
		return cq;
	}
	
	public boolean updateCouponGn(Long id){
		StatementParameter param=new StatementParameter();
		param.setLong(id);
		
		StringBuilder sql = new StringBuilder();
		//table
		sql.append(" update "+DBSchemaEnum.BOC_CAMPAIGN+".coupon_quota ");
		//value
		sql.append(" update set GN=GN+1");
		//where
		sql.append(" where id=?");
		
		return this.updateForBoolean(sql.toString(), param);
	}
	
	public boolean updateCouponRn(Long id){
		StatementParameter param=new StatementParameter();
		param.setLong(id);
		
		StringBuilder sql = new StringBuilder();
		//table
		sql.append(" update "+DBSchemaEnum.BOC_CAMPAIGN+".coupon_quota ");
		//value
		sql.append(" update set RN=RN+1");
		//where
		sql.append(" where id=?");
		
		return this.updateForBoolean(sql.toString(), param);
	}
}
