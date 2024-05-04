CREATE DATABASE AquaMate;
USE AquaMate;
ALTER DATABASE AquaMate CHARACTER SET utf8;

CREATE TABLE Usuario (
	Usuario_ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Dados_Usuario (
	Dados_Usuario_ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Meta_Manual (
	Meta_Manual_ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Meta_Automatica (
	Meta_Automatica_ID INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Registro_Consumo (
	Registro_Consumo_ID INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Usuario
	ADD COLUMN Nome VARCHAR (255),
    	ADD COLUMN Email VARCHAR (255) UNIQUE,
    	ADD COLUMN Senha VARCHAR (10),
    
    	ADD COLUMN Dados_Usuario_ID INT,    
    	ADD FOREIGN KEY (Dados_Usuario_ID) REFERENCES Dados_Usuario(Dados_Usuario_ID);
    
ALTER TABLE Dados_Usuario
	ADD COLUMN Data_Nascimento DATE,
    	ADD COLUMN Apelido VARCHAR(255),
    	ADD COLUMN Telefone VARCHAR(12),
    	ADD COLUMN Peso_Inicial DECIMAL(5,2),
    	ADD COLUMN Peso_Atual DECIMAL(5,2),
    	ADD COLUMN Idade INT,
    	ADD COLUMN Altura INT,
    	ADD COLUMN Tipo_Meta ENUM('Automática', 'Manual');
    
ALTER TABLE Registro_Consumo
	ADD COLUMN Data_Registro DATE,
    	ADD COLUMN Quantidade_Consumida INT,
    	ADD COLUMN Percentual_Atingido INT,
    	ADD COLUMN Streak INT,
    
    	ADD COLUMN Usuario_ID INT,    
	ADD FOREIGN KEY (Usuario_ID) REFERENCES Usuario(Usuario_ID);
    
ALTER TABLE Meta_Manual
	ADD COLUMN Meta_Manual INT,
    
    	ADD COLUMN Dados_Usuario_ID INT,    
    	ADD FOREIGN KEY (Dados_Usuario_ID) REFERENCES Dados_Usuario(Dados_Usuario_ID);

ALTER TABLE Meta_Automatica
	ADD COLUMN Meta_Automatica INT,
    
    	ADD COLUMN Dados_Usuario_ID INT,    
    	ADD FOREIGN KEY (Dados_Usuario_ID) REFERENCES Dados_Usuario(Dados_Usuario_ID);
