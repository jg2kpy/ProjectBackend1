CREATE TABLE public.persona
(
    id_persona integer NOT NULL,
    apellido character varying(50) NOT NULL,
    nombre character varying(50) NOT NULL,
    CONSTRAINT persona_pkey PRIMARY KEY (id_persona)
);
CREATE SEQUENCE public.persona_sec;
CREATE TABLE public.agenda
(
    id_agenda integer NOT NULL,
    fecha date NOT NULL,
    id_persona integer NOT NULL,
    actividad character varying(200) NOT NULL,
    CONSTRAINT agenda_pkey PRIMARY KEY (id_agenda),
    CONSTRAINT fk_1 FOREIGN KEY (id_persona)
        REFERENCES public.persona (id_persona) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE SEQUENCE public.agenda_sec;