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
