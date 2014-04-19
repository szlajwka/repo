
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'AplikacjeSklepy')

BEGIN 

--Take database offline ignoring any connection made

ALTER DATABASE [AplikacjeSklepy] 

SET OFFLINE 

WITH ROLLBACK IMMEDIATE;



--Bring online before delete 

ALTER DATABASE [AplikacjeSklepy]

SET ONLINE;



DROP DATABASE [AplikacjeSklepy]; 

END 
