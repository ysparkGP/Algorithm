/*
 * 프로그래머스 PL_SQL 5번 복기 및 재풀이(mysql 5.7)
 * 최다연승횟수 구하기
 */
drop table test;

create table test(
	p_date date
	, result varchar(255)
)
;

insert into test(p_date, result)
values
(cast('20200801' as date), 'WIN')
,(cast('20200802' as date), 'WIN')
,(cast('20200803' as date), 'WIN')
,(cast('20200804' as date), 'LOSE')
,(cast('20200805' as date), 'WIN')
,(cast('20200807' as date), 'WIN')
,(cast('20200808' as date), 'WIN')
,(cast('20200809' as date), 'WIN')
;

select *
from (
	select t1.*
			, @RANK := IF(@RESULT = result, @RANK+1,1) as rnk
			, @RESULT := result
	from (
		SELECT *
		from test
		order by p_date
	) t1, (
		select @RANK := 1, @RESULT := ''
	) t2
) t3
where 1=1
-- and result = 'WIN'
-- group by result
order by p_date
;