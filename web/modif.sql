-- Table: public.tachedistrict

-- DROP TABLE public.tachedistrict;

CREATE TABLE public.tachedistrict
(
  idtachedistrict bigint NOT NULL,
  libelle text,
  responsable character varying,
  cout double precision,
  m1 boolean,
  m2 boolean,
  m3 boolean,
  m4 boolean,
  m5 boolean,
  m6 boolean,
  m7 boolean,
  m8 boolean,
  m9 boolean,
  m10 boolean,
  m11 boolean,
  m12 boolean,
  idactivitestructure bigint,
  idannee integer,
  CONSTRAINT pk_tachedistrict PRIMARY KEY (idtachedistrict),
  CONSTRAINT fk_activite_tachedistrict FOREIGN KEY (idactivitestructure)
      REFERENCES public.activite_structure (idactivite_structure) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_annee_tachedistrict FOREIGN KEY (idannee)
      REFERENCES public.annee (idannee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tachedistrict
  OWNER TO postgres;

-- Index: public.fki_activite_tachedistrict

-- DROP INDEX public.fki_activite_tachedistrict;

CREATE INDEX fki_activite_tachedistrict
  ON public.tachedistrict
  USING btree
  (idactivitestructure);

-- Index: public.fki_annee_tachedistrict

-- DROP INDEX public.fki_annee_tachedistrict;

CREATE INDEX fki_annee_tachedistrict
  ON public.tachedistrict
  USING btree
  (idannee);

-----------------------------------------------------------------------------------------------------------------

-- Table: public.tacheregion

-- DROP TABLE public.tacheregion;

CREATE TABLE public.tacheregion
(
  idtacheregion bigint NOT NULL,
  libelle character varying,
  cout double precision,
  responsable character varying,
  m1 boolean,
  m2 boolean,
  m3 boolean,
  m4 boolean,
  m5 boolean,
  m6 boolean,
  m7 boolean,
  m8 boolean,
  m9 boolean,
  m10 boolean,
  m11 boolean,
  m12 boolean,
  idactivitestructure bigint,
  idannee integer,
  CONSTRAINT pk_tacheregion PRIMARY KEY (idtacheregion),
  CONSTRAINT fk_activite_tacheregion FOREIGN KEY (idactivitestructure)
      REFERENCES public.activite_structure_region (idactivite_structure_region) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_annee_tacheregion FOREIGN KEY (idannee)
      REFERENCES public.annee (idannee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tacheregion
  OWNER TO postgres;

-----------------------------------------------------------------------------------------------------------------------

-- Column: valeurrealisee

-- ALTER TABLE public.cible DROP COLUMN valeurrealisee;

ALTER TABLE public.cible ADD COLUMN valeurrealisee double precision;

-- Column: commentaire

-- ALTER TABLE public.cible DROP COLUMN commentaire;

ALTER TABLE public.cible ADD COLUMN commentaire text;


-- Column: recommandation

-- ALTER TABLE public.cible DROP COLUMN recommandation;

ALTER TABLE public.cible ADD COLUMN recommandation text;


-- Column: etat

-- ALTER TABLE public.cible DROP COLUMN etat;

ALTER TABLE public.cible ADD COLUMN etat boolean;


ALTER TABLE public.cible ADD COLUMN ecart integer;

-- Column: evaluee

-- ALTER TABLE public.cible DROP COLUMN evaluee;

ALTER TABLE public.cible ADD COLUMN evaluee boolean;
ALTER TABLE public.cible ALTER COLUMN evaluee SET DEFAULT false;


----------------------------------------------------------------------------------------------------------------------


-- Column: valeurrealisee

-- ALTER TABLE public.cible_region DROP COLUMN valeurrealisee;

ALTER TABLE public.cible_region ADD COLUMN valeurrealisee double precision;


-- Column: commentaire

-- ALTER TABLE public.cible_region DROP COLUMN commentaire;

ALTER TABLE public.cible_region ADD COLUMN commentaire text;

-- Column: recommandation

-- ALTER TABLE public.cible_region DROP COLUMN recommandation;

ALTER TABLE public.cible_region ADD COLUMN recommandation text;


-- Column: etat

-- ALTER TABLE public.cible_region DROP COLUMN etat;

ALTER TABLE public.cible_region ADD COLUMN etat boolean;



-- Table: public.niveauactivite

-- DROP TABLE public.niveauactivite;

CREATE TABLE public.niveauactivite
(
  idniveauactivite integer NOT NULL,
  nom character varying,
  numero integer,
  CONSTRAINT pk_niveauactivite PRIMARY KEY (idniveauactivite)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.niveauactivite
  OWNER TO postgres;


-- Column: idniveauactivite

-- ALTER TABLE public.tacheregion DROP COLUMN idniveauactivite;

ALTER TABLE public.tacheregion ADD COLUMN idniveauactivite integer;


-- Column: idniveauactivite

-- ALTER TABLE public.tachedistrict DROP COLUMN idniveauactivite;

ALTER TABLE public.tachedistrict ADD COLUMN idniveauactivite integer;


-- Column: observation

-- ALTER TABLE public.tachedistrict DROP COLUMN observation;

ALTER TABLE public.tachedistrict ADD COLUMN observation text;


-- Foreign Key: public.fk_niveauactivite_tachedistrict

-- ALTER TABLE public.tachedistrict DROP CONSTRAINT fk_niveauactivite_tachedistrict;

ALTER TABLE public.tachedistrict
  ADD CONSTRAINT fk_niveauactivite_tachedistrict FOREIGN KEY (idniveauactivite)
      REFERENCES public.niveauactivite (idniveauactivite) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;


INSERT INTO niveauactivite (idniveauactivite, nom, numero) VALUES (1, '-', 0);
INSERT INTO niveauactivite (idniveauactivite, nom, numero) VALUES (2, 'Non débuté', 1);
INSERT INTO niveauactivite (idniveauactivite, nom, numero) VALUES (3, 'Partiel', 2);
INSERT INTO niveauactivite (idniveauactivite, nom, numero) VALUES (4, 'Terminé', 3);


-- Table: public.partiehaute_structure

-- DROP TABLE public.partiehaute_structure;

CREATE TABLE public.partiehaute_structure
(
  idpartiehaute_structure integer NOT NULL,
  introdution text,
  descriptionstructure text,
  idstructure integer,
  CONSTRAINT partiehaute_structure_pkey PRIMARY KEY (idpartiehaute_structure),
  CONSTRAINT partiehaute_structure_idstructure_fkey FOREIGN KEY (idstructure)
      REFERENCES public.structure (idstructure) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.partiehaute_structure
  OWNER TO postgres;



-- Table: public.ciblevaleur

-- DROP TABLE public.ciblevaleur;

CREATE TABLE public.ciblevaleur
(
  idciblevaleur bigint NOT NULL,
  idcible bigint,
  valeurcible double precision,
  valeurrealisee double precision,
  commentaire text,
  recommandation text,
  etat boolean,
  ecart double precision,
  evaluee boolean,
  idperiode integer,
  CONSTRAINT pk_ciblevaleur PRIMARY KEY (idciblevaleur),
  CONSTRAINT fk_cibledistrict_ciblevaleur FOREIGN KEY (idcible)
      REFERENCES public.cible (idcible) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_periode_ciblevaleur FOREIGN KEY (idperiode)
      REFERENCES public.periodederattachement (idperiodederattachement) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ciblevaleur
  OWNER TO postgres;



-- Table: public.cible_region_valeur

-- DROP TABLE public.cible_region_valeur;

CREATE TABLE public.cible_region_valeur
(
  idcible_region_valeur bigint NOT NULL,
  idcible_region bigint,
  idperiode integer,
  valeurcible double precision,
  valeurrealisee double precision,
  ecart double precision,
  commentaire text,
  recommandation text,
  evaluee boolean,
  CONSTRAINT pk_cible_region_valeur PRIMARY KEY (idcible_region_valeur),
  CONSTRAINT fk_cibleregion_cible_region_valeur FOREIGN KEY (idcible_region)
      REFERENCES public.cible_region (idcible_region) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_periode_cible_region_valeur FOREIGN KEY (idperiode)
      REFERENCES public.periodederattachement (idperiodederattachement) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cible_region_valeur
  OWNER TO postgres;



-- Table: public.tachedistrict_periode

-- DROP TABLE public.tachedistrict_periode;

CREATE TABLE public.tachedistrict_periode
(
  idtachedistrict_periode bigint NOT NULL,
  idtachedistrict bigint,
  idniveauactivite integer,
  idperiode integer,
  observation text,
  CONSTRAINT pk_tachedistrict_periode PRIMARY KEY (idtachedistrict_periode),
  CONSTRAINT fk_niveauactivite_tachedistrict_periode FOREIGN KEY (idniveauactivite)
      REFERENCES public.niveauactivite (idniveauactivite) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_periode_tachedistrict_periode FOREIGN KEY (idperiode)
      REFERENCES public.periodederattachement (idperiodederattachement) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tachedistrict_tachedistrict_periode FOREIGN KEY (idtachedistrict)
      REFERENCES public.tachedistrict (idtachedistrict) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tachedistrict_periode
  OWNER TO postgres;



-- Table: public.tacheregion_periode

-- DROP TABLE public.tacheregion_periode;

CREATE TABLE public.tacheregion_periode
(
  idtacheregion_periode bigint NOT NULL,
  idtacheregion bigint,
  idperiode integer,
  idniveauactivite integer,
  CONSTRAINT pk_tacheregion_periode PRIMARY KEY (idtacheregion_periode),
  CONSTRAINT fk_niveauactivite_tacheregion_periode FOREIGN KEY (idniveauactivite)
      REFERENCES public.niveauactivite (idniveauactivite) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_periode_tacheregion_periode FOREIGN KEY (idperiode)
      REFERENCES public.periodederattachement (idperiodederattachement) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tacheregion_tacheregion_periode FOREIGN KEY (idtacheregion)
      REFERENCES public.tacheregion (idtacheregion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tacheregion_periode
  OWNER TO postgres;



-- Table: public.chronogramme_tache_district

-- DROP TABLE public.chronogramme_tache_district;

CREATE TABLE public.chronogramme_tache_district
(
  idchronogramme_tache_district bigint NOT NULL,
  idtachedistrict bigint,
  idtachedistrict_periode bigint,
  idperiode integer,
  CONSTRAINT pk_chronogramme_tache_district PRIMARY KEY (idchronogramme_tache_district),
  CONSTRAINT fk_periode_chronogramme_tache_district FOREIGN KEY (idperiode)
      REFERENCES public.periode (idperiode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tache_district_periode_chronogramme_tache_district FOREIGN KEY (idtachedistrict_periode)
      REFERENCES public.tachedistrict_periode (idtachedistrict_periode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tachedistrict_chronogramme_tache_district FOREIGN KEY (idtachedistrict)
      REFERENCES public.tachedistrict (idtachedistrict) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.chronogramme_tache_district
  OWNER TO postgres;



-- Table: public.chronogramme_tache_region

-- DROP TABLE public.chronogramme_tache_region;

CREATE TABLE public.chronogramme_tache_region
(
  idchronogramme_tache_region bigint NOT NULL,
  idtacheregion bigint,
  idperiode integer,
  idtacheregion_periode bigint,
  CONSTRAINT pk_chronogramme_tache_region PRIMARY KEY (idchronogramme_tache_region),
  CONSTRAINT fk_periode_chronogramme_tache_region FOREIGN KEY (idperiode)
      REFERENCES public.periode (idperiode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tacheregion_chronogramme_tache_region FOREIGN KEY (idtacheregion)
      REFERENCES public.tacheregion (idtacheregion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tacheregionperiode_chronogramme_tache_region FOREIGN KEY (idtacheregion_periode)
      REFERENCES public.tacheregion_periode (idtacheregion_periode) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.chronogramme_tache_region
  OWNER TO postgres;



-- Column: code

-- ALTER TABLE public.periodederattachement DROP COLUMN code;

ALTER TABLE public.periodederattachement ADD COLUMN code character varying(15);



