create table javaee_shop.categories
(
    id          bigint primary key AUTO_INCREMENT,
    title       varchar(128)  not null,
    description varchar(1024) not null
);

insert into javaee_shop.categories (id, title, description)
values (1, 'category1', 'description of category1'),
       (2, 'category2', 'description of category2');

create table javaee_shop.products
(
    id          bigint primary key AUTO_INCREMENT,
    title       varchar(255)  not null,
    category_id bigint references categories (id),
    description varchar(1024) not null,
    price       float default 0.0
);


insert into javaee_shop.products
values (1, 'Product1', 1, 'descriprion of product1', 100.00),
       (2, 'Product2', 2, 'descriprion of product2', 200.00),
       (3, 'Product3', 1, 'descriprion of product3', 300.00);

create table javaee_shop.orders
(
    id       bigint primary key AUTO_INCREMENT,
    username varchar(255) not null
);

create table javaee_shop.orderItems
(
    id             bigint primary key AUTO_INCREMENT,
    order_id       bigint references orders (id),
    product_id     bigint references products (id),
    quantity       bigint,
    orderItemPrice float default 0.0
);

create table javaee_shop.users
(
    id       bigint primary key AUTO_INCREMENT,
    username varchar(30) not null,
    password varchar(80) not null
);

create table javaee_shop.roles
(
    id   bigint primary key AUTO_INCREMENT,
    name varchar(50) not null
);

CREATE TABLE javaee_shop.users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into javaee_shop.roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into javaee_shop.users (username, password)
values ('admin', 'admin'),
       ('user', 'user');

insert into javaee_shop.users_roles (user_id, role_id)
values (1, 2),
       (2, 1);
