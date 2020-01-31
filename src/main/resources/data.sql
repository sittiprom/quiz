
   INSERT INTO HOTEL (ID_HOTEL, NAME, ADDRESS,STAR_RATING) VALUES
  (1001, 'HotelOne', 'Thailand' ,5),
  (1002, 'HotelTwo', 'China' ,2),
  (1003, 'HotelThree', 'China' ,4),
  (1004, 'HotelFour', 'UK' ,4.5),
  (1005, 'HotelEight', 'UK' ,4.5);

   INSERT INTO BOOKING (ID, CUSTOMER_NAME,CUSTOMER_SURNAME,NUMBER_OF_PAX,CURRENCY,PRICE_AMOUNT,ID_HOTEL) VALUES
  (1001, 'TOM', 'Ebron' ,5,'THB',200.00,1005),
  (1002, 'Rosalie', 'Ebron' ,2,'THB',100.00,1005),
  (1003, 'Karleen', 'Mcnamee' ,2,'THB',500.00,1005),
  (1004, 'Jutta', 'Hatton' ,1,'THB',250.00,1005),
  (1005, 'Marilyn', 'Hatton' ,4,'THB',400.00,1005),
  (1006, ' Lauson', 'Hen' ,3,'THB',600.00,1005),
  (1007, 'Tomas', 'Ebron' ,5,'THB',300.00,1005),
  (1008, 'Adams', 'Baker' ,5,'THB',490.00,1005),

  (1009, 'TOM', 'Ebron' ,5,'USD',2000.00,1005),
  (1010, 'Rosalie', 'Ebron' ,2,'USD',3000.00,1005),
  (1011, 'Karleen', 'Mcnamee' ,2,'USD',5000.00,1005),
  (1012, 'Jutta', 'Hatton' ,1,'USD',2500.00,1005),
  (1013, 'Marilyn', 'Hatton' ,4,'USD',400.00,1005),
  (1014, ' Lauson', 'Hen' ,3,'USD',1600.00,1005),
  (1015, 'Tomas', 'Ebron' ,5,'USD',300.00,1005),
  (1016, 'Adams', 'Baker' ,5,'USD',490.00,1005),

  (1017, 'TOM', 'Ebron' ,5,'EUR',5000.00,1005),
  (1018, 'Rosalie', 'Ebron' ,2,'EUR',7000.00,1005),
  (1019, 'Karleen', 'Mcnamee' ,2,'EUR',8000.00,1005),
  (1020, 'Jutta', 'Hatton' ,1,'EUR',250.00,1005),
  (1021, 'Marilyn', 'Hatton' ,4,'EUR',700.00,1005),
  (1022, ' Lauson', 'Hen' ,3,'EUR',900.00,1005),
  (1023, 'Tomas', 'Ebron' ,5,'EUR',300.00,1005),
  (1024, 'Adams', 'Baker' ,5,'EUR',8000.00,1005);