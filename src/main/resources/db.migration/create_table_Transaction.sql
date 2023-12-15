CREATE TABLE Transaction (
    ID SERIAL PRIMARY KEY,
    Label VARCHAR(100),
    Montant DECIMAL(15, 2),
    Date_heure TIMESTAMP,
    type VARCHAR (50),
    Compte_ID INT REFERENCES Compte(ID)
);