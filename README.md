# Awesome Items

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
All components are intended to have a **attribute contract** that are initialized with an unique public method named ***setAttributes*** (some components may have more public methods in spaecial cases). This way of working with components allow us to generalize the way components are initialized in our application, make it more easy from developers to manage any component.


### mvi
Module that allow us to use ***mvi pattern*** in our project in a simple way.

### utils
Utils module manage some of shared logic and extensions, that allow us to compact and hide this type of logic for better code reading.

### testingtools
Testingtools module is intended to have a responsability similar to utils, but thinking only for testing porpuses.

## Possible improvements
- Linter (with Ktlint or Detekt)
- Create Hilt componets per flow
- Amount formatted based of country
- Use BigDecimal for amount (thinking in the future, for amount manipulation)
- Support Dark theme
- Split Fragment responsabilities in (Fragment lifecycle, RenderUi and userIntentHandler)
- Load more product pages with an infinite scrolling
