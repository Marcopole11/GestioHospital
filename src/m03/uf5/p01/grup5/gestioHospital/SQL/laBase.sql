/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  miral
 * Created: Mar 28, 2019
 */
CREATE DATABASE hospital_grup5;
CREATE USER IF NOT EXISTS 'admin_hospita_grup5l'@'127.0.0.1' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON hospital_grup5.* TO 'admin_hospital'@'127.0.0.1';
CREATE USER IF NOT EXISTS usuario_hospital_grup5 IDENTIFIED BY 'usuari';
GRANT SELECT, INSERT, UPDATE ON hospital_grup5.* TO usuario_hospital_grup5;
FLUSH PRIVILEGES;
USE hospital_grup5;

create table pacients(
    casaObloquePac BOOLEAN,
    nomPac VARCHAR(15),
    primerCognomPac VARCHAR(20),
    segonCognomPac VARCHAR(20),
    numSegSocialPac VARCHAR(12) UNIQUE NOT NULL,
    nifPac VARCHAR(9) UNIQUE NOT NULL,
    telPac VARCHAR(16),
    tipoPac VARCHAR(15),
    carrerPac VARCHAR(60),
    numeroPac INT,
    plantaPac INT,
    portaPac VARCHAR(10),
    ciutatPac VARCHAR(30),
    codiPostalPac int,
    iexPac int UNIQUE NOT NULL,
    PRIMARY KEY(nifPac)
);

create table metges(
casaObloqueMet BOOLEAN,
    nomMet VARCHAR(15),
    primerCognomMet VARCHAR(20),
    segonCognomMet VARCHAR(20),
    numSegSocialMet VARCHAR(12)UNIQUE NOT NULL,
    nifMet VARCHAR(9) UNIQUE NOT NULL,
    telMet VARCHAR(16),
    numEmpleatMet int UNIQUE NOT NULL,
    salariMensualMet int,
    codiCompteCorrentMet VARCHAR(50),
    tipoMet VARCHAR(15),
    carrerMet VARCHAR(60),
    numeroMet INT,
    plantaMet INT,
    portaMet VARCHAR(10),
    ciutatMet VARCHAR(30),
    codiPostalMet int,   
    PRIMARY KEY(nifMet)
);

create table visites(
    codi INT auto_increment,
    fecha DATETIME,
    codiMalaltia INT,
    nomMet VARCHAR(15),
    nifPac VARCHAR(9),
    orden Int,
    PRIMARY KEY(codi),
    FOREIGN KEY (nifPac) REFERENCES Pacients(nifPac),
    FOREIGN KEY (codiMalaltia) REFERENCES malalties(codi),
    FOREIGN KEY (nomMet) REFERENCES METGES(nomMet)
);

create table malalties(
    codi int,
    nom VARCHAR(15),
    tractament VARCHAR(80), 
    causaBaixa boolean, 
    duradaTractament INT(14),
    PRIMARY KEY(codi)
);

create table adreca(
    idAdreca INT auto_increment,
    tipo VARCHAR(15),
    carrer VARCHAR(60),
    numero INT,
    planta INT,
    porta VARCHAR(10),
    ciutat VARCHAR(30),
    codiPostal int,
    PRIMARY KEY(idAdreca)
);

DROP FUNCTION IF EXISTS aPacient;
DELIMITER // 
CREATE FUNCTION aPacient(NcasaObloquePac BOOLEAN,NnomPac VARCHAR(15),NprimerCognomPac VARCHAR(20),NsegonCognomPac VARCHAR(20),
NnumSegSocialPac VARCHAR(12),NtelPac VARCHAR(16),NtipoPac VARCHAR(15),NcarrerPac VARCHAR(60),NnumeroPac INT,
NplantaPac INT,NportaPac VARCHAR(10),NciutatPac VARCHAR(30),NcodiPostalPac int) RETURNS BOOL
BEGIN 
    UPDATE hospital_grup5.PACIENTS SET 
