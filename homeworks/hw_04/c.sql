-- запрос убирает из выборки при помощи исключения через not exists студенто со сдачей
SELECT
	u.full_name
FROM
	users u
WHERE
	u.role = 'STUDENT'
	-- и у которого нет ни одной сдачи
	AND NOT EXISTS (
	SELECT
		1
	FROM
		submissions s
	WHERE
		s.student_id = u.id);

-- результат на тестовых данных
-- full_name
-- Морозов Игорь
