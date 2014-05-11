
-- TABLE: TBL_AD
DROP TABLE SM_MBA.TBL_AD;

CREATE TABLE SM_MBA.TBL_AD  (
	INTADID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 179, INCREMENT BY 1),
	STRLOADBANNER_CHT VARCHAR(500) , 
	STRIMAGE_CHT VARCHAR(500) , 
	DTSTART TIMESTAMP NOT NULL , 
	DTEND TIMESTAMP NOT NULL , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	STRBANK VARCHAR(500) NOT NULL , 
	STRTITLE VARCHAR(500) NOT NULL , 
	BNAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	INTGROUPID INTEGER , 
	STRTYPE VARCHAR(500) , 
	STRLOADBANNER_ENG VARCHAR(500) , 
	STRIMAGE_ENG VARCHAR(500) , 
	STRLOADBANNER_CHS VARCHAR(500) , 
	STRIMAGE_CHS VARCHAR(500)
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_AD ADD CONSTRAINT PK_TBL_AD PRIMARY KEY (INTADID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_AD TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_AD TO USER medpmba;

-- TABLE: TBL_ADMIN_GROUPS
DROP TABLE SM_MBA.TBL_ADMIN_GROUPS;

CREATE TABLE SM_MBA.TBL_ADMIN_GROUPS ( 
	INTGROUPID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 5, INCREMENT BY 1), 
	STRNAME VARCHAR(255), 
	INTACTIVE INTEGER NOT NULL WITH DEFAULT 1, 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1, 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0, 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP, 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP, 
	STRBANK VARCHAR(50)
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_ADMIN_GROUPS ADD CONSTRAINT PK_TBL_ADMIN_GROUPS PRIMARY KEY (INTGROUPID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_ADMIN_GROUPS TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_ADMIN_GROUPS TO USER medpmba;

-- TABLE: TBL_ADMIN_USERS
DROP TABLE SM_MBA.TBL_ADMIN_USERS;

CREATE TABLE SM_MBA.TBL_ADMIN_USERS  (
	INTGROUPID INTEGER NOT NULL, 
	INTUSERID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 5, INCREMENT BY 1),
	STRUSERNAME VARCHAR(500) NOT NULL , 
	STRPASSWORD VARCHAR(500) NOT NULL , 
	STRNAME VARCHAR(255) , 
	INTACTIVE INTEGER NOT NULL WITH DEFAULT 1 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP, 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP, 
	STRSUPPLIER VARCHAR(500) NOT NULL WITH DEFAULT 'MTEL' , 
	BNAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNREPORT SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNCONTENTOPERATION SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNUSERADMIN SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRDEPARTMENT VARCHAR(500) 
)IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_ADMIN_USERS ADD CONSTRAINT PK_TBL_ADMIN_USERS PRIMARY KEY (INTUSERID);

CREATE UNIQUE INDEX ITBS_MBA.UQ1_TBL_ADMIN_USERS ON SM_MBA.TBL_ADMIN_USERS (STRUSERNAME ASC) ALLOW REVERSE SCANS;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_ADMIN_USERS TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_ADMIN_USERS TO USER medpmba;

-- TABLE: TBL_APP_CUSTOMERDEVICES
DROP TABLE SM_MBA.TBL_APP_CUSTOMERDEVICES;

CREATE TABLE SM_MBA.TBL_APP_CUSTOMERDEVICES  (
	STRDEVICEID VARCHAR(500) NOT NULL , 
	STRDEVICETYPE VARCHAR(500) NOT NULL , 
	STRAPPTOKENID VARCHAR(500) NOT NULL , 
	STRPERFERLANG VARCHAR(500) NOT NULL WITH DEFAULT 'zh_TW' , 
	STRAPPVERSION VARCHAR(500) , 
	STRUA VARCHAR(255) , 
	DTLASTSEEN TIMESTAMP NOT NULL , 
	DTFIRSTSEEN TIMESTAMP NOT NULL , 
	INTSTARTUP INTEGER NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTACTIVE INTEGER NOT NULL WITH DEFAULT 1 , 
	STRSERVICEID VARCHAR(500) NOT NULL WITH DEFAULT '' , 
	STROPERATORID VARCHAR(500) NOT NULL WITH DEFAULT 'UNI' , 
	STRSUBTYPE VARCHAR(255) WITH DEFAULT '' 
) IN DTBS_MBA_32K INDEX IN ITBS_MBA_32K ;

ALTER TABLE SM_MBA.TBL_APP_CUSTOMERDEVICES ADD CONSTRAINT PK_TBL_APP_CUSTOMERDEVICES PRIMARY KEY (STROPERATORID, STRSERVICEID, STRDEVICETYPE, STRDEVICEID, STRAPPTOKENID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_APP_CUSTOMERDEVICES TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_APP_CUSTOMERDEVICES TO USER medpmba;

-- TABLE: TBL_BRANCH
DROP TABLE SM_MBA.TBL_BRANCH;

CREATE TABLE SM_MBA.TBL_BRANCH  (
	INTBRANCHID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1094, INCREMENT BY 1),
	INTBOCBRANCHID INTEGER , 
	STRBRANCH_CHT VARCHAR(255) , 
	STRBRANCH_ENG VARCHAR(255) , 
	STRBRANCH_CHS VARCHAR(255) , 
	STRDISTRICT VARCHAR(255) , 
	STR9DISTRICT_ID VARCHAR(255) , 
	STR18DISTRICT_ID VARCHAR(255) , 
	STRADDRESS_CHT VARCHAR(255) , 
	STRADDRESS_ENG VARCHAR(255) , 
	STRADDRESS_CHS VARCHAR(255) , 
	STRTEL VARCHAR(255) , 
	STRFAX VARCHAR(255) , 
	BNFEATURE_ATM SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_ATMVOC SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_ATMRMB SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_PLOANDSA SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_VIP SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_FINCEN SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_EXCHANGE SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_EXCHANGERMB SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_BUYSELLGOLD SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_BUYSELLSECURITY SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_INVESTMENT SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_SAFEBOX SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRWGS84LATITUDE VARCHAR(255) , 
	STRWGS84LONGITUDE VARCHAR(255) , 
	FLTWGS84LATITUDE DOUBLE NOT NULL WITH DEFAULT 0 , 
	FLTWGS84LONGITUDE DOUBLE NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRBANK VARCHAR(255) NOT NULL , 
	STROFFICEHOUR_CHT VARCHAR(255) , 
	STROFFICEHOUR_CHS VARCHAR(255) , 
	STROFFICEHOUR_ENG VARCHAR(255) , 
	BNFEATURE_BRANCH SMALLINT NOT NULL WITH DEFAULT 0 , 
	SALESPERSON_CHT VARCHAR(255) , 
	SALESPERSON_ENG VARCHAR(255) , 
	SALESPERSONTEL VARCHAR(255) , 
	SALESPERSON_CHS VARCHAR(255) , 
	BNFEATURE_BANKNOTE SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_AIRPORT SMALLINT NOT NULL WITH DEFAULT 0 ,
	BNFEATURE_CORPRIVILEGE SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_CORCENTRE SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_BUSINESSACCOUNT SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_CUSTOMERSHIP SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNFEATURE_BUSINESSCENTER SMALLINT NOT NULL WITH DEFAULT 0 
) IN DTBS_MBA INDEX IN ITBS_MBA ;

ALTER TABLE SM_MBA.TBL_BRANCH ADD CONSTRAINT PK_TBL_BRANCH PRIMARY KEY (INTBRANCHID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_BRANCH TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_BRANCH TO USER medpmba;

-- TABLE: TBL_CONTACT
DROP TABLE SM_MBA.TBL_CONTACT;

CREATE TABLE SM_MBA.TBL_CONTACT  (
	INTCONTACTID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 16, INCREMENT BY 1),
	STRCONTACTINFO_CHT VARCHAR(500) , 
	STRCONTACTINFO_CHS VARCHAR(500) , 
	STRCONTACTINFO_ENG VARCHAR(500) , 
	STREMAIL VARCHAR(500) , 
	STRTEL VARCHAR(500) , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	STRBANK VARCHAR(500) , 
	BNAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_CONTACT  ADD CONSTRAINT PK_TBL_CONTACT PRIMARY KEY (INTCONTACTID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_CONTACT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_CONTACT TO USER medpmba;

-- TABLE: TBL_DISTRICT
DROP TABLE SM_MBA.TBL_DISTRICT;

CREATE TABLE SM_MBA.TBL_DISTRICT (
	STRDISTRICT VARCHAR(500) NOT NULL , 
	STRAREA VARCHAR(500) NOT NULL WITH DEFAULT 'KLN' , 
	STRDISTRICT_CHT VARCHAR(500) NOT NULL , 
	STRDISTRICT_ENG VARCHAR(500) NOT NULL , 
	STRDISTRICT_CHS VARCHAR(500) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_DISTRICT ADD CONSTRAINT PK_TBL_DISTRICT PRIMARY KEY (STRDISTRICT);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_DISTRICT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_DISTRICT TO USER medpmba;

-- TABLE: TBL_EVAL_AREA
DROP TABLE SM_MBA.TBL_EVAL_AREA;

CREATE TABLE SM_MBA.TBL_EVAL_AREA  (
	STRAREACODE VARCHAR(500) NOT NULL , 
	STRAREA_E VARCHAR(255) NOT NULL , 
	STRAREA_C VARCHAR(255) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_AREA ADD CONSTRAINT PK_TBL_EVAL_AREA PRIMARY KEY (STRAREACODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_AREA TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_AREA TO USER medpmba;

-- TABLE: TBL_EVAL_BLOCK
DROP TABLE SM_MBA.TBL_EVAL_BLOCK;

CREATE TABLE SM_MBA.TBL_EVAL_BLOCK  (
	STRDISTRICTCODE VARCHAR(500) NOT NULL , 
	STRESTATECODE VARCHAR(500) NOT NULL , 
	STRPHASECODE VARCHAR(500) NOT NULL , 
	STRSTREETCODE VARCHAR(500) NOT NULL , 
	STRBLOCKCODE VARCHAR(500) NOT NULL , 
	STRSTREETNO VARCHAR(500) NOT NULL , 
	STRBLOCK_E VARCHAR(255) , 
	STRBLOCK_C VARCHAR(255) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRWGS84LATITUDE VARCHAR(500) , 
	STRWGS84LONGITUDE VARCHAR(500) , 
	FLTWGS84LATITUDE DOUBLE NOT NULL WITH DEFAULT 0 , 
	FLTWGS84LONGITUDE DOUBLE NOT NULL WITH DEFAULT 0 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_BLOCK ADD CONSTRAINT PK_TBL_EVAL_BLOCK PRIMARY KEY (STRBLOCKCODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_BLOCK TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_BLOCK TO USER medpmba;

-- TABLE: TBL_EVAL_CARPARK
DROP TABLE SM_MBA.TBL_EVAL_CARPARK;

CREATE TABLE SM_MBA.TBL_EVAL_CARPARK  (
	STRBLOCK VARCHAR(500) NOT NULL , 
	STRCARPARKTYPE VARCHAR(500) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_CARPARK ADD CONSTRAINT PK_TBL_EVAL_CARPARK PRIMARY KEY (STRBLOCK, STRCARPARKTYPE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_CARPARK TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_CARPARK TO USER medpmba;

-- TABLE: TBL_EVAL_CARPARKTYPE
DROP TABLE SM_MBA.TBL_EVAL_CARPARKTYPE;

CREATE TABLE SM_MBA.TBL_EVAL_CARPARKTYPE  (
	STRCARPARKTYPE VARCHAR(500) NOT NULL , 
	STRCARPARKTYPE_E VARCHAR(255) NOT NULL , 
	STRCARPARKTYPE_C VARCHAR(255) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_CARPARKTYPE  ADD CONSTRAINT PK_TBL_EVAL_CARPARKTYPE PRIMARY KEY (STRCARPARKTYPE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_CARPARKTYPE TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_CARPARKTYPE TO USER medpmba;

-- TABLE: TBL_EVAL_DISTRICT
DROP TABLE SM_MBA.TBL_EVAL_DISTRICT;

CREATE TABLE SM_MBA.TBL_EVAL_DISTRICT  (
	STRAREACODE VARCHAR(500) NOT NULL , 
	STRDISTRICTCODE VARCHAR(500) NOT NULL , 
	STRDISTRICT_E VARCHAR(255) NOT NULL , 
	STRDISTRICT_C VARCHAR(255) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_DISTRICT  ADD CONSTRAINT PK_TBL_EVAL_DISTRICT PRIMARY KEY (STRAREACODE, STRDISTRICTCODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_DISTRICT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_DISTRICT TO USER medpmba;

-- TABLE: TBL_EVAL_ESTATEPHASE
DROP TABLE SM_MBA.TBL_EVAL_ESTATEPHASE;

CREATE TABLE SM_MBA.TBL_EVAL_ESTATEPHASE  (
	STRAREACODE VARCHAR(500) NOT NULL , 
	STRDISTRICTCODE VARCHAR(500) NOT NULL , 
	STRESTATECODE VARCHAR(500) NOT NULL , 
	STRPHASECODE VARCHAR(500) NOT NULL , 
	STRESTATEPHASECODE VARCHAR(500) NOT NULL , 
	STRESTATEPHASE_E VARCHAR(255) NOT NULL , 
	STRESTATEPHASE_C VARCHAR(255) NOT NULL , 
	STRHKMCCODE VARCHAR(500) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_ESTATEPHASE ADD CONSTRAINT PK_TBL_EVAL_ESTATEPHASE PRIMARY KEY (STRESTATEPHASECODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_ESTATEPHASE TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_ESTATEPHASE TO USER medpmba;

-- TABLE: TBL_EVAL_STREET
DROP TABLE SM_MBA.TBL_EVAL_STREET;

CREATE TABLE SM_MBA.TBL_EVAL_STREET  (
	STRAREACODE VARCHAR(500) NOT NULL , 
	STRDISTRICTCODE VARCHAR(500) NOT NULL , 
	STRSTREETCODE VARCHAR(500) NOT NULL , 
	STRSTREET_E VARCHAR(255) NOT NULL , 
	STRSTREET_C VARCHAR(255) NOT NULL , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_STREET ADD CONSTRAINT PK_TBL_EVAL_STREET PRIMARY KEY (STRDISTRICTCODE, STRSTREETCODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_STREET TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_STREET TO USER medpmba;

-- TABLE: TBL_EVAL_UNIT
DROP TABLE SM_MBA.TBL_EVAL_UNIT;

CREATE TABLE SM_MBA.TBL_EVAL_UNIT  (
	STRDISTRICTCODE VARCHAR(500) NOT NULL , 
	STRESTATECODE VARCHAR(500) NOT NULL , 
	STRPHASECODE VARCHAR(500) NOT NULL , 
	STRSTREETCODE VARCHAR(500) NOT NULL , 
	STRBLOCKCODE VARCHAR(500) NOT NULL , 
	STRUNITCODE VARCHAR(500) NOT NULL , 
	STRFLOOR VARCHAR(500) NOT NULL , 
	INTFLOOR INTEGER NOT NULL WITH DEFAULT -1 , 
	STRUNIT VARCHAR(500) NOT NULL , 
	FTGROSSFLOORAREA DOUBLE NOT NULL WITH DEFAULT -1 , 
	FTSALEABLEFLOORAREA DOUBLE NOT NULL WITH DEFAULT -1 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_EVAL_UNIT ADD CONSTRAINT PK_TBL_EVAL_UNIT PRIMARY KEY (STRUNITCODE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_EVAL_UNIT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_EVAL_UNIT TO USER medpmba;

-- TABLE: TBL_LOG_EDIT
DROP TABLE SM_MBA.TBL_LOG_EDIT;

CREATE TABLE SM_MBA.TBL_LOG_EDIT  (
	INTLOGID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 5869, INCREMENT BY 1),
	INTUSERID INTEGER , 
	STRUSERNAME VARCHAR(500) , 
	BNLOGIN SMALLINT , 
	STRUSERIP VARCHAR(500) , 
	STRACTION VARCHAR(500) , 
	STRPAGE VARCHAR(500) , 
	STRFIELD_EDIT VARCHAR(3000) , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTRECORDID INTEGER , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 
) IN DTBS_MBA INDEX IN ITBS_MBA ;

ALTER TABLE SM_MBA.TBL_LOG_EDIT ADD CONSTRAINT PK_TBL_LOG_EDIT PRIMARY KEY (INTLOGID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_LOG_EDIT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_LOG_EDIT TO USER medpmba;

DROP TABLE SM_MBA.TBL_PROMOTION;

CREATE TABLE SM_MBA.TBL_PROMOTION  (
	INTPROMOTIONID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1883, INCREMENT BY 1),
	STRTYPE VARCHAR(50) NOT NULL WITH DEFAULT 'CREDITCARD' , 
	STRCATEGORY VARCHAR(50) NOT NULL WITH DEFAULT '' , 
	STRCUISINE VARCHAR(50) NOT NULL WITH DEFAULT '' , 
	STRELIGIBLECARDTYPE VARCHAR(20) NOT NULL , 
	STROFFERTITLE_CHT CLOB(1048576) , 
	STROFFERTITLE_ENG CLOB(1048576) , 
	STROFFERTITLE_CHS CLOB(1048576) , 
	STROFFERDESC_CHT CLOB(1048576) , 
	STROFFERDESC_ENG CLOB(1048576) , 
	STROFFERDESC_CHS CLOB(1048576) , 
	STROFFERDETAIL_CHT CLOB(1048576) , 
	STROFFERDETAIL_ENG CLOB(1048576) , 
	STROFFERDETAIL_CHS CLOB(1048576) , 
	STRMUSTTC_CHT CLOB(1048576) , 
	STRMUSTTC_ENG CLOB(1048576) , 
	STRMUSTTC_CHS CLOB(1048576) , 
	STRTEL VARCHAR(50) , 
	STRLISTICON_CHT CLOB(1048576) , 
	STRLISTICON_ENG CLOB(1048576) , 
	STRLISTICON_CHS CLOB(1048576) , 
	STRLISTTITLE_CHT CLOB(1048576) , 
	STRLISTTITLE_ENG CLOB(1048576) , 
	STRLISTTITLE_CHS CLOB(1048576) , 
	STRBANNER_CHT CLOB(1048576) , 
	STRBANNER_ENG CLOB(1048576) , 
	STRBANNER_CHS CLOB(1048576) , 
	STRLOGO_CHT CLOB(1048576) , 
	STRLOGO_ENG CLOB(1048576) , 
	STRLOGO_CHS CLOB(1048576) , 
	STRCOUPONURL_CHT CLOB(1048576) , 
	STRCOUPONURL_ENG CLOB(1048576) , 
	STRCOUPONURL_CHS CLOB(1048576) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	BNDATEVALID SMALLINT , 
	DTSTART TIMESTAMP , 
	DTEND TIMESTAMP , 
	DTPUBLISH TIMESTAMP , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	BNPREAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNAPPROVAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNWITHDRAWAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRBANK VARCHAR(50) NOT NULL WITH DEFAULT 'All' , 
	INTGLOBALPROI INTEGER NOT NULL WITH DEFAULT 1000 , 
	BNGLOBAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREAPPROVALGLOBAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNAPPROVALGLOBAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNPREWITHDRAWALGLOBAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNWITHDRAWALGLOBAL SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRREWARDTYPE CLOB(1048576) , 
	BNHAVEAPPLY SMALLINT WITH DEFAULT 0 , 
	STRAPPLYPRODUCTNAME_CHT VARCHAR(50) , 
	STRAPPLYPRODUCTNAME_CHS VARCHAR(50) , 
	STRAPPLYPRODUCTNAME_ENG VARCHAR(50) , 
	BNCUP SMALLINT NOT NULL WITH DEFAULT 0 , 
	DTPRENEW TIMESTAMP , 
	DTNEW TIMESTAMP , 
	INTPREIPHONEPROI INTEGER NOT NULL WITH DEFAULT 0 , 
	INTIPHONEPROI INTEGER NOT NULL WITH DEFAULT 0 , 
	INTGLOBALIPHONEPROI INTEGER NOT NULL WITH DEFAULT 1000 , 
	BNPLATINUM SMALLINT NOT NULL WITH DEFAULT 0 , 
	BNOTHERCARD SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRREMARKS_CHT CLOB(1048576) , 
	STRREMARKS_ENG CLOB(1048576) , 
	STRREMARKS_CHS CLOB(1048576) , 
	BNHAVEAPPLYP2 SMALLINT WITH DEFAULT 0 , 
	STRAPPLYTYPE VARCHAR(30) , 
	STRMERCHANT_CHT CLOB(1048576) , 
	STRMERCHANT_ENG CLOB(1048576) , 
	STRMERCHANT_CHS CLOB(1048576) , 
	STRAPPLYTEL VARCHAR(50) , 
	STRFTREMARK_CHT CLOB(1048576) , 
	STRFTREMARK_CHS CLOB(1048576) , 
	STRFTREMARK_ENG CLOB(1048576) , 
	HASATM SMALLINT WITH DEFAULT 0 , 
	HASADDVALUE SMALLINT WITH DEFAULT 0 , 
	STRCASHINSTITLE_CHT CLOB(1048576) , 
	STRCASHINSTITLE_CHS CLOB(1048576) , 
	STRCASHINSTITLE_ENG CLOB(1048576) , 
	STRCASHINSDETAIL_CHT CLOB(1048576) , 
	STRCASHINSDETAIL_CHS CLOB(1048576) , 
	STRCASHINSDETAIL_ENG CLOB(1048576) , 
	STRCASHINSPHONE VARCHAR(50) , 
	STRCASHDEPTITLE_CHT CLOB(1048576) , 
	STRCASHDEPTITLE_CHS CLOB(1048576) , 
	STRCASHDEPTITLE_ENG CLOB(1048576) , 
	STRCASHDEPDETAIL_CHT CLOB(1048576) , 
	STRCASHDEPDETAIL_CHS CLOB(1048576) , 
	STRCASHDEPDETAIL_ENG CLOB(1048576) , 
	STRCASHDEPPHONE CLOB(1048576) , 
	STRCASHINSIMG_CHT CLOB(1048576) , 
	STRCASHINSIMG_CHS CLOB(1048576) , 
	STRCASHINSIMG_ENG CLOB(1048576) , 
	STRCASHINSTNC_CHT CLOB(1048576) , 
	STRCASHINSTNC_CHS CLOB(1048576) , 
	STRCASHINSTNC_ENG CLOB(1048576) , 
	STRCASHDEPIMG_CHT CLOB(1048576) , 
	STRCASHDEPIMG_CHS CLOB(1048576) , 
	STRCASHDEPIMG_ENG CLOB(1048576) , 
	STRCASHDEPTNC_CHT CLOB(1048576) , 
	STRCASHDEPTNC_CHS CLOB(1048576) , 
	STRCASHDEPTNC_ENG CLOB(1048576) , 
	BNLUCKYDRAW SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRLUCKYDRAWURL_CHT CLOB(1048576) , 
	STRLUCKYDRAWURL_CHS CLOB(1048576) , 
	STRLUCKYDRAWURL_ENG CLOB(1048576) , 
	INTANDROIDPROI INTEGER NOT NULL WITH DEFAULT 0 , 
	BNUNIONPAY SMALLINT NOT NULL WITH DEFAULT 0 , 
	STRLEAFLET_CHT CLOB(1048576) , 
	STRLEAFLET_CHS CLOB(1048576) , 
	STRLEAFLET_ENG CLOB(1048576) 
) IN DTBS_MBA_32K INDEX IN ITBS_MBA_32K LONG IN LTBS_MBA_32K; 

ALTER TABLE SM_MBA.TBL_PROMOTION ADD CONSTRAINT PK_TBL_PROMOTION PRIMARY KEY (INTPROMOTIONID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_PROMOTION TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_PROMOTION TO USER medpmba;

-- TABLE: TBL_PROMOTION_ADDRESS
DROP TABLE SM_MBA.TBL_PROMOTION_ADDRESS;

CREATE TABLE SM_MBA.TBL_PROMOTION_ADDRESS  (
	INTPROMOTIONADDRESSID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 5738, INCREMENT BY 1),
	INTPROMOTIONID BIGINT NOT NULL DEFAULT , 
	INTMERCHANTID BIGINT NOT NULL DEFAULT 0 , 
	INTSHOPID BIGINT NOT NULL DEFAULT 0 , 
	STRMERCHANT_CHT VARCHAR(255) , 
	STRMERCHANT_ENG VARCHAR(255) , 
	STRMERCHANT_CHS VARCHAR(255) ,
	STRDISTRICT VARCHAR(256) NOT NULL ,
	STRADDRESS_CHT VARCHAR(255) , 
	STRADDRESS_ENG VARCHAR(255) , 
	STRADDRESS_CHS VARCHAR(255) ,
	STRTEL VARCHAR(256) ,
	STRWGS84LATITUDE VARCHAR(256) ,
	STRWGS84LONGITUDE VARCHAR(256) ,
	FLTWGS84LATITUDE DOUBLE NOT NULL WITH DEFAULT 0 , 
	FLTWGS84LONGITUDE DOUBLE NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_PROMOTION_ADDRESS ADD CONSTRAINT PK_TBL_PROMOTION_ADDRESS PRIMARY KEY (INTPROMOTIONADDRESSID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_PROMOTION_ADDRESS TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_PROMOTION_ADDRESS TO USER medpmba;

-- TABLE: TBL_PUSH_LOG
DROP TABLE SM_MBA.TBL_PUSH_LOG;

CREATE TABLE SM_MBA.TBL_PUSH_LOG  (
	STRCONTENT VARCHAR(255) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRUSER VARCHAR(500) , 
	INTSENTAMOUNT INTEGER 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_PUSH_LOG ADD CONSTRAINT PK_TBL_PUSH_LOG PRIMARY KEY (DTADD);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_PUSH_LOG TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_PUSH_LOG TO USER medpmba;

-- TABLE: TBL_PUSH_SECTION_SELECT
DROP TABLE SM_MBA.TBL_PUSH_SECTION_SELECT;

CREATE TABLE SM_MBA.TBL_PUSH_SECTION_SELECT  (
	INTREQUESTID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 85, INCREMENT BY 1),
	STRSECTION VARCHAR(500) NOT NULL , 
	INTPROMOTIONID INTEGER NOT NULL , 
	DTSTART TIMESTAMP NOT NULL , 
	DTEND TIMESTAMP NOT NULL , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTPUSHIDENG INTEGER , 
	INTPUSHIDCHS INTEGER , 
	INTPUSHIDCHT INTEGER , 
	STRTYPEID VARCHAR(500) 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_PUSH_SECTION_SELECT ADD CONSTRAINT PK_TBL_PUSH_SECTION_SELECT PRIMARY KEY (INTREQUESTID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_PUSH_SECTION_SELECT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_PUSH_SECTION_SELECT TO USER medpmba;

-- TABLE: TBL_PUSH_SENDER_QUEUE
DROP TABLE SM_MBA.TBL_PUSH_SENDER_QUEUE;

CREATE TABLE SM_MBA.TBL_PUSH_SENDER_QUEUE  (
	INTID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
	STRDEVICEID VARCHAR(500) NOT NULL , 
	STRDEVICETYPE VARCHAR(500) NOT NULL , 
	STRAPPTOKENID VARCHAR(500) NOT NULL , 
	STRSERVICEID VARCHAR(500) NOT NULL , 
	STROPERATORID VARCHAR(500) NOT NULL WITH DEFAULT 'UNI' , 
	STRMSGTYPE VARCHAR(500) NOT NULL WITH DEFAULT 'PUSH' , 
	STRLANG VARCHAR(500) NOT NULL WITH DEFAULT 'zh_TW' , 
	STRTITLE VARCHAR(255) , 
	STRCONTENT VARCHAR(255) , 
	STRURL VARCHAR(255) , 
	INTBADGE INTEGER NOT NULL WITH DEFAULT 0 , 
	STRAPNS_LOCKEY VARCHAR(500) , 
	STRAPNS_LOCARGS1 VARCHAR(255) , 
	STRAPNS_LOCARGS2 VARCHAR(255) , 
	STRAPNS_LOCARGS3 VARCHAR(255) , 
	STRSTATUS VARCHAR(32) NOT NULL WITH DEFAULT 'A' , 
	INTRETRY INTEGER NOT NULL WITH DEFAULT 0 , 
	STRRETURN VARCHAR(500) , 
	INTPROI INTEGER NOT NULL WITH DEFAULT 1000 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTSENT TIMESTAMP , 
	INTSMSID INTEGER 
) IN DTBS_MBA INDEX IN ITBS_MBA ;  

ALTER TABLE SM_MBA.TBL_PUSH_SENDER_QUEUE ADD CONSTRAINT PK_TBL_PUSH_SENDER_QUEUE PRIMARY KEY (INTID);

CREATE INDEX ITBS_MBA.IX2_TBL_PUSH_SENDER_QUEUE ON SM_MBA.TBL_PUSH_SENDER_QUEUE (STRDEVICEID ASC) ALLOW REVERSE SCANS;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_PUSH_SENDER_QUEUE TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_PUSH_SENDER_QUEUE TO USER medpmba;

-- TABLE: TBL_SMS_QUEUE
DROP TABLE SM_MBA.TBL_SMS_QUEUE;

CREATE TABLE SM_MBA.TBL_SMS_QUEUE  (
	STRTYPEID VARCHAR(256) ,
	STRTEXT VARCHAR(256) , 
	INTID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRSA VARCHAR(256) , 
	DTSENT TIMESTAMP , 
	BNSENT SMALLINT NOT NULL WITH DEFAULT 0 , 
	DTTARGETSEND TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTCREATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	BNTESTSENT SMALLINT NOT NULL WITH DEFAULT 0 , 
	DTTESTSENT TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STROPERATORID VARCHAR(256) NOT NULL WITH DEFAULT 'UNI' , 
	STRLANG VARCHAR(256) NOT NULL WITH DEFAULT 'zh_TW' , 
	STRSUBTYPE VARCHAR(256) , 
	INTTESTSENT INTEGER NOT NULL WITH DEFAULT -1 , 
	INTSENT INTEGER NOT NULL WITH DEFAULT -1 , 
	STRFMMSISDN VARCHAR(256) 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_SMS_QUEUE ADD CONSTRAINT PK_TBL_SMS_QUEUE PRIMARY KEY (INTID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_SMS_QUEUE TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_SMS_QUEUE TO USER medpmba;

-- TABLE: TBL_TAXLOAN
DROP TABLE SM_MBA.TBL_TAXLOAN;

CREATE TABLE SM_MBA.TBL_TAXLOAN  (
	INTID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 2, INCREMENT BY 1),
	STRLOAN VARCHAR(255) , 
	STRLOAN2 VARCHAR(255) , 
	STRLOAN3 VARCHAR(255) , 
	STRLOAN4 VARCHAR(255) , 
	STRLOAN5 VARCHAR(255) , 
	STRLOAN6 VARCHAR(255) , 
	STRLOAN7 VARCHAR(255) , 
	STRLOAN8 VARCHAR(255) , 
	STRLOAN9 VARCHAR(255) , 
	STRAPR VARCHAR(255) , 
	STRAPR2 VARCHAR(255) , 
	STRAPR3 VARCHAR(255) , 
	DTENABLE SMALLINT NOT NULL WITH DEFAULT 0 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_TAXLOAN TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_TAXLOAN TO USER medpmba;

-- TABLE: TBL_TRAVEL_INSURANCE
DROP TABLE SM_MBA.TBL_TRAVEL_INSURANCE;

CREATE TABLE SM_MBA.TBL_TRAVEL_INSURANCE  (
	STRINFO1_CHT VARCHAR(500) , 
	STRINFO1_ENG VARCHAR(2000) , 
	STRINFO1_CHS VARCHAR(500) , 
	STRINFO2_CHT VARCHAR(500) , 
	STRINFO2_ENG VARCHAR(2000) , 
	STRINFO2_CHS VARCHAR(500) , 
	STRINFO3_CHT VARCHAR(1000) , 
	STRINFO3_ENG VARCHAR(2000) , 
	STRINFO3_CHS VARCHAR(1000) , 
	INTSHOW INTEGER WITH DEFAULT 1 , 
	INTDELETED INTEGER WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	BNISBOC SMALLINT , 
	BNISNCB SMALLINT , 
	STRIMAGE_CHT VARCHAR(500) , 
	STRIMAGE_ENG VARCHAR(2000) , 
	STRIMAGE_CHS VARCHAR(500) 
) IN DTBS_MBA_32K INDEX IN ITBS_MBA_32K ;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_TRAVEL_INSURANCE TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_TRAVEL_INSURANCE TO USER medpmba;

-- TABLE: TBL_APP
DROP TABLE SM_MBA.TBL_APP;

CREATE TABLE SM_MBA.TBL_APP  (
	STRAPPID VARCHAR(256) NOT NULL , 
	STRAPPNAME VARCHAR(256) , 
	STRSUPPLIERID VARCHAR(256) , 
	STRUSERID VARCHAR(256) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRENCRYPTKEY VARCHAR(256) 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_APP ADD CONSTRAINT PK_TBL_APP PRIMARY KEY (STRAPPID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_APP TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_APP TO USER medpmba;

-- TABLE: TBL_CAT
DROP TABLE SM_MBA.TBL_CAT;

CREATE TABLE SM_MBA.TBL_CAT  (
	STRAPPID VARCHAR(256) NOT NULL , 
	STRCATID VARCHAR(256) NOT NULL , 
	STRCATNAME VARCHAR(256) , 
	STRUSERID VARCHAR(256) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	STRENCRYPTKEY VARCHAR(256) 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_CAT ADD CONSTRAINT PK_TBL_CAT PRIMARY KEY (STRAPPID, STRCATID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_CAT TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_CAT TO USER medpmba;

-- TABLE: TBL_SECTION
DROP TABLE SM_MBA.TBL_SECTION;

CREATE TABLE SM_MBA.TBL_SECTION  (
	STRAPPID VARCHAR(256) NOT NULL , 
	STRSECTIONID VARCHAR(256) NOT NULL , 
	STRSECTION VARCHAR(256) , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP 
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_SECTION ADD CONSTRAINT PK_TBL_SECTION PRIMARY KEY (STRAPPID, STRSECTIONID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_SECTION TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_SECTION TO USER medpmba;

-- TABLE: TBL_ITEM
DROP TABLE SM_MBA.TBL_ITEM;

CREATE TABLE SM_MBA.TBL_ITEM  (
	STRAPPID VARCHAR(256) NOT NULL , 
	STRCATID VARCHAR(256) NOT NULL , 
	STRITEM VARCHAR(1024) NOT NULL , 
	STRREPORTPERIOD VARCHAR(500) NOT NULL WITH DEFAULT 'MONTHLY' , 
	STRPERIOD VARCHAR(256)  NOT NULL WITH DEFAULT '2000-01-01',
	DTPERIOD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTCOUNT INTEGER NOT NULL WITH DEFAULT 0 , 
	FLTRATE DOUBLE NOT NULL WITH DEFAULT 0 , 
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP ,
	STRUDID VARCHAR(256) NOT NULL WITH DEFAULT 'NULL' , 
	STRDEVICETYPE VARCHAR(256) , 
	STREMAIL VARCHAR(256) , 
	STRCAT VARCHAR(256)
) IN DTBS_MBA_32K INDEX IN ITBS_MBA_32K ; 

ALTER TABLE SM_MBA.TBL_ITEM ADD CONSTRAINT PK_TBL_ITEM PRIMARY KEY (STRAPPID, STRCATID, STRITEM, STRPERIOD, STRUDID);

CREATE INDEX ITBS_MBA_32K.IX2_INDEX_TBL_ITEM_NEW_AC ON SM_MBA.TBL_ITEM (STRAPPID ASC, STRCATID ASC) ALLOW REVERSE SCANS;
CREATE INDEX ITBS_MBA_32K.IX3_INDEX_TBL_ITEM_NEW_ACDP ON SM_MBA.TBL_ITEM (STRAPPID ASC, STRCATID ASC, DTPERIOD ASC) ALLOW REVERSE SCANS;
CREATE INDEX ITBS_MBA_32K.IX4_INDEX_TBL_ITEM_NEW_ACI ON SM_MBA.TBL_ITEM (STRAPPID ASC, STRCATID ASC, STRITEM ASC) ALLOW REVERSE SCANS;
CREATE INDEX ITBS_MBA_32K.IX5_INDEX_TBL_ITEM_NEW_ACSP ON SM_MBA.TBL_ITEM (STRAPPID ASC, STRCATID ASC, STRPERIOD ASC) ALLOW REVERSE SCANS;
CREATE INDEX ITBS_MBA_32K.IX6_INDEX_TBL_ITEM_NEW_AT ON SM_MBA.TBL_ITEM (STRAPPID ASC, STRDEVICETYPE ASC) ALLOW REVERSE SCANS;
CREATE INDEX ITBS_MBA_32K.IX7_INDEX_TBL_ITEM_NEW_STRAPPID ON SM_MBA.TBL_ITEM (STRAPPID ASC) ALLOW REVERSE SCANS;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_ITEM TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_ITEM TO USER medpmba;

-- TABLE: TBL_BOC_LOG
DROP TABLE SM_MBA.TBL_BOC_LOG;

CREATE TABLE SM_MBA.TBL_BOC_LOG  (
	STRAPPID VARCHAR(256) NOT NULL , 
	STRCAT VARCHAR(256) NOT NULL , 
	STRITEMID VARCHAR(256) NOT NULL, 
	STRITEM VARCHAR(256)  , 
	STRPERIOD VARCHAR(256) NOT NULL , 
	STRDEVICETYPE VARCHAR(256) NOT NULL, 
	DTPERIOD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	INTCOUNT INTEGER NOT NULL WITH DEFAULT 0 , 
	STRCOUNTMEANING VARCHAR(256) NOT NULL,
	INTSHOW INTEGER NOT NULL WITH DEFAULT 1 , 
	INTDELETED INTEGER NOT NULL WITH DEFAULT 0 , 
	DTADD TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP , 
	DTUPDATE TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP
) IN DTBS_MBA INDEX IN ITBS_MBA ; 

ALTER TABLE SM_MBA.TBL_BOC_LOG ADD CONSTRAINT PK_TBL_BOC_LOG PRIMARY KEY (STRAPPID, STRCAT, STRITEMID, STRPERIOD, STRDEVICETYPE);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_BOC_LOG TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_BOC_LOG TO USER medpmba;

-- TABLE: TBL_COUPON
DROP TABLE SM_MBA.TBL_COUPON;

CREATE TABLE SM_MBA.TBL_COUPON ( 
	INTADID          	BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
	UNIQUE_CODE			VARCHAR(30) ,
	USED	       		INTEGER NOT NULL DEFAULT 0,	
	USED_DATE          	TIMESTAMP ,
	USER_ID				VARCHAR(255) DEFAULT NULL,
	USER_AGENT			VARCHAR(45) DEFAULT NULL,
	PASSBOOK       		INTEGER NOT NULL DEFAULT 0,	
	USER_IP				VARCHAR(45) DEFAULT NULL	
)IN DTBS_MBA INDEX IN ITBS_MBA ;
 
ALTER TABLE SM_MBA.TBL_COUPON ADD CONSTRAINT PK_TBL_COUPON PRIMARY KEY (INTADID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_COUPON TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_COUPON TO USER medpmba;

-- TABLE: TBL_COUPON_CONFIG
DROP TABLE SM_MBA.TBL_COUPON_CONFIG;

CREATE TABLE SM_MBA.TBL_COUPON_CONFIG(
INTADID           BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
NAME VARCHAR(400) ,
ENABLE       INTEGER NOT NULL DEFAULT 0,
BANK VARCHAR(45) ,
BANNER_TC VARCHAR(200) DEFAULT NULL,
BANNER_SC VARCHAR(200) DEFAULT NULL,
BANNER_EN VARCHAR(200) DEFAULT NULL,
BANNER_COUNT       INTEGER NOT NULL DEFAULT 0,
BANNER_COUNT_TEST   INTEGER NOT NULL DEFAULT 0
)IN DTBS_MBA INDEX IN ITBS_MBA ;
 
ALTER TABLE SM_MBA.TBL_COUPON_CONFIG ADD CONSTRAINT PK_TBL_COUPON_CONFIG PRIMARY KEY (INTADID);

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE SM_MBA.TBL_COUPON_CONFIG TO USER mbausr;
GRANT SELECT ON TABLE SM_MBA.TBL_COUPON_CONFIG TO USER medpmba;

-- CREATE FOREIGN KEY
ALTER TABLE SM_MBA.TBL_ADMIN_USERS ADD CONSTRAINT FK1_TBL_ADMIN_USERS FOREIGN KEY (INTGROUPID)
	REFERENCES SM_MBA.TBL_ADMIN_GROUPS (INTGROUPID) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;

ALTER TABLE SM_MBA.TBL_EVAL_DISTRICT ADD CONSTRAINT FK2_TBL_EVAL_DISTRICT FOREIGN KEY (STRAREACODE)
	REFERENCES SM_MBA.TBL_EVAL_AREA (STRAREACODE) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;
	
ALTER TABLE SM_MBA.TBL_PROMOTION_ADDRESS ADD CONSTRAINT FK3_TBL_PROMOTION_ADDRESS FOREIGN KEY (INTPROMOTIONID)
	REFERENCES SM_MBA.TBL_PROMOTION (INTPROMOTIONID) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;
	
ALTER TABLE SM_MBA.TBL_EVAL_ESTATEPHASE ADD CONSTRAINT FK4_TBL_EVAL_ESTATEPHASE FOREIGN KEY (STRDISTRICTCODE, STRAREACODE)
	REFERENCES SM_MBA.TBL_EVAL_DISTRICT (STRDISTRICTCODE, STRAREACODE) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;	
	
ALTER TABLE SM_MBA.TBL_EVAL_STREET ADD CONSTRAINT FK5_TBL_EVAL_STREET FOREIGN KEY (STRDISTRICTCODE, STRAREACODE)
	REFERENCES SM_MBA.TBL_EVAL_DISTRICT (STRDISTRICTCODE, STRAREACODE) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;
	
ALTER TABLE SM_MBA.TBL_ITEM ADD CONSTRAINT FK1_TBL_ITEM_STRAPPID FOREIGN KEY (STRAPPID)
	REFERENCES SM_MBA.TBL_APP (STRAPPID) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;

ALTER TABLE SM_MBA.TBL_ITEM ADD CONSTRAINT FK2_TBL_ITEM_STRAPPID FOREIGN KEY (STRAPPID, STRCATID)
	REFERENCES SM_MBA.TBL_CAT (STRAPPID, STRCATID) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;	

ALTER TABLE SM_MBA.TBL_CAT ADD CONSTRAINT FK3_TBL_CAT_STRAPPID FOREIGN KEY (STRAPPID)
	REFERENCES SM_MBA.TBL_APP (STRAPPID) 
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	ENFORCED
	ENABLE QUERY OPTIMIZATION;	

-- DROP FUNCTION
DROP FUNCTION SM_MBA.DISTANCECALCULATOR;
DROP FUNCTION SM_MBA.CONVERTDEGREESTORADIANS;

-- FUNCTION: CONVERTDEGREESTORADIANS	
CREATE FUNCTION SM_MBA.CONVERTDEGREESTORADIANS(deg DOUBLE) 
RETURNS DOUBLE 
RETURN (deg * 3.141592654 / 180.0);

GRANT EXECUTE ON FUNCTION SM_MBA.CONVERTDEGREESTORADIANS TO mbausr;
GRANT EXECUTE ON FUNCTION SM_MBA.CONVERTDEGREESTORADIANS TO medpmba;

-- FUNCTION: DISTANCECALCULATOR	
CREATE FUNCTION SM_MBA.DISTANCECALCULATOR(lat1 DOUBLE, lon1 DOUBLE, lat2 DOUBLE, lon2 DOUBLE) 
RETURNS DOUBLE 
RETURN 
	(ACOS(
		COS(SM_MBA.CONVERTDEGREESTORADIANS(lat1))
		* COS(SM_MBA.CONVERTDEGREESTORADIANS(lon1))
		* COS(SM_MBA.CONVERTDEGREESTORADIANS(lat2))
		* COS(SM_MBA.CONVERTDEGREESTORADIANS(lon2))
		+ COS(SM_MBA.CONVERTDEGREESTORADIANS(lat1))
		* SIN(SM_MBA.CONVERTDEGREESTORADIANS(lon1))
		* COS(SM_MBA.CONVERTDEGREESTORADIANS(lat2))
		* SIN(SM_MBA.CONVERTDEGREESTORADIANS(lon2))
		+ SIN(SM_MBA.CONVERTDEGREESTORADIANS(lat1))
		* SIN(SM_MBA.CONVERTDEGREESTORADIANS(lat2))
	) * 6378.1 );

GRANT EXECUTE ON FUNCTION SM_MBA.DISTANCECALCULATOR TO mbausr;
GRANT EXECUTE ON FUNCTION SM_MBA.DISTANCECALCULATOR TO medpmba;


