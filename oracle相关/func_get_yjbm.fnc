CREATE OR REPLACE FUNCTION func_get_yjbm(i_bbid IN NUMBER) RETURN NUMBER DETERMINISTIC AS

    v_orgtype NUMBER;
    v_grade   NUMBER;
    v_fid     NUMBER;
    v_sjbm    NUMBER;
    v_yjbm    NUMBER;
    v_name    VARCHAR2(100);

BEGIN
    SELECT orgtype, grade, fid, NAME
      INTO v_orgtype, v_grade, v_fid, v_name
      FROM lborganization
     WHERE id = i_bbid;
    SELECT fid INTO v_sjbm FROM lborganization WHERE id = v_fid;
    IF (v_orgtype = 8 AND v_grade = 4) OR v_orgtype = 12 THEN
        v_yjbm := v_sjbm;
    ELSIF (v_orgtype = 8 AND v_grade = 3) OR v_orgtype IN (10, 12) OR (v_fid = 9014) THEN
        v_yjbm := v_fid;
    ELSE
        v_yjbm := i_bbid;
    END IF;

    RETURN(v_yjbm);
END;
/
