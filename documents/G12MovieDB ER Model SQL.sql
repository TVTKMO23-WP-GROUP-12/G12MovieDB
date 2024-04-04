CREATE TABLE "users" (
  "id" INTEGER PRIMARY KEY,
  "username" VARCHAR(255) UNIQUE,
  "email" VARCHAR(255) UNIQUE,
  "password" VARCHAR(128),
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now())
);

CREATE TABLE "followers" (
  "id" INTEGER PRIMARY KEY,
  "joined_at" TIMESTAMPTZ DEFAULT (Now()),
  "left_at" TIMESTAMPTZ DEFAULT (Now()),
  "following" BOOL(FALSE,TRUE) DEFAULT true,
  "foreign" key(users_id)
);

CREATE TABLE "movie" (
  "id" INTEGER PRIMARY KEY,
  "title" VARCHAR(128)
);

CREATE TABLE "movie_scores" (
  "id" INTEGER PRIMARY KEY,
  "score" INTEGER,
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now()),
  "foreign" key(movie_id)
);

CREATE TABLE "review" (
  "id" INTEGER PRIMARY KEY,
  "content" TEXT,
  "foreign" key(users_id,movie_id,movie_scores_id)
);

CREATE TABLE "movies_watched" (
  "primary" key(users_id,movie_id) PRIMARY KEY,
  "note" TEXT,
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now())
);

CREATE TABLE "movies_to_watch" (
  "primary" key(users_id,movie_id) PRIMARY KEY,
  "note" TEXT,
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now())
);

CREATE TABLE "favorites" (
  "primary" key(users_id,movie_id) PRIMARY KEY,
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now())
);

CREATE TABLE "user_group" (
  "id" INTEGER PRIMARY KEY,
  "group_name" VARCHAR(255) UNIQUE,
  "group_description" TEXT,
  "created_at" TIMESTAMPTZ DEFAULT (Now()),
  "updated_at" TIMESTAMPTZ DEFAULT (Now()),
  "joined_at" TIMESTAMPTZ DEFAULT (Now()),
  "left_at" TIMESTAMPTZ DEFAULT (Now()),
  "foreign" key(users_id)
);

CREATE TABLE "members" (
  "id" INTEGER PRIMARY KEY,
  "is_admin" BOOL(FALSE,TRUE) DEFAULT false,
  "foreign" key(users_id,user_group_id)
);

CREATE TABLE "message" (
  "id" INTEGER PRIMARY KEY,
  "creator_id" INTEGER,
  "content" TEXT,
  "parent_message_id" INTEGER,
  "create_date" TIMESTAMPTZ DEFAULT (Now()),
  "foreign" key(creator_id,users_id)
);

CREATE TABLE "message_recipient" (
  "id" INTEGER PRIMARY KEY,
  "recipient_id" INTEGER,
  "message_id" INTEGER,
  "is_read" BOOL(FALSE,TRUE) DEFAULT false,
  "foreign" key(recipient_id,users_id,user_group_id)
);

COMMENT ON COLUMN "message"."creator_id" IS 'FK -> users_id';

COMMENT ON COLUMN "message_recipient"."recipient_id" IS 'FK -> users_id';

ALTER TABLE "message" ADD FOREIGN KEY ("id") REFERENCES "user_group" ("id");

ALTER TABLE "movies_to_watch" ADD FOREIGN KEY ("primary") REFERENCES "users" ("id");

ALTER TABLE "movies_watched" ADD FOREIGN KEY ("primary") REFERENCES "users" ("id");

ALTER TABLE "followers" ADD FOREIGN KEY ("id") REFERENCES "users" ("id");

ALTER TABLE "members" ADD FOREIGN KEY ("id") REFERENCES "user_group" ("id");

ALTER TABLE "movie_scores" ADD FOREIGN KEY ("id") REFERENCES "movie" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("id") REFERENCES "users" ("id");

ALTER TABLE "members" ADD FOREIGN KEY ("id") REFERENCES "users" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("id") REFERENCES "movie_scores" ("id");

ALTER TABLE "favorites" ADD FOREIGN KEY ("primary") REFERENCES "users" ("id");

ALTER TABLE "favorites" ADD FOREIGN KEY ("primary") REFERENCES "movie" ("id");

ALTER TABLE "movie" ADD FOREIGN KEY ("id") REFERENCES "users" ("id");

ALTER TABLE "users" ADD FOREIGN KEY ("id") REFERENCES "message" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("id") REFERENCES "movie" ("id");
