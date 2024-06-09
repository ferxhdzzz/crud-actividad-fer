create table usuariosss (
UUID varchar2 (50),
nombre varchar2 (50),
contraseña varchar2 (50)
)
 

create table ticket (
numeroticket varchar2 (50),
tituloticket varchar2 (50),
descripcion varchar2 (50),
autor varchar2 (50),
email varchar2 (50),
fechacreacion varchar2 (50),
estado varchar2 (50),
fechafinalizacion varchar2 (50)
)
delete ticket
 commit;

select*from ticket;

insert into ticket (numeroticket, tituloticket, descripcion, autor, email, fechacreacion, estado, fechafinalizacion) values('ff', 'ff', 'ff', 'ff', 'ff', 'ff', 'ff', 'ff');
drop tab