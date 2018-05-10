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
Adquirida varchar(10),
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
Data varchar(10),
Maquina_Uso int foreign key references Maquina(idMaquina)
)


create table Logss(
idLogs int primary key identity(1,1),
Data date,
Msg varchar(280),
Leitura_Logs int foreign key references Leitura(idLeitura)
)


/*select * from Grupo*/
select * from Maquina inner join grupo on idgrupo = Grupo_Cliente where Grupo_Cliente = 1
select * from leitura where idLeitura=(select max(idleitura) from leitura where Maquina_Uso = 1)
select * from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina
select idCliente,Acesso_Cliente,Grupo_Cliente from Cliente where Email = 'veio@nubeliu.com.br' and Senha = '123456'
select idGrupo from grupo inner join Cliente on idGrupo = Grupo_Cliente where Email = 'bebe@nubeliu.com.br'
select idMaquina as codigo, Responsavel, Data_Compra, Sistema, Hd, Mram, Cpu, data as Data_Leitura from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina inner join Leitura on idMaquina=Maquina_Uso where idMaquina = 1 and Grupo_Cliente=1



insert into Acesso(Descricao_Acesso,Nome_Acesso) values('So os picas usam', 'Administrador')
insert into Acesso(Descricao_Acesso,Nome_Acesso) values('O resto', 'Cliente')

insert into Grupo values('Imperius')
insert into Grupo values('Logicalis')

insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Carlos Vinicius','bebe@nubeliu.com.br','123456',2,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Wilian Mathias','veio@nubeliu.com.br','123456',1,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('Adrelayne ','dodoi@nubeliu.com.br','123456',2,1)
insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('chewbacca','chewbacca@nubeliu.com.br','123456',2,2)

insert into Maquina values('Will','Teste-pc0',getdate(),'25-12-2000','Microsoft',1,1)
insert into Maquina values('Will','Teste-pc1',getdate(),'25-12-2000','Microsoft',0,1)
insert into Maquina values('Will','Teste-pc2',getdate(),'25-12-2000','Microsoft',1,1)
insert into Maquina values('Will','Teste-pc3',getdate(),'25-12-2000','Microsoft',0,1)
insert into Maquina values('Will','Teste-pc4',getdate(),'25-12-2000','Microsoft',1,1)
insert into Maquina values('Will','Teste-pc5',getdate(),'25-12-2000','Microsoft',0,1)

insert into Leitura(Hd,Mram,Cpu,data,Maquina_Uso) values(44,80,66,'25-12-2000',1)

insert into Processador values('core i3',1)

insert into Memoria(Qtd,Geracao,Maquina_Memoria) values (2048,'ddr3',1)

insert into Disco(Espaco,Marca,N_Discos,Maquina_Disco) values (248000,'seagate', 1 , 1)

select max(idMAquina) from Maquina

select * from Processador 
select * from Memoria
select * from Disco
delete from maquina where idMAquina = 41
delete from leitura where Maquina_Uso = 41
delete from Processador where Maquina_Cpu = 41
delete from Memoria where Maquina_Memoria = 41
delete from Disco where Maquina_Disco = 41
