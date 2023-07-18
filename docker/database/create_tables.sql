\c cnab;

create table transaction_type_cnab
(
    id          bigserial
        primary key,
    code        bigint not null,
    description varchar(255),
    nature      varchar(255),
    signal      varchar(255)
);

alter table transaction_type_cnab
    owner to postgres;

create table transaction_cnab
(
    value_cnab        double precision,
    code         bigint not null
        constraint transaction_cnab_transaction_type_cnab_id_fk
            references transaction_type_cnab,
    id           bigserial
        primary key,
    card_number  varchar(255),
    cpf          varchar(255),
    date         varchar(255),
    hour_cnab         varchar(255),
    name_store   varchar(255),
    onwner_store varchar(255)
);

alter table transaction_cnab
    owner to postgres;

create index idx_transaction_namestore
    on transaction_cnab (name_store);



-- INSERT

INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (1, 'Débito', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (2, 'Boleto', 'Saída', '-');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (3, 'Financiamento', 'Saída', '-');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (4, 'Crédito', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (5, 'Recebimento Empréstimo', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (6, 'Vendas', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (7, 'Recebimento TED', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (8, 'Recebimento DOC', 'Entrada', '+');
INSERT INTO transaction_type_cnab (code, description, nature, signal) VALUES (9, 'Aluguel', 'Saída', '-');

