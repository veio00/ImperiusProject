create table Acesso(
idAcesso int primary key identity(1,1),
Descricao_Acesso varchar(100),
Nome_Acesso varchar(15)
)

create table Grupo(
idGrupo int primary key identity(1,1),
Nome_grupo varchar(150)
)

create table Cliente(
idCliente int primary key identity(1,1),
Nome varchar(100),
Email varchar(100),
Senha varchar(6),
Acesso_Cliente int foreign key references Acesso(idAcesso),
Grupo_Cliente int foreign key references Grupo(idGrupo),
)

Create table Maquina(
idMaquina int primary key identity(1,1),
Responsavel varchar(50),
Nome_Maquina varchar(30),
Adquirida varchar(20),
Data_Compra varchar(10), 
Sistema varchar(20),
Keep_Alive int,
Grupo_Cliente int foreign key references Grupo(idGrupo)
)

create table Processador(
idCpu int primary key identity(1,1),
Modelo varchar(100),
Maquina_Cpu int foreign key references Maquina(idMaquina)
)

create table Memoria(
idMemoria int primary key identity(1,1),
Qtd int,
Geracao varchar(4),
Maquina_Memoria int foreign key references Maquina(idMaquina)
)

create table Disco(
idDisco int primary key identity(1,1),
Espaco int ,
Marca varchar(100),
N_Discos int,
Maquina_Disco int foreign key references Maquina(idMaquina)
)

create table Leitura(
idLeitura int primary key identity(1,1),
Hd int,
Mram int,
Cpu int,
Data varchar(20),
Maquina_Uso int foreign key references Maquina(idMaquina)
)


create table Logss(
idLogs int primary key identity(1,1),
Data Varchar(20),
Msg varchar(280),
Leitura_Logs int foreign key references Leitura(idLeitura)
)

create table Aviso(
idAviso int primary key identity(1,1),
NomeAviso varchar(200),
AvisoI1 int,
AvisoI2 int,
AvisoI3 int,
AvisoF1 int,
AvisoF2 int,
AvisoF3 int,
Maquina_Aviso int foreign key references Maquina(idMaquina)
)
insert into Aviso values('personalizado',50,50,50,50,50,50,11)
update Aviso set NomeAviso='outroteste', AvisoI1=0,AvisoI2=0,AvisoI3=0,AvisoF1=0,AvisoF2=0,AvisoF3=0 where idAviso=1
select * from Aviso
delete from aviso

select * from Maquina inner join grupo on idgrupo = Grupo_Cliente inner join Aviso on Maquina_Aviso=idMaquina where Grupo_Cliente =1

select Min(Email) as Email from Logss lo inner join Leitura  l on l.idLeitura=lo.Leitura_Logs inner join Maquina m on m.idMAquina=l.Maquina_Uso inner join grupo g on g.idGrupo = m.Grupo_Cliente inner join cliente c on c.Grupo_Cliente=m.Grupo_Cliente where idLogs = 1

/*select * from Grupo*/
select * from Maquina inner join grupo on idgrupo = Grupo_Cliente where Grupo_Cliente = 40
select * from leitura where idLeitura=(select max(idleitura) from leitura where Maquina_Uso = 1)
select * from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina
select idCliente,Acesso_Cliente,Grupo_Cliente from Cliente where Email = 'chewbacca@nubeliu.com.br' and Senha = '123456'
select idGrupo from grupo inner join Cliente on idGrupo = Grupo_Cliente where Email = 'Wilian@Mathias.com.br'
select idMaquina as codigo, Nome_Maquina, Responsavel, Adquirida, Modelo as Modelo_CPU,Qtd as Memoria_Total, Espaco as Tamanho_HD from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina where Grupo_Cliente=2
select idLogs, Msg as Mensagem, Hd as Status_HD, Mram as Status_Ram, Cpu as Status_CPU, l.Data as Data_Ocorr�ncia from Logss l inner join Leitura le on idLeitura = Leitura_Logs inner join maquina m on idmaquina = Maquina_uso where Grupo_Cliente=1
select idlogs, Msg, Leitura_Logs from logss inner join leitura on idLeitura = Leitura_Logs inner join Maquina on idMaquina = Maquina_Uso where Grupo_Cliente = 2

