# Path of Champions Guide API

Path of Champions Guide API is a RESTful API designed to allow for voting on the effectiveness of equippable items for the playable champions in the "Path of Champions" game mode in Legends of Runeterra.

## Project Structure

src/ ├── main/ │ ├── java/app/ │ │ ├── config/ # Application and Hibernate configuration │ │ ├── controllers/ # REST controllers handling HTTP requests │ │ ├── entities/ # JPA/Hibernate entity classes │ │ ├── exceptions/ # Custom exception classes │ │ ├── middleware/ # Middleware for handling authentication, authorization, and request processing │ │ ├── persistence/ # Persistence layer handling database connections and queries │ │ │ ├── dao/ # Data Access Objects for database operations │ │ │ ├── dto/ # Data Transfer Objects for API requests/responses │ │ └── utils/ # Utility classes │ └── resources/ # Configuration files and resources └── test/ └── java/app/ # Test classes

markdown
Kopiér

## Getting Started

### Setup

1. Clone the repository
2. Create a `config.properties` file in `src/main/resources/` with the following content:

DB_NAME=pathofchampions DB_USERNAME=postgres DB_PASSWORD=your_password

markdown
Kopiér

3. Run `mvn clean install` to build the project

## Endpoints

### Public Endpoints

- `GET /hello`: Returns a simple "Hello World" message, used for testing if the API is running.
- `GET /champions`: Retrieves a list of all champions.
- `GET /champions/{id}`: Fetches a champion's information, along with the average rating for each item in combination with that champion.
- `GET /items`: Returns a list of all items.
- `POST /votes`: Submit a vote.
- `POST /admin/signup`: Enables an administrator to create a new account, using a unique secret key.
- `POST /admin/login`: Authenticates an administrator and returns a JWT for accessing protected endpoints.

### Protected Endpoints (Requires admin JWT)

- `POST /champions`: Create a new champion.
- `PUT /champions/{championId}`: Update champion info.
- `DELETE /champions/{championId}`: Delete a champion.
- `POST /items`: Create a new item.
- `PUT /items/{itemId}`: Update an item.
- `DELETE /items/{itemId}`: Delete an item.
