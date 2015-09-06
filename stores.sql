--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brands; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace:
--

CREATE TABLE brands (
    id integer NOT NULL,
    brand_name character varying,
    style character varying,
    type character varying,
    color character varying
);


ALTER TABLE brands OWNER TO nataliyamiller;

--
-- Name: brands_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brands_id_seq OWNER TO nataliyamiller;

--
-- Name: brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE brands_id_seq OWNED BY brands.id;


--
-- Name: stores; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace:
--

CREATE TABLE stores (
    id integer NOT NULL,
    name character varying,
    address character varying,
    phone_number character varying
);


ALTER TABLE stores OWNER TO nataliyamiller;

--
-- Name: stores_brands; Type: TABLE; Schema: public; Owner: nataliyamiller; Tablespace:
--

CREATE TABLE stores_brands (
    id integer NOT NULL,
    store_id integer,
    brand_id integer
);


ALTER TABLE stores_brands OWNER TO nataliyamiller;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE stores_brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_brands_id_seq OWNER TO nataliyamiller;

--
-- Name: stores_brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE stores_brands_id_seq OWNED BY stores_brands.id;


--
-- Name: stores_id_seq; Type: SEQUENCE; Schema: public; Owner: nataliyamiller
--

CREATE SEQUENCE stores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stores_id_seq OWNER TO nataliyamiller;

--
-- Name: stores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nataliyamiller
--

ALTER SEQUENCE stores_id_seq OWNED BY stores.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY brands ALTER COLUMN id SET DEFAULT nextval('brands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY stores ALTER COLUMN id SET DEFAULT nextval('stores_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: nataliyamiller
--

ALTER TABLE ONLY stores_brands ALTER COLUMN id SET DEFAULT nextval('stores_brands_id_seq'::regclass);


--
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY brands (id, brand_name, style, type, color) FROM stdin;
2	Best Brand	Sport	men	brown
4	Just brand	Casual	Kid's	Blue
5	Hoddey	Wedding	Men's	Black
\.


--
-- Name: brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('brands_id_seq', 5, true);


--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY stores (id, name, address, phone_number) FROM stdin;
11	Best	Tigard, OR	971-222-3333
12	Shoe Family	Wilsonville, OR	503-333-5533
\.


--
-- Data for Name: stores_brands; Type: TABLE DATA; Schema: public; Owner: nataliyamiller
--

COPY stores_brands (id, store_id, brand_id) FROM stdin;
9	11	4
10	12	5
13	12	2
\.


--
-- Name: stores_brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('stores_brands_id_seq', 15, true);


--
-- Name: stores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nataliyamiller
--

SELECT pg_catalog.setval('stores_id_seq', 12, true);


--
-- Name: brands_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace:
--

ALTER TABLE ONLY brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- Name: stores_brands_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace:
--

ALTER TABLE ONLY stores_brands
    ADD CONSTRAINT stores_brands_pkey PRIMARY KEY (id);


--
-- Name: stores_pkey; Type: CONSTRAINT; Schema: public; Owner: nataliyamiller; Tablespace:
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: nataliyamiller
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM nataliyamiller;
GRANT ALL ON SCHEMA public TO nataliyamiller;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