casaObloquePac =NcasaObloquePac,
    nomPac=NnomPac ,
    primerCognomPac =NprimerCognomPac,
    segonCognomPac =NsegonCognomPac,
    numSegSocialPac =NnumSegSocialPac,    
    telPac =NtelPac,
    tipoPac =NtipoPac,
    carrerPac =NcarrerPac,
    numeroPac =NnumeroPac,
    plantaPac =NplantaPac,
    portaPac =NportaPac,
    ciutatPac =NciutatPac,
    codiPostalPac =NcodiPostalPac       
    WHERE nifPac = NnifPac;
    RETURN TRUE;
END;
// 
DELIMITER ;

GRANT EXECUTE ON FUNCTION hospital_grup5.aPacient TO usuario_hospital_grup5;



DROP FUNCTION IF EXISTS aMetge;
DELIMITER // 
CREATE FUNCTION aMetge(NcasaObloqueMet BOOLEAN,NnomMet VARCHAR(15),NprimerCognomMet VARCHAR(20),NsegonCognomMet VARCHAR(20),
NnumSegSocialMet VARCHAR(12),NtelMet VARCHAR(16),NnumEmpleatMet int,NsalariMensualMet int,NcodiCompteCorrentMet VARCHAR(50), NtipoMet VARCHAR(15),
    NcarrerMet VARCHAR(60), NnumeroMet INT, NplantaMet INT, NportaMet VARCHAR(10),NciutatMet VARCHAR(30), NcodiPostalMet int) RETURNS BOOL

BEGIN 
    UPDATE hospital_grup5.METGES SET 
       casaObloqueMet =NcasaObloqueMet,
    nomMet =NnomMet,
    primerCognomMet =NprimerCognomMet,
    segonCognomMet =NsegonCognomMet,
    numSegSocialMet =NnumSegSocialMet,    
    telMet =NtelMet,
    numEmpleatMet =NnumEmpleatMet,
    salariMensualMet =NsalariMensualMet,
    codiCompteCorrentMet =NcodiCompteCorrentMet,
    tipoMet =NtipoMet,
    carrerMet =NcarrerMet,
    numeroMet =NnumeroMet,
    plantaMet =NplantaMet,
    portaMet =NportaMet,
    ciutatMet =NciutatMet,
    codiPostalMet =NcodiPostalMet,   
    WHERE nifMet = NnifMet;
    RETURN TRUE;
END;
// 
DELIMITER ;

GRANT EXECUTE ON FUNCTION hospital_grup5.actualizaMetge TO usuario_hospital_grup5;



DROP FUNCTION IF EXISTS aMalaltia;
DELIMITER // 
CREATE FUNCTION aMalaltia(Ncodi int, Nnom VARCHAR(15),
   Ntractament VARCHAR(80), NcausaBaixa boolean, NduradaTractament INT(14)) RETURNS BOOL

BEGIN 
    UPDATE hospital_grup5.MALALTIES SET 
        nom = Nnom,
        causaBaixa = NCausaBaixa,
        tractament = Ntractament, 
        duradaTractament = NduradaTractament
    WHERE codi = Ncodi;
    RETURN TRUE;
END;
// 
DELIMITER ;

GRANT EXECUTE ON FUNCTION hospital_grup5.aMalaltia TO usuario_hospital_grup5;

INSERT INTO pacients VALUES(true, 'Cubi', 'Lemocan', 'Resat', '041985550627', '22222222J', '+33993231387', 'Carretera', 'Basetnasci sutnu', 10, 0, null, 'Tumas', 03245,0);
INSERT INTO metges VALUES(false, 'Bergasso', 'Grande', 'Pill', '281234567840', '05413374W', '+34969491580',0, 1500, '0123456789', 'Carrer', 'Atlanta', 2, 3, 'C', 'Sabadell', 03245);
INSERT INTO visites VALUES();
INSERT INTO malalties VALUES(0,'refredat', 'Caldo de pollo\nBisolgrip\nBivaporux', false, 8);
INSERT INTO adreca VALUES("Avenida", "Atlanta", 32,0,null, "Barcelona", 03245);