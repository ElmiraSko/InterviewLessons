CREATE SCHEMA `cinema_db` ;

CREATE TABLE `cinema_db`.`films` (
  `id` SMALLINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `duration` TINYINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `cinema_db`.`schedule` (
  `id` MEDIUMINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `start` TIME NOT NULL,
  `price` DECIMAL(8,2) NOT NULL,
  `film_id` SMALLINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_film_id_idx` (`film_id` ASC) VISIBLE,
  CONSTRAINT `fk_film_id`
    FOREIGN KEY (`film_id`)
    REFERENCES `cinema_db`.`films` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `cinema_db`.`orders` (
  `id` MEDIUMINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ticket_number` MEDIUMINT(10) NULL,
  `purchase_date` DATETIME(6) NOT NULL,
  `session` MEDIUMINT(10) UNSIGNED NOT NULL,
  `film` SMALLINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_session_idx` (`session` ASC) VISIBLE,
  INDEX `fk_film_idx` (`film` ASC) VISIBLE,
  CONSTRAINT `fk_session`
    FOREIGN KEY (`session`)
    REFERENCES `cinema_db`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_film`
    FOREIGN KEY (`film`)
    REFERENCES `cinema_db`.`films` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
# Заполняем таблицу films данными    
INSERT INTO `cinema_db`.`films` (`name`, `duration`) VALUES ('Леон', '60');
INSERT INTO `cinema_db`.`films` (`name`, `duration`) VALUES ('Аватар', '90');
INSERT INTO `cinema_db`.`films` (`name`, `duration`) VALUES ('Гладиатор', '60');
INSERT INTO `cinema_db`.`films` (`name`, `duration`) VALUES ('Матрица', '120');
INSERT INTO `cinema_db`.`films` (`name`, `duration`) VALUES ('Области тьмы', '60');
/*
+----+--------------+----------+
| id | name         | duration |
+----+--------------+----------+
|  1 | Леон         |       60 |
|  2 | Аватар       |       90 |
|  3 | Гладиатор    |       60 |
|  4 | Матрица      |      120 |
|  5 | Области тьмы |       60 |
+----+--------------+----------+
*/

# Вносим данные в таблицу schedule
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '10:00', '120', '1');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '11:20', '130', '2');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '13:10', '120', '3');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '14:40', '150', '1');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '16:00', '150', '4');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '18:20', '180', '5');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '19:30', '200', '3');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '20:40', '200', '2');
INSERT INTO `cinema_db`.`schedule` (`date`, `start`, `price`, `film_id`) VALUES ('27.12.2020', '22:20', '250', '1');
/*
+----+------------+-----------+--------+---------+
| id | date       | beginning | price  | film_id |
+----+------------+-----------+--------+---------+
|  1 | 2027-12-20 | 10:00:00  | 120.00 |       1 |
|  2 | 2027-12-20 | 11:20:00  | 130.00 |       2 |
|  3 | 2027-12-20 | 13:20:00  | 120.00 |       3 |
|  4 | 2027-12-20 | 14:45:00  | 150.00 |       1 |
|  5 | 2027-12-20 | 16:20:00  | 150.00 |       4 |
|  6 | 2027-12-20 | 18:30:00  | 180.00 |       5 |
|  7 | 2027-12-20 | 20:00:00  | 200.00 |       3 |
|  8 | 2027-12-20 | 21:40:00  | 200.00 |       2 |
|  9 | 2027-12-20 | 23:20:00  | 250.00 |       1 |
+----+------------+-----------+--------+---------+
*/
# Вносим данные в таблицу orders

/* Вопрос 2.
 Перерывы 30 минут и более между фильмами 
— выводить по уменьшению длительности перерыва.
 Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма»,
 «длительность перерыва»;
  Ответ:
 Предварительно создала два представления: width_end и width_between_films.
width_end - выводит таблицу с дополнительным полем end_time - время окончания фильма
*/
SELECT sch.id, name, beginning, duration, date_add(beginning, interval duration minute) AS end_time 
FROM cinema_db.schedule sch LEFT JOIN cinema_db.films f ON sch.film_id=f.id ORDER BY beginning;
/*
+----+--------------+-----------+----------+----------+
| id | name         | beginning | duration | end_time |
+----+--------------+-----------+----------+----------+
|  1 | Леон         | 10:00:00  |       60 | 11:00:00 |
|  2 | Аватар       | 11:20:00  |       90 | 12:50:00 |
|  3 | Гладиатор    | 13:20:00  |       60 | 14:20:00 |
|  4 | Леон         | 14:45:00  |       60 | 15:45:00 |
|  5 | Матрица      | 16:20:00  |      120 | 18:20:00 |
|  6 | Области тьмы | 18:30:00  |       60 | 19:30:00 |
|  7 | Гладиатор    | 20:00:00  |       60 | 21:00:00 |
|  8 | Аватар       | 21:40:00  |       90 | 23:10:00 |
|  9 | Леон         | 23:20:00  |       60 | 24:20:00 |
+----+--------------+-----------+----------+----------+
*/
/* width_between_films - выводит таблицу с дополнительным полем between_films -
время между сеансами
*/
SELECT t1.name, t1.beginning AS beginning_1, t1.duration, t2.beginning AS beginning_2, 
timediff(t2.beginning, t1.end_time) AS between_films 
FROM width_end AS t1
LEFT JOIN cinema_db.schedule AS t2 ON t1.id = t2.id-1;
/*
+--------------+-------------+----------+-------------+---------------+
| name         | beginning_1 | duration | beginning_2 | between_films |
+--------------+-------------+----------+-------------+---------------+
| Леон         | 10:00:00    |       60 | 11:20:00    | 00:20:00      |
| Аватар       | 11:20:00    |       90 | 13:20:00    | 00:30:00      |
| Гладиатор    | 13:20:00    |       60 | 14:45:00    | 00:25:00      |
| Леон         | 14:45:00    |       60 | 16:20:00    | 00:35:00      |
| Матрица      | 16:20:00    |      120 | 18:30:00    | 00:10:00      |
| Области тьмы | 18:30:00    |       60 | 20:00:00    | 00:30:00      |
| Гладиатор    | 20:00:00    |       60 | 21:40:00    | 00:40:00      |
| Аватар       | 21:40:00    |       90 | 23:20:00    | 00:10:00      |
| Леон         | 23:20:00    |       60 | NULL        | NULL          |
+--------------+-------------+----------+-------------+---------------+
*/

