create table user
   (username    varchar(20),
    password    varchar(20),
	primary key(username));
    
create table book
   (bookname    varchar(20),
    img_name    varchar(25),
    category    varchar(20),
	description varchar(200),
    price       float,
    stock_num   integer,
	primary key(bookname));
    
create table order_form
   (id        integer auto_increment,
    username  varchar(20),
    bookname  varchar(20),
    num       integer,
    primary key(id),
    foreign key(username) references user(username)
    on delete cascade,
    foreign key(bookname) references book(bookname) 
    on delete cascade on update cascade);

create table sales_record
   (id        integer auto_increment,
    username  varchar(20),
    bookname  varchar(20),
    num       integer,
    date      timestamp,
    primary key(id));
    