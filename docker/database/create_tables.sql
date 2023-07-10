\c cnab;

create table transaction_type
(
    id          bigserial
        primary key,
    code        bigint not null,
    description varchar(255),
    nature      varchar(255),
    signal      varchar(255)
);

alter table transaction_type
    owner to postgres;

create table transaction
(
    value        double precision,
    code         bigint not null
        constraint transaction_transaction_type_id_fk
            references transaction_type,
    id           bigserial
        primary key,
    card_number  varchar(255),
    cpf          varchar(255),
    date         varchar(255),
    hour         varchar(255),
    name_store   varchar(255),
    onwner_store varchar(255)
);

alter table transaction
    owner to postgres;

create index idx_transaction_namestore
    on transaction (name_store);



-- INSERT

INSERT INTO transaction_type (code, description, nature, signal) VALUES (1, 'Débito', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (2, 'Boleto', 'Saída', '-');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (3, 'Financiamento', 'Saída', '-');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (4, 'Crédito', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (5, 'Recebimento Empréstimo', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (6, 'Vendas', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (7, 'Recebimento TED', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (8, 'Recebimento DOC', 'Entrada', '+');
INSERT INTO transaction_type (code, description, nature, signal) VALUES (9, 'Aluguel', 'Saída', '-');

