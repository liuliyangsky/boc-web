package com.cherrypicks.boc.api.dao.db2;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.Activity;
import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

@Repository
public class ActivityDaoDB2 extends AbstractBaseDaoDB2<Activity>{

	public List<ActivityModel> getActivityList(String lang) {
		StatementParameter param=new StatementParameter();
		param.setString(lang);
		List<ActivityModel> activityList=this.queryForList(
				
				"select A.ID ACTIVITY_ID,M.ACTIVITY_NAME,M.ACTIVITY_TITLE,to_char(A.START_DATE,'YYYY-MM-dd') START_DATE," +
				
				"to_char(A.END_DATE,'YYYY-MM-dd') END_DATE from "+
				
				DBSchemaEnum.BOC_CAMPAIGN+"."+this.tableName+" A left join "+DBSchemaEnum.BOC_CAMPAIGN+"."+
				
				"ACTIVITY_LANG_MAP M ON M.ACTIVITY_ID = A.ID where "+
				
				"A.IS_DELETED=0 and M.IS_DELETED=0 and A.STATUS=1 and M.LANG_CODE=?", ActivityModel.class,param );
		
		return  activityList;
	}
	
	public ActivityDetailModel getActivityDetail(String lang,Long activityid){
		
		StatementParameter param=new  StatementParameter();
		param.setString(lang);
		param.setLong(activityid);
		StringBuffer sb=new StringBuffer();
		
		sb.append("select a.ID as activityId,to_char(a.START_DATE ,'YYYY-MM-dd' ) as startDate, to_char(a.END_DATE ,'YYYY-MM-dd' ) as endDate,");
		sb.append("(case when m.ACTIVITY_NAME is null then m1.ACTIVITY_NAME else m.ACTIVITY_NAME END )as activityName ,(case when m.ACTIVITY_DESC is null then m1.ACTIVITY_DESC else m.ACTIVITY_DESC END ) as activityDesc ,");
		sb.append("(case when m.ACTIVITY_TITLE is null then m1.ACTIVITY_TITLE else m.ACTIVITY_TITLE END ) as activityTitle,(case when m.T_AND_C is null then m1.T_AND_C else m.T_AND_C END ) as tc ,");
		sb.append("(case when m.ICON is null then m1.ICON else m.ICON END ) as activityIcon");
		sb.append(" from "+DBSchemaEnum.BOC_CAMPAIGN+".ACTIVITY a");
		sb.append(" left join "+DBSchemaEnum.BOC_CAMPAIGN+".ACTIVITY_LANG_MAP m on m.ACTIVITY_ID = a.ID and m.LANG_CODE = ?");
		sb.append(" left join "+DBSchemaEnum.BOC_CAMPAIGN+".ACTIVITY_LANG_MAP m1 on m1.ACTIVITY_ID = a.ID and m1.LANG_CODE ='en_US'");
		sb.append(" where a.ID = ?");
		
		ActivityDetailModel activityDetail =this.query(sb.toString(),ActivityDetailModel.class ,param);
		return activityDetail;
				
	}
	
	public List<AddressModel> getAddress(Long activityid){
		StatementParameter param=new  StatementParameter();
		param.setLong(activityid);
	
		StringBuffer sb=new StringBuffer();
		sb.append("select ACTIVITY_ID as addressId ,NUMBER_OF_PEOPLE as peopleNum,ADDRESS as address ");
		sb.append("from "+DBSchemaEnum.BOC_CAMPAIGN+".ACTIVITY_ADDRESS");
		sb.append(" where ACTIVITY_ID = ?");
	
		List<AddressModel> addressmodel=this.queryForList(sb.toString(),AddressModel.class,param);
		return addressmodel;
	}

}
