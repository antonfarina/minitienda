create table usuarios(
	nombre varchar(50) not null,
	apellido1 varchar(50) not null,
	apellido2 varchar(50) not null,
	correo varchar(50) not null,
	direccion varchar(100) not null,
	telefono char(9),
	tarjeta char(16) not null,
	tipo char(7),
	primary key (correo)
);

create sequence secuencia_pedidos start 1 increment 1;
create table pedidos(
	numero integer default nextval('secuencia_pedidos') not null,
	usuario varchar(50) not null,
	precio integer not null,
	narticulos integer not null,
	foreign key(usuario) references usuarios(correo) ON DELETE CASCADE ON UPDATE CASCADE,
	primary key(usuario,numero)
);
