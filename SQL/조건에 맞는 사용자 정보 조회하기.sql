/*
* ORACLE
* GROUP BY, HAVING, STRING
*/

SELECT T1.USER_ID
    , T1.NICKNAME
    , CONCAT(CONCAT(CONCAT(CONCAT(T1.CITY,' '), T1.STREET_ADDRESS1),' '),STREET_ADDRESS2) 전체주소
    , CONCAT(
            CONCAT(
                CONCAT(
                        CONCAT(
                                SUBSTR(T1.TLNO,1,3),'-'
                        ),
                        CASE WHEN LENGTH(T1.TLNO) < 11 THEN SUBSTR(T1.TLNO,4,3)
                        ELSE SUBSTR(T1.TLNO,4,4)
                        END
                ),
                '-'
            ),
            CASE WHEN LENGTH(T1.TLNO) < 11 THEN SUBSTR(T1.TLNO,7,4)
            ELSE SUBSTR(T1.TLNO,8,4)
            END
        ) 전화번호
    -- ,TLNO
FROM USED_GOODS_USER T1, 
(
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(*) >= 3
) T2
WHERE T1.USER_ID = T2.WRITER_ID
ORDER BY T1.USER_ID DESC