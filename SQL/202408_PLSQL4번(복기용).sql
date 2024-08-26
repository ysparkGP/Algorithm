/*
 * 프로그래머스 PL_SQL 4번 복기 및 재풀이(mysql 5.7)
 * 현재 남은 연속된 좌석 수량 구하기
 */
drop table test;

create table seat_info(
	row_id varchar(255)
	, seats integer
)
;

insert into seat_info values
('A',8)
,('B',11)
,('C',13)
;

create table reserved_seats(
	id integer
	, reserve_seat varchar(255)
)
;

insert into reserved_seats values
(1,'A3')
,(2,'B2')
,(3,'B3')
,(4,'B8')
,(5,'B9')
,(6,'B5')
;

with recursive cte(row_id, seats, lev) as (
-- 	전체 좌석 데이터 세팅
	select row_id, seats, 1
	from seat_info 
	union all
	select row_id, seats, lev+1
	from cte
	where seats > lev
), tmp as (
--  남은 좌석 데이터 세팅
	SELECT *
	from cte
	where 1=1
	and not exists (
		select 'x'
		from reserved_seats 
		where reserve_seat = concat(cte.row_id, cte.lev)
	)
	order by row_id, lev
), tmp2 as (
--  남은 연속된 데이터를 그룹화
	select row_id
			, seats
			, lev
			, @LEVEL := if(@PREV_ROW_ID = row_id and @PREV_LEV + 1 = lev, @LEVEL, @LEVEL+1) as lev_group
			, @PREV_ROW_ID := row_id
			, @PREV_LEV := lev
	from tmp t1 
		,(select @PREV_LEV := 1, @LEVEL := 0, @PREV_ROW_ID := '') t2
) 
, tmp3 as (
-- 그룹화한 데이터들 수량 집계
	select lev_group
			, count(*) as cnt
	from tmp2
	group by lev_group
)
-- 수량 별로 연속된 좌석 집계
select cnt, count(*)
from tmp3
group by cnt
;