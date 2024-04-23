-- Active: 1711955562629@@dpg-co55plv79t8c739g3c80-a.frankfurt-group12.render.com@5432@g12moviedb@public
-- group12QL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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
-- Name: favorites; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.favorites (
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.favorites OWNER TO group12;

--
-- Name: followers; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.followers (
    follower_id integer NOT NULL,
    user_id integer,
    following boolean NOT NULL,
    joined_at timestamp with time zone DEFAULT now() NOT NULL,
    left_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.followers OWNER TO group12;

--
-- Name: followers_follower_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.followers ALTER COLUMN follower_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.followers_follower_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: members; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.members (
    member_id integer NOT NULL,
    user_id integer,
    group_id integer,
    is_admin boolean DEFAULT false NOT NULL,
    joined_at timestamp with time zone DEFAULT now() NOT NULL,
    left_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.members OWNER TO group12;

--
-- Name: members_member_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.members ALTER COLUMN member_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.members_member_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: message; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.message (
    message_id integer NOT NULL,
    creator_id integer NOT NULL,
    content text NOT NULL,
    parent_message_id integer,
    create_date timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.message OWNER TO group12;

--
-- Name: message_message_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.message ALTER COLUMN message_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.message_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: message_recipient; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.message_recipient (
    message_recipient_id integer NOT NULL,
    recipient_id integer NOT NULL,
    message_id integer,
    is_read boolean DEFAULT false,
    group_id integer
);


ALTER TABLE public.message_recipient OWNER TO group12;

--
-- Name: message_recipient_message_recipient_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.message_recipient ALTER COLUMN message_recipient_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.message_recipient_message_recipient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: movie; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.movie (
    movie_id integer NOT NULL,
    title character varying(128)
);


ALTER TABLE public.movie OWNER TO group12;

--
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.movie ALTER COLUMN movie_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: movie_scores; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.movie_scores (
    movie_score_id integer NOT NULL,
    movie_id integer NOT NULL,
    score integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.movie_scores OWNER TO group12;

--
-- Name: movie_scores_movie_score_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.movie_scores ALTER COLUMN movie_score_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.movie_scores_movie_score_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: movies_to_watch; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.movies_to_watch (
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    note text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.movies_to_watch OWNER TO group12;

--
-- Name: movies_watched; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.movies_watched (
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    note text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.movies_watched OWNER TO group12;

--
-- Name: review; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.review (
    review_id integer NOT NULL,
    user_id integer,
    movie_id integer,
    movie_score_id integer,
    content text NOT NULL
);


ALTER TABLE public.review OWNER TO group12;

--
-- Name: review_review_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.review ALTER COLUMN review_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.review_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_group; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.user_group (
    group_id integer NOT NULL,
    user_id integer,
    group_name character varying(255) NOT NULL,
    group_description text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.user_group OWNER TO group12;

--
-- Name: user_group_group_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.user_group ALTER COLUMN group_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_group_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: group12
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(128) NOT NULL,
    password character varying(128) NOT NULL,
    email character varying(255) NOT NULL,
    last_login timestamp without time zone,
    id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.users OWNER TO group12;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: group12
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users_id_seq1; Type: SEQUENCE; Schema: public; Owner: group12
--

CREATE SEQUENCE public.users_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq1 OWNER TO group12;

--
-- Name: users_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: group12
--

ALTER SEQUENCE public.users_id_seq1 OWNED BY public.users.id;


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq1'::regclass);


--
-- Name: favorites favorites_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_pkey PRIMARY KEY (user_id, movie_id);


--
-- Name: followers followers_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_pkey PRIMARY KEY (follower_id);


--
-- Name: members members_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT members_pkey PRIMARY KEY (member_id);


--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (message_id);


--
-- Name: message_recipient message_recipient_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message_recipient
    ADD CONSTRAINT message_recipient_pkey PRIMARY KEY (message_recipient_id);


--
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (movie_id);


--
-- Name: movie_scores movie_scores_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movie_scores
    ADD CONSTRAINT movie_scores_pkey PRIMARY KEY (movie_score_id);


--
-- Name: movies_to_watch movies_to_watch_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_to_watch
    ADD CONSTRAINT movies_to_watch_pkey PRIMARY KEY (user_id, movie_id);


--
-- Name: movies_watched movies_watched_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_watched
    ADD CONSTRAINT movies_watched_pkey PRIMARY KEY (user_id, movie_id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (review_id);


--
-- Name: user_group user_group_group_name_key; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_group_name_key UNIQUE (group_name);


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (group_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: favorites favorites_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- Name: favorites favorites_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: followers fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: members group_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT group_id FOREIGN KEY (group_id) REFERENCES public.user_group(group_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: message message_creator_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_creator_id_fkey FOREIGN KEY (creator_id) REFERENCES public.users(user_id);


--
-- Name: message message_parent_message_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_parent_message_id_fkey FOREIGN KEY (parent_message_id) REFERENCES public.message(message_id);


--
-- Name: message_recipient message_recipient_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message_recipient
    ADD CONSTRAINT message_recipient_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.user_group(group_id);


--
-- Name: message_recipient message_recipient_message_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message_recipient
    ADD CONSTRAINT message_recipient_message_id_fkey FOREIGN KEY (message_id) REFERENCES public.message(message_id);


--
-- Name: message_recipient message_recipient_recipient_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.message_recipient
    ADD CONSTRAINT message_recipient_recipient_id_fkey FOREIGN KEY (recipient_id) REFERENCES public.users(user_id);


--
-- Name: movie_scores movie_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movie_scores
    ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: review movie_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: review movie_score_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT movie_score_id FOREIGN KEY (movie_score_id) REFERENCES public.movie_scores(movie_score_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: movies_to_watch movies_to_watch_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_to_watch
    ADD CONSTRAINT movies_to_watch_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- Name: movies_to_watch movies_to_watch_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_to_watch
    ADD CONSTRAINT movies_to_watch_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: movies_watched movies_watched_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_watched
    ADD CONSTRAINT movies_watched_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movie(movie_id);


--
-- Name: movies_watched movies_watched_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.movies_watched
    ADD CONSTRAINT movies_watched_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: review user_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: user_group user_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: members user_id; Type: FK CONSTRAINT; Schema: public; Owner: group12
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- group12QL database dump complete
--