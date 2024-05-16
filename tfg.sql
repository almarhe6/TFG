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
    isin character varying(255) NOT NULL,
    name character varying(255),
    company character varying(255),
    index character varying(255)
);


ALTER TABLE public.funds OWNER TO alejandromartorellhernandez;

--
-- Name: funds_historical; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.funds_historical (
    isin character varying(255) NOT NULL,
    date date NOT NULL,
    price double precision,
    rent_mont double precision,
    rent_year double precision,
    rent_5year double precision,
    rent_10year double precision,
    rent_20year double precision,
    rent double precision
);


ALTER TABLE public.funds_historical OWNER TO alejandromartorellhernandez;

--
-- Name: investment_plans; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.investment_plans (
    isin character varying(255) NOT NULL,
    quantity double precision,
    email character varying(255) NOT NULL,
    day_of_month integer
);


ALTER TABLE public.investment_plans OWNER TO alejandromartorellhernandez;

--
-- Name: transactions; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.transactions (
    buy_sell boolean,
    isin character varying(255) NOT NULL,
    quantity double precision,
    processed boolean,
    email character varying(255) NOT NULL,
    effect_datetime timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.transactions OWNER TO alejandromartorellhernandez;

--
-- Name: users; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.users (
    document_number character varying(255) NOT NULL,
    phone character varying(255),
    email character varying(255) NOT NULL,
    name character varying(255),
    surname character varying(255),
    card_number character varying(255)
);


ALTER TABLE public.users OWNER TO alejandromartorellhernandez;

--
-- Name: wallet; Type: TABLE; Schema: public; Owner: alejandromartorellhernandez
--

CREATE TABLE public.wallet (
    isin character varying(255) NOT NULL,
    quantity double precision,
    email character varying(255) NOT NULL
);


ALTER TABLE public.wallet OWNER TO alejandromartorellhernandez;

--
-- Data for Name: funds; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.funds (isin, name, company, index) FROM stdin;
IE00B03HCZ61	Vanguard Global Stock Index Fund Investor EUR Accumulation	The Vanguard Group Inc.	World
LU0996182563	Amundi Index Solutions - Amundi Index MSCI World AE-C	Amundi	World
LU0836512615	Shares World Equity Index Fund (LU) N7 EUR	BlackRock Inc.	World
IE0007472990	Vanguard Euro Government Bond Index Fund EUR Acc	The Vanguard Group Inc.	Europe
IE00B4XCK338	iShares Ultra High Quality Euro Government Bond Index	Blacrock Inc.	Europe
\.


--
-- Data for Name: funds_historical; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.funds_historical (isin, date, price, rent_mont, rent_year, rent_5year, rent_10year, rent_20year, rent) FROM stdin;
IE00B03HCZ61	2024-04-09	44.53739929199219	\N	\N	\N	\N	\N	0
LU0996182563	2024-04-09	302.7099914550781	\N	\N	\N	\N	\N	0
LU0836512615	2024-04-09	304.8999938964844	\N	\N	\N	\N	\N	0
IE0007472990	2024-04-09	207.30320739746094	\N	\N	\N	\N	\N	0
IE00B4XCK338	2024-04-09	10.902999877929688	\N	\N	\N	\N	\N	0
IE00B03HCZ61	2024-04-13	44.864898681640625	\N	\N	\N	\N	\N	0.7353356838402592
LU0996182563	2024-04-13	307.510009765625	\N	\N	\N	\N	\N	1.585682153229882
LU0836512615	2024-04-13	301.8599853515625	\N	\N	\N	\N	\N	-0.9970510350203479
IE0007472990	2024-04-13	206.56590270996094	\N	\N	\N	\N	\N	-0.3556648721244197
IE00B4XCK338	2024-04-13	10.854999542236328	\N	\N	\N	\N	\N	-0.4402488877444058
IE00B03HCZ61	2024-04-23	43.22779846191406	\N	\N	\N	\N	\N	-3.6489555706864616
LU0996182563	2024-04-23	296.42999267578125	\N	\N	\N	\N	\N	-3.603140300469767
LU0836512615	2024-04-23	293.0400085449219	\N	\N	\N	\N	\N	-2.9218767755416146
IE0007472990	2024-04-23	206.210693359375	\N	\N	\N	\N	\N	-0.17195933400716512
IE00B4XCK338	2024-04-23	10.836000442504883	\N	\N	\N	\N	\N	-0.17502626008891708
IE00B03HCZ61	2024-04-27	43.74620056152344	\N	\N	\N	\N	\N	1.1992331741486075
LU0996182563	2024-04-27	299.94000244140625	\N	\N	\N	\N	\N	1.1840940027495985
LU0836512615	2024-04-27	298.3599853515625	\N	\N	\N	\N	\N	1.8154438477724428
IE0007472990	2024-04-27	204.89320373535156	\N	\N	\N	\N	\N	-0.6389046089512798
IE00B4XCK338	2024-04-27	10.75100040435791	\N	\N	\N	\N	\N	-0.7844226160563332
IE00B03HCZ61	2024-04-28	43.74620056152344	\N	\N	\N	\N	\N	0
LU0996182563	2024-04-28	299.94000244140625	\N	\N	\N	\N	\N	0
LU0836512615	2024-04-28	298.3599853515625	\N	\N	\N	\N	\N	0
IE0007472990	2024-04-28	204.89320373535156	\N	\N	\N	\N	\N	0
IE00B4XCK338	2024-04-28	10.75100040435791	\N	\N	\N	\N	\N	0
IE00B03HCZ61	2024-05-01	44.290199279785156	\N	\N	\N	\N	\N	1.2435336355591706
LU0996182563	2024-05-01	303.67999267578125	\N	\N	\N	\N	\N	1.2469127838677045
LU0836512615	2024-05-01	299.1600036621094	\N	\N	\N	\N	\N	0.2681386076635579
IE0007472990	2024-05-01	206.6916046142578	\N	\N	\N	\N	\N	0.8777259792516778
IE00B4XCK338	2024-05-01	10.836000442504883	\N	\N	\N	\N	\N	0.7906244530743199
IE00B03HCZ61	2024-05-02	43.829200744628906	\N	\N	\N	\N	\N	-1.0408590222050729
LU0996182563	2024-05-02	303.67999267578125	\N	\N	\N	\N	\N	0
LU0836512615	2024-05-02	299.04998779296875	\N	\N	\N	\N	\N	-0.03677492572332096
IE0007472990	2024-05-02	205.76010131835938	\N	\N	\N	\N	\N	-0.4506730196598326
IE00B4XCK338	2024-05-02	10.782999992370605	\N	\N	\N	\N	\N	-0.4891145069206512
IE00B03HCZ61	2024-05-06	44.07170104980469	\N	\N	\N	\N	\N	0.5532847988461179
LU0996182563	2024-05-06	302.1700134277344	\N	\N	\N	\N	\N	-0.49722710895181643
LU0836512615	2024-05-06	301.4800109863281	\N	\N	\N	\N	\N	0.8125809371514409
IE0007472990	2024-05-06	206.26800537109375	\N	\N	\N	\N	\N	0.24684282787581238
IE00B4XCK338	2024-05-06	10.809000015258789	\N	\N	\N	\N	\N	0.2411204943575965
\.


--
-- Data for Name: investment_plans; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.investment_plans (isin, quantity, email, day_of_month) FROM stdin;
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.transactions (buy_sell, isin, quantity, processed, email, effect_datetime) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.users (document_number, phone, email, name, surname, card_number) FROM stdin;
11111111A	688888888	robe@mail.com	Roberto	Iniesta Ojea	1111222233334444
22222222B	677777777	apla@mail.com	Albert	Pla i Alvarez	4444333322221111
\.


--
-- Data for Name: wallet; Type: TABLE DATA; Schema: public; Owner: alejandromartorellhernandez
--

COPY public.wallet (isin, quantity, email) FROM stdin;
IE00B03HCZ61	500	robe@mail.com
\.


--
-- Name: users email; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT email PRIMARY KEY (email);


--
-- Name: funds_historical funds_historical_pkey; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.funds_historical
    ADD CONSTRAINT funds_historical_pkey PRIMARY KEY (isin, date);


--
-- Name: funds funds_pkey; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.funds
    ADD CONSTRAINT funds_pkey PRIMARY KEY (isin);


--
-- Name: transactions pk_transactions; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT pk_transactions PRIMARY KEY (email, isin, effect_datetime);


--
-- Name: wallet pk_wallet; Type: CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT pk_wallet PRIMARY KEY (email, isin);


--
-- Name: investment_plans email_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.investment_plans
    ADD CONSTRAINT email_foreign FOREIGN KEY (email) REFERENCES public.users(email);


--
-- Name: transactions email_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT email_foreign FOREIGN KEY (email) REFERENCES public.users(email);


--
-- Name: wallet email_foreign; Type: FK CONSTRAINT; Schema: public; Owner: alejandromartorellhernandez
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT email_foreign FOREIGN KEY (email) REFERENCES public.users(email);


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
-- PostgreSQL database dump complete
--

