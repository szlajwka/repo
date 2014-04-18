USE AplikacjeSklepy;
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Samsung', 'Korea Po³udniowa', 'DyskTwardy'); 
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Samsung', 'Korea Po³udniowa', 'Monitor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Samsung', 'Korea Po³udniowa', 'Naped'); 
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Kingston', 'USA', 'DyskTwardy');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('WD', 'USA', 'DyskTwardy');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Seagate', 'USA', 'DyskTwardy');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Gigabyte', 'Tajwan', 'KartaGraficzna'); 
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('XFX', 'USA', 'KartaGraficzna'); 
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Dell', 'USA', 'Monitor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('LG', 'Korea Po³udniowa', 'Monitor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Transcend', 'Tajwan', 'Naped');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Asus', 'Tajwan', 'Naped');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Asus', 'Tajwan', 'PlytaGlowna');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('AMD', 'USA', 'Procesor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Intel', 'USA', 'Procesor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Toshiba', 'Japonia', 'Procesor');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('Kingston', 'USA', 'Ram');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES ('GoodRam', 'USA', 'Ram');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES('Microsoft', 'USA', 'Peryferja');
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES('GoodRam', 'USA', 'Ram');                          
INSERT INTo dbo.Producent( nazwa, kraj, specjalizacja ) VALUES('GoodRam', 'USA', 'Ram');
 
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (327, 407, 850, 'DDR3', 'wewnetrzny', 3000, 3.5, 'Serial ATA', 7200, 1, 1);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (150, 180, 910, 'DDR3', 'magnetyczny', 512, 3.5, 'Serial ATA', 5400, 5, 4);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (155, 185, 910, 'DDR3', 'magnetyczny', 512, 2.5, 'Serial ATA', 5400, 6,4);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (100, 120, 750, 'DDR3', 'wewnetrzny', 240, 1.8, 'Serial ATA', 5400, 2, 5);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (120, 144, 860, 'DDR3', 'magnetyczny', 320, 2.5, 'Serial ATA', 5400, 8, 5);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (130, 156, 860, 'DDR3', 'wewnetrzny', 320, 2.5, 'Serial ATA', 5400, 9, 6);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (290, 348, 660, 'DDR3', 'wewnetrzny', 512, 2.5, 'Serial ATA', 5400, 3, 2);
INSERT INTO dbo.DyskTwardy (cena_netto, cena_brutto, waga, model, typ, pojemnosc, szerokosc, interfejs, predkosc_obrotowa, stan_mag, producent) VALUES (500, 600, 960, 'DDR3', 'wewnetrzny', 3000, 3.5, 'Serial ATA', 7200, 2, 6);

INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (700, 840, 550, 'Radeon HD 7770', 1050, 4500, 1024, 'AMD', 2, 7);
INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (990, 1188, 720, 'Quadro 400', 860, 4800, 1024, 'NVIDIA', 5, 8);
INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (450, 540, 600, 'Radeon HD 7750', 1050, 4500, 1024, 'AMD', 1, 7);
INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (320, 384, 650, 'Radeon HD 8000', 860, 4800, 1024, 'AMD', 3, 8);
INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (200, 240, 550, 'Radeon HD 4350', 900,1620 , 2048 , 'AMD', 9, 7);
INSERT INTO dbo.KartaGraficzna(cena_netto, cena_brutto, waga, model, taktowanie_rdzenia, taktowanie_pamieci, wielkosc_pamieci, producent_chipsetu, stan_mag, producent) VALUES (1000, 1200, 880, 'Quadero 770', 900, 1620 , 2048 , 'AMD', 12, 8);

INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (1069, 1282, 4000, '920N', 'GH19LS', 24, 'LED', '1920 x 1200', 2, 1);
INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (500, 600, 5000, '94B', 'GH19LS', 24, 'LED', '1920 x 1080', 2, 9);
INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (450, 540, 4000, '9N', 'GH19LS', 19.5, 'LED', '1366 x 768', 2, 1);
INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (1069, 1282, 4000, '92TG', 'GH18LS', 18.5, 'LED', '1920 x 1200', 2, 9);
INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (320, 384, 4000, 'P2214H', 'GH18LS', 21.5, 'LED', '1920 x 1200', 2, 10);                                                                              
INSERT INTO dbo.Monitor( cena_netto, cena_brutto, waga, model, typ, przekatna, rodzaj_podswietlenia, rozdzielczosc, stan_mag, producent) VALUES (1069, 1282, 4000, '920N', 'GH20LS', 24, 'LED', '1366 x 768', 2, 1);
	
INSERT INTO dbo.Naped (cena_netto,cena_brutto, waga, model, typ, interfejs, stan_mag, producent) VALUES (320, 384, 380, 'AT95', 'DVD+/-RW slim', 'USB 2.0', 2, 11); 
INSERT INTO dbo.Naped (cena_netto,cena_brutto, waga, model, typ, interfejs, stan_mag, producent)VALUES (120, 144, 400, ' XJ-HD165H', 'External Slim DVD±R/RW', 'USB 2.0', 5, 3); 
INSERT INTO dbo.Naped (cena_netto,cena_brutto, waga, model, typ, interfejs, stan_mag, producent)VALUES (320, 384, 350, ' XJ-HD160H', 'DVD+/-RW', 'USB 2.0', 1, 11); 
INSERT INTO dbo.Naped (cena_netto,cena_brutto, waga, model, typ, interfejs, stan_mag, producent)VALUES (100, 120, 290, 'GCA-4164B', 'DVD+/-RW slim', 'USB 2.0', 8, 12);
INSERT INTO dbo.Naped (cena_netto,cena_brutto, waga, model, typ, interfejs, stan_mag, producent)VALUES (320, 384, 300, 'GCA-4050B', 'DVD+/-RW', 'USB 2.0', 3,11);
	   
