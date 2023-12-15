CREATE TABLE Account (
    ID SERIAL PRIMARY KEY,
    Nom VARCHAR(100),
    Solde DECIMAL(15, 2),
    Date_maj_solde TIMESTAMP,
    Type VARCHAR (50),
    Devise_ID INT REFERENCES Devise(ID),
);