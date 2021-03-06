CREATE TABLE  "PURCHASE" 
   (	"PURCHASE_NO" NUMBER(10,0) NOT NULL ENABLE, 
	"PROD_NO" NUMBER(10,0) NOT NULL ENABLE, 
	"CART_NO" NUMBER, 
	"USER_ID" VARCHAR2(20) NOT NULL ENABLE, 
	"RECEIVER_NAME" VARCHAR2(20) NOT NULL ENABLE, 
	"RECEIVER_PHONE" VARCHAR2(20), 
	"RECEIVER_ADDR1" VARCHAR2(100), 
	"RECEIVER_ADDR2" VARCHAR2(100), 
	"DIVY_REQUEST" VARCHAR2(100), 
	"PURCHASE_QUANTITY" NUMBER(10,0), 
	"PURCHASE_PRICE" NUMBER(10,0), 
	"PURCHASE_DATE" DATE, 
	"PURCHASE_CONDITION" VARCHAR2(3) DEFAULT '1', 
	"CANCEL_DATE" DATE, 
	"DIVY_DATE" DATE, 
	"PAYMENT_OPTION" VARCHAR2(3) DEFAULT '0', 
	 PRIMARY KEY ("PURCHASE_NO", "PROD_NO") ENABLE, 
	 FOREIGN KEY ("PROD_NO")
	  REFERENCES  "PRODUCT" ("PROD_NO") ENABLE
   )