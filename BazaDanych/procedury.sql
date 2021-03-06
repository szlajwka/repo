use AplikacjeSklepy
go
Create view wszystkieProdukty As 
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'ZestawPC' as typ from ZestawPC
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'Ram' as typ from Ram
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'Procesor' as typ from dbo.Procesor
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'KartaGraficzna' as typ from KartaGraficzna
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'Monitor' as typ from Monitor
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'Peryferia' as typ from Peryferia
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'Naped' as typ from Naped
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'DyskTwardy' as typ from DyskTwardy
Union
Select id, stan_mag,cena_netto,cena_brutto,model,waga,producent, 'PlytaGlowna' as typ from PlytaGlowna
go
create procedure wszystko as begin
select * from wszystkieProdukty inner join producent on Producent.id=wszystkieprodukty.producent
end
go
create procedure wyczerpane as begin
select id, typ,cena_netto from  wszystkieprodukty where stan_mag=0
end
go
create procedure dodaj @id int, @ilosc int, @tabela varchar(50) as begin
declare @staraIlosc int
if @tabela='ZestawPc'
begin
select @staraIlosc=stan_mag from ZestawPc where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update ZestawPC set stan_mag=@staraIlosc where id=@id
end
if @tabela='Monitor'
begin
select @staraIlosc=stan_mag from Monitor where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update Monitor set stan_mag=@staraIlosc where id=@id
end
if @tabela='Peryferia'
begin
select @staraIlosc=stan_mag from Peryferia where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update Peryferia set stan_mag=@staraIlosc where id=@id
end
if @tabela='Procesor'
begin
select @staraIlosc=stan_mag from Procesor where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update Procesor set stan_mag=@staraIlosc where id=@id
end
if @tabela='DyskTwardy'
begin
select @staraIlosc=stan_mag from DyskTwardy where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update DyskTwardy set stan_mag=@staraIlosc where id=@id
end
if @tabela='Naped'
begin
select @staraIlosc=stan_mag from Naped where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update Naped set stan_mag=@staraIlosc where id=@id
end
if @tabela='PlytaGlowna'
begin
select @staraIlosc=stan_mag from PlytaGlowna where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update PlytaGlowna set stan_mag=@staraIlosc where id=@id
end
if @tabela='Ram'
begin
select @staraIlosc=stan_mag from Ram where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update Ram set stan_mag=@staraIlosc where id=@id
end
if @tabela='KartaGraficzna'
begin
select @staraIlosc=stan_mag from KartaGraficzna where id=@id
set @staraIlosc=@staraIlosc+@ilosc
update KartaGraficzna set stan_mag=@staraIlosc where id=@id
end
end
go
create procedure getKartaGraficzna as begin
select * from KartaGraficzna inner join Producent on KartaGraficzna.producent=Producent.Id
end
go
create procedure getProcesor as begin
select * from Procesor inner join Producent on Procesor.producent=Producent.Id
end
go
create procedure getMonitor as begin
select * from Monitor inner join Producent on Monitor.producent=Producent.Id
end
go
create procedure getPeryferia as begin
select * from Peryferia inner join Producent on Peryferia.producent=Producent.Id
end
go
create procedure getNaped as begin
select * from Naped inner join Producent on Naped.producent=Producent.Id
end
go
create procedure getRam as begin
select * from Ram inner join Producent on Ram.producent=Producent.Id
end
go
create procedure getDyskTwardy as begin
select * from DyskTwardy inner join Producent on DyskTwardy.producent=Producent.Id
end
go
create procedure getPlytaGlowna as begin
select * from PlytaGlowna inner join Producent on PlytaGlowna.producent=Producent.Id
end
go
create view zestawPCview as
select ZestawPC.id,ZestawPC.cena_netto,ZestawPC.cena_brutto,ZestawPC.waga,ZestawPC.model,ZestawPC.stan_mag,Naped.Model as ModelNapedu,Procesor.model as ModelProcesora,Ram.model as ModelRam, kartagraficzna.model as ModelKarty, dyskTwardy.model as ModelDysku, Monitor.model as modelMonitora,PlytaGlowna.model as ModelPlyty, producent.nazwa as nazwaProducenta from ZestawPC inner join Naped on ZestawPC.Naped=Naped.ID inner join Procesor on ZestawPC.Procesor=Procesor.ID inner join Ram on ZestawPc.ram=Ram.id inner join KartaGraficzna on KartaGraficzna.id=ZestawPC.karta_graficzna inner join DyskTwardy on ZestawPC.dysk_twardy=DyskTwardy.id inner join monitor on monitor.id=ZestawPc.monitor inner join producent on ZestawPC.producent=producent.id inner join plytaGlowna on plytaglowna.id=ZestawPc.plyta_glowna
go
create procedure getZestawPc as begin
select * from zestawpcview
end
go
create procedure getItem @id int as begin
select wszystkieprodukty.id,stan_mag,cena_netto,cena_brutto,model,waga, producent, Producent.nazwa as NazwaProducenta from wszystkieprodukty inner join Producent on wszystkieprodukty.producent=Producent.id where wszystkieprodukty.id=@id
end
go
create procedure getItemProducent @id int as begin
declare @idprod int
select @idprod=producent from wszystkieprodukty where id=@id
select nazwa,id from producent where id=@idprod
end 
go
create procedure getZamowienie @nrklienta int as begin
declare @id int
set @id=@nrklienta
select id,data_zamowienia from zamowienie where idklienta=@id
end
go
create procedure getSzczegoly @idzamowienia int as begin
select model, typ, producent.nazwa from wszystkieprodukty inner join producent on producent.id=wszystkieprodukty.producent inner join szczegoly on wszystkieprodukty.id=szczegoly.produkt_id where szczegoly.zamowienie=@idzamowienia
end
go
create procedure getKoszt @idzamowienia int as begin
select koszt_calkowity from koszt inner join szczegoly on koszt.szczegoly_id=szczegoly.id where szczegoly.zamowienie=@idzamowienia
end
go
create procedure checkLogin @email varchar(50),@haslo varchar(50) as begin
select * from Klient where @email=email and @haslo=haslo
end
go
create trigger rejestracja on Klient instead of insert as 
begin
declare @i int
select @i=count(Klient.email) from Klient inner join inserted on Klient.email=inserted.email
if @i>0
begin
RAISERROR (15600,-1,-1, 'klient o takim mailu juz istnieje');
end
if @i=0
begin
declare @email varchar(50)
declare @haslo varchar(50)
declare @ulica varchar(50)
declare @nazwa varchar(50)
declare @nr varchar(50)
declare @kod varchar(10)
declare @miejscowosc varchar(50)
Select @email=email,@haslo=haslo,@ulica=ulica,@nazwa=nazwa,@nr=nr_domu,@kod=kod_pocztowy,@miejscowosc=miejscowosc from inserted
insert into Klient values(@nazwa,@email,@haslo,@ulica,@nr,@kod,@miejscowosc)
end
end
go
create procedure addKlient @nazwa varchar(50),@email varchar(50),@haslo varchar(50),@ulica varchar(50),@nr varchar(50),@kod varchar(50), @miejscowosc varchar(50) as 
begin
insert into Klient values(@nazwa,@email,@haslo,@ulica,@nr,@kod,@miejscowosc)
end
go
create procedure getIdfromEmail @email varchar(50) as
begin
select id from Klient where email=@email
end
go
create procedure getKlient @id int as
begin
select * from Klient where id=@id
end
