# Home Assistant REST Api Client




## Usage
* Install the lib to your local:
  * `mvn clean install`


* Declare the dependency in your project (copy this block from pom.xml):
  * `<groupId>com.mgustran</groupId>`
  * `<artifactId>ha-api-client</artifactId>`
  * `<version>0.0.2-SNAPSHOT</version>`


* **Set Config values**
  * put those properties in `application.properties` file inside **your project** resources' folder / classpath:
    * `homeassistant.host=http://example.com:8123`  # HA host
    * `homeassistant.token=eyJ0eXAiOiJ...`   # HA Long Lived Token


* To use the different methods see the tests under `src/test/java/com/mgustran/homeassistant/client`

## About the lib

* **Development still in progress**


* Currently, I have never done a lib to be used
  with many different configurations (java versions, other deps, etc), so be pacient if something is not THE STANDARD or THE GOOD WAY


* This library is not published in any maven repo,
  you will have to install it locally with maven
  or deploy it to your repo.
  (I like to think that one day I will publish it to central, but who knows)


* This library works with **Java >= 8**, **Maven** and has some dependencies already provided:
  * **lombok** : 1.18.24
  * **jackson-databind**: 2.14.0-rc1
  * **jackson-datatype-jsr310**: 2.14.0-rc1


## Contributing
You are invited to participate and contribute to the improvement of this lib, but i want to stay clear about some things:

I tried to make this project not dependent on many libs, to be easily integrated with other different projects, so..:
* I don't want to put more deps on project, unless they optimize significantly something
* [Angulos, Angulos rectos!](https://www.youtube.com/watch?v=bxaYsGo-Sec)

## License
* see LICENSE.txt


![alt text](https://media.tenor.com/Qk9SE5aOLPEAAAAM/yes-awkward.gif "It is what it is")
