# escolaAlf
Sistema para geração de notas em provas de múltipla escolha - Escola Alf

1. O que foi utilizado para completar o desafio foi:
- Linguagem de programação Java 8;
- IDE Spring Tools Suite versão 4.8.1.RELEASE;
- Banco de dados PostgreSQL;
- Ferramenta Postman para testar os serviços RESTful.
-----------------------------------------------------------------------------------

2. De onde baixar algumas das tecnologias
- Spring Tools Suite:
https://spring.io/tools/

- Banco de dados PostgreSQL
https://www.enterprisedb.com/postgresql-tutorial-resources-training?cid=80

- Postman
https://www.postman.com/downloads/
-----------------------------------------------------------------------------------

3. Sobre algumas das configurações do PostgreSQL
- password = admin
- port = 5432
- user = postgres
É importante que na instalação estejam com essas configurações porque o
arquivo application.properties do projeto foi configurado com esses dados.
-----------------------------------------------------------------------------------

4. No Banco de dados PostgreSQL
Em Database, crie o banco de dados com o nome de "escolaAlf".
-----------------------------------------------------------------------------------

5. Como utilizar o programa
Estando o projeto importado no Spring Tools Suite e em execução, será aberto o
Postman e serão feitos os seguintes testes para cada tipo de requisito do desafio.

5. a) Cadastrar o gabarito de cada prova
O primeiro que deve ser cadastrado no sistema é o GABARITO, pois todas as outras
alternativas das provas serão comparadas com as alternativas dele. Caso queira,
basta copiar o comando JSON abaixo.

OBS.: SUGIRO QUE, CASO QUEIRA VERIFICAR O ARQUIVO PELO PRÓPRIO GITHUB, QUE ABRA
ESTE ARQUIVO E TENHA UMA VISUALIZAÇÃO MELHOR DOS COMANDOS EM JSON COM AS PROVAS
E ALTERNATIVAS PARA COPIA-LOS E FAZER OS TESTES.

. Requisição: POST
. Url: http://localhost:8080/escolaAlf/aluno/
. Comando JSON:

{"id": "", "nome": "GABARITO DA PROVA", "provas": 
[   {"id": "", "respostas": ["c", "c", "c", "c", "c", "c", "c", "d", "b", "a"]},
    {"id": "", "respostas": ["a", "a", "a", "a", "a", "b", "e", "a", "b", "a"]},
    {"id": "", "respostas": ["a", "b", "a", "e", "d", "d", "d", "d", "d", "c"]},
    {"id": "", "respostas": ["b", "a", "a", "a", "d", "d", "c", "b", "c", "d"]}
]}

Foram padronizadas 4 provas e 10 questões com as opções a, b, c, d, e com a
inserção de um nome para o usuário.


5. b) Cadastrar as respostas de cada aluno para cada prova
Depois que foi criado o primeiro usuário e entendido que as opções escolhidas
nas provas dele são as alternativas corretas, todos os demais a serem
cadastrados serão alunos. A requisição e a url serão as mesmas, agora
basta utilizar as opções que você queira. Cadastre de formas que hajam
alunos aprovados e reprovados para que no futuro seja possível listar apenas
quem foi aprovado.

. Requisição: POST
. Url: http://localhost:8080/escolaAlf/aluno/
. Comando JSON:

{"id": "", "nome": "Nome do Aluno", "provas": 
[   {"id": "", "respostas": ["c", "c", "c", "c", "d", "c", "d", "d", "b", "a"]},
    {"id": "", "respostas": ["a", "a", "b", "a", "a", "b", "a", "a", "b", "a"]},
    {"id": "", "respostas": ["a", "b", "a", "e", "d", "d", "d", "d", "d", "c"]},
    {"id": "", "respostas": ["b", "a", "c", "a", "d", "d", "c", "c", "c", "d"]}
]}


5. c) Verificar a nota final de cada aluno
Para verificar a nota final de cada aluno, basta colocar o seguinte que todos
os alunos serão listados com a nota final:

. Requisição: GET
. Url: http://localhost:8080/escolaAlf/aluno/


5. d) Listar os alunos aprovados
Para verificar quais alunos foram aprovados, basta colocar o seguinte:

. Requisição: GET
. Url: http://localhost:8080/escolaAlf/aluno/aprovados
