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
    telPac VARCHAR(12),
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
    telMet VARCHAR(12),
    tipoMet VARCHAR(15),
    carrerMet VARCHAR(60),
    numeroMet INT,
    plantaMet INT,
    portaMet VARCHAR(10),
    ciutatMet VARCHAR(30),
    codiPostalMet int,
    numEmpleatMet int,
    salariMensualMet int,
    codiCompteCorrentMet VARCHAR(50),
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
  tractament VARCHAR(30), 
 causaBaixa boolean, 
 duradaTractament INT(14),
PRIMARY KEY(codi)
);

INSERT INTO pacients VALUES();
INSERT INTO metges VALUES();
INSERT INTO visites VALUES();
INSERT INTO malalties VALUES();