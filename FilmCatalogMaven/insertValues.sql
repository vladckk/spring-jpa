insert into genres(name) values('musical'), ('comedy'), ('drama'), ('animated'), ('fantasy');

insert into movies(title, year) values('The Green Mile', 1999), ('Forrest Gump', 1994), 
    ('The Little Mermaid', 1994), ('Moonlight Kingdom', 2012), ('The Grand Budapest Hotel', 2014);
    
insert into staff(fullname, position) values('Tom Hanks', 'actor'), ('Michael Clarke Duncan', 'actor'),
	('David Morse', 'actor'), ('Bonnie Hunt', 'actor'), ('Robin Wright', 'actor'), ('Mykelti Williamson', 'actor'),
    ('Jodi Benson', 'actor'), ('Pat Carroll', 'actor'), ('Rene Auberjonois', 'actor'), ('Frank Darabont', 'director'),
    ('Robert Zemeckis', 'director'), ('John Musker', 'director'), ('Wes Anderson', 'director'), ('Ralph Fiennes', 'actor'),
    ('Tony Revolori', 'actor'), ('Adrien Brody', 'actor'), ('Edward Norton', 'actor'), ('Jared Gilman', 'actor'),
    ('Kara Hayward', 'actor');
    
insert into movies_genres values(1, 3), (1, 5), (2, 2), (2, 3), (3, 1), (3, 4), (3, 5), (4, 2), (4, 3),
	(5, 2), (5, 3);

insert into moviestaff(Movies_id, Staff_id) values(1, 1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 5), (2, 6),
	(3, 7), (3, 8), (3, 9), (1, 10), (2, 11), (3, 12), (4, 13), (4, 14), (4, 15), (4, 16), (4, 17), (5, 13),
    (5, 17), (5, 18), (5, 19);