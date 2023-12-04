/*
    Script creato da Gabriele Passarello per il progetto di database.
    Questo file è stato allegato insieme ad altri due:
        - relazione_gp_x81000604.pdf
        - creazione.sql
    
    email: gabriele.passarello98@gmail.com

    MariaDB 10.4.25
*/

USE `cinema_multi` ;

-- Operazioni previste
-- --------------------------------------------
-- P.s. Le zone commentate tra /* */ se decommentate stamperanno le modifiche effettuate dall'operazione

-- Op1. Ricerca dei film attualmente in proiezione nella citta' di Catania
SELECT film.titolo as Film, cinema.nome as Cinema, cinema.indirizzo as Indirizzo
FROM inProiezione  as ip JOIN cinema ON ip.idcinema = cinema.idcinema
        JOIN film ON ip.idfilm = film.idfilm
WHERE cinema.citta = "Catania" AND ip.data_ultima_proiezione IS NOT NULL;



-- Op2. Acquisto di un biglietto per la proiezione 4 e 10 (anche online)
INSERT INTO `biglietto` (`idproiezione`, `prezzo`, `usato`, `data_emissione`)
    VALUES
    (4, DEFAULT, DEFAULT, DEFAULT),
    (10, DEFAULT, DEFAULT, DEFAULT);

/*  
SELECT *
FROM proiezione
WHERE idproiezione = 4 OR idproiezione = 10;
SELECT *
FROM biglietto
WHERE idproiezione = 4 OR idproiezione = 10;
*/



-- Op3. Uso abbonamento per ingresso alla proiezione 1 e 3 (anche online)
INSERT INTO `prenotazione` (`idabbonamento`, `idproiezione`)
    VALUES
    ("ffffffff-ffff-ffff-ffff-ffffffffffff", 3),
    ("ffffffff-ffff-ffff-ffff-ffffffffffff", 4);

/*
SELECT *
FROM proiezione
WHERE idproiezione = 1 OR idproiezione = 3;
SELECT *
FROM prenotazione
WHERE idabbonamento = "bbbbbb";
SELECT *
FROM abbonamento
WHERE idabbonamento = "bbbbbb";

*/



-- Op4. Vendita di un abbonamento
INSERT INTO `abbonamento` (`idtipo`, `ingressi_rimanenti`, `data_emissione`)
    VALUES
    (1, DEFAULT, DEFAULT);

-- Op4. Rinnovo di un abbonamento manentenendo il piano
UPDATE abbonamento
    SET data_emissione = "2024-01-01"
    WHERE idabbonamento = "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb";

-- Op4. Rinnovo di un abbonamento cambiando il piano
UPDATE abbonamento
    SET idtipo = 1, data_emissione = "2025-01-01"
    WHERE idabbonamento = "ffffffff-ffff-ffff-ffff-ffffffffffff";

/*
SELECT *
FROM abbonamento
WHERE idabbonamento = "aaaaaa" OR idabbonamento = "bbbbbb";
*/



-- Op5. Cambio programmazione settimanale dei film in proiezione nel cinema Lux di Catania e Odeon di Milano
-- P.s. Le due select servono soltanto per mostrare il prima e il dopo la modifica.
SELECT film.titolo as Film, ip.data_prima_proiezione as "Dal:", ip.data_ultima_proiezione as "Al:",
         cinema.nome as Cinema, cinema.indirizzo as Indirizzo
FROM inProiezione  as ip JOIN cinema ON ip.idcinema = cinema.idcinema
        JOIN film ON ip.idfilm = film.idfilm
WHERE (cinema.nome = "Lux"  OR cinema.nome = "Odeon") AND ip.data_ultima_proiezione IS NOT NULL;

SET @p0="Catania"; SET @p1="Lux";
CALL cambio_programmazione_cinema(@p0, @p1);
SET @p0="Milano"; SET @p1="Odeon";
CALL cambio_programmazione_cinema(@p0, @p1);

SELECT film.titolo as Film, ip.data_prima_proiezione as "Dal:", ip.data_ultima_proiezione as "Al:",
         cinema.nome as Cinema, cinema.indirizzo as Indirizzo
FROM inProiezione  as ip JOIN cinema ON ip.idcinema = cinema.idcinema
        JOIN film ON ip.idfilm = film.idfilm
WHERE (cinema.nome = "Lux"  OR cinema.nome = "Odeon") AND ip.data_ultima_proiezione IS NOT NULL;



-- Op6. Inserimento di un nuovo film
INSERT INTO `film` (`titolo`, `regista`, `genere`, `durata`, `trama`)
    VALUES
    ("La storia infinita", "Wolfgang Petersen", "fantasy", 97, NULL);

/*
SELECT *
FROM film;
*/



-- Op7. Ricerca dei cinema che proietteranno il film Django
SELECT film.titolo as Titolo, cinema.nome as Cinema, cinema.indirizzo, cinema.citta as Citta,
        COALESCE(ip.data_ultima_proiezione, "Coming soon!") as "In proiezione fino al:"
FROM inProiezione as ip JOIN film ON ip.idfilm = film.idfilm
    JOIN cinema ON cinema.idcinema = ip.idcinema
WHERE film.titolo LIKE "%Django%";



-- Op8. Genere film più visti
CREATE VIEW genere_visti AS
SELECT film.genere, COUNT(*) as count
FROM biglietto as b JOIN proiezione as p ON b.idproiezione = p.idproiezione
    JOIN film ON film.idfilm = p.idfilm
GROUP BY film.genere
UNION ALL
SELECT film.genere, COUNT(*)
FROM prenotazione as pre JOIN proiezione as pro ON pre.idproiezione = pro.idproiezione
    JOIN film ON film.idfilm = pro.idfilm
GROUP BY film.genere;

SELECT genere, SUM(count) as n_presenze
FROM genere_visti as gv
GROUP BY genere
ORDER BY n_presenze DESC;

DROP VIEW genere_visti;

-- Op9. Città più redditizie
SELECT cinema.citta, SUM(b.prezzo) as guadagno
FROM biglietto as b JOIN proiezione as p ON b.idproiezione = p.idproiezione
    JOIN cinema ON cinema.idcinema = p.idcinema
GROUP BY cinema.citta
ORDER BY guadagno DESC;



-- Op10. Abbonamento più usato
SELECT a.idtipo as "id_tipo", ta.nome, COUNT(*) as "n_utilizzi"
FROM prenotazione as pre JOIN proiezione as pro ON pre.idproiezione = pro.idproiezione
    JOIN abbonamento as a ON a.idabbonamento = pre.idabbonamento
    JOIN tipoAbbonamento as ta ON a.idtipo = ta.idtipo
GROUP BY a.idtipo;
