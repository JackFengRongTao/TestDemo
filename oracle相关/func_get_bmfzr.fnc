CREATE OR REPLACE FUNCTION func_get_bmfzr(i_bmid IN NUMBER) RETURN VARCHAR2 DETERMINISTIC AS
    o_bmfzr   VARCHAR2(100);
    v_orgtype NUMBER;
    v_grade   NUMBER;
    v_fid     NUMBER;
    v_sjbm    NUMBER;
    v_yjbm    NUMBER;

BEGIN
    SELECT orgtype, grade, fid
      INTO v_orgtype, v_grade, v_fid
      FROM lborganization
     WHERE id = i_bmid;
    SELECT fid INTO v_sjbm FROM lborganization WHERE id = v_fid;
    IF (v_orgtype = 8 AND v_grade = 4) OR v_orgtype = 12 THEN
        v_yjbm := v_sjbm;
    ELSIF (v_orgtype = 8 AND v_grade = 3) OR v_orgtype IN (10, 12) OR (v_fid = 9014) THEN
        v_yjbm := v_fid;
    ELSE
        v_yjbm := i_bmid;
    END IF;
    IF i_bmid IN (100677, 100647) THEN
        v_yjbm := i_bmid;
    END IF;
    BEGIN
        SELECT wm_concat(userid)
          INTO o_bmfzr
          FROM lbmember
         WHERE roleid = (SELECT id FROM lbrole WHERE rolecode = 'BMFZR')
           AND orgid = v_yjbm;
    EXCEPTION
        WHEN OTHERS THEN
            o_bmfzr := NULL;
            RETURN(o_bmfzr);
    END;
    RETURN(o_bmfzr);
END;
/
