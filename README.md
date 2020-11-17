[![Build Status](https://travis-ci.com/EduardVavrynchuk/HelloBackEnd.svg?branch=master)](https://travis-ci.com/EduardVavrynchuk/HelloBackEnd)

# Hello back-end demo

## What tools did I use?

The following tools I did used:

    Java 8 (1.8.0_192)
    Maven (3.6.0)
    PostgreSQL (42.2.2)
    SpringBoot (2.1.4.RELEASE)
    Docker (19.03.5)
    Junit

## How to run?

You should have Java, Maven, PostgreSQL. After installation, you need to clone this repository:

    git@github.com:EduardVavrynchuk/HelloBackEnd.git
    
You need compile application: 
    
    mvn clean package

Next step it's generate spring boot app image for docker:

    docker build ./ -t springbootapp

## And run the program:

    docker compose up
    
## How to run test?

    mvn test
