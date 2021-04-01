# Flow Lab
This lab focuses on interacting with Kotlin flows in various implementations. Several helper and setup functions already exist in UsefulFunctions.
Try working through the list of questions or just start writing code with Kotlin flow.

## Setup

Import the project into Intellij

## Unit Tests
There are a series of unit tests that need to be implemented or are broken

## Some questions to help you get started
- What is a flow
- What is a producer
- What is an intermediate operator? 
  - Test out different operators
- What is a terminal operator
    - What kinds of terminal operators are there, how do they differ?
- What happens if you  run a
  producer block in a different context than the collector? Why does this happen?
- How does flow relate with coroutines?
- What happens if you manually cancel a coroutine’s job while it’s operating a flow
- What does it mean for flows to be cold?
- What happens if a flow is collected again once it’s finished?
- Can you have a producer block and a collector operate at different speeds?
- What happens if the different operators have different execution times?
- How long would it take a flow to complete?
- During a flow when can you run a suspend function?
- What happens if there’s a runtime error on your flow?
    - Can you respond to the crash?
- One of the goals of coroutines and flow is to remove the need for nested callbacks. Can you replace `implementCallbackApi`  to return a flow? (Hint: There’s a specific builder for callbacks)
- Combining Flows
    - What happens if flows emit items at different speeds?
- ShareFlows
    - What makes this a hot flow?
    - What happens if this flow had multiple concurrent collectors?
    - What happens if the consumers on this flow are slower than the producer?
    -  What if our intermediate operators are slower than the producer?
- StateFlow
    - State flow is an implementation of ShareFlow. 
        -What are the implementation details?
    - When running kickstartStateFlow why are some values not collected?
    - How would you stop the service once you're done collecting? 
      - What happens if the service stops running while there are still subscribers?


