\c cnab;

create table transaction (
                                    value double precision,
                                    code bigint not null,
                                    id bigint primary key not null default nextval('transaction_id_seq'::regclass),
                                    card_number character varying(255),
                                    cpf character varying(255),
                                    date character varying(255),
                                    hour character varying(255),
                                    name_store character varying(255),
                                    onwner_store character varying(255),
                                    foreign key (code) references transaction_type (id)
                                        match simple on update no action on delete no action
);
create unique index transaction_name_store_key on transaction using btree (name_store);
create index idx_transaction_namestore on transaction using btree (name_store);

create table transaction_type (
                                         id bigint primary key not null default nextval('transaction_type_id_seq'::regclass),
                                         code character varying(255),
                                         description character varying(255),
                                         nature character varying(255),
                                         signal character varying(255)
);



alter table transaction
    owner to postgres;



-- INSERT

INSERT INTO transaction_type (code, description, nature, signal) VALUES ('1', 'Débito', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('2', 'Boleto', 'Saída', '-');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('3', 'Financiamento', 'Saída', '-');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('4', 'Crédito', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('5', 'Recebimento Empréstimo', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('6', 'Vendas', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('7', 'Recebimento TED', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('8', 'Recebimento DOC', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES ('9', 'Aluguel', 'Saída', '-');

