
   INSERT INTO HOTEL (ID_HOTEL, NAME, ADDRESS,STAR_RATING) VALUES
  (1001, 'HotelOne', 'Thailand' ,5),
  (1002, 'HotelTwo', 'China' ,2),
  (1003, 'HotelThree', 'China' ,4),
  (1004, 'HotelFour', 'UK' ,4.5);

   INSERT INTO BOOKING (ID, CUSTOMER_NAME,CUSTOMER_SURNAME,NUMBER_OF_PAX,CURRENCY,PRICE_AMOUNT,ID_HOTEL) VALUES
  (1001, 'TOM', 'Ebron' ,5,'EU',200.00,1001),
  (1002, 'Rosalie', 'Ebron' ,2,'USD',200.00,1001),
  (1003, 'Karleen', 'Mcnamee' ,2,'THB',200.00,1002),
  (1004, 'Jutta', 'Hatton' ,1,'THB',200.00,1003),
  (1005, 'Marilyn', 'Hatton' ,4,'USD',200.00,1003),
  (1006, ' Lauson', 'Hen' ,3,'EU',200.00,1004);