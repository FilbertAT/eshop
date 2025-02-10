- [Reflection 1](##Reflection-1)
- [Reflection 2](##Reflection-2)

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

## Reflection 2