INSERT INTO dbo.Peryferia (cena_netto, cena_brutto, waga, rodzaj, model, typ, stan_mag, producent) VALUES (50, 60, 57, 'myszka', 'Wireless Mobile Mouse 2000', 'optyczna', 2, 19);
INSERT INTO dbo.Peryferia (cena_netto, cena_brutto, waga, rodzaj, model, typ, stan_mag, producent) VALUES (40, 48, 57, 'myszka', 'Wireless Mouse M175', 'optyczna', 3,16);
INSERT INTO dbo.Peryferia (cena_netto, cena_brutto, waga, rodzaj, model, typ, stan_mag, producent) VALUES (299, 359, 640, 'klawiatura', 'Ergo 4000', 'multimedialna', 2, 19);
INSERT INTO dbo.Peryferia (cena_netto, cena_brutto, waga, rodzaj, model, typ, stan_mag, producent) VALUES (100, 120, 650, 'klawiatura', ' WIRELESS 2000', 'multimedialna', 4, 19);
INSERT INTO dbo.Peryferia (cena_netto, cena_brutto, waga, rodzaj, model, typ, stan_mag, producent) VALUES (65, 78, 2000, 'drukarka', 'C 4180', 'atramentowa', 2, 16);

INSERT INTO dbo.PlytaGlowna (cena_netto, cena_brutto, waga, model, gniazdo_procesora, chipset, rodzaj_pamieci, karta_graficzna, karta_sieciowa, stan_mag, producent) VALUES (660, 792, 380, 'Z87-PRO', 'Socket 1150', 'Intel Z87', 'DDR3','KartaGraficzna','Gigabit LAN Intel I217V', 2,13);
INSERT INTO dbo.PlytaGlowna (cena_netto, cena_brutto, waga, model, gniazdo_procesora, chipset, rodzaj_pamieci, karta_graficzna, karta_sieciowa, stan_mag, producent) VALUES (229, 275, 350, 'H81M-PLUS', 'Socket 1150', 'Intel H81','DDR3', 'KartaGraficzna2','Gigabit LAN', 6, 13);
INSERT INTO dbo.PlytaGlowna (cena_netto, cena_brutto, waga, model, gniazdo_procesora, chipset, rodzaj_pamieci, karta_graficzna, karta_sieciowa, stan_mag, producent) VALUES (1170, 1404, 350, 'MAXIMUS VI FORMULA', 'Socket 1150','DDR3', 'Intel Z87', 'KartaGraficzna', 'Gigabit LAN', 1, 13);
	
INSERT INTO dbo.Procesor (cena_netto, cena_brutto, waga, model, ilosc_rdzeni, typ_gniazda, czestotliwosc_taktowania, stan_mag, producent) VALUES (270, 324, 7, '531', 4, 'Socket FM1', 3000, 1, 14);
INSERT INTO dbo.Procesor (cena_netto, cena_brutto, waga, model, ilosc_rdzeni, typ_gniazda, czestotliwosc_taktowania, stan_mag, producent) VALUES (320, 384, 7, '641', 4, 'Socket FM1', 2800, 3, 14);
INSERT INTO dbo.Procesor (cena_netto, cena_brutto, waga, model, ilosc_rdzeni, typ_gniazda, czestotliwosc_taktowania, stan_mag, producent) VALUES (450, 540, 7, '3220', 2, 'Socket 1155', 3300, 1, 15);
INSERT INTO dbo.Procesor (cena_netto, cena_brutto, waga, model, ilosc_rdzeni, typ_gniazda, czestotliwosc_taktowania, stan_mag, producent) VALUES (270, 324, 7, '220', 4, 'Socket FM1', 3000, 4, 16);
INSERT INTO dbo.Procesor (cena_netto, cena_brutto, waga, model, ilosc_rdzeni, typ_gniazda, czestotliwosc_taktowania, stan_mag, producent) VALUES (320, 384, 7, '3225', 2, 'Socket 1155', 3400, 2, 15);
	
INSERT INTO dbo.Ram ( cena_netto, cena_brutto, waga, model, rodzaj, czestotliwosc_pracy, przepustowosc, stan_mag, producent) VALUES (443, 531, 26, '667','SO-DIMM', 667, 5300, 2, 17);
INSERT INTO dbo.Ram ( cena_netto, cena_brutto, waga, model, rodzaj, czestotliwosc_pracy, przepustowosc, stan_mag, producent) VALUES (65, 78, 24, '514','DDR2 DIMM', 667, 5300, 3, 18);
INSERT INTO dbo.Hurtownia values (4,200,10,'Procesor','Hurtownia1');
INSERT INTO dbo.Hurtownia values (5,280,10,'Naped','Hurtownia2');
INSERT INTO dbo.Hurtownia values (3,300,10,'Naped','Hurtownia5');
INSERT INTO dbo.Hurtownia values (6,800,10,'KartaGraficzna','Hurtownia2');
INSERT INTO dbo.Hurtownia values (5,500,5,'KartaGraficzna','Hurtownia2');
INSERT INTO dbo.Ram ( cena_netto, cena_brutto, waga, model, rodzaj, czestotliwosc_pracy, przepustowosc, stan_mag, producent) VALUES (65, 78, 24, '514','DDR2 DIMM', 667, 5300, 0, 18);
