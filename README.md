
# Sample-graphql-kmp

Welcome to my Kotlin Multiplatform (KMP) project where I explore the use of GraphQL in combination with Apollo Kotlin for Android and iOS development.

## Objective

The main goal of this project is to explore the benefits of GraphQL compared to traditional REST APIs in mobile development. I aim to share my findings and experiences with the developer community.

https://github.com/josue-lubaki/sample-graphql-kmp/assets/70981701/af1daf8d-0556-440c-bfcf-435ac49f00ff

## Getting Started

To get started with this project and download the Apollo GraphQL schema, you can run the following Gradle command:

```bash
gradle downloadApolloSchema --endpoint='https://countries.trevorblades.com/graphql' --schema=shared/src/commonMain/graphql/ca/josuelubaki/countries/schema.graphqls
```

# Benefits of GraphQL over REST

GraphQL offers several advantages over traditional REST APIs :

- **Improved Handling of Nested Queries**: GraphQL simplifies the retrieval of complex, nested data structures, allowing you to fetch only the data you need. This reduces over-fetching and under-fetching of data.

- **Dynamic Queries with Directives**: GraphQL allows for dynamic queries using directives like `@include` and `@skip`. This flexibility enables clients to request exactly the data they require, reducing the need for multiple endpoints.

- **Strongly Typed**: GraphQL schemas are strongly typed, providing clear and predictable data structures. This type safety helps catch errors at compile-time rather than runtime.

- **Single Endpoint**: Unlike REST, GraphQL typically uses a single endpoint for all queries, reducing the number of network requests and simplifying API management.

- **Reduced Over-fetching**: Clients can request only the specific fields they need, eliminating the problem of over-fetching data common in REST.

- **Versioning is Optional**: GraphQL often does not require versioning of APIs, as clients can request the exact shape of data they need, reducing the need for maintaining multiple API versions.

- **Introspection**: GraphQL provides introspection capabilities, allowing clients to discover the schema and understand what data is available, making it easier to build powerful tools like documentation and query builders.

## Technologies Used

- **Apollo Kotlin**: I use the Apollo Kotlin library to generate Kotlin code from my GraphQL schema, which simplifies the integration of GraphQL into my KMP project.

- **Koin**: I use Koin for dependency injection into Kotlin Multiplatform (KMP) code.

- **Clean Architecture**: My project follows Clean Architecture principles, with a clear separation between data, domain, and presentation layers.

## Additional Resources

- [Article on GraphQL in Android by Vasim Mansuri](https://vasim-mansuri71.medium.com/graphql-in-android-basic-a26270ff67db): An excellent article I found informative for understanding GraphQL in Android.

## License
[MIT License](https://github.com/josue-lubaki/sample-graphql-kmp/blob/main/LICENSE)

```
Copyright (c) 2023 Josue Lubaki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
