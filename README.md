# E-shop

---
<strong>

Filbert Aurelian Tjiaranata ~ 2306152336 ~ AdPro B

## Website URL
[E-shop](https://striped-myrilla-filbert-9053e3e5.koyeb.app/)

## Reflections
- [Reflection 1](##Reflection-1)
- [Reflection 2](##Reflection-2)
- [Reflection 3](##Reflection-3)
- [Reflection 4](##Reflection-4)
- [Reflection 5](##Reflection-5)

</strong>

# Tutorial 1
## Reflection 1
In my implementation of the edit and delete product features, I have made sure to follow clean code principles 
and secure coding practices that is being taught in class by Dr. Ade Azurat or the coding standards module given in SCELE.
I keep methods small (each method focused on a single task), I separated concerns by using distinct layers for the controller, 
service, and repository (following how the tutorial implemented the create product method), which makes the code easier to read, test, 
and maintain later on. I also made sure to have consistent naming conventions and clear method names help anyone reading the code understand its purpose quickly 
(I also used of comments, but only a few since the features are still quite easy to understand by just the code)

For secure coding practices, I focused on validating inputs and safely handling data. For example, using UUIDs for product IDs prevents 
predictable patterns that could be exploited, and null-safe comparisons in the repository methods help avoid RTE (at least that's what I've learnt in SDA).
I don't think there's much secure coding practices that I can implement at this moment as the code is still pretty much simple.

I admit that I have made a mistake in my commit process on the delete-product branch where I unintentionally duplicated several commits. 
Specifically, there are two commits labeled "Adding delete services", two commits labeled "Adding delete function to ProductRepository.java," and other 2 duplicated commits, 
even though those sets of commits are identical and contain no additional changes. This mistake has resulted in an inflated commit count, doubling the number of commits of the delete-branch 
that should have been recorded. 

## Reflection 2
### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
After enduring high level of stress with hair fall symptoms and significant mental _breakdance_ on writing and verifying all the unit tests, I felt really relieved and happy when I saw all 30 of my tests passed 😭.

I think there isn’t a fixed number of unit tests that should be written for a class since it really depends on the complexity (like how many CRUD functions it has) and the functionality of the code. 
I think other than relying on the rule of thumb or the preferred number of unit tests, we just gotta ensure that our unit tests adequately verify our program, we should aim to cover all logical paths, 
including typical use cases and corner cases, boundary conditions, and error scenarios.

Having 100% code coverage does not guarantee that our code is free of bugs or errors. Code coverage simply indicates that every line of code was executed during testing; it doesn’t ensure that every logical 
condition or edge case has been properly validated, leaving room for potential issues to be overlooked. So that's why I think although utilizing metrics like code coverage can help us to identify untested portions of our 
code, but the focus should be on the quality and relevance of the tests rather than merely achieving a high percentage of the code coverage.

### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
### What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

I think copying the same setup procedures and instance variables into a new test suite makes the code less clean because we're repeating the same things over and over, which after some research, this repetition violates the <a href='https://en.wikipedia.org/wiki/Don%27t_repeat_yourself'> DRY (Don't Repeat Yourself) </a>
principle, which means that if we need to make a change to the common setup or helper methods, we'll have to update it in multiple places, increasing the chance of mistakes. Hard-coded values like URLs, button names, and page 
titles are also a problem because if they ever change, we have to hunt them down in every single file 😵. A possible improvement which I suggest is to extract the common code into a base or helper class that all our test suites can use. This way, our code 
is easier to maintain and update, and it stays cleaner overall.

# Tutorial 2
## Reflection 3

### Code Quality Issues from SonarCloud that I fixed:

#### 1. Group dependencies by their destination

Previous Code
```Java
// build.gradle.kts

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
    testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}
```

Fixed Code
```Java
// build.gradle.kts

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
    testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
```

**As you can see, I grouped together testImplementation and testRuntimeOnly**

#### 2. Merge Pull Request Failed in SonarQube and was showing 0.0% Coverage on Code (even though already at 100% in local)

Previous Code
```java
// EshopApplicationTests

class EshopApplicationTests {
    @Test
    void mainMethodRuns() {
        EshopApplication.main(new String[] {});
    }
}
```

```java
// EshopApplicationTests

    class EshopApplicationTests {
        @Test
        void mainMethodRuns() {
            assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
        }
    }
```

**As you can see, I added assertDoesNotThrow to the corresponding test case**

#### 3. Use constructor injection instead of field injection (2x)

Previous Code
```Java
// ProductServiceImpl.java

@Autowired
private ProductRepository productRepository;

// ProductController.java

@Autowired
private ProductService service;
```

Fixed Code
```Java
// ProductServiceImpl.java

private final ProductRepository productRepository;
@Autowired
public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
}

// ProductController.java

private final ProductService service;
@Autowired
public ProductController(ProductService service) {
    this.service = service;
}
```
**As you can see, I changed the Autowired injection from field injection to an injection on the class constructor**

#### 4. Add a nested comment explaining why this method is empty

Previous Code
```Java
// EshopApplicationTests.java

@Test
void contextLoads() {
}

// ProductRepositoryTest.java

@BeforeEach
void setUp() {
}
```

Fixed Code
```Java
// EshopApplicationTests.java

@Test
void contextLoads() {
    // No implementation needed – verifies context startup
}

// ProductRepositoryTest.java

@BeforeEach
void setUp() {
    // This method is intentionally left empty.
    // If necessary, initialize common test dependencies here.
}
```
**As you can see, I put comments inside the method explaining why that method is empty**

#### 5. Remove the declaration of thrown exception 'java.lang.Exception'
**I removed "throws Exception" in several functions of the unit tests. Since they're quite a few, I choose not to show the before-and-after code**

#### 6. Define a constant instead of duplicating for 'redirect:/product/list'
**I replaced every `return "redirect:/product/list";` with `return REDIRECT_PRODUCT_LIST;`, a constant that I have previously made which holds on a value of the exact same thing**

#### 7. Remove the 'public' modifier on several Unit Tests

### CI/CD workflows Reflection

I think my current CI/CD implementation already meets the definition of Continuous Integration and Continuous
Deployment because it already **fully automates processes that ensure both code quality and reliable delivery**.

In terms of Continuous Integration, **the ci.yml file runs unit tests on every push or pull request**,
ensuring that each change is immediately verified against the codebase. **The scorecard.yml workflow 
checks the repository's supply chain security**, while **the sonarcloud.yml pipeline conducts static 
code analysis to detect bugs, vulnerabilities, and maintainability issues**.

On the Continuous Deployment side, **the pipeline is set up to automatically deploy updates to Koyeb
whenever the main branch is updated**, which streamlines the release process and minimizes the risk of
introducing errors. Moreover, **if any test fails during the integration phase, the deployment is
immediately halted**, preventing broken or insecure code from reaching production.

I think by applying them, where every commit triggers the same automated steps, it already
effectively eliminates human errors (my errors since I'm the dev) and inconsistencies, 
thereby achieving a stable and reliable delivery process.

# Tutorial 3
## Reflection 4

### I. Have I Implemented SOLID?

#### 1) SRP
Yes, I have implemented SRP.

The Single Responsibility Principle emphasizes that a class should have only one reason to change, meaning it should be responsible for just one aspect. In my project:

- Models (e.g., Product, Car) are dedicated to representing data.
- Repositories (e.g., ProductRepository, CarRepository) handle data persistence operations.
- Services (e.g., ProductService, CarService) focus exclusively on business logic.
- Controllers (e.g., ProductController, CarController) manage HTTP request handling.
- Utility Services (e.g., IdGeneratorService) serve a single, specific function.

For example, our ProductController only handles routing and request handling:
```Java
@Controller
@RequestMapping("/product")
public class ProductController extends AbstractItemController<Product> {

}
```

#### 2) OCP
Yes, I have implemented OCP.

The Open/Closed Principle asserts that software entities should allow extensions without requiring modifications to existing code. Our project applies this principle through:

- Abstract Base Classes, which enable extensions without altering existing implementations.
- Interfaces, which establish contracts that can be implemented in various ways.
- Inheritance Hierarchies, which facilitate adding new functionality through extension.

For example, in my `AbstractItemRepository` provides a base implementation that can be extended:
```java
public abstract class AbstractItemRepository<T extends Identifiable> implements ItemRepository<T> {
    @Override
    public abstract T update(T item);
}
```

#### 3) LSP
Yes, I have implemented LSP.

The Liskov Substitution Principle ensures that objects of a superclass can be substituted with objects of a subclass without compromising program correctness. In our project:

- Subclasses such as Product and Car inherit from AbstractItem while preserving its expected behavior.
- AbstractItemService and AbstractItemRepository establish contracts that are properly adhered to by their subclasses.
- The service layer operates seamlessly with any Item implementation without requiring awareness of its specific type.

For example, my inheritance hierarchy ensures that:
```java
public abstract class AbstractItemService<T extends Identifiable, R extends ItemRepository<T>> implements ItemService<T> {
    @Override
    public List<T> findAll() {
        Iterator<T> itemIterator = repository.findAll();
        List<T> allItems = new ArrayList<>();
        itemIterator.forEachRemaining(allItems::add);
        return allItems;
    }
}
```

#### 4) ISP
Yes, I have implemented ISP.

The Interface Segregation Principle emphasizes that clients should not be required to depend on interfaces they do not use. Our project applies this principle by:

- Small, specialized interfaces (e.g., Identifiable, Named, Quantifiable, Colorable) that focus on specific responsibilities.
- Composed interfaces that integrate smaller interfaces to meet particular requirements.
- Interface hierarchies that enable clients to depend only on the functionalities they need.

For example, instead of having one large Item interface with all possible methods, I've segregated them:

```java
public interface Identifiable {
    String getId();
    void setId(String id);
}

public interface Named {
    String getName();
    void setName(String name);
}

public interface Quantifiable {
    int getQuantity();
    void setQuantity(int quantity);
}

public interface Colorable {
    String getColor();
    void setColor(String color);
}
```

#### 5) DIP
Yes, I have implemented DIP.

The Dependency Inversion Principle asserts that high-level modules should not rely on low-level modules; instead, both should depend on abstractions. Our project adheres to this principle by:

- Utilizing dependency injection across the codebase.
- Employing configuration classes to manage component wiring.
- Defining interfaces to separate implementation details from their usage.

For example, services depend on repository interfaces, not concrete implementations:
```java
public abstract class AbstractItemService<T extends Identifiable, R extends ItemRepository<T>> implements ItemService<T> {
    @Autowired
    protected R repository; // Depends on the repository interface, not implementation
}
```
And our configuration classes handle the wiring:
```java
@Configuration
public class RepositoryConfig {
    @Bean
    public ItemRepository<Product> productRepositoryBean(IdGeneratorService idGeneratorService) {
        return new ProductRepository(idGeneratorService);
    }
}
```

### II. Advantages of Applying SOLID Principles
#### 1. Improved Maintainability
Applying the Single Responsibility Principle (SRP) makes the codebase more structured, understandable, and easier to maintain. Since each class has a well-defined purpose, debugging and making modifications become more efficient. **For example**, if there is a need to alter the way products are stored in the system, changes will be isolated to `ProductRepository` without affecting other parts of the application. This reduces complexity and minimizes unintended side effects.

The Open/Closed Principle (OCP) further enhances maintainability by allowing new functionalities to be added without modifying existing code. This reduces the likelihood of introducing bugs when making updates. **For example**, when incorporating a new item type, such as `Car` in this case, we don't have to modify the existing code. Instead of modifying the existing implementation, we simply extend it, ensuring the system remains stable and easier to manage.

#### 2. Greater Flexibility and Extensibility
The Liskov Substitution Principle (LSP) supports polymorphism by ensuring that subclasses can replace their parent classes without altering expected behavior. This enables seamless extensibility, as different Item implementations can be used interchangeably within the service layer without requiring modifications. **For example**, subclasses like `Product` and `Car` extend `AbstractItem` without breaking its behavior. `AbstractItemService` and `AbstractItemRepository` define contracts that are correctly implemented by their subclasses.

The Interface Segregation Principle (ISP) promotes modular and targeted interface design, preventing unnecessary dependencies on methods that are not relevant to certain classes. This allows for more precise interface implementations. **For example**, if only `Car` items require color-related attributes, the `Colorable` interface can be implemented exclusively for them without imposing unnecessary methods on unrelated classes. This ensures that each class only contains the behavior it actually needs, making the system more adaptable to future changes.

#### 3. Enhanced Testability
The Dependency Inversion Principle (DIP) plays a crucial role in improving testability by encouraging the use of dependency injection. Instead of directly depending on concrete implementations, high-level modules rely on abstractions, making it easier to substitute real dependencies with mock objects during testing. This facilitates unit testing and reduces the complexity of setting up test cases.

**For example**, in `ProductServiceImplTest`, we can mock the `ProductRepository` instead of relying on an actual database connection. This allows for isolated and efficient testing, ensuring that business logic is properly validated without being affected by external dependencies. As a result, the overall reliability of the codebase improves, and debugging becomes more manageable.

### III. Disadvantages of Not Applying SOLID Principles

#### 1. Code Duplication Issue
Failing to adhere to the Single Responsibility Principle (SRP) and Open/Closed Principle (OCP) can lead to redundant code across different controllers and services. Without a shared abstraction like `AbstractItemController`, each individual controller would have to reimplement common CRUD operations separately, resulting in unnecessary duplication and maintenance challenges.
Example:
```java
// Duplicate code in every controller without proper abstraction
@GetMapping("/list")
public String productListPage(Model model) {
    model.addAttribute("products", productService.findAll());
    return "productList";
}

@GetMapping("/list")
public String carListPage(Model model) {
    model.addAttribute("cars", carService.findAll());
    return "carList";
}
```

#### 2. Bloated Interfaces
Without ISP, clients would need to implement methods they don't use. **For example**, Without ISP - one large interface forcing unnecessary implementations. So, all items must implement these, even if not needed. Like color only relevant for cars and not for books, so do author only relevant to books not to car

#### 3. Tight Coupling
Without DIP, high-level modules would depend directly on low-level modules:
```java
// Tight coupling without DIP
public class ProductServiceImpl {
    // Direct dependency on concrete implementation instead of interface
    private ProductRepository repository = new ProductRepository();

    // Hard to test, hard to change implementations
}
```

#### 4. Rigidity
Without adhering to the Open/Closed Principle (OCP), introducing new features would require modifying existing code, as seen in ItemService, where adding a new item type like Book necessitates altering the method instead of simply extending the functionality.


# Tutorial 4
## Reflection 5

1. **Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.**
Throughout this learning module, I applied the principles of Test-Driven Development (TDD) and evaluated its impact on my development process. Using Percival’s self-reflective questions as a guide, I have assessed how well TDD influenced my testing approach and what improvements I can make moving forward.

**Effectiveness of TDD in Refactoring**
TDD provided a structured and reliable approach to modifying my code with confidence. Having a well-defined set of tests ensured that when I refactored sections of my implementation—such as adjusting the logic for handling status updates—the tests immediately identified any unintended changes. This helped me maintain stability in the code while making necessary enhancements.

**Approach to Writing Tests**
I generally followed the principle of writing tests before implementation, particularly when designing critical features. For instance, while working on the order processing functionality, I created test cases first to define expected behaviors before writing the actual implementation. This practice helped shape the logic and ensured that each component was built with testability in mind. However, there were instances where I found myself writing tests after the implementation, which is something I need to be more mindful of in future projects.

**Assessment of Test Quality**
While I believe my tests covered essential functionalities such as order creation, updates, and filtering, I recognize that there is room for improvement. Specifically, I need to enhance coverage for edge cases and unexpected input scenarios. Testing for error conditions, particularly around validation, could be more rigorous to ensure robustness.

**What would I do for Future Improvement?**

1️⃣ Develop a more disciplined approach to always writing failing tests before implementation.

2️⃣ Increase focus on testing boundary conditions and exceptional cases.

3️⃣ Consider incorporating acceptance tests early to guide development from a broader perspective.

4️⃣ Ensure that all critical validation and error-handling mechanisms are thoroughly tested.

In conclusion, TDD has been a valuable methodology that has improved the reliability and maintainability of my code.

2. **You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.**

I think my unit tests mostly already follow the F.I.R.S.T. principle, like

- Fast: The tests run quickly and don’t depend on external systems. Using mocks helps avoid slow database operations.

- Independent: Most tests don’t affect each other, but some repository tests might depend on the order they run. I need to reset the repository for each test.

- Repeatable: The tests always use the same data and don’t rely on things like network or database state, making them reliable.

- Self-validating: The test results are clear, but some error messages could be more detailed to make debugging easier.

- Timely: Most tests were written before the implementation (TDD), but some edge cases were added later. I should write these earlier.

But, I think there are also some things I can improve like maybe I can make sure each test is fully independent by resetting the repository, use better error messages so test failures are easier to understand, and write more edge case tests before coding, not after.