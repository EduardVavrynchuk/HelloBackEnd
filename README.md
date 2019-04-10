# Hello back-end demo

## What tools did I use?

The following tools I did used:

    Java 8 (1.8.0_192)
    Maven (3.6.0)
    PostgreSQL (42.2.5)
    SpringBoot (2.1.4.RELEASE)
    Junit (4.12)

## How to run?

You should have Java, Maven, PostgreSQL. After installation, you need to clone this repository:

    git clone git@github.com:EduardVavrynchuk/

You need create DB and user, also grant user access on DB, commands:

    1. sudo -u postgres createdb test_db;
    2. sudo -u postgres createuser db_user;
    3. sudo -u postgres psql test_db;
    4. ALTER USER "db_user" WITH PASSWORD 'qwAS123zx';
    5. GRANT ALL PRIVILEGES ON DATABASE test_db TO db_user;
    
## And run the program:

    mvn spring-boot:run
    
## How to run test?

    mvn test
