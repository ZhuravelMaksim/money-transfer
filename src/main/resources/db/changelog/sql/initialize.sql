create table account
(
    id varchar(255) not null
        constraint account_pkey
            primary key,
    creation_date timestamp not null,
    modification_date timestamp not null,
    account_number varchar(255),
    balance double precision
);

create table transaction
(
    id varchar(255) not null
        constraint transaction_pkey
            primary key,
    creation_date timestamp not null,
    modification_date timestamp not null,
    amount double precision,
    status varchar(255),
    account_from_id varchar(255)
        constraint fklax6nioh16qapni5oetnsocoh
            references account,
    account_to_id varchar(255)
        constraint fki2std9osl77a5s35sf736s64
            references account
);


