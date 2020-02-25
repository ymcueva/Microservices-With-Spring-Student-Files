## Lab 1 - Spring Boot

- En este ejercicico creamos una aplicación Spring Boot. Si ya conoces Spring Boot saltate este ejercicio. Todos los laboratorio siguientes asumen que conoces Spring Boot.

**Part 1 - Simple Web Application**

1.  Usando Intellij IDEA, o [https://start.spring.io](https://start.spring.io), crearemos un nuevo proyecto Spring Boot.

- Usa Maven. Todas las instrucciones estan hechas para Maven.
- Usa la última versión estable de Boot y Java. Estas instrucciones han sido testeadas con Java 11, Boot 2.2.4.RELEASE.
- Usa JAR packaging por ahora. No usaremos Tomcat ni otro contenedor de servlets.
- Usa cualquier valor que gustes para group, artifact, package, description, etc.
- Selecciona las siguientes dependencias: Web, Thymeleaf, JPA, H2, Actuator.

2.  Crea un nuevo Controller debajo del paquete base y en el subpaquete controller:

- Ponle el nombre del Controller como gustes. Sugerencia <Name>Controller, donde <Name> puede ser: Index, Home, Inicio, etc.
- Decora el Controller con la anotación @Controller.

3.  Crea un nuevo método en la controladora:

- Nombra el método como gustes. Este debe retornar un String. No se necesita parámetros.
- Anota el método con @RequestMapping("/") o @GetMapping("/).
- El método debe retornar el String "hello".

4.  Si es que no esta presente, crear un nuevo directorio bajo src/main/resources denominado "templates"

5.  Crea un nuevo archivo en el directorio templates denominado "hello.html". Coloca la frase "Hello from Thymeleaf" (o lo que tu quieras) al interior del archivo.

6.  Guarda todo tu trabajo. Ejecuta tu aplicación.

- Usa las opciones disponibles que hay en Intellij IDEA o las que tengas disponible en el IDE que estes usando.
- Si deseas ejecutarlo desde un command prompt, desde el dirctorio raíz de la aplicación ejecuta mvn spring-boot:run.

7.  Abre un navegador y ve a [http://localhost:8080/](http://localhost:8080/). Tu deberías ver tu pagina web.

**Part 2 - Retornar un RESTful Response**

9.  Crea una nueva clase llamada "Team" en el subpaquete model. Crea las siguientes propiedades o atributos: Long id, String name, location, y mascot (o las propiedades que desees). Genera los "getters and setters" para todos los campos. Salva tu trabajo.

10. Crea una nueva Controladora denominada "TeamController". Anota esta con @RestController.

11. Crea un nuevo método en TeamController.

- Coloca como nombre de método "getTeams". Este debe retornar una Lista de objetos Team.
- Anota el método con @RequestMapping("/teams") o @GetMapping("/teams").
- Coloca el siguiente cuerpo al método:

```
@RequestMapping("/teams")
public List<Team> getTeams() {
List<Team> list = new ArrayList<>();

Team team = new Team();
team.setId(0l);
team.setLocation("Harlem");
team.setName("Globetrotters");
list.add(team);

team = new Team();
team.setId(1l);
team.setLocation("Washington");
team.setName("Generals");
list.add(team);

return list;
}

```

12. Guarda todo tu trabajo. Deten la aplicación si ya se esta ejecutando, e inicia este nuevamente. Abre [http://localhost:8080/teams](http://localhost:8080/teams). Tu deberías ver un JSON response con la data de tus teams.

**Part 3 - Crear Spring Data JPA Repositories**

13. Retorna a la clase Team. Agrega las anotaciones JPA requeridas: La clase debe ser anotada con @Entity, el id debe ser anotado con @Id y @GeneratedValue.

14. Crear una nueva interface denominada "TeamRepository". Hacer que extienda de CrudRepository<Team,Long>.

15. Abre la clase Application (anotada con @SpringBootApplication). Usa @Autowired para inyectar una instancia de TeamRepository. Coloca el nombre que gustes a la propiedad en cuestión (sugiero: "teamRepository").

16. Agrega algo de lógica para poblar la base de datos: Agrega un método public void init(). Anota este con @PostConstruct. Ejemplo de código:

```
  public void init() {
List<Team> list = new ArrayList<>();

Team team = new Team();
team.setLocation("Harlem");
team.setName("Globetrotters");
list.add(team);

team = new Team();
team.setLocation("Washington");
team.setName("Generals");
list.add(team);

teamRepository.saveAll(list);
}
```

17. Retorna a el TeamController. Usa @Autowired para inyectar una variable de tipo TeamRepository. Colocale el nombre que quieras (sugiero: "teamRepository").

18. Modifica la logica en tu controladora para retornar el resultado de tu repositorio con findAll():

```
@RequestMapping("/teams")
public Iterable<Team> getTeams() {
return teamRepository.findAll();
}
```

19. Guarda todo tu trabajo. Deten la aplicación si ya esta ejecutandose, e inicia esta nuevamente. Abre [http://localhost:8080/teams](http://localhost:8080/teams). Tu deberías ver un JSON response con la data de los teams.

**Part 4 (Opcional)- Crear un Single Team endpoint**

20. Retornar a la clase TeamController y agregar un método que retorna un simple Team dado un ID.

- Colocale el nombre que gustes. Sugerimos: getTeam.
- El tipo de retorno debe ser Team.
- Usa un @RequestMapping o @GetMapping con el patrón "/teams/{id}".
- Define un parámetro denominado "id" de tipo Long anotado con @PathVariable.
- Logica: retorna el resultado del teamRepository's findById().

19. Guarda todo tu trabajo. Deten tu aplicación y vuelve a ejecutarla. Usa [http://localhost:8080/teams](http://localhost:8080/teams) para ver los ID generados por cada Team. Luego usa estos urls [http://localhost:8080/teams/1](http://localhost:8080/teams/1) o [http://localhost:8080/teams/2](http://localhost:8080/teams/2) para obtener los resultados de cada teams de forma individual.

**Part 5 - Agregar Players**

20. Agregar una nueva clase Player. Agregar las propiedades id, name, y position. El id debería ser Long, y los otros campos pueden ser Strings. Generar getters / setters para cada campo. Agrega una anotación @Entity en la clase, y @Id y @GeneratedValue en el id. Agrega un constructor sin argumentos y otro con argumentos para crear un objeto Player proporcionando su name y position. Guarda tu trabajo.

21. Abre la clase Team. Agrega un Set de objetos Player denominado players. Genera getters y setters. Anota el set con @OneToMany(cascade=CascadeType.ALL) y @JoinColumn(name="teamId"). Crea un constructor sin argumentos y con argumentos con name, location, y Set de Players. Guarda tu trabajo.

22. Retorna a la clase Application y modifica la lógica de creación de teams inicial para agregarle algunos players a cada team. Ejemplo:

```
  @PostConstruct
public void init() {
List<Team> list = new ArrayList<>();

Set<Player> set = new HashSet<>();
set.add(new Player("Big Easy", "Showman"));
set.add(new Player("Buckets", "Guard"));
set.add(new Player("Dizzy", "Guard"));

list.add(new Team("Harlem", "Globetrotters", set));
list.add(new Team("Washington","Generals",null));

teamRepository.saveAll(list);
}
```

23. Guarda todo tu trabajo. Reinicia la aplicación. Abre [http://localhost:8080/teams](http://localhost:8080/teams) para ver los players.

**Part 6 - Agregar Spring Data REST**

24. Abre el archivo POM. Agrega una dependencia para el grupo org.springframework.boot y artifact spring-boot-starter-data-rest. Guarda tu trabajo.

25. Abre TeamRepository. Agrega una anotación @RestResource(path="teams", rel="team") a la interface.

26. Crea una nueva Interface denominada "PlayerRepository". Haz que extienda de CrudRepository<Player,Long>. Agrega un @RestResource(path="players", rel="player") a la interface.

27. Abre TeamController. Comenta la anotación @RestController de la clase. (Usaremos Spring Data REST para implementar el controller, así que no deseamos que haya interferencias entre las dos).

28. Guarda todo tu trabajo. Reinicia la aplicación. Abre [http://localhost:8080/teams](http://localhost:8080/teams) para ver los players. Observa que puedes navegar con los links de players y teams.

**Part 7 (Opcional) - Explorar los endpoints Actuator**

29. Una de las dependencias que escogimos fue Actuator. Este nos permite agregar algunos útiles endpoints a nuestras aplicaciones web. Por defecto, tenemos los siguientes:

- [/actuator/info](http://localhost:8080/actuator/info)
- [/actuator/health](http://localhost:8080/actuator/health)

30. Existen otros endpoints que no estan habilitados, si deseas hacerlo, visita este URL para que veas como hacerlo ahora en Spring Boot 2.X:

[Actuator para Spring Boot 2.X](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)

**Part 8 (Opcional) - DevTools**

33. Cuando desarrollamos necesitamos ejecutar/detener constantemente nuestra aplicación, hacer algunos cambios, luego reiniciar. La dependencia Spring Boot "DevTools" puede reiniciar automaticamente cuando se detecta algunos cambios:

```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
  </dependency>
```

34. Para que trabaje con Intellij IDEA sugiero sigas estos pasos:

[Habilitar DevTools en Intellij IDEA](https://dev.to/suin/spring-boot-developer-tools-how-to-enable-automatic-restart-in-intellij-idea-1c6i)

Después de eso, realizar algunos cambios en tus controladoras y verifica los cambios en tu navegador o respuesta JSON sin necesidad de parar y volver a ejecutar la aplicación. ¿Que te parece esta característica?. Cool no?.
