# Awesome Items
Native android application consisting of three screens.
1. Screen with search bar to look for some products in mercadolibre api.
2. Screen with the list of product results.
3. Screen with details of a selected product.

## Usage
In order to run the application in Android Studio, we need to choose the build variant.\
Use ***RemoteDebug*** to consume mercadolibre api with internet connection.\
Use ***LocalDebug*** to read a json file in **app->assets->mock.api->search_get.json**. You can manipulate the data inside that file in order to see diferent results in the app.

## Architecture
### Packages
We recognize domain flows for our app that become a package. Inside this domain we recognize differents flows that can have.\
*For Example:* \
*Mutual Funds is a Product ***Domain****\
*List mutual funds is a* ***flow*** **inside that domain**\
*Take a mutual fund is another ***flow*** **inside that domain***\
\
For this project we don't have much information, therefore we recognize only a Domain/Flow named products.

### Clean Architecture
Package strategy in a recognized flow is based on clean architecture principles.\
Using ***Data***, ***Presentation*** and ***Ui*** layer inside that flow.\
Domain layer is not considered necessary because we dont have a complex business logic to manage.\
\
Inside each layer we can see one class *(or more in case of have multiple viewmodels)* that represent the layer entry point. All other classes are hidden by its package, so we can inmediatly see each layer entry point when open its package.\
***Repository*** is Data layer entry point.\
***ViewModels*** are Presentation layer entry points.\
***Activity*** is Ui layer entry point.

### MVVM
We use Model-View-ViewModel pattern.\
We create a ViewModel for each screen-logic combo. Like screen/remote-call and/or screen/cache-logic.\
For this project, we only have one ViewModel in product list view.

### MVI
Model-View-Intent is not a very common pattern but its have a lot of cool benefits!.\
With this pattern we can manage an unidirectional reactive data flow and aditionally we totally decouple ui and presentation layer. That means that we can totally change presentation or ui layer without affecting each other!.\
\
We implement this pattern inside ***presentation layer***. In that layer we recognize each screen that have a remote/cache logic to use mvi on it.\
Then we observe app state changes and user intents in ***ui layer***.\
This pattern doesn't affect ***data layer***.\
\
**Mvi Architecture Diagram Concept**
![mvi](https://user-images.githubusercontent.com/35037350/170899300-898e278d-4aca-470f-a7ec-22f01fc533ab.png)

## Modules
In order to modularize our project, we create some gradle modules which are described below.

### network
Network module is intended for **remote** and **local** support.\
***Remote Support:*** Allow us to use internet connection to make api calls.\
***Local Support:*** Allow us to read mocks from json files locally to fake api responses.\
\
To be able to choose one of those supports, we use **Environments**, this environments are attached to the project current build variant name and managed by a network factory, to make it easy to use.

### ui-components
All ui components have cathegories like: ***component***, ***group component***, ***template*** or ***view***.\
\
All components are intended to have an ***attribute contract*** that are initialized with an unique public method named ***setAttributes*** (some components may have more public methods in special cases). This way of working with components allow us to generalize the way components are initialized in our application, make it more easy from developers to manage any component.


### mvi
Module that allow us to use ***mvi pattern*** in our project in a simple way.

### utils
Utils module manage some of shared logic and extensions, that allow us to compact and hide this type of logic for better code reading.

### testingtools
Testingtools module is intended to have a responsability similar to utils, but thinking only for testing porpuses.

## Tools
- Networking -> ***Retrofit2*** and ***OKHttp***
- Network Logger -> ***OKHttp-LoggingInterceptor***
- JsonConverter -> ***Moshi***
- Dependency Injector -> ***Hilt***
- Navigation -> ***Jetpack Navigation*** and ***safeArgs***
- Views -> ***ViewBinding*** and ***Xmls***
- Animations -> ***Lottie***
- Image render -> ***Picasso***
- Base Ui -> ***Material***
- Asynchrony -> ***Kotlin Coroutines*** and ***Flow***
- Parcelable generator -> ***Kotlin-parcelize***
- Unit Tests -> ***JUNIT5***
- Mocks -> ***Mockk***

## Possible improvements
- Linter (with Ktlint or Detekt)
- Create Hilt componets per flow
- Amount formatted based of country
- Use BigDecimal for amount (thinking in the future, for amount manipulation)
- Support Dark theme
- Split Fragment responsabilities in (Fragment lifecycle, RenderUi and userIntentHandler)
- Load more product pages with an infinite scrolling
