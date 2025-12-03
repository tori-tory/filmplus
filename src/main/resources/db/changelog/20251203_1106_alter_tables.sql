ALTER TABLE filmplus.film ADD COLUMN genre VARCHAR;

ALTER TABLE filmplus."user" ADD CONSTRAINT user_login_key UNIQUE (login);
ALTER TABLE filmplus.film ALTER COLUMN duration TYPE INT USING duration::int;

ALTER TABLE filmplus.film ALTER COLUMN description DROP NOT NULL;
ALTER TABLE filmplus.film ALTER COLUMN duration DROP NOT NULL;
ALTER TABLE filmplus.film RENAME COLUMN "releasedate" TO release_date;

ALTER TABLE filmplus.friend ADD CONSTRAINT unique_friend_pair UNIQUE (user_id, friend_id);

ALTER TABLE filmplus.feedback RENAME COLUMN content TO review_text;
ALTER TABLE filmplus.feedback ALTER COLUMN review_text TYPE TEXT;
ALTER TABLE filmplus.feedback ALTER COLUMN review_text DROP NOT NULL;
ALTER TABLE filmplus.feedback ADD COLUMN created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP;
