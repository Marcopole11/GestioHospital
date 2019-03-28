/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  miral
 * Created: Mar 28, 2019
 */
CREATE DATABASE hospital;
CREATE USER 'admin_hospital'@'127.0.0.1' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON hospital.* TO 'admin_hospital'@'127.0.0.1';
FLUSH PRIVILEGES;
USE hospital;

create table pacients(
    casaObloquePac BOOLEAN,
    nomPac VARCHAR(15),
    primerCognomPac VARCHAR(20),
    segonCognomPac VARCHAR(20),
    numSegSocialPac VARCHAR(12),
    nifPac VARCHAR(9),
    telPac VARCHAR(16),
    tipoPac VARCHAR(15),
    carrerPac VARCHAR(60),
    numeroPac INT,
    plantaPac INT,
    portaPac VARCHAR(10),
    ciutatPac VARCHAR(30),
    codiPostalPac int,
    iexPac int,
    PRIMARY KEY(nifPac)
);

create table metges(
casaObloqueMet BOOLEAN,
    nomMet VARCHAR(15),
    primerCognomMet VARCHAR(20),
    segonCognomMet VARCHAR(20),
    numSegSocialMet VARCHAR(12),
    nifMet VARCHAR(9),
    telMet VARCHAR(16),
    numEmpleatMet int,
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
    dia int,
    mes int,  
    anyV int, 
    hora double
);

create table malalties(
    codi int,
    nom VARCHAR(15),
    tractament VARCHAR(80), 
    causaBaixa boolean, 
    duradaTractament INT(14),
    PRIMARY KEY(codi)
);

INSERT INTO pacients VALUES(true, 'Cubi', 'Lemocan', 'Resat', '041985550627', '22222222J', '+33993231387', 'Carretera', 'Basetnasci sutnu', 10, 0, null, 'Tumas', 03245,0);
INSERT INTO metges VALUES(false, 'Bergasso', 'Grande', 'Pill', '281234567840', '05413374W', '+34969491580',0, 1500, '0123456789', 'Carrer', 'Atlanta', 2, 3, 'C', 'Sabadell', 03245);
INSERT INTO visites VALUES();
INSERT INTO malalties VALUES(0,'refredat', 'Caldo de pollo\nBisolgrip\nBivaporux', false, 8);