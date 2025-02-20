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

## Reflection 3