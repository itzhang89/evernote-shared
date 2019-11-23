INSERT INTO public.category
(id, name)
VALUES('a3ac04ee-db6a-436e-8494-ba2cedbb39ab', NULL);

INSERT INTO public.article
(id, author, brief_intro, content, created, deleted, is_original, is_shared, source_url, title, update_sequence_num, updated, category_id)
VALUES('840b5190-7493-4dfb-afa1-9ce6507ed953', '爱穿格子裤', 'JSON Schema 是一种可以对Json格式进行验证。本文主要是对JSON Schema中的一些', '17854', '2019-07-24 11:00:58.000', '1970-01-01 00:00:00.000', false, true, NULL, 'JSON Schema Validation: A Vocabulary for Structural Validation of JSON', 1492, '2019-07-24 11:30:42.000', 'a3ac04ee-db6a-436e-8494-ba2cedbb39ab');


INSERT INTO public.label (id, name, article_id)
VALUES ('efe890d8-05d0-4773-9c01-caa35f6f5834', NULL, '840b5190-7493-4dfb-afa1-9ce6507ed953');

