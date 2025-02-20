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

