-- запрос соединяет данные 3-х таблиц и фильтрует по фамилии студента
SELECT
	u.full_name,
	a.pair_number,
	s.link,
	s.status,
	s.created_at
FROM
	submissions s
JOIN users u ON
	s.student_id = u.id
JOIN assignment a ON
	s.assignment_id = a.id
WHERE
	u.role = 'STUDENT'
	-- с пробелом и с одним %, потому что иначе будет находиться еще "Иванова"
	AND u.full_name like 'Иванов %'
ORDER BY
	s.created_at,
	s.id;


-- результат на тестовых данных
-- full_name	pair_number	link	status	created_at
-- Иванов Пётр	6	gitverse.ru/ivanov/hw6-pr1	ACCEPTED	2026-10-22 19:04
-- Иванов Пётр	6	gitverse.ru/ivanov/hw6-pr2	NEEDS_REWORK	2026-10-24 12:30
-- Иванов Пётр	7	gitverse.ru/ivanov/hw7	UNCHECKED	2026-10-26 18:00
