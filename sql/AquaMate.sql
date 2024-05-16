CREATE DATABASE AquaMate;
USE AquaMate;
ALTER DATABASE AquaMate CHARACTER SET utf8;

CREATE TABLE Usuario (
	ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Dados_Usuario (
	ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Meta_Manual (
	ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Meta_Automatica (
	ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Registro_Consumo (
	ID INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Usuario
    	ADD COLUMN Email VARCHAR (255) UNIQUE,
    	ADD COLUMN Senha VARCHAR (10);

ALTER TABLE Dados_Usuario
	ADD COLUMN Data_Nascimento DATE,
    	ADD COLUMN Apelido VARCHAR(255),
    	ADD COLUMN Peso FLOAT,
    	ADD COLUMN Idade INT,
    	ADD COLUMN Altura FLOAT,
    	ADD COLUMN Tipo_Meta BOOLEAN,

	ADD COLUMN ID_Usuario INT,
    	ADD FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID);

ALTER TABLE Registro_Consumo
	ADD COLUMN Data_Registro DATE,
    	ADD COLUMN Quantidade_Consumida INT,
    	ADD COLUMN Percentual_Atingido INT,
    	ADD COLUMN Streak INT,
    
    	ADD COLUMN ID_DadosUsuario INT,    
	ADD FOREIGN KEY (ID_DadosUsuario) REFERENCES Dados_Usuario(ID);

ALTER TABLE Meta_Manual
	ADD COLUMN Meta_Manual INT,
    
	ADD COLUMN ID_DadosUsuario INT,
    	ADD FOREIGN KEY (ID_DadosUsuario) REFERENCES Dados_Usuario(ID);

ALTER TABLE Meta_Automatica
	ADD COLUMN Meta_Automatica INT,
    
    	ADD COLUMN ID_DadosUsuario INT,
    	ADD FOREIGN KEY (ID_DadosUsuario) REFERENCES Dados_Usuario(ID);
