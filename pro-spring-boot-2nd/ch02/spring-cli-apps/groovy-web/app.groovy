@RestController
class WebApp{
      @GetMapping("/")
      String welcome(){
         "<h1><font face='verdana'>Spring Boot Rocks!</font></h1>"
      }
}

