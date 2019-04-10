/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  miral
 * Created: Mar 28, 2019
 */
DROP DATABASE IF EXISTS hospital_grup5;
CREATE DATABASE hospital_grup5;
CREATE USER IF NOT EXISTS 'admin_hospita_grup5'@'127.0.0.1' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON hospital_grup5.* TO 'admin_hospital'@'127.0.0.1';
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

create table malalties(
    codi int,
    nom VARCHAR(15),
    tractament VARCHAR(80), 
    causaBaixa boolean, 
    duradaTractament INT(14),
    PRIMARY KEY(codi)
);

create table visites(
    codi INT auto_increment,
    fecha DATETIME,
    codiMalaltia INT,
    nomMet VARCHAR(15),
    nifPac VARCHAR(9),
    orden Int,
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

INSERT INTO pacients VALUES(true, 'Cubi', 'Lemocan', 'Resat', '041985550627', '22222222J', '+33 993231387', 'Carretera', 'Basetnasci sutnu', 10, 0, null, 'Tumas', 03245,0);
INSERT INTO metges VALUES(false, 'Bergasso', 'Grande', 'Pill', '281234567840', '05413374W', '+34 969491580',0, 1500, '0123456789', 'Carrer', 'Atlanta', 2, 3, 'C', 'Sabadell', 03245);
INSERT INTO malalties VALUES(0,'refredat', 'Caldo de pollo\nBisolgrip\nBivaporux', false, 8);
INSERT INTO adreca (tipo, carrer, numero,planta,porta,ciutat,codiPostal)VALUES("Avenida", "Atlanta", 32,0,null, "Barcelona", 03245);