--
-- nombre de la base: 		libros
-- usuario de la base : 	postgres 
-- contrasña de la base: 	12345
--
CREATE TABLE USUARIO(

	id_usuario SERIAL primary key,	--llave primaria
	u_nombre character (32), -- nombre 
	u_apellido character (32),	--apellidos
	u_telefono character (32),	--teléfono
	u_contrasenha character (32) ,	--contraseña
	u_correo character (32) unique,	--correo
	u_calificaficacion integer ,	--calificación del usuario
	u_numero_calificacion integer	--número de calificaciones
);


CREATE TABLE LIBRO(

	id_libro SERIAL primary key,		-- llave primaria
	l_titulo character (32),		-- nombre completo 
	l_autor character (32) ,		--autor
	l_editorial character (32) ,	--editorial
	l_isbn character (32) ,		--isbn
	l_anho character (32) ,		--año
	n_edicion integer ,					--edicion
	l_evalucion_contenido integer ,		--evaluación de contenido	
	l_evaluacion_redaccion integer ,	--evaluación de redacción
	l_resehna character (1024) ,	--reseña
	l_pablas_clave character (100) ,	--palabras clave
	l_foto character (32) ,		---foto
	l_latitud double precision,			--Latitud
	l_longitud double precision,		--Longitud
	l_oferta boolean,					--está en oferta
	 id_usuario integer references USUARIO( id_usuario )	--usuario que ofrece el libro
);

CREATE TABLE PRESTAMO(

	id_prestamo SERIAL primary key,		--llave primaria
	p_activo boolean,					--prestamo activo
	id_libro integer references LIBRO (id_libro),	--idlibro
	id_usuario_prestador integer references USUARIO(id_usuario),	--idusuario (propietario del libro solicitado)
	id_usuario_solicitante integer references USUARIO(id_usuario)	--idusuario (solicitante)
	
);

CREATE TABLE INTERCAMBIO(
	id_intercambio SERIAL primary key,		--idintercambio
	id_libro integer references LIBRO ( id_libro ),	--idlibro que se intercambia
	id_prestamo integer references PRESTAMO (id_prestamo)	--iddelpréstamo

);