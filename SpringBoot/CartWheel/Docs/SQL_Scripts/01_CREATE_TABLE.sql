CREATE TABLE "ETLREWRITE"."SCRIPS" (
	"SCRIP" varchar(20) NOT NULL,
	"SCRIP_ID" int4 NOT NULL DEFAULT nextval('"SCRIPS_SCRIP_ID_seq"'::regclass),
	"SCRIP_NAME" varchar(150) NULL,
	CONSTRAINT "SCRIPS_pkey" PRIMARY KEY ("SCRIP_ID")
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE "ETLREWRITE"."HISTORICAL_INTRADAY_DATA" (
	"SCRIP" varchar(50) NOT NULL,
	"DATE_TIME" timestamp NOT NULL,
	"OPEN" numeric(8,4) NOT NULL,
	"HIGH" numeric(8,4) NOT NULL,
	"LOW" numeric(8,4) NOT NULL,
	"CLOSE" numeric(8,4) NOT NULL,
	"ADJUSTED_CLOSE" numeric(8,4) NOT NULL,
	"VOLUME" numeric(8,4) NULL,
	"DIVIDEND_AMOUNT" numeric(8,4) NULL,
	"SPREAD" numeric(8,4) NULL
)
WITH (
	OIDS=FALSE
) ;
