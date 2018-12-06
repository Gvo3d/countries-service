REMOTE: http://35.165.83.26/index.html

TO CHECK: http://35.165.83.26:8081/countries/UA
where 'UA' is a code of country;

TO START:
start spring service from IDE or by compiling jar
host from application.yml is pointing to the mongo database - if you have your own, you can change host to that.

To create collection in your own mongo:
use countries
db.createUser({user: 'cntr',pwd: 'password',roles: [{ role: 'readWrite', db:'countries'}]})

To compile and start web part:
./scripts/installing-web-service.sh
to launch - we go to web-ui and launching:
npm start

The local web-server will work at localhost:4200
You must point your web-app to the spring application in the:
web-ui/src/app/utils/constants.ts in the HOME_URL() method.