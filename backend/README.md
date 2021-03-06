# The Backend

The place where all the data bits are managed.

## Getting Started

* [installing maven](https://maven.apache.org/download.cgi#Installation)
* [installing and running postgres](https://wiki.postgresql.org/wiki/Detailed_installation_guides)
* [creating a user for postgres](https://www.postgresql.org/docs/9.3/static/app-createuser.html) Make sure this user is a superuser. Don't forget the username and password for the user you create.
* setting up the databases for Tourneynizer

For setting up the database, `cd` into `backend/`, and run `./scripts/createDB.sh`. This just creates 3 databases. You will have to run that script as the postgres user, otherwise you'll have to provide the username and password you created when creating a user for postgres. Run `man createdb` for more info.

Now you have 3 databases: `tourneynizer-prod`, `tourneynizer-dev`, and `tourneynizer-test`. But they're all empty.

Modify `./scripts/test.conf`, `./scripts/dev.conf`, and `./scripts/prod.conf` to contain the db credentials you just set up. Follow the instructions in the file. The URL schema is `jdbc:postgresql://host:port/database`. The default port is `5432` and default host is `localhost`. The databases are `tourneynizer-test` for `test.conf`, `tourneynizer-prod` for `prod.conf`, and `tourneynizer-dev` for `dev.conf`.  The username and password are the ones you set up before.

Now run `./scripts/migrate.sh`. Now all your databases will have tables in them. That's it for now!

For connecting to the database, you'll need to have the proper environment variables set. Export the following into a file called `.env`.
```
export TOURNEYNIZER_DRIVER_CLASS_NAME_TEST=org.postgresql.Driver
export TOURNEYNIZER_URL_TEST=jdbc:postgresql://localhost:5432/tourneynizer-test
export TOURNEYNIZER_USERNAME_TEST=[The username you set up for the db]
export TOURNEYNIZER_PASSWORD_TEST=[The password you set up for the db]
export TOURNEYNIZER_DRIVER_CLASS_NAME_DEV=org.postgresql.Driver
export TOURNEYNIZER_URL_DEV=jdbc:postgresql://localhost:5432/tourneynizer-dev
export TOURNEYNIZER_USERNAME_DEV=[The username you set up for the db]
export TOURNEYNIZER_PASSWORD_DEV=[The password you set up for the db]
export TOURNEYNIZER_DRIVER_CLASS_NAME_PROD=org.postgresql.Driver
export TOURNEYNIZER_URL_PROD=jdbc:postgresql://localhost:5432/tourneynizer-prod
export TOURNEYNIZER_USERNAME_PROD=[The username you set up for the db]
export TOURNEYNIZER_PASSWORD_PROD=[The password you set up for the db]
```
Then, run `source .env` to load the environment variables. Currently only the environment variables ending in `DEV` need to be set to run, and the ones ending in `TEST` to run tests.

Run `mvn test` to run tests.
Run `mvn spring-boot:run` to start the server in dev mode on port 8080.
