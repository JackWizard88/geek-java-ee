

create table javaee_shop.categories
(
    id              bigint primary key AUTO_INCREMENT,
    title           varchar(128)  not null,
    description     varchar(1024) not null
);

insert into javaee_shop.categories (id, title, description)
values
       (1, 'category1', 'description of category1'),
       (2, 'category2', 'description of category2');

create table javaee_shop.products
(
    id              bigint primary key AUTO_INCREMENT,
    title           varchar(255)  not null,
    category_id     bigint references categories(id),
    description     varchar(1024) not null,
    price           float default 0.0
);


insert into javaee_shop.products
values
    (1, 'Product1', 1, 'descriprion of product1', 100.00),
    (2, 'Product2', 2, 'descriprion of product2', 200.00),
    (3, 'Product3', 1, 'descriprion of product3', 300.00);

create table javaee_shop.orders
(
    id              bigint primary key AUTO_INCREMENT,
    username        varchar(255)  not null
);

create table javaee_shop.orderItems
(
    id              bigint primary key AUTO_INCREMENT,
    order_id        bigint references orders(id),
    product_id      bigint references products(id),
    quantity        bigint,
    orderItemPrice  float default 0.0
);
