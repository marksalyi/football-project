A main class az en projectemben a DemoApplicaton ugy hivjuk hogy BELEPESI PONT



resource/static - kepek, videok stb mennek ide
resourse/template - thymeleaf stb dolgok
application.properties - milyen porton fusson es alabbi beallitasok

6. video 3:40 korul @SpringBootApplication - alabbiakat tartalmaza @Configurator, @EnableAutoConfiguration, @ComponentScan

@RestController - azt jelzi hogy ez kulonleges, olyan mint egy lada (betehetunk kivehetunk belole)

@RequestMapping("/") 

@Bean - 8. video 0:20 
@Scope megadom hogy session, prototype vagy singleton legyen

DEPENDENCY INJECTIONS

field injections - = new ....();
@Autowired kell

set injectionsnel a settere kell az autowired

counstructor injections 
construtorra kell az autowired!

@ComponentScan() - megadhatom hogy milyen mas packageket figyeljen es tegyen bele 
4:00