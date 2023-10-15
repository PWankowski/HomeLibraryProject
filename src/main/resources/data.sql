INSERT INTO customer( uuid, name, surname, age, sex, email_address) VALUES
   ( 'b1ca1df8-2fbc-11ee-be56-0242ac120002', 'Jacek', 'Bąk', 20, 'M', 'bąk123@gmail.com'),
   ( 'b80f5872-2fbc-11ee-be56-0242ac120001', 'Paweł', 'Zalewski', 45, 'M', 'zalew12@gmail.com'),
   ( 'bc203512-2fbc-11ee-be56-0242ac120003', 'Anna', 'Kogut', 34, 'F', 'kukuryku@gmail.com'),
   ( 'bfa25bde-2fbc-11ee-be56-0242ac120004', 'Katarzyna', 'Wrzęsicka', 18, 'F', 'brząszcz@gmail.com')
;

INSERT INTO book_details(id, isbn, lang, publisher) VALUES
   (100001, '0001', 'EN', 'WhiteBook'),
   (100002, '0002', 'EN', 'BlackBook'),
   (100003, '0003', 'EN', 'BlueBook'),
   (100004, '0004', 'EN', 'RedBook')
;
INSERT INTO book_tag(id, tag_Value) VALUES
   (100010, 'lorem'),
   (100020, 'ipsum'),
   (100030, 'dolor'),
   (100040, 'consectetur'),
   (100050, 'adipiscing'),
   (100060,'maximus'),
   (100070, 'semper'),
   (100080, 'adipiscing')
;

INSERT INTO book (id, uuid, title, author, details_id) VALUES
    (100100, '19f83664-e6fe-46d0-a79f-b240e6a319c7', 'The Lord Of The Rings', 'J.R.R. Tolkien', 100001),
    (100200, '2fe15345-8154-4322-b3d0-62c667b6382a', '1984', 'George Orwell', 100002),
    (100300, '2fe15345-8154-4322-b3d0-62c667b6112c', '2020', 'George Orwell', 100003),
    (100400, '3ec642a5-016f-46b1-a1bd-ac0f12bddc8a', 'The Hitchhikers Guide To The Galaxy', 'Douglas Adams', 100004)
;

INSERT INTO books_tags(book_id, tag_id) VALUES
    (100100,100020),
    (100100,100010),
    (100100,100060),
    (100200,100030),
    (100200,100010),
    (100200,100040),
    (100300,100020),
    (100300,100050),
    (100300,100080),
    (100400,100030),
    (100400,100070),
    (100400,100080)
;