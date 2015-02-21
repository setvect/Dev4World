CREATE TABLE TBCA_CTMEMO (
		CTMEMO_SEQ				int NOT NULL,
		CONTENT	      varchar(4000) NOT NULL,
		FONT_CSS    	varchar(20) NOT NULL,
		BG_CSS      	varchar(20) NOT NULL,
		Z_INDEX       int NOT NULL,
		WIDTH         int NOT NULL,
		HEIGHT        int NOT NULL,
		POSITION_X    int NOT NULL,
		POSITION_Y    int NOT NULL,
		UPT_DATE      datetime NOT NULL,
		REG_DATE      datetime NOT NULL,
		DELETE_F      varchar(1) NOT NULL,
  PRIMARY KEY  (CTMEMO_SEQ)
)	