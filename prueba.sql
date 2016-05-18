



--
-- nombre de la base: 		prueba
-- usuario de la base : 	postgres 
-- contrasña de la base: 	12345
--

CREATE TABLE USUARIO(
	--llave primaria 
	id_usuario SERIAL primary key,
	--- atributos 
	u_nombre character varying(32), --- nombre completo 
	u_apellido character varying(32),
	u_telefono character varying(32),
	u_contrasenha character varying(32) ,
	u_correo character varying(32) unique,
	u_calificaficacion integer ,
	u_numero_calificacion integer
);


CREATE TABLE LIBRO(
	-- llave primaria
	id_libro SERIAL primary key,
	-- atribtutos
	l_titulo character varying(32), --- nombre completo 
	l_autor character varying(32) ,
	l_editorial character varying(32) ,
	l_isbn character varying(32) ,
	l_anho character varying(32) ,
	l_edicion character varying(32) ,
	l_evalucion_contenido character varying(32) ,
	l_evaluacion_redaccion character varying(32) ,
	l_resehna character varying(32) ,
	l_pablas_clave character varying(32) ,
	l_foto character varying(32) ,
	l_mapa_ubicacion character varying(32) ,
	l_oferta boolean,
	 -- llave foraneas
	 id_usuario integer references USUARIO( id_usuario )
);

CREATE TABLE PRESTAMO(
	-- llave primaria
	id_prestamo SERIAL primary key,
	--- atributos
	p_activo boolean,
	-- llaves foraneas 
	
	
	id_libro integer references LIBRO (id_libro),
	id_usuario integer references USUARIO(id_usuario)
	
);

CREATE TABLE INTERCAMBIO(
	id_intercambio SERIAL primary key,
	-- llaves foraneas 
	id_libro integer references LIBRO ( id_libro ),
	id_prestamo integer references PRESTAMO (id_prestamo)

);


