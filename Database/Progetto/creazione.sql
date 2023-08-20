/*
    Script creato da Gabriele Passarello per il progetto di database.
    Questo file è stato allegato insieme ad altri due:
        - relazione_gp_x81000604.pdf
        - operazioni.sql
    
    email: gabriele.passarello98@gmail.com

    MariaDB 10.4.25
*/

-- Creazione dello schema
-- --------------------------------------------
CREATE SCHEMA IF NOT EXISTS  `cinema_multi` DEFAULT CHARACTER SET utf8 ;
USE `cinema_multi` ;USE `cinema_multi` ;



-- Creazione delle tabelle
-- --------------------------------------------
CREATE TABLE `cinema_multi`.`cinema` (
    `idcinema` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(45) NOT NULL,
    `citta` VARCHAR(45) NOT NULL,
    `indirizzo` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`idcinema`))
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`sala` (
    `idcinema` INT NOT NULL,
    `idsala` INT NOT NULL,
    `n_posti` SMALLINT UNSIGNED NOT NULL,
    `3D` BOOLEAN NOT NULL,
    PRIMARY KEY (`idcinema`, `idsala`),
    FOREIGN KEY (`idcinema`)
        REFERENCES `cinema_multi`.`cinema` (`idcinema`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`film` (
    `idfilm` INT NOT NULL AUTO_INCREMENT,
    `titolo` VARCHAR(150) NOT NULL,
    `regista` VARCHAR(45) NOT NULL,
    `genere` VARCHAR(45) NOT NULL,
    `durata` SMALLINT UNSIGNED NOT NULL,
    `trama` TEXT NULL,
    PRIMARY KEY (`idfilm`))
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`turno` (
    `idturno` INT NOT NULL AUTO_INCREMENT,
    `orario` TIME NOT NULL,
    PRIMARY KEY (`idturno`))
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`proiezione` (
    `idproiezione` INT NOT NULL AUTO_INCREMENT,
    `idcinema` INT NOT NULL,
    `idsala` INT NOT NULL,
    `idfilm` INT NOT NULL,
    `posti_rimanenti` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    `idturno` INT NOT NULL,
    `data` DATE NOT NULL,
    `3D` BOOLEAN NOT NULL,
    PRIMARY KEY (`idproiezione`),
    FOREIGN KEY (`idcinema`, `idsala`)
        REFERENCES `cinema_multi`.`sala` (`idcinema`, `idsala`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`idturno`)
        REFERENCES `cinema_multi`.`turno` (`idturno`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`idfilm`)
        REFERENCES `cinema_multi`.`film` (`idfilm`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`biglietto` (
    `idbiglietto` INT NOT NULL AUTO_INCREMENT,
    `idproiezione` INT NOT NULL,
    `prezzo` FLOAT NOT NULL DEFAULT 6,
    `usato` BOOLEAN NOT NULL DEFAULT 0,
    `data_emissione` DATE NOT NULL DEFAULT (CURRENT_DATE),
    PRIMARY KEY (`idbiglietto`),
    FOREIGN KEY (`idproiezione`)
        REFERENCES `cinema_multi`.`proiezione` (`idproiezione`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`tipoAbbonamento` (
    `idtipo` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(45) NOT NULL,
    `prezzo` FLOAT NOT NULL,
    `3D` BOOLEAN NOT NULL,
    `ingressi_totali` TINYINT NOT NULL,
    PRIMARY KEY (`idtipo`))
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`abbonamento` (
    `idabbonamento` CHAR(6) NOT NULL DEFAULT uuid(),
    `idtipo` INT NOT NULL,
    `ingressi_rimanenti` TINYINT NOT NULL DEFAULT 0,
    `data_emissione` DATE NOT NULL DEFAULT (CURRENT_DATE),
    PRIMARY KEY (`idabbonamento`),
    FOREIGN KEY (`idtipo`)
        REFERENCES `cinema_multi`.`tipoAbbonamento` (`idtipo`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`prenotazione` (
    `idabbonamento` CHAR(6) NOT NULL,
    `idproiezione` INT NOT NULL,
    PRIMARY KEY (`idabbonamento`, `idproiezione`),
    FOREIGN KEY (`idabbonamento`)
        REFERENCES `cinema_multi`.`abbonamento` (`idabbonamento`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`idproiezione`)
        REFERENCES `cinema_multi`.`proiezione` (`idproiezione`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `cinema_multi`.`inProiezione` (
    `idcinema` INT NOT NULL,
    `idfilm` INT NOT NULL,
    `data_prima_proiezione` DATE NULL DEFAULT NULL,
    `data_ultima_proiezione` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`idcinema`, `idfilm`),
    FOREIGN KEY (`idcinema`)
        REFERENCES `cinema_multi`.`cinema` (`idcinema`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`idfilm`)
        REFERENCES `cinema_multi`.`film` (`idfilm`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- Creazione dei trigger
-- --------------------------------------------

-- PROIEZIONE: all'aggiunta di una nuova proiezione controlla che, nel caso di proiezione 3D lo sia anche la SALA
-- PROIEZIONE: all'aggiunta setta 'posti_rimanenti' = al valore corretto
DROP TRIGGER IF EXISTS `check_set_proiezione`;
DELIMITER $$
CREATE TRIGGER check_set_proiezione
BEFORE INSERT ON proiezione
FOR EACH ROW
BEGIN
    DECLARE 3dCheck BOOLEAN;
    DECLARE posti SMALLINT;
    SELECT sala.3D, sala.n_posti INTO 3dCheck, posti
        FROM sala
        WHERE sala.idcinema = NEW.idcinema AND sala.idsala = NEW.idsala;
    IF ( NEW.3D = 1 AND 3dCheck = 0 )
    -- Se per errore viene creata una proiezione 3D in una sala non idonea ==> annullo operazione e mando errore
        THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Errore - Hai creato una proiezione 3D in una SALA non idonea!';
    END IF;
    SET NEW.posti_rimanenti = posti;
END $$
DELIMITER ;


-- BIGLIETTO: all'aggiunta di un nuovo biglietto controlla che vi siano "posti_rimanenti" > 0 in SALA
-- BIGLIETTO: nel caso di proiezione 3D incrementa il prezzo di 2euro
DROP TRIGGER IF EXISTS `check_biglietto`;
DELIMITER $$
CREATE TRIGGER `check_biglietto`
BEFORE INSERT ON biglietto
FOR EACH ROW
BEGIN
    DECLARE nPosti SMALLINT;
    DECLARE 3dCheck BOOLEAN;
    SELECT proiezione.posti_rimanenti, proiezione.3D INTO nPosti, 3dCheck
        FROM proiezione
        WHERE NEW.idproiezione = proiezione.idproiezione;
    IF (nPosti <= 0)
    -- Se la sala e' gia' piena => annullo operazione e mando errore
        THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Errore - Posti in sala esauriti!';
    END IF;
    IF (3dCheck = 1)
        THEN SET NEW.prezzo = NEW.prezzo + 2;
    END IF;
    UPDATE proiezione
        SET proiezione.posti_rimanenti = proiezione.posti_rimanenti - 1
        WHERE proiezione.idproiezione = NEW.idproiezione;
END $$
DELIMITER ;


-- PRENOTAZIONE: all'aggiunta di un nuova prenotazione controlla che vi siano "ingressi_rimanenti" > 0 in ABBONAMENTO e nel caso decrementa di uno
-- '-> controlla anche che, nel caso di proiezione 3D, l'abbonamento sia idoneo.
-- PRENOTAZIONE: all'aggiunta controlla che vi siano "posti_rimanenti" > 0 in SALA e nel caso decrementa di uno
DROP TRIGGER IF EXISTS `check_prenotazione`;
DELIMITER $$
CREATE TRIGGER `check_prenotazione`
BEFORE INSERT ON prenotazione
FOR EACH ROW
BEGIN
    DECLARE nPosti SMALLINT;
    DECLARE nIngressi TINYINT;
    DECLARE 3dCheck_abb BOOLEAN;
    DECLARE 3dCheck_proiez BOOLEAN;
    SELECT abbonamento.ingressi_rimanenti, tipoAbbonamento.3D INTO nIngressi, 3dCheck_abb
        FROM abbonamento JOIN tipoAbbonamento
        WHERE NEW.idabbonamento = abbonamento.idabbonamento AND abbonamento.idtipo = tipoAbbonamento.idtipo;
    IF (nIngressi <= 0)
        THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Errore - Ingressi CARD esauriti!';
    END IF;
    SELECT proiezione.posti_rimanenti, proiezione.3D INTO nPosti, 3dCheck_proiez
        FROM proiezione
        WHERE proiezione.idproiezione = NEW.idproiezione;
    IF (nPosti <= 0)
        THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Errore - Posti in sala esauriti!';
    END IF;
    IF (3dCheck_proiez = 1 AND 3dCheck_abb <> 1)
        THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Errore - La CARD non consente ingressi 3D!';
    END IF;
    UPDATE abbonamento
        SET abbonamento.ingressi_rimanenti = abbonamento.ingressi_rimanenti - 1
        WHERE abbonamento.idabbonamento = NEW.idabbonamento;
    UPDATE proiezione
        SET proiezione.posti_rimanenti = proiezione.posti_rimanenti - 1
        WHERE proiezione.idproiezione = NEW.idproiezione;
END $$
DELIMITER ;


-- ABBONAMENTO: alla creazione di un abbonamento setta "ingressi_rimanenti" al valore corretto.
DROP TRIGGER IF EXISTS `set_abbonamento`;
DELIMITER $$
CREATE TRIGGER set_abbonamento
BEFORE INSERT ON abbonamento
FOR EACH ROW
BEGIN
    DECLARE ingressi TINYINT;
    SELECT ingressi_totali INTO ingressi FROM tipoAbbonamento WHERE tipoAbbonamento.idtipo = NEW.idtipo;
    SET NEW.ingressi_rimanenti = ingressi;
END $$
DELIMITER ;


-- ABBONAMENTO: al rinnovamento (UPDATE data_emissione) di un abbonamento setta "ingressi_rimanenti" al valore corretto.
-- P.s. un abbonamento può essere rinnovato con un piano differente
DROP TRIGGER IF EXISTS `set_rinnovo_abbonamento`;
DELIMITER $$
CREATE TRIGGER set_rinnovo_abbonamento
BEFORE UPDATE ON abbonamento
FOR EACH ROW
BEGIN
    DECLARE ingressi TINYINT;
    IF (NEW.data_emissione <> OLD.data_emissione) -- sarebbe '...OF data_emissione ON abbonamento...'
    THEN
        SELECT ingressi_totali INTO ingressi
            FROM tipoAbbonamento
            WHERE tipoAbbonamento.idtipo = NEW.idtipo;
        SET NEW.ingressi_rimanenti = ingressi;
    END IF;
END $$
DELIMITER ;


-- IN-PROIEZIONE: quando viene aggiunta una proiezione prossima (data_prima_proiezione == NULL && data_ultima_proiezione == NULL)
-- => setta automaticamente l'attributo data_prima_proiezione alla data prossima utile (tra 7 giorni)
DROP TRIGGER IF EXISTS `set_null_inProiezione`;
DELIMITER $$
CREATE TRIGGER set_null_inProiezione
BEFORE INSERT ON inProiezione
FOR EACH ROW
BEGIN
    DECLARE new_date DATE DEFAULT NULL;
    IF (NEW.data_prima_proiezione IS NULL)
    THEN
        SELECT data_ultima_proiezione INTO new_date
            FROM inProiezione
            WHERE inProiezione.data_ultima_proiezione IS NOT NULL AND inProiezione.idcinema = NEW.idcinema
            LIMIT 1;
        IF (new_date IS NULL)
            THEN SELECT curdate() - weekday(curdate()) + INTERVAL 6 DAY INTO new_date; -- Seleziona il prossimo lunedì
        END IF;
        SET NEW.data_prima_proiezione = new_date + INTERVAL 1 DAY;
    END IF;
END $$
DELIMITER ;


-- IN-PROIEZIONE: procedura che elimina dalla tabella inProiezione tutte le proiezioni attuali (data_ultima_proiezione != null) del cinema X della citta Y
-- '-> ... e che successivamente aggiorna tutte le proiezioni prossime (data_ultima_proiezione == null) impostandole come correnti
DROP PROCEDURE IF EXISTS `cambio_programmazione_cinema`;
DELIMITER $$
CREATE PROCEDURE `cambio_programmazione_cinema` (INOUT var_citta VARCHAR(45), INOUT var_cinema VARCHAR(45))
BEGIN
    DECLARE idC INTEGER;
    SET idC = (SELECT idcinema
                    FROM cinema
                    WHERE citta = var_citta AND nome = var_cinema);
    
    DELETE FROM inProiezione
    WHERE idcinema = idC AND data_ultima_proiezione IS NOT NULL;

    UPDATE inProiezione
    SET data_ultima_proiezione = data_prima_proiezione + INTERVAL 7 day
    WHERE idcinema = idC AND data_prima_proiezione IS NOT NULL;

    -- TRUNCATE TABLE `cinema_multi`.`proiezione`;
END$$
DELIMITER ;



-- Popolamento delle tabelle
-- --------------------------------------------
INSERT INTO `cinema` (`nome`, `citta`, `indirizzo`)
    VALUES
    ("Teatro Pellicola", "Torino", "Via verdi, 45"),
    ("ARCADIA", "Roma", "Via rossi, 14"),
    ("CineGold", "Firenze", "Via bianchi, 3"),
    ("Lux", "Catania", "Via silvestre, 5"),
    ("Ciak", "Catania", "Via casetta, 126"),
    ("Odeon", "Milano", "Via soldoni, 44");

INSERT INTO `sala` (`idcinema`, `idsala`, `n_posti`, `3D`)
    VALUES
    (1, 1, 100, 0),
    (2, 1, 200, 1),
    (2, 2, 130, 0),
    (3, 1, 150, 1),
    (3, 2, 90, 0),
    (3, 3, 110, 0),
    (4, 1, 150, 1),
    (4, 2, 90, 0),
    (5, 1, 110, 0),
    (6, 1, 80, 0),
    (6, 2, 150, 1),
    (6, 3, 160, 1);

INSERT INTO `film` (`titolo`, `regista`, `genere`, `durata`, `trama`)
    VALUES
    ("Il Signore degli Anelli - La Compagnia dell'Anello", "Peter Jackson", "fantasy", 228, "Un giovane hobbit e un variegato gruppo, composto da umani, un nano, un elfo e altri hobbit, partono per un delicata missione, guidati dal potente mago Gandalf. Devono distruggere un anello magico e sconfiggere così il malvagio Sauron."),
    ("La carica dei 101", "regista2", "cartoon", 10, NULL),
    ("Topolino", "regista3", "cartoon", 10, NULL),
    ("Inception", "regista4", "thriller", 10, NULL),
    ("Via col vento", "regista5", "fantasy", 120, NULL),
    ("20.000 leghe sotto i mari", "regista56", "thriller", 120, NULL),
    ("Django", "regista5", "azione", 120, NULL),
    ("Il conte di montecristo", "regista5", "noir", 120, NULL),
    ("Casablanca", "regista5", "drammatico", 120, NULL),
    ("Il settimo sigillo", "regista5", "noir", 120, NULL),
    ("The Raven", "regista5", "thriller", 120, NULL),
    ("Il padrino", "regista5", "gangster", 120, NULL),
    ("Il cavaliere oscuro", "regista5", "azione", 120, NULL),
    ("Django unchained", "regista5", "azione", 180, NULL);

INSERT INTO `turno` (`orario`)
    VALUES
    ("16:00:00"),
    ("19:00:00"),
    ("22:00:00");

INSERT INTO `proiezione` (`idcinema`, `idsala`, `idfilm`, `posti_rimanenti`, `idturno`, `data`,`3D`)
    VALUES
    (1, 1, 5, DEFAULT, 1, "2023-04-10", 0),
    (2, 1, 2, DEFAULT, 1, "2023-04-10", 0),
    (2, 1, 2, DEFAULT, 2, "2023-04-10", 1),
    (2, 1, 2, DEFAULT, 3, "2023-04-10", 1),
    (2, 2, 7, DEFAULT, 2, "2023-04-10", 0),
    (2, 2, 10, DEFAULT, 3, "2023-04-10", 0),
    (4, 1, 12, DEFAULT, 1, "2023-04-10", 1),
    (5, 1, 13, DEFAULT, 2, "2023-04-10", 0),
    (6, 1, 4, DEFAULT, 1, "2023-04-17", 0),
    (6, 1, 7, DEFAULT, 3, "2023-04-17", 0);

INSERT INTO `inProiezione` (`idcinema`, `idfilm`, `data_prima_proiezione`, `data_ultima_proiezione`)
    VALUES
    (1, 5, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (2, 2, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (2, 7, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (2, 10, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (4, 7, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (4, 9, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (4, 12, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (5, 13, "2023-04-10", data_prima_proiezione + INTERVAL 7 DAY),
    (6, 4, "2023-04-17", data_prima_proiezione + INTERVAL 7 DAY),
    (6, 7, "2023-04-17", data_prima_proiezione + INTERVAL 7 DAY),
    (1, 7, DEFAULT, DEFAULT),
    (3, 7, DEFAULT, DEFAULT),
    (5, 7, DEFAULT, DEFAULT),
    (5, 14, DEFAULT, DEFAULT),
    (4, 8, DEFAULT, DEFAULT),
    (4, 2, DEFAULT, DEFAULT),
    (6, 10, DEFAULT, DEFAULT),
    (6, 11, DEFAULT, DEFAULT),
    (6, 12, DEFAULT, DEFAULT);

INSERT INTO `biglietto` (`idproiezione`, `prezzo`, `usato`, `data_emissione`)
    VALUES
    (3, DEFAULT, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT, DEFAULT),
    (2, DEFAULT, DEFAULT, DEFAULT),
    (5, DEFAULT, DEFAULT, DEFAULT),
    (5, DEFAULT, DEFAULT, DEFAULT),
    (4, DEFAULT, DEFAULT, DEFAULT),
    (4, DEFAULT, DEFAULT, DEFAULT),
    (4, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT),
    (9, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO `tipoAbbonamento` (`nome`, `prezzo`, `3D`, `ingressi_totali`)
    VALUES
    ("BASIC", 30, 0, 5),
    ("MEMBER", 55, 0, 10),
    ("DELUXE", 80, 1, 15);

INSERT INTO `abbonamento` (`idtipo`, `ingressi_rimanenti`, `data_emissione`)
    VALUES
    (1, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT),
    (1, DEFAULT, DEFAULT),
    (2, DEFAULT, DEFAULT),
    (2, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT),
    (3, DEFAULT, DEFAULT);

INSERT INTO `abbonamento` (`idabbonamento`, `idtipo`, `ingressi_rimanenti`, `data_emissione`)
    VALUES
    ("aaaaaa", 2, DEFAULT, DEFAULT),
    ("bbbbbb", 3, DEFAULT, DEFAULT);

INSERT INTO `prenotazione` (`idabbonamento`, `idproiezione`)
    VALUES
    ("aaaaaa", 6),
    ("aaaaaa", 8),
    ("aaaaaa", 1),
    ("aaaaaa", 10);

