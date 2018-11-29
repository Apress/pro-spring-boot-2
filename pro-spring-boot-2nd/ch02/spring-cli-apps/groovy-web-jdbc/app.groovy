@Grab('spring-boot-starter-jdbc')
@Grab('com.h2database:h2:1.4.195')

@RestController
class Web {

  @Autowired
  JdbcTemplate jdbc

  def rowMapper = [ 
      mapRow: { rs, row ->
       
         new Person(name:rs.getString("name"),email:rs.getString("email"))

      }
  ] as RowMapper

  @RequestMapping('/')
  def index(){
    this.jdbc.query "select * from person", rowMapper 

  }

}

class Person {

   String name
   String email

}
