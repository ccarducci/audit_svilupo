USE DS04570_DB_CruscottoAuditAtpo;


-- ---------------------------------------------------
-- -------------------AU_C_VARCOMP--------------------
-- ---------------------------------------------------
 SELECT  
	t1.ID_FASE 
	, t1.ID_M_NONCONF 
	, t1.ID_M_VARCOMP 
	, t1.DATA_INIZIO 
	, t1.DATA_FINE 
	, NUM_VC 
	, NUM_NC 
	, SOGLIA 
	, (cast((NUM_VC) as decimal (7,2))/cast((NUM_NC) as decimal (7,2))) AS PERC_SU_PS 
	, (cast((NUM_VC) as decimal (7,2))/cast((NUM_NC) as decimal (7,2))*SOGLIA) AS PERC_PESATA   
	FROM 
  (SELECT svc2.ID_M_NONCONF, sum (svc2.num) as NUM_NC  
  FROM 
  AU_S_SESSIONE ausess2  
  , AU_S_NONCONF snc2 
  , AU_SESSIONI sess2 
  , AU_S_VARCOMP svc2 
  , AU_M_NONCONF mnc2 
  where  
  snc2.ID_S_SESSIONE = ausess2.ID_S_SESSIONE and 
  snc2.ID_S_NONCONF = svc2.ID_S_NONCONF and 
  ausess2.ID_SESSIONE = sess2.ID_SESSIONE and 
  svc2.ID_M_NONCONF = mnc2.ID_M_NON_CONF and 
  sess2.ID_CAMPAGNA =   2	  and 
  ausess2.STATO_ESAME_SESSIONE = 'C' 
  group by svc2.ID_M_NONCONF) as t2 
  left join  
  (SELECT svc1.ID_M_NONCONF, svc1.ID_M_VARCOMP, mnc.ID_FASE, svc1.DATA_INIZIO, svc1.DATA_FINE, soglia, sum (svc1.num) as NUM_VC 
  FROM 
  AU_S_SESSIONE ausess  
  , AU_S_NONCONF snc 
  , AU_SESSIONI sess 
  , AU_S_VARCOMP svc1 
  , AU_M_NONCONF mnc 
  , AU_M_VARCOMP mvc 
  , AU_TPL_ISNC   
  where  
  snc.ID_S_SESSIONE = ausess.ID_S_SESSIONE and 
  snc.ID_S_NONCONF = svc1.ID_S_NONCONF and 
  ausess.ID_SESSIONE = sess.ID_SESSIONE and 
  svc1.ID_M_NONCONF = mnc.ID_M_NON_CONF and 
  mvc.ID_M_NONCONF = mnc.ID_M_NON_CONF and  
  mvc.ID_M_COMP = svc1.id_m_varcomp and 
  ID_TPL_ISNC = mvc.PESO_VC and 
  sess.ID_CAMPAGNA =   2	  and 
  ausess.STATO_ESAME_SESSIONE = 'C' 
  group by svc1.ID_M_NONCONF, svc1.ID_M_VARCOMP, mnc.ID_FASE, svc1.DATA_INIZIO, svc1.DATA_FINE, soglia) as t1 on t2.ID_M_NONCONF = t1.ID_M_NONCONF;
  
  
