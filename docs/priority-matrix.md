# Матрица приоритетов функционала «Полка»

Версия 1.1  
Дата: 09.11.2026  
Шкала: 10 — наивысший приоритет, 50 — минимальный.

Длина итерации: 1 неделя. Оценка — в человеко-днях. Итерация 2: senior + junior.

| ID | Title (en) | Приоритет | Оценка, дн. | Итерация | Статус |
|----|------------|-----------|-------------|----------|--------|
| REQ-FUNC-001 | Save book | 10 | 3 | Iteration 1 | Approved |
| REQ-FUNC-002 | Track current page and remainder | 15 | 3 | Iteration 1 | Approved |
| REQ-FUNC-006 | Export and import shelf copy | 18 | 4 | Iteration 2 | Approved |
| REQ-FUNC-005 | Finish date | 20 | 2 | Iteration 1 | Approved |
| REQ-FUNC-004 | Monthly page goal | 25 | 5 | Iteration 2 | Approved |
| REQ-FUNC-003 | Filter shelf by status | 45 | 4 | Backlog | Deferred |

## Как считали приоритет

Три критерия, в таком порядке:

1. Ценность для читателя: без карточки книги остальное бессмысленно.
2. Зависимость: дата завершения нужна норме месяца; копия полки не зависит от фильтра.
3. Трудозатраты при стеке HTML + vanilla JS + localStorage.

REQ-FUNC-006 получил 18 после CR-001: без копии второй компьютер обнуляет работу итерации 1. REQ-FUNC-003 опущен до 45: короткая личная полка читается без фильтра.
