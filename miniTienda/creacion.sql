create sequence secuencia_ids start 1 increment 1;
create table usuarios(
	id integer default nextval('secuencia_ids') not null,
	nombre varchar(50) not null,
	apellido1 varchar(50) not null,
	apellido2 varchar(50) not null,
	correo varchar(50) not null,
	telefono char(9),
	tarjeta char(16) not null,
	tipo char(7),
	primary key (id)
);

create table pedidos(
	usuario integer not null,
	precio integer not null,
	foreign key(usuario) references usuarios(id),
	primary key(usuario,precio)
);
