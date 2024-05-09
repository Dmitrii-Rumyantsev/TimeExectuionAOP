# Система учета времени выполнения методов при помощи аспектов

## Технологии/зависимости

В рамках проекта используется:<br>
[![Java21](https://img.shields.io/badge/JAVA-21-blue.svg)](https://docs.oracle.com/en/java/)
[![AspectJ](https://img.shields.io/badge/AspectJ-82312?style=flat-square&color=red)](https://eclipse.dev/aspectj/doc/latest/)
[![Spring Boot v3](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C.svg?style=flat-square&logo=Hibernate&logoColor=white)](https://hibernate.org)
[![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=flat-square&logo=Apache-Maven&logoColor=white)](https://maven.apache.org)

### JDK

Проект написан с использованием JDK (Java) версии 22
### База данных

В качестве БД в проекте используется PostgreSQL.<br>

## Структура проекта

* `TaskOneApplication` - представляет собой точку входа в приложение Spring Boot,
* `annotation/*` - пакет, в котором находятся аннотации,
* `aspect/*` - пакет, в котором находятся реализация аспектов,
* `config/*` - пакет, в котором находятся настройки для асинхронного выполнения задач в приложении,
* `contoller/*` - пакет, в котором находятся REST-контроллеры
* `dto/*` - классы для передачи данных,
* `mapper/*` - интерфейсы для преобразования данных, 
* `entity/*` - классы-сущности для работы с репозиториями,
* `repository/*` - интерфейсы для манипуляции с хранилищами данных,
* `service/impl/*` - классы-сервисы для работы с репозиториями,
* `test/aspect/*` - тестирование работы аспектов
* `test/controller/*` - тестирование работы контроллеров
* `test/mapper/*` - тестирование работы мапперов
* `test/repository/*` - тестирование работы репозиторий

#### Структуры Баз данных:

Таблица для записи названия и пути метода
```postgresql
CREATE TABLE method
(
  method_id   serial  primary key,
  class_name  varchar(255) not null,
  method_name varchar(255) not null
);
```
Таблица для записи времени и статуса выполнения методов
```postgresql
CREATE TABLE time_exectuion
(
  time_id    serial primary key,
  start_time timestamp not null,
  end_time   timestamp not null,
  execution bigint not null,
  is_complete boolean not null,
  method_id  bigint           not null,
  foreign key (method_id) references method (method_id)
);
```
Таблица для сущности аккаунт
```postgresql
CREATE TABLE account(
  id serial primary key ,
  full_name varchar(255) not null ,
  data_of_birth DATE not null ,
  phone varchar(255) not null,
  email varchar(255) not null
);
```

Используя аннотации @TrackTime и @TrackAsyncTime, мы можем измерять 
время выполнения методов в приложении Spring Boot с помощью AOP. 
Далее, для анализа и изучения этих данных, мы можем запросить 
необходимую выборку данных через запущенный REST-сервис, 
используя различные критерии поиска.