-- ---------------------------------------------------
-- -------------------AU_C_NONCONF--------------------
-- ---------------------------------------------------
  
   Select t2.ID_C_CAMPAGNA, t2.ID_M_NON_CONF, t2.PESO_NON_CONF, t2.ID_FASE, t2.DATA_INIZIO, t2.DATA_FINE, t2.NUM_NC,
	  case when t2.PESO_NON_CONF = 0 then null else t2.INCC end AS INCC, t1.TOT_PESO_NC, t3.TOT_PESO_FS,
	  case when t2.PESO_NON_CONF = 0 then null else t2.INCC*(cast((t2.PESO_NON_CONF) as decimal (7,2))/cast((t1.TOT_PESO_NC) as decimal (7,2))) end AS VALORE_PESATO, 
	  case when t2.PESO_NON_CONF = 0 then null else t2.INCC*(cast((t2.PESO_NON_CONF) as decimal (7,2))/cast((t3.TOT_PESO_FS) as decimal (7,2))) end AS VALORE_PESATO_FASE  from
	  (Select ID_C_CAMPAGNA, cvc2.id_fase, mnc.ID_M_NON_CONF, cvc2.data_inizio, cvc2.data_fine, mnc.Peso_NON_CONF,
	  case when sum (cvc2.PERC_PESATA) < 0 then 0 else sum (cvc2.PERC_PESATA) end  as INCC,
	  sum (cvc2.num_vc) as NUM_NC 
	  FROM AU_C_VARCOMP cvc2
	  , AU_M_NONCONF mnc
	  where 
	  cvc2.ID_M_NONCONF = mnc.ID_M_NON_CONF and
	  cvc2.ID_C_CAMPAGNA =   2
	  group by ID_C_CAMPAGNA, cvc2.id_fase, mnc.ID_M_NON_CONF, cvc2.data_inizio, cvc2.data_fine, mnc.Peso_NON_CONF) as t2
	  left join 
	  (Select sess.id_campagna, sum (mnc.PESO_NON_CONF) as TOT_PESO_NC
	  FROM
	  AU_S_SESSIONE ausess 
	  , AU_S_NONCONF snc
	  , AU_SESSIONI sess
	  , AU_M_NONCONF mnc
	  where 
	  snc.ID_S_SESSIONE = ausess.ID_S_SESSIONE and
	  snc.ID_S_NONCONF = snc.ID_S_NONCONF and
	  ausess.ID_SESSIONE = sess.ID_SESSIONE and
	  snc.ID_M_NONCONF = mnc.ID_M_NON_CONF and
	  sess.ID_CAMPAGNA =   2   and
	  ausess.STATO_ESAME_SESSIONE = 'C'
	  group by sess.id_campagna) as t1 on t2.ID_C_CAMPAGNA = t1.ID_CAMPAGNA
	  left join 
	  (Select sess.ID_CAMPAGNA, mnc.ID_FASE, sum (mnc.PESO_NON_CONF) as TOT_PESO_FS
	  FROM
	  AU_S_SESSIONE ausess 
	  , AU_S_NONCONF snc
	  , AU_SESSIONI sess
	  , AU_M_NONCONF mnc
	  where 
	  snc.ID_S_SESSIONE = ausess.ID_S_SESSIONE and
	  snc.ID_S_NONCONF = snc.ID_S_NONCONF and
	  ausess.ID_SESSIONE = sess.ID_SESSIONE and
	  snc.ID_M_NONCONF = mnc.ID_M_NON_CONF and
	  sess.ID_CAMPAGNA =   2   and
	  ausess.STATO_ESAME_SESSIONE = 'C'
	  group by sess.ID_CAMPAGNA, mnc.ID_FASE) as t3 
	  on t3.ID_CAMPAGNA = t1.ID_CAMPAGNA and t3.id_fase =t2.id_fase;
	  
-- ---------------------------------------------------
-- -------------------AU_C_RISESPR--------------------
-- ---------------------------------------------------  
	  
	  Select t1.ID_CAMPAGNA, t1.ID_M_RISCHIO, t1.ID_M_RISESPR, t1.DESCRIZIONE, t1.RAGGRUPPAMENTO_RISCHIO, NUM, NUM_RS, (cast((NUM) as decimal (7,2))/cast((NUM_RS) as decimal (7,2))*100) AS SU_TOT, IMPORTO from
	  (Select svc.ID_M_RISCHIO, sum (svc.num) as NUM_RS
		FROM
		AU_S_SESSIONE ausess 
	  , AU_S_RISCHIO snc
		, AU_SESSIONI sess
		, AU_S_RISESPR svc
		, AU_M_RISESPR mrise
		, AU_M_RISCHIO mnc					 
		where 
		snc.ID_S_SESSIONE = ausess.ID_S_SESSIONE and
	  snc.ID_S_RISCHIO = svc.ID_S_RISCHIO and
		ausess.ID_SESSIONE = sess.ID_SESSIONE and
		svc.ID_M_RISCHIO = mnc.ID_M_RISCHIO and
		mrise.ID_M_RISCHIO = svc.ID_M_RISCHIO AND
		mrise.ID_M_RISESPR = svc.ID_M_RISESPR and
		sess.ID_CAMPAGNA =   2    and
		ausess.STATO_ESAME_SESSIONE = 'C'
		group by svc.ID_M_RISCHIO) as t2
		left join
		(Select sess.id_campagna, svc.ID_M_RISCHIO, svc.ID_M_RISESPR, mrise.DESCRIZIONE, mrise.RAGGRUPPAMENTO_RISCHIO, sum (svc.num) as NUM, sum (svc.importo) as IMPORTO
		FROM
		AU_S_SESSIONE ausess
		, AU_S_RISCHIO snc
		, AU_SESSIONI sess
		, AU_S_RISESPR svc
		, AU_M_RISESPR mrise
		, AU_M_RISCHIO mnc					
		where 
		snc.ID_S_SESSIONE = ausess.ID_S_SESSIONE and
		snc.ID_S_RISCHIO = svc.ID_S_RISCHIO and
		ausess.ID_SESSIONE = sess.ID_SESSIONE and
		svc.ID_M_RISCHIO = mnc.ID_M_RISCHIO and
		mrise.ID_M_RISCHIO = svc.ID_M_RISCHIO AND
		mrise.ID_M_RISESPR = svc.ID_M_RISESPR AND
		sess.ID_CAMPAGNA =   2    and
		ausess.STATO_ESAME_SESSIONE = 'C'
		group by sess.id_campagna, svc.ID_M_RISCHIO, svc.ID_M_RISESPR, mrise.DESCRIZIONE, mrise.RAGGRUPPAMENTO_RISCHIO) as t1 on t2.ID_M_RISCHIO = t1.ID_M_RISCHIO;
	  