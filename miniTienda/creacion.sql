	create sequence secuencia_ids start 1 increment 1;
	create table usuarios(
		id integer default nextval('secuencia_ids') not null,
		dni char(9) not null,
		nombre varchar(50) not null,
		apellido1 varchar(50) not null,
		apellido2 varchar(50) not null,
		contrasena varchar(20) not null,
		genero varchar(10),
		fecha_nacimiento Date,
		correo varchar(50),
		telefono char(9),
		color_favorito char(7),
		primary key (dni)
	);
