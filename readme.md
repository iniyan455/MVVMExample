Why  Design Patterns ?

Makes the code more understandable
Makes the code maintainable for long run
Makes the project loosely coupled
Makes the code testable
Making changes, adding new features are easy.

Architectural principles

Seperation of Concerns
Drive UI From Model

Why MVVM ?
MVC
MVP
MVVM

Every pattern having advantages and disadvantanges

MVVM pattern is set by google . We should use it. Common architecutal principals one of the model


API
|
|
|
Retrofit
|
|
|
View - Destroyed



Diagram



                                 Activity / Fragment
                                          |
                                          |
                                          |
                                          |
                                       View Model -> LiveData (LifeCycles changes)
                                          |
                                          |
                                          |
                                      Repository  ( Multiple dependent)
                                          |
                                          |
                                          |
                 --------------------------------------------------------------
                 |                                                            |
                 |                                                            |
                 |                                                            |
               Model                                                  Remote Data Source
                 Room                                                            Retrofit
                 |                                                             |
                 |                                                             |
                 SQLLite                                                     WebServices
