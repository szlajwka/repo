Create database AplikacjeSklepy 
GO
USE AplikacjeSklepy
GO
Create table Klient(
Id int primary key identity(1,1),
nazwa varchar(50) not null,
email varchar(50) not null,
haslo varchar(50) not null,
ulica varchar(50) not null,
nr_domu varchar(50) not null,
kod_pocztowy varchar(10) not null,
miejscowosc varchar(50) not null
);
GO
Create table Zamowienie(
Id int primary key identity(1,1),
data_zamowienia datetime not null,
IdKlienta int references Klient(Id) not null
);
GO
Create table Szczegoly(
Id int primary key identity(1,1),
produkt_id int not null,
Zamowienie int references Zamowienie(Id) not null
);
Go
Create table Koszt(
Id int primary key identity(1,1),
szczegoly_id int references Szczegoly(Id) not null,
koszt_netto int not null,
koszt_brutto int not null,
znizka int not null,
koszt_dostawy int not null,
waga_zamowienia int not null,
koszt_calkowity int not null
);
GO
Create table Hurtownia(
Id int primary key identity(1,1),
id_produktu int not null,
cena int not null,
ilosc_sztuk int not null
);
GO
Create table Producent(
Id int primary key identity(1,1),
nazwa varchar(50) not null,
kraj varchar(20),
specjalizacja varchar(50)
);
Create table Peryferia(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
typ varchar(50) not null,
stan_mag int not null,
producent int references Producent(Id)
);
Go 
Create table PlytaGlowna(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
gniazdo_procesora varchar(50) not null,
chipset varchar(50) not null,
rodzaj_pamieci varchar(50) not null,
karta_graficzna varchar(50) not null,
karta_sieciowa varchar(50) not null,
stan_mag int not null,
producent int references Producent(Id)
);
Go
Create table KartaGraficzna(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
taktowanie_rdzenia varchar(50) not null,
taktowanie_pamieci varchar(50) not null,
wielkosc_pamieci int not null,
producent_chipsetu varchar(50) not null,
stan_mag int not null,
producent int references Producent(Id)
);
Go
Create table Ram(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
rodzaj varchar(50) not null,
czestotliwosc_pracy int not null,
przepustowosc int not null,
stan_mag int not null,
producent int references Producent(Id)
);
Go
Create table Procesor(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
ilosc_rdzeni int not null,
typ_gniazda varchar(50) not null,
czestotliwosc_taktowania int not null,
stan_mag int not null,
producent int references Producent(Id)
);
Go
Create table Naped(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
typ varchar(50) not null,
interfejs varchar(50) not null,
stan_mag int not null,
producent int references Producent(Id)
);
GO
Create table Monitor(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
typ varchar(50) not null,
przekatna int not null,
rodzaj_podswietlenia varchar(50) not null,
rozdzielczosc varchar(20) not null,
stan_mag int not null,
producent int references Producent(Id)
);
GO
Create table DyskTwardy(
Id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
typ varchar(50) not null,
szerokosc int not null,
interfejs varchar(50) not null,
predkosc_obrotowa int not null,
stan_mag int not null,
producent int references Producent(Id)
);
Create table ZestawPC(
id int primary key identity(1,1),
cena_netto int not null,
cena_brutto int not null,
waga int not null,
model varchar(50) not null,
stan_mag int not null,
naped int references Naped(Id),
procesor int references Procesor(Id),
ram int references Ram(Id),
karta_graficzna int references KartaGraficzna(Id),
plyta_glowna int references PlytaGlowna(Id),
dysk_twardy int references DyskTwardy(Id),
monitor int references Monitor(Id),
producent int references Producent(Id)
);