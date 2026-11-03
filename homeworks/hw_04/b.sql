-- запрос соединяет 2 таблицы и показывает за счет внешнего соединения кол-во сдач
SELECT
	a.pair_number,
	a.title,
	COUNT(s.id) AS submissions
FROM
	assignment a
--внешнее соединение, чтобы не упустить задания, по которым не было сдач
LEFT JOIN submissions s ON
	s.assignment_id = a.id
GROUP BY
	a.pair_number,
	a.title;

-- результат на тестовых данных
-- pair_number	title	submissions
-- 6	Стек/очередь/HashMap: Valid Parentheses + Group Anagrams	6
-- 7	Карта зависимостей StPlatform	3
-- 8	SQL-выборки по схеме платформы	0
