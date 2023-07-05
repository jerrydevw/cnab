\c cnab;

DROP TABLE IF EXISTS bills;
CREATE TABLE IF NOT EXISTS bills
(
    id                 uuid    not null
    constraint bill_pk
    primary key,
    value              real    not null,
    payment_date_limit varchar not null,
    debtor_id         uuid    not null
);

ALTER TABLE bills
    OWNER TO postgres;
