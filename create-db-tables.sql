CREATE TABLE IF NOT EXISTS account (
    id serial PRIMARY KEY,
    ip varchar(45)
);

CREATE TABLE IF NOT EXISTS language (
    id serial PRIMARY KEY,
    lang_code varchar(10) UNIQUE
);

CREATE TABLE IF NOT EXISTS translate (
    id serial PRIMARY KEY,
    user_id integer REFERENCES account (id),
    source_lang_id integer REFERENCES language (id),
    target_lang_id integer REFERENCES language (id),
    source_text varchar,
    translated_text varchar
);