# Запрос 2:
SELECT f.name, beginning_1, duration, beginning_2, between_films
FROM width_between_films f WHERE between_films >= '00:30:00'
ORDER BY between_films DESC;
/*
+--------------+-------------+----------+-------------+---------------+
| name         | beginning_1 | duration | beginning_2 | between_films |
+--------------+-------------+----------+-------------+---------------+
| Гладиатор    | 20:00:00    |       60 | 21:40:00    | 00:40:00      |
| Леон         | 14:45:00    |       60 | 16:20:00    | 00:35:00      |
| Аватар       | 11:20:00    |       90 | 13:20:00    | 00:30:00      |
| Области тьмы | 18:30:00    |       60 | 20:00:00    | 00:30:00      |
+--------------+-------------+----------+-------------+---------------+
*/

/* Вопрос 3.
 Список фильмов, для каждого — с указанием общего числа посетителей за все время,
 среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму 
(отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», 
содержащая данные по всем фильмам сразу;
  Ответ:
 Предварительно создала представление: group_by_session
*/
SELECT sch.id, f.name, count(ticket_number) AS visitors, sum(price) AS total_price
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id
GROUP BY session;
/*
+----+--------------+----------+-------------+
| id | name         | visitors | total_price |
+----+--------------+----------+-------------+
|  1 | Леон         |        3 |      360.00 |
|  2 | Аватар       |        4 |      520.00 |
|  3 | Гладиатор    |        1 |      120.00 |
|  4 | Леон         |        3 |      450.00 |
|  5 | Матрица      |        4 |      600.00 |
|  6 | Области тьмы |        3 |      540.00 |
|  7 | Гладиатор    |        3 |      600.00 |
|  8 | Аватар       |        3 |      600.00 |
|  9 | Леон         |        3 |      750.00 |
+----+--------------+----------+-------------+
*/
# Запрос 3:
SELECT f.name, count(ticket_number) AS visitors, avg(s.visitors) AS avg_visitors, sum(price) AS total_price 
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id inner JOIN group_by_session s ON sch.id = s.id
GROUP BY f.name
UNION 
SELECT 'Итого: ', count(ticket_number) AS visitors, avg(s.visitors) AS avg_visitors, sum(price) AS total_price 
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id inner JOIN group_by_session s ON sch.id = s.id;
/*
+--------------+----------+--------------+-------------+
| name         | visitors | avg_visitors | total_price |
+--------------+----------+--------------+-------------+
| Леон         |        9 |       3.0000 |     1560.00 |
| Аватар       |        7 |       3.5714 |     1120.00 |
| Гладиатор    |        4 |       2.5000 |      720.00 |
| Матрица      |        4 |       4.0000 |      600.00 |
| Области тьмы |        3 |       3.0000 |      540.00 |
| Итого:       |       27 |       3.2222 |     4540.00 |
+--------------+----------+--------------+-------------+
*/

/*
Вопрос 4.
Число посетителей и кассовые сборы, сгруппированные по времени начала фильма: 
с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 
(сколько посетителей пришло с 9 до 15 часов и т.д.).
Запрос:
*/
(SELECT "с 9 до 15 " AS total_visitors,  null AS total_price FROM films)
UNION
(SELECT  count(ticket_number) AS visitors, sum(price) AS total_price
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id where beginning >= '10:00:00' AND beginning <= '15:00:00')
UNION
(SELECT "с 15 до 18",  null FROM films)
UNION
(SELECT count(ticket_number) AS visitors, sum(price) AS total_price
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id where beginning >= '15:00:00' AND beginning <= '18:00:00')
UNION
(SELECT "с 18 до 21",  null FROM films)
UNION
(SELECT count(ticket_number) AS visitors, sum(price) AS total_price
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id where beginning >= '18:00:00' AND beginning <= '21:00:00')
UNION
(SELECT "с 21 до 00",  null FROM films)
UNION
(SELECT count(ticket_number) AS visitors, sum(price) AS total_price
FROM schedule sch LEFT JOIN orders o ON sch.id = o.session
LEFT JOIN films f ON sch.film_id = f.id where beginning >= '21:00:00' AND beginning <= '24:00:00');
/*
+----------------+-------------+
| total_visitors | total_price |
+----------------+-------------+
| с 9 до 15      |        NULL |
| 11             |     1450.00 |
| с 15 до 18     |        NULL |
| 4              |      600.00 |
| с 18 до 21     |        NULL |
| 6              |     1140.00 |
| с 21 до 00     |        NULL |
| 6              |     1350.00 |
+----------------+-------------+
*/

