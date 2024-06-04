create table usuarios (
UUID int primary key,
nombre varchar2 (50),
contraseña varchar2 (50)
)

create table ticket (
numeroticket int primary key,
tituloticket varchar2 (50),
descripcion varchar2 (50),
autor varchar2 (50),
email varchar2 (50),
fechacreacion varchar2 (50),
estado varchar2 (50),
fechafinalizacion varchar2 (50)
)