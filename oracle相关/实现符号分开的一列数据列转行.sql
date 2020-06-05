--实现符号分开的一列数据，列转行：
--英文逗号隔开：
SELECT REGEXP_SUBSTR('738,1345,1000', '[^,]+', 1, rownum)
          FROM DUAL
        CONNECT BY ROWNUM <=
                   LENGTH('738,1345,1000') - LENGTH(REPLACE('738,1345,1000', ',', '')) + 1;
-- |隔开的数据：                   
 SELECT REGEXP_SUBSTR('738|1345a|1000b', '[^|]+', 1, rownum)
          FROM DUAL
        CONNECT BY ROWNUM <=
                   LENGTH('738|1345a|1000b') - LENGTH(REPLACE('738|1345a|1000b', '|', '')) + 1;
                   
--其他符号隔开的数据也可以
 SELECT REGEXP_SUBSTR('738*1345a*1000b', '[^*]+', 1, rownum)
          FROM DUAL
        CONNECT BY ROWNUM <=
                   LENGTH('738*1345a*1000b') - LENGTH(REPLACE('738*1345a*1000b', '*', '')) + 1;
