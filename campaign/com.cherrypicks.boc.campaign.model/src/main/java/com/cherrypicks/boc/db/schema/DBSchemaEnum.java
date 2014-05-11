package com.cherrypicks.boc.db.schema;

public enum DBSchemaEnum{
	
	BOC_SM_MBA("SM_MBA"),
	BOC_CAMPAIGN("BOC_CAMPAIGN");
     // 定义私有变量

     private String schema ;
     // 构造函数，枚举类型只能为私有

     private DBSchemaEnum(String _schema) {

         this . schema = _schema;

     }
     
     @Override
     public String toString() {

         return this . schema ;

     }
    
}
