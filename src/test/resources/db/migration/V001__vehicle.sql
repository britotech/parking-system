CREATE TABLE IF NOT EXISTS public.tb_vehicle
(
    id         uuid          NOT NULL,
    color      varchar(20)   NOT NULL,
    model      varchar(20)   NOT NULL,
    license    varchar(15)   NOT NULL,
    entry_date timestamp without time zone NOT NULL,
    exit_date  timestamp without time zone,
    bill_total numeric(10,2),

    CONSTRAINT vehicle_pk PRIMARY KEY (id)
 );

ALTER TABLE IF EXISTS public.tb_vehicle OWNER to postgres;