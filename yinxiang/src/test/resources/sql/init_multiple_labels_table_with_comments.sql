INSERT INTO public.category
    (id, name)
VALUES ('category_id_1', NULL);

INSERT INTO public.article
(id, author, brief_intro, content, created, deleted, is_original, is_shared, source_url, title, update_sequence_num,
 updated, category_id)
VALUES ('article_id_1', '爱穿格子裤', 'this is a brief introduction for title', '17854',
        '2019-07-24 11:00:58.000', '1970-01-01 00:00:00.000', false, true, NULL,
        'title for article_id_1', 1492, '2019-07-24 11:30:42.000',
        'category_id_1');


INSERT INTO public.label (id, tag_guid, name, article_id)
VALUES (1, 'tag_guid_1', 'article1_tag_1', 'article_id_1'),
       (2, 'tag_guid_2', 'article1_tag_2', 'article_id_1'),
       (3, 'tag_guid_2', 'article1_tag_2', 'article_id_1');

INSERT INTO public.post_comment (id, parent_id, answer_to, content, dis_likes, likes, username, comment_time,
                                 article_id)
VALUES (1, null, 0, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (2, null, 0, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (3, 2, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (4, 2, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (5, 2, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (6, 2, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (7, 2, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1'),
       (8, null, 0, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem
            laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat
            saepe enim sapiente iste iure! Quam voluptas earum impedit
            necessitatibus, nihil?', 0, 1, 'John Doe', '2019-12-03 14:21:00.000', 'article_id_1');