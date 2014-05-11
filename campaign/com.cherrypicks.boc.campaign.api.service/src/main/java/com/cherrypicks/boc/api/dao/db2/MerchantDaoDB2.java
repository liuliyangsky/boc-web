package com.cherrypicks.boc.api.dao.db2;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.Merchant;
import com.cherrypicks.bos.api.model.MerchantDetailModel;

@Repository
public class MerchantDaoDB2 extends AbstractBaseDaoDB2<Merchant>{

	public MerchantDetailModel getMerchantDetail(String lang, String merchantId) {
		StatementParameter param=new StatementParameter();
		param.setLong(Long.parseLong(merchantId));
		param.setString(lang);
		StringBuilder sb = new StringBuilder();
		sb.append("select L.MERCHANT_NAME,L.MERCHANT_CONTENT, L.MERCHANT_DETAIL,M.MERCHANT_PHONE from ");
		
		
		sb.append(DBSchemaEnum.BOC_CAMPAIGN).append(".").append(this.tableName).append(" M left join ").append(DBSchemaEnum.BOC_CAMPAIGN).append(".");
		
		sb.append("MERCHANT_LANG_MAP L ON L.MERCHANT_ID = M.ID where M.ID=? and L.LANG_CODE=?");
		
		MerchantDetailModel merchantDetail=this.query(sb.toString(), MerchantDetailModel.class, param);
		
		return  merchantDetail;
	}

}
