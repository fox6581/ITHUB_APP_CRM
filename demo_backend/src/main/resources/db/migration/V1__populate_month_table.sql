-- INSERT INTO month (id, name)
-- SELECT id, name
-- FROM (VALUES
--           (1, 'Январь'),
--           (2, 'Февраль'),
--           (3, 'Март'),
--           (4, 'Апрель'),
--           (5, 'Май'),
--           (6, 'Июнь'),
--           (7, 'Июль'),
--           (8, 'Август'),
--           (9, 'Сентябрь'),
--           (10, 'Октябрь'),
--           (11, 'Ноябрь'),
--           (12, 'Декабрь')
--      ) AS temp(id, name)
-- WHERE NOT EXISTS (
--     SELECT 1
--     FROM month
--     WHERE month.id = temp.id
-- );
