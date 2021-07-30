# anonymizer

An anonymization service will take a text as an input and will replace all occurrences of URLs, Ids and Emails with fake data.

ID - any character sequence of number longer than 3 digits\
Email - any valid email address\
URL - any valid URL but need to only replace the domain

To run all the tests: `./gradlew clean test -i`\
To run an example test: `./gradlew clean -Dtest.single=AnonymizerApplicationTests test -i`\
To run the server: `./gradlew clean bootrun`

The server will start at `8080` port, it has a single endpoint: `POST /anonymize` with expected `{"text":"your text here..."}` json body.

cURL example:
```
curl --request POST \
  --url http://localhost:8080/anonymize \
  --data '{
	"text": "text email@gmail.com https://google.com/help 12345"
}'
```