insert into Acesso(Descricao_Acesso,Nome_Acesso) values('acesso total', 'Administrador')
insert into Acesso(Descricao_Acesso,Nome_Acesso) values('acesso com restri��o', 'Cliente')

insert into Grupo values('Imperius')
insert into Grupo values('Logicalis')

insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Carlos Vinicius','carlos.macedo@impeirus.com.br','123456',2,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Wilian Mathias','wilian.gomes@nubeliu.com.br','123456',1,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Adrelayne ','adreline.souza@impeirus.com.br','123456',2,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('chewbacca','chewbacca@impeirus.com.br','123456',2,1)

insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc0',getdate(),'N/D','Microsoft',1,1)
insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc1',getdate(),'N/D','Microsoft',0,1)
insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc2',getdate(),'N/D','Microsoft',1,1)
insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc3',getdate(),'N/D','Microsoft',0,1)
insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc4',getdate(),'N/D','Microsoft',1,1)
insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Data_Compra,Sistema,Keep_Alive,Grupo_Cliente) values('Will','Teste-pc5',getdate(),'N/D','Microsoft',0,1)

insert into Leitura(Hd,Mram,Cpu,data,Maquina_Uso) values(44,80,66,'25-12-2000',1)
insert into Leitura(Hd,Mram,Cpu,data,Maquina_Uso) values(44,80,66,'25-12-2000',2)

insert into Processador values('core i3',1)
insert into Processador values('core i3',2)

insert into Memoria(Qtd,Geracao,Maquina_Memoria) values (2048,'ddr3',1)
insert into Memoria(Qtd,Geracao,Maquina_Memoria) values (2048,'ddr3',2)

insert into Disco(Espaco,Marca,N_Discos,Maquina_Disco) values (248000,'seagate', 1 , 1)
insert into Disco(Espaco,Marca,N_Discos,Maquina_Disco) values (248000,'seagate', 1 , 2)

insert into Logss(Data,Msg,Leitura_Logs) values(GetDate(),'Teste de insert no banco',1)
insert into Logss(Data,Msg,Leitura_Logs) values('26/5/2018 10:16','m',1)

select idCliente,Nome,Email,Senha,Nome_Acesso,Nome_grupo from Cliente inner join Acesso on Acesso_Cliente=idAcesso inner join grupo on Grupo_Cliente=idGrupo where idCliente=9
update cliente set Nome='chewbacca',Email='chewbacca@nubeliu.com.br',Senha='123456',Acesso_Cliente=2,Grupo_Cliente=2 where idCliente=12
select * from acesso
select * from grupo
select * from cliente
select max(idGrupo) as idGrupo from grupo
select max(idLeitura) as idLeitura from Leitura
select max(idLogs) as idLogs from Logss


select * from Processador 
select * from Memoria
select * from Disco
delete from maquina where idMAquina = 7
delete from leitura where Maquina_Uso = 7
delete from logss where Leitura_Logs in (select idLeitura from Maquina inner join Leitura on idMaquina = Maquina_Uso where idMAquina = 7)
delete from Aviso where idAviso in (select idAviso from Maquina inner join Aviso on idMaquina = Maquina_Aviso where idMAquina = 7)
delete from Processador where Maquina_Cpu = 7
delete from Memoria where Maquina_Memoria = 7
delete from Disco where Maquina_Disco = 7


select * from aviso

select idMaquina as Codigo, Responsavel, Data_Compra as Instalada, Sistema, Espaco, Qtd as Qtd_Memoria , Modelo as Processador from Maquina 
inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina 
inner join Disco on Maquina_Disco = idMaquina  where Grupo_Cliente=1

select idMaquina as Codigo, Responsavel, Data_Compra as Instalada, Sistema, Hd, Mram as Memoria , Cpu as CPU, data as Leitura from Maquina 
inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina 
inner join Disco on Maquina_Disco = idMaquina inner join Leitura on idMaquina=Maquina_Uso where idMaquina = 2 
select idLeitura as Codigo, hd as HD, Mram as Memoria, cpu as CPU, Data  from leitura inner join Maquina on idMaquina=Maquina_Uso where Maquina_Uso = 1 and Grupo_Cliente=1
select * from maquina 
select * from Processador
select * from Memoria
select * from Disco
select * from leitura order by idLeitura desc
select * from cliente
select * from logss
select * from grupo