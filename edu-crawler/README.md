## edu-crawler

##### About
Web cralwer for **bacalaureat.edu.ro** build. Built as SpringBoot application with Java 8 strictly our of convenience.
Crawls 2018 results based and stores them in MongoDB database, while also caching the HTML files for further use.

##### Run
A Mongo instance must be runnning on port 27017.
Build .jar with **mvn clean install** and then run it with **java**.

##### Configuration
You can list the counties you want to be crawled in **counties.json**. By default, the crawler runs 4 threads which fetch the HTML pages and parses them using Jsoup.
