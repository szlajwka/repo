Create view wszystkieProdukty As 
Select id, stan_mag,cena_netto, 'ZestawPC' as typ from ZestawPC
Union
Select id, stan_mag,cena_netto, 'Ram' as typ from Ram
Union
Select id, stan_mag,cena_netto, 'Procesor' as typ from dbo.Procesor
Union
Select id, stan_mag,cena_netto, 'KartaGraficzna' as typ from KartaGraficzna
Union
Select id, stan_mag,cena_netto, 'Monitor' as typ from Monitor
Union
Select id, stan_mag,cena_netto, 'Peryferia' as typ from Peryferia
Union
Select id, stan_mag,cena_netto, 'Naped' as typ from Naped
Union
Select id, stan_mag,cena_netto, 'DyskTwardy' as typ from DyskTwardy
Union
Select id, stan_mag,cena_netto, 'PlytaGlowna' as typ from PlytaGlowna

create procedure wyczerpane as begin
select id, typ,cena_netto from  wszystkieprodukty where stan_mag=0
end

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
