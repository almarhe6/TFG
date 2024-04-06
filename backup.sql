--
-- PostgreSQL database dump
--

-- Dumped from database version 15.6 (Homebrew)
-- Dumped by pg_dump version 15.6 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: funds; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.funds (
    isin character(12) NOT NULL,
    name character(30),
    company character(30),
    index character(30)
);


ALTER TABLE public.funds OWNER TO alejandromartorellhernandez;

--
-- Name: funds_historical; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.funds_historical (
    isin character(12) NOT NULL,
    day date NOT NULL,
    price double precision,
    rent_mont double precision,
    rent_year double precision,
    rent_5year double precision,
    rent_10year double precision,
    rent_20year double precision
);


ALTER TABLE public.funds_historical OWNER TO alejandromartorellhernandez;

--
-- Name: investment_plans; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.investment_plans (
    document_number character(9) NOT NULL,
    isin character(12) NOT NULL,
    quantity double precision,
    day timestamp without time zone
);


ALTER TABLE public.investment_plans OWNER TO alejandromartorellhernandez;

--
-- Name: transactions; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.transactions (
    buy_sell boolean,
    document_number character(9),
    isin character(12),
    quantity double precision,
    id integer NOT NULL,
    processed boolean
);


ALTER TABLE public.transactions OWNER TO alejandromartorellhernandez;

--
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO alejandromartorellhernandez;

--
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alejandromartorellhernandez
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.users (
    document_number character(9) NOT NULL,
    phone character(9),
    email character(40),
    name character(20),
    surname character(40),
    card_number character(16)
);


ALTER TABLE public.users OWNER TO alejandromartorellhernandez;

--
-- Name: wallet; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.wallet (
    document_number character(9) NOT NULL,
    isin character(12) NOT NULL,
    quantity double precision
);


ALTER TABLE public.wallet OWNER TO alejandromartorellhernandez;

--
-- Name: transactions id; Type: DEFAULT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- Data for Name: funds; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.funds (isin, name, company, index) FROM stdin;
\.


--
-- Data for Name: funds_historical; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.funds_historical (isin, day, price, rent_mont, rent_year, rent_5year, rent_10year, rent_20year) FROM stdin;
\.


--
-- Data for Name: investment_plans; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.investment_plans (document_number, isin, quantity, day) FROM stdin;
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.transactions (buy_sell, document_number, isin, quantity, id, processed) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.users (document_number, phone, email, name, surname, card_number) FROM stdin;
\.


--
-- Data for Name: wallet; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.wallet (document_number, isin, quantity) FROM stdin;
\.


--
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alejandromartorellhernandez
--

SELECT pg_catalog.setval('public.transactions_id_seq', 1, false);


--
-- Name: users document_number; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT document_number PRIMARY KEY (document_number);


--
-- Name: funds funds_pkey; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.funds
    ADD CONSTRAINT funds_pkey PRIMARY KEY (isin);


--
-- Name: funds_historical isin_day; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.funds_historical
    ADD CONSTRAINT isin_day PRIMARY KEY (isin, day);


--
-- Name: investment_plans user_isin; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.investment_plans
    ADD CONSTRAINT user_isin PRIMARY KEY (document_number, isin);


--
-- Name: wallet user_isin_wallet; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT user_isin_wallet PRIMARY KEY (document_number, isin);


--
-- Name: funds_historical isin_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.funds_historical
    ADD CONSTRAINT isin_foreign FOREIGN KEY (isin) REFERENCES public.funds(isin);


--
-- Name: investment_plans isin_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.investment_plans
    ADD CONSTRAINT isin_foreign FOREIGN KEY (isin) REFERENCES public.funds(isin);


--
-- Name: transactions isin_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT isin_foreign FOREIGN KEY (isin) REFERENCES public.funds(isin);


--
-- Name: wallet isin_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT isin_foreign FOREIGN KEY (isin) REFERENCES public.funds(isin);


--
-- Name: transactions user_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT user_foreign FOREIGN KEY (document_number) REFERENCES public.users(document_number);


--
-- Name: investment_plans user_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.investment_plans
    ADD CONSTRAINT user_foreign FOREIGN KEY (document_number) REFERENCES public.users(document_number);


--
-- Name: wallet user_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT user_foreign FOREIGN KEY (document_number) REFERENCES public.users(document_number);


--
-- PostgreSQL database dump complete
--

