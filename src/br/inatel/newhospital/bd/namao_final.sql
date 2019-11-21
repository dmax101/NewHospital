create schema if not exists `c207trabalhobd`;
use `c207trabalhobd`;

create table if not exists enfermeiro (
cre varchar(20) not null,
nome varchar(45) not null,
telefone varchar(45) not null,
primary key(cre)
)engine = innodb;

create table if not exists medico (
crm varchar(20) not null,
nome varchar(45) not null,
telefone varchar(45) not null,
primary key(crm)
)engine = innodb;

create table if not exists paciente (
cpf varchar(20) not null,
nome varchar(45) not null,
idade int not null,
primary key(cpf)
)engine = innodb;

create table if not exists bandeja (
id_bandeja int not null auto_increment,
descricao varchar(100) not null,
primary key (id_bandeja)
)engine = innodb;

create table if not exists cirurgia (
id_cirurgia int not null auto_increment,
data datetime not null,
bandeja_id int not null,
paciente_cpf varchar(20) not null,
medico_crm varchar(20) not null,
primary key (id_cirurgia),
constraint fk_bandeja foreign key (bandeja_id)
references bandeja(id_bandeja)
on delete no action
on update no action,
constraint fk_paciente foreign key (paciente_cpf)
references paciente(cpf)
on delete no action
on update no action,
constraint fk_medico foreign key (medico_crm)
references medico(crm)
on delete no action
on update no action
)engine = innodb;

create table if not exists esterilizacao (
id_esterilizacao int not null auto_increment,
data datetime not null,
enfermeiro_cre varchar(20) not null,
bandeja_id int not null,
primary key (id_esterilizacao),
constraint fk_esterilizacao_enfermeiro foreign key (enfermeiro_cre)
references enfermeiro(cre)
on delete no action
on update no action,
constraint fk_esterilizacao_bandeja foreign key (bandeja_id)
references bandeja(id_bandeja)
on delete no action
on update no action
)engine = innodb;

create table if not exists escala_enfermeiros (
id_escala int not null auto_increment,
enfermeiro_cre varchar(20) not null,
cirurgia_id int not null,
primary key (id_escala),
constraint fk_enfermeiro_cirurgia_enfermeiro foreign key (enfermeiro_cre)
references enfermeiro(cre)
on delete no action
on update no action,
constraint fk_enfermeiro_cirurgia_cirurgia foreign key (cirurgia_id)
references cirurgia(id_cirurgia)
on delete no action
on update no action
)engine = innodb;

INSERT INTO `c207trabalhobd`.`paciente` (`cpf`, `nome`, `idade`) VALUES ('05995555642', 'Danilo Vidal Ribeiro', '35');
INSERT INTO `c207trabalhobd`.`paciente` (`cpf`, `nome`, `idade`) VALUES ('05455545455', 'Maria Fernandes', '21');
INSERT INTO `c207trabalhobd`.`paciente` (`cpf`, `nome`, `idade`) VALUES ('04887844545', 'Jorge Matias', '35');

INSERT INTO `c207trabalhobd`.`medico` (`crm`, `nome`, `telefone`) VALUES ('1542', 'Joaquim de Jesus', '(35) 9945-6526');
INSERT INTO `c207trabalhobd`.`medico` (`crm`, `nome`, `telefone`) VALUES ('5452', 'Mariana Guedes', '(35) 9945-6526');
INSERT INTO `c207trabalhobd`.`medico` (`crm`, `nome`, `telefone`) VALUES ('8455', 'Juscelino Amaral', '(35) 9945-6526');

INSERT INTO `c207trabalhobd`.`bandeja` (`id_bandeja`, `descricao`) VALUES ('1', 'Forceps');
INSERT INTO `c207trabalhobd`.`bandeja` (`id_bandeja`, `descricao`) VALUES ('2', 'Tesoura');
INSERT INTO `c207trabalhobd`.`bandeja` (`id_bandeja`, `descricao`) VALUES ('3', 'Bisturis');

INSERT INTO `c207trabalhobd`.`enfermeiro` (`cre`, `nome`, `telefone`) VALUES ('6558', 'Josefina Amarante', '(35) 9565-4554');
INSERT INTO `c207trabalhobd`.`enfermeiro` (`cre`, `nome`, `telefone`) VALUES ('5544', 'Fernanda Araújo', '(35) 9565-4554');
INSERT INTO `c207trabalhobd`.`enfermeiro` (`cre`, `nome`, `telefone`) VALUES ('7544', 'Paula Magalhães', '(35) 9565-4554');



