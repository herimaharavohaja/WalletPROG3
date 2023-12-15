CREATE TABLE CurrencyValue (
    ID SERIAL PRIMARY KEY,
    Devise_Source_ID INT REFERENCES Devise(ID),
    Devise_Destination_ID INT REFERENCES Devise(ID),
    Montant DECIMAL(15, 2),
    Date_effet TIMESTAMP
);