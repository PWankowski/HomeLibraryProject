Aplikacja pozwalająca tworzyć domową biblioteczkę z półkami książek które chcielibyśmy przeczytać w najbliższej przyszłości. Pozwala na komunikację z zewnętrznym api goglebooks, gdzie można wyszukiwać książki, które nas interesują i dodawać je do naszej biblioteczki a następnie układać na półkach dzieląc je np. na regały S-F, Fantasy, Programowanie,  etc...  W projekcie użyto między innymi Open Api (Swagger) do utworzenia dokumentacji aplikacji, Spring Security do uwierzytelniania i autentykacji użytkownika, EhCache jako provider dla Spring Cache do szybkiego dostępu do zasobów z pamięci podręcznej, Log4J do logowania aplikacji, Spring Boot jako szkielet aplikacji.

Baza Danych :
http://localhost:8080/h2-console/
Login i Hasło : user

Open API dokumentacja :
http://localhost:8080/swagger-ui/index.html#/ 

Dokumentacja GoogleBooks API :
https://developers.google.com/books/docs/v1/getting_started?hl=pl

Logi z działania aplikacji przechowywane są w pliku myLog.txt. 
Po Uruchomieniu aplikacji należy dodać nowego użytkownika na endpoincie http://localhost:8080/auth/signup np.:
{
        "name": "Jacek",
        "surname": "Testowy",
        "age": 23,
        "sex": "M",
        "emailAddress": "1123@gmail.com",
        "login": "Test",
        "password": "Test123"
    }
W odpowiedzi dostaniemy Token autoryzacyjny dzięki któremu będziemy mogli uwierzytelniać nasze kolejne Requesty do api.
Gdyby Token wygasł po czasie możemy wygenerować nowy za pomocą metody http://localhost:8080/auth/authenticate podając login oraz hasło. 
Przykładowe requesty do googleBook Api:

Po jeden zasób (identyfikator zwracany jako uuid przy wyszukiwaniu książek w liscie przekazując parametr wyszukiwania):
http://localhost:8080/book/yfIyyQEACAAJ

Po listę : 
http://localhost:8080/books?parameters= Java Developer Red

Następnie możemy wykonywać operacje typu CRUD na książkach oraz regałach czy wyszukiwać interesujące nas pozycje za pomocą zapytań do googlebooks Api i tworzyć nasze własne regały z książkami. 

English Version:

Application that allows to create own home book library with book shelves which we would like to read in the close future. Application communicate with external Googlebooks API, where we can searching for books which we are interested and add them into our library directly to the shelves  named as for example S-F, Fantasy, Programming etc…  In project I used frameworks as Opean API (Swagger) to create project documentation and testing, Spring Security for user authentication and authorization, EhCache as a privider for Spring Cache to quick access to resources from cache, Log4J for logging application, and Spring Boot.

Data Base:
 http://localhost:8080/h2-console/
Login and Password : user

Googlebooks Api documentation:
https://developers.google.com/books/docs/v1/getting_started?hl=pl
Application operation logs are stored in the myLog.txt file.

After starting the application, add a new user on the endpoint:
http://localhost:8080/auth/signup example.:


{
        "name": "Jacek",
        "surname": "Testowy",
        "age": 23,
        "sex": "M",
        "emailAddress": "1123@gmail.com",
        "login": "Test",
        "password": "Test123"
    }
In Response we will receive authorization Token with which we will be able to authenticate our requests to api.
If token expired, we can generate another one from endpoint http://localhost:8080/auth/authenticate sending login and password in request.

