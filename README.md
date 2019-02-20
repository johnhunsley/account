# account
An example of a hierarchical persistent domain model which can be exposed as a REST resource using profiles

[![CircleCI](https://circleci.com/gh/johnhunsley/account.svg?style=svg)](https://circleci.com/gh/johnhunsley/account)

This build may be used as both a standalone service or a dependency in other projects which use the domain model as in a client for the
running service. This is achieved through Spring Profiles and the 'service' profile should be active when running as a service, either with the following annotation -

```java
@ActiveProfiles("service")
```

Or as a java execution parameter

```
-Dspring.profiles.active=service
```