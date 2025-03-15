--
-- INSERT INTO user_role (`id`,`name`) VALUES (1,'USER');
-- INSERT INTO user_role (`id`,`name`) VALUES (2,'ADMIN');
-- INSERT INTO user_role (`id`,`name`) VALUES (3,'EMPLOYEE');
--
-- INSERT INTO users (`id`,`address`,`e_mail`,`first_name`,`is_logged`,`last_name`,`password`) VALUES (11,'New York','p@mail.com','Peter','0','Parker','842b130448a51526dca0b82460b5942aa73075bf1bbd9284789bc2ecbc055b9a9de8eaa40b90e39824a59a08d17fa687');
-- INSERT INTO users (`id`,`address`,`e_mail`,`first_name`,`is_logged`,`last_name`,`password`) VALUES (12,'Gotham','b@mail.com','Bruce','0','Wayne','4bbabbb959b2bc644246199ad3b7c7a8798e6c233ed2f0bcb4193bd93a6eab605654f82ac99849cc962597fe37dcd6a9');
--


INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (1, 'bike',
        'The Blend makes it easy for you to get into road cycling. This bike is affordable, beautiful and well thought-out.',
        'Blend Road', 'https://cdn.rosebikes.de/images/1997ECADA8B2C1E242D39197E9379D2C.png', 1201.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (2, 'bike',
        'Competitive advantages built-in - light, fast, aerodynamically-optimised race bikes for training and competition at the highest level',
        'Xlite', 'https://cdn.rosebikes.de/cms/cms.679b6ac1861e79.51647575.png', 5401.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (3, 'bike',
        'The Reveal AL is made for the distance. For nice and long tours. Aesthetic, comfortable and always fast when you want it.',
        'Reveal', 'https://cdn.rosebikes.de/cms/cms.64d397bc9b6e18.63042479.png', 2401.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (4, 'bike', 'Made to cross countries.', 'PDQ', 'https://cdn.rosebikes.de/cms/cms.64ca512bf2b490.40848535.png',
        2211.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (5, 'wheels',
        'Upgraded with the latest components: the benchmark setting and victory proven DT Swiss Aero rim brake wheelset is now even faster.',
        'ARC 110 DICUT',
        'https://d2a13k6araex7u.cloudfront.net/dam/00/00/00/00/00/00/00/10/00/00/03/48/1/40195/image-thumb__40195__product-stage/PHO_AAOTIX0000A_ARC1100_rimbrake_WEB_SHO_001.webp',
        2999.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (6, 'wheels', 'The ultimate racing wheelset made of carbon for the mountains, a few hills or in the flat.',
        'PRC 1400 SPLINE',
        'https://d2a13k6araex7u.cloudfront.net/dam/00/00/00/00/00/00/00/10/00/00/03/49/2/61382/image-thumb__61382__product-stage/PHO_AAPWO_PRC1400_WEB_SHO_001.webp',
        2041.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (7, 'wheels', 'Unrestricted road riding experience with multi-purpose ERC wheels.', 'ERC 1100 DICUT',
        'https://d2a13k6araex7u.cloudfront.net/dam/00/00/00/00/00/00/00/10/00/00/03/50/6/59010/image-thumb__59010__product-stage/PHO_AAQZIX_ERC1100_WEB_SHO_003.webp',
        1201.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (8, 'wheels', 'Assists to start adventures where the road ends.', 'HG 1800 SPLINE',
        'https://d2a13k6araex7u.cloudfront.net/dam/00/00/00/00/00/00/00/10/00/00/04/99/8/PHO_AAXBWX_HG1800_WEB_SHO_001.jpg',
        512.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (9, 'groupset',
        'Wide-range 1x12 gearing for gravel, bikepacking, adventure, casual cruises, and everything in between.',
        'Apex', 'https://images.bike24.com/media/720/i/mb/7e/8f/8b/sram-apex-xplr-axs-groupset-12s-1537874.jpg',
        899.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (10, 'groupset', 'Every component has been refined to create an effortless ride.', 'RED',
        'https://images.bike24.com/media/720/i/mb/a2/45/97/sram-red-xplr-groupset-with-powermeter-axs-e1-1712390.jpg',
        4520.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (11, 'groupset',
        'SRAM Rival eTap AXS features the technology modern riders wantâ€”intuitive wireless shifting, innovative gearing, integrated power measurement, AXS connectivity, and refined hydraulic disc brakes.',
        'Rival', 'https://images.bike24.com/media/1440/i/mb/f9/7d/57/134246-00-d-229540.jpg', 720.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (12, 'groupset',
        'Choose from integrated power solutions for road and gravel. Accelerate with intelligent gear ranges.', 'Force',
        'https://images.bike24.com/media/1440/i/mb/62/47/16/sram-force-axs-groupset-2x12s-1450397.jpg', 1520.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (13, 'bike', 'The bike that makes every road ride better', 'Trek Domane',
        'https://media.trekbikes.com/image/upload/f_auto,fl_progressive:semi,q_auto,w_1920,h_1440,c_pad/DomaneSLR9_23_37357_A_Primary',
        1899.00);
INSERT INTO products (`id`, `category`, `description`, `name`, `picture_url`, `price`)
VALUES (14, 'chain', 'Gold is the new silver.', 'KMC X10 SL',
        'https://cdn.rosebikes.de/images/06311A08D1FC99E54C0195D6A4A11FE5.png', 37.00);
