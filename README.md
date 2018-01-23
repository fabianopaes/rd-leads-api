[![Build Status](https://travis-ci.org/fabianopaes/rd-leads-api.svg?branch=master)](https://travis-ci.org/fabianopaes/rd-leads-api)
# rd-leads-api  

rd-leads-api is a very simple rest api to storage data about leads which will be provided by  [guest-navegate-tracker](https://github.com/fabianopaes/rd-guest-navegate-tracker) javascript lib. This way you are able to track and sotarage what pages are access by any guest on your website.

This project has built using PlayFramework for java and postegres on production mode

### Running application on local environment

To run in you own machine you might have the activator properly installed otherwise you can use the activator that's inside the project root folder

Jump into the projects folder and run the follow commands

```
./activator clean compile 
```

To run tests

```
./activator test
```

To set the application up on localhost just run....

```
./activator run
```

After that, the API will be exposed on http://localhost:9000/


### Continuous Integration

You can check the history of compilation jobs on [TravisCI](https://travis-ci.org/fabianopaes/rd-leads-api)
 
Every single time when some contributors push a change into the repo a travis-ci job will start

### Deploying

The deploy was provided by [Heroku](https://www.heroku.com) with github integration


in case you have any doubt or even you are facing troubles, drop me a line ;)

### Future implementation

To make the rd-leads-api scalable I may need to make these changes...

* The endpoint that register the guest tracker might receive the request save the payload and response with http accept(202)
* Send to some MQ (RabbitMQ, ActiveMQ, SQS) to process later
* Read (when SQS)/receive from MQ (RabbitMQ, ActiveMQ) and add it as guest's page visited


##### Restriction points

* This version is still facing CORS troubles, I will fix it asap 


