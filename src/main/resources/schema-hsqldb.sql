CREATE SEQUENCE session_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE session ADD COLUMN id numeric DEFAULT nextval(‘session_id_seq’);
ALTER SEQUENCE session_id_seq OWNED BY session.id;