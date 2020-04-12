create table plats
(
      id   int(11)      not null primary key auto_increment,
      nom  varchar(100) not null unique,
      prixencentimeseuros int(7)       not null
);