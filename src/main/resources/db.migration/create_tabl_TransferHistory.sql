CREATE TABLE TransferHistory (
    ID SERIAL PRIMARY KEY,
    Transfer_ID INT REFERENCES Transaction(ID),
    Date_transfert TIMESTAMP
